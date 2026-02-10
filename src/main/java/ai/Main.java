package ai;

import java.util.Scanner;

import org.apache.commons.lang3.Strings;

import ai.generate.Generate;

public class Main {

	public static void main(String[] args) {

		final Scanner scanner = new Scanner(System.in);
		String response = initialPrompt(scanner);

		while (true) {

			if (Strings.CI.equals(response, "generate")) {
				new Generate().chat(scanner);
				break;
			} else if (Strings.CI.equals(response, "chat")) {
				System.out.println("Not implemented.");
				break;
			} else if (Strings.CI.equals(response, "quit")) {
				System.out.println("Bye!");
				break;
			} else {
				System.out.println();
				System.out.println("Invalid option.");
				System.out.println();
				response = initialPrompt(scanner);
			}
		}

		scanner.close();
	}

	private static String initialPrompt(final Scanner scanner) {

		System.out.println("Which mode to use?");
		System.out.println("generate");
		System.out.println("chat");
		System.out.println("Or type quit to exit the program.");

		final String response = scanner.nextLine();
		return response;

	}

}
