package ai;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;

import ai.util.Util;

public class TestMic {

	public static void main(String[] args) {

		try {
			File audioFile = new File(new Util().readProperty("wav.root.folder.path") + "/test.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

			Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
			Mixer selectedMixer = null;

			for (Mixer.Info mixerInfo : mixerInfos) {
				if (mixerInfo.getName().contains("CABLE Input (VB-Audio Virtual Cable)")) {
					selectedMixer = AudioSystem.getMixer(mixerInfo);
					break;
				}
			}

			if (selectedMixer != null) {
				SourceDataLine sourceLine = (SourceDataLine) selectedMixer.getLine(info);
				sourceLine.open(format);
				sourceLine.start();

				byte[] bufferBytes = new byte[4096];
				int readBytes = -1;
				while ((readBytes = audioStream.read(bufferBytes)) != -1) {
					sourceLine.write(bufferBytes, 0, readBytes);
				}

				sourceLine.drain();
				sourceLine.close();
				audioStream.close();
				System.out.println("Playback finished.");
			} else {
				System.err.println("Virtual cable not found.");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
