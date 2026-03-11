package ai;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;
import org.vosk.Model;
import org.vosk.Recognizer;

import ai.sound.SoundRecorder;
import ai.util.Util;

public class Test {

	public static void main(String[] args) {

		try {
			// callKokoroScript();
			// callKokoroToGenerateAudio(null);
			// transcribeAudioToText(false);
			captureAudio();
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

	// https://github.com/alphacep/vosk-api/blob/master/java/demo/src/main/java/org/vosk/demo/DecoderDemo.java
	private static void transcribeAudioToText(final boolean showPartialResult)
			throws IOException, UnsupportedAudioFileException {
		try (Model model = new Model(new Util().readProperty("vosk.model"));
				InputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(
						new FileInputStream(new Util().readProperty("wav.root.folder.path") + "/test.wav")));
				Recognizer recognizer = new Recognizer(model, 16000)) {

			int nbytes;
			byte[] b = new byte[4096];

			while ((nbytes = ais.read(b)) >= 0) {
				if (recognizer.acceptWaveForm(b, nbytes)) {
					if (showPartialResult)
						System.out.println(recognizer.getResult());
				} else {
					if (showPartialResult)
						System.out.println(recognizer.getPartialResult());
				}
			}

			System.out.println(recognizer.getFinalResult());
		}
	}

	private static void captureAudio() throws IOException {

		final SoundRecorder recorder = new SoundRecorder("test_recording.wav");
		JOptionPane.showMessageDialog(null, "Press OK to start");
		recorder.start();
		JOptionPane.showMessageDialog(null, "Press OK to stop");
		recorder.stop();

	}

}
