package ai.generate;

import java.io.IOException;

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

	public AiResponse generate(final AiRequest request) throws ClientProtocolException, IOException {

		final HttpResponse httpResponse = Request.Post(URL).body(new StringEntity(new Gson().toJson(request))).execute()
				.returnResponse();

		final String json = EntityUtils.toString(httpResponse.getEntity());
		return new Gson().fromJson(json, AiResponse.class);

	}

}
