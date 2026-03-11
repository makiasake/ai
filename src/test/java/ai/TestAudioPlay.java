package ai;

import java.io.IOException;
import java.nio.file.Paths;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ai.sound.SoundPlayer;
import ai.util.Util;

public class TestAudioPlay {

	public static void main(String[] args) {

		try {
			new SoundPlayer().play(Paths.get(new Util().readProperty("wav.root.folder.path")  + "/test.wav").toFile());
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
