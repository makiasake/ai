package ai.generate;

import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.lang3.Strings;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import ai.AiRequest;
import ai.AiResponse;

public class Generate {

	private static String URL = "http://localhost:11434/api/generate";

	public void chat(final Scanner scanner) {

		System.out.println("Type bye to exit the chat.");

		while (true) {

			System.out.println("User: ");
			final String prompt = scanner.nextLine();

			if (Strings.CI.equals(prompt, "bye"))
				break;

			try {
				final AiResponse response = new Generate().send(new AiRequest(prompt, false));
				System.out.println(response.getResponse());

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

}
