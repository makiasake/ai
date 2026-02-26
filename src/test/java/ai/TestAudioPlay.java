package ai;

import java.io.IOException;
import java.nio.file.Paths;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ai.sound.SoundPlayer;

public class TestAudioPlay {

	public static void main(String[] args) {

		try {
			new SoundPlayer().play(Paths.get("").toFile());
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
