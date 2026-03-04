package ai;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import ai.util.Util;

public class Test {

	public static void main(String[] args) {

		try {
			// callKokoroScript();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void callTestScript() throws IOException, InterruptedException {
		new Util().runCommand(Arrays.asList(StringUtils.split(new Util().readProperty("test.script.path"), " ")), true);
	}

	private static void callKokoroScript() throws IOException, InterruptedException {
		final String command = "py -m uv run --directory=" + new Util().readProperty("kokoro.path")
				+ " kokoro-tts --help-voices";
		new Util().runCommand(Arrays.asList(StringUtils.split(command, " ")), true);
	}

	private static void callKokoroToGenerateAudio(String voice) throws IOException, InterruptedException {

		if (StringUtils.isEmpty(voice))
			voice = "af_sarah";

		final String command = "py -m uv run -m --directory=" + new Util().readProperty("kokoro.path") + " kokoro_tts "
				+ new Util().readProperty("text.root.folder.path") + "/test-tts.txt "
				+ new Util().readProperty("wav.root.folder.path") + "/" + System.currentTimeMillis() + ".wav"
				+ " --speed 1.0 --lang en-us --voice " + voice + " --format wav";

		System.out.println("Running: " + command);

		new Util().runCommand(Arrays.asList(StringUtils.split(command, " ")), true);
	}

}
