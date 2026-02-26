package ai.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

public class Chat {

	private static String URL = "http://localhost:11434/api/chat";
	private List<Message> messages = new ArrayList<Message>();

	public void chat(final Scanner scanner) {

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
				System.out.println(response.getMessage().getContent());

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
				ex.printStackTrace();
			}
		}

		scanner.close();

	}

	public AiResponse send(final AiRequest request) throws ClientProtocolException, IOException {

		final HttpResponse httpResponse = Request.Post(URL).body(new StringEntity(new Gson().toJson(request))).execute()
				.returnResponse();

		final String json = EntityUtils.toString(httpResponse.getEntity());
		return new Gson().fromJson(json, AiResponse.class);

	}
}
