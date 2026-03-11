package ai.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import ai.util.Util;

public class SoundRecorder {

	final File audioFile;
	final AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	TargetDataLine line;

	public SoundRecorder(final String fileName) throws IOException {
		this.audioFile = new File(new Util().readProperty("wav.root.folder.path") + "/" + fileName);
	}

	public void start() {
		try {
			final AudioFormat format = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

			if (!AudioSystem.isLineSupported(info)) {
				System.out.println("Line not supported");
				return;
			}

			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start();

			new Thread(() -> {
				try (AudioInputStream ais = new AudioInputStream(line)) {
					AudioSystem.write(ais, fileType, this.audioFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();

		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		}
	}

	public void stop() {
		if (line != null) {
			line.stop();
			line.close();
		}
	}

	private AudioFormat getAudioFormat() {
		return new AudioFormat(44100.0f, 16, 2, true, false);
	}

}
