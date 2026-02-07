package ai;

import ai.generate.Generate;

public class Main {

	public static void main(String[] args) {

		AiRequest request = new AiRequest("Hello", false);

		try {
			final AiResponse response = new Generate().generate(request);
			System.out.println(response.getResponse());

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

}
