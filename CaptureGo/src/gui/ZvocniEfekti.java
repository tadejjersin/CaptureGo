package gui;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ZvocniEfekti {
	static String fileName;
	
	public ZvocniEfekti (String ime) {
		if (ime.equals("kamen")) {
			fileName = "kamen.wav";
		}
		else {
			if (ime.equals("poraz")) fileName = "poraz.wav";
			else fileName = "zmaga.wav";
		}
	}
	
	public static void predvajajZvok () throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		File url = new File(fileName);
	    Clip clip = AudioSystem.getClip();
	    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
	    clip.open(ais);
	    clip.start();
	}
}
