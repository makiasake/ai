package ai.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

public class Util {

	public String readProperty(final String property) throws IOException {

		Properties prop = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null)
				throw new IOException("config.properties not found!");

			prop.load(input);
			return prop.getProperty(property);
		} catch (IOException ex) {
			throw ex;
		}

	}

	public void runCommand(final List<String> command) throws IOException, InterruptedException {

		ProcessBuilder pb = new ProcessBuilder(command);
		pb.redirectErrorStream(true);
		Process process = pb.start();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		StringBuilder output = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			output.append(line).append("\n");
		}

		int exitCode = process.waitFor(); // Wait for the process to complete
		System.out.println("Python script exited with code: " + exitCode);
		System.out.println("Script Output:\n" + output.toString());
	}

}
