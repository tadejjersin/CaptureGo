package gui;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ZvocniEfekti {
	
	public static void zvokKamen () throws Exception {
		File url = new File("kamen.wav");
	    Clip clip = AudioSystem.getClip();
	    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
	    clip.open(ais);
	    clip.start();
	}
	
	public static void zvokZmaga () throws Exception {
		File url = new File("kamen.wav");
	    Clip clip = AudioSystem.getClip();
	    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
	    clip.open(ais);
	    clip.start();
	}
}
