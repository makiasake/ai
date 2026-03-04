package ai.chat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import ai.dto.AiRequest;
import ai.dto.AiResponse;
import ai.dto.Message;
import ai.sound.SoundPlayer;
import ai.util.Util;

public class ChatWithAudio {

	private static String URL = "http://localhost:11434/api/chat";
	private List<Message> messages = new ArrayList<Message>();

	public void chat(final Scanner scanner) {

		addSystemPrompt();
		System.out.println("Type bye to exit the chat.");

		while (true) {

			System.out.println("User: ");
			final String prompt = scanner.nextLine();

			if (Strings.CI.equals(prompt, "bye"))
				break;

			messages.add(new Message("user", prompt));

			try {
				final AiResponse response = send(new AiRequest(null, false, messages));
				messages.add(new Message(response.getMessage().getRole(), response.getMessage().getContent()));

				final String responseContent = response.getMessage().getContent();
				System.out.println(responseContent);

				final String pathTotextResponse = generateTextFileFromResponse(responseContent);
				final File audioFile = generateAudio(pathTotextResponse);

				new SoundPlayer().play(audioFile);

				new Util().deleteFiles(Arrays.asList(audioFile, Paths.get(pathTotextResponse).toFile()));

			} catch (IOException ioex) {
				System.out.println("IO error: " + ioex.getMessage());
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
				ex.printStackTrace();
			}
		}

		scanner.close();

	}

	private AiResponse send(final AiRequest request) throws ClientProtocolException, IOException {

		final HttpResponse httpResponse = Request.Post(URL).body(new StringEntity(new Gson().toJson(request))).execute()
				.returnResponse();

		final String json = EntityUtils.toString(httpResponse.getEntity());
		return new Gson().fromJson(json, AiResponse.class);
	}

	private String generateTextFileFromResponse(final String response) throws IOException {

		final String path = new Util().readProperty("text.root.folder.path") + "/" + System.currentTimeMillis()
				+ ".txt";

		final Path file = Paths.get(path);
		Files.writeString(file, response);

		return path;
	}

	private File generateAudio(final String pathToTextFile) throws IOException, InterruptedException {

		final String pathToAudioFile = new Util().readProperty("wav.root.folder.path") + "/"
				+ FilenameUtils.getBaseName(pathToTextFile) + ".wav";

		final String command = "py -m uv run -m --directory=" + new Util().readProperty("kokoro.path") + " kokoro_tts "
				+ pathToTextFile + " " + pathToAudioFile + " --speed 1.0 --lang en-us --voice af_sarah --format wav";

		new Util().runCommand(Arrays.asList(StringUtils.split(command, " ")), false);

		return Paths.get(pathToAudioFile).toFile();
	}

	private void addSystemPrompt() {

		try {
			final Path systemPromptFile = Paths.get(new Util().readProperty("system.prompt.path"));
			final String systemPrompt = Files.readString(systemPromptFile);

			messages.add(new Message("system", systemPrompt));
		} catch (IOException ex) {
			System.out.println("Error adding system prompt at start of conversation, error: " + ex.getMessage());
		}

	}

}
