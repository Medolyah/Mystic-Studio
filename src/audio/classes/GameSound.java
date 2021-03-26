package audio.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class GameSound {
	
	private Sound sound;
	private float soundVolume;
	
	public GameSound(String musicFilePath) throws FileNotFoundException, SlickException {
		loadSound(musicFilePath);
	}

	private void loadSound(String musicFilePath) throws SlickException, FileNotFoundException {
		sound = new Sound(musicFilePath);
		getVolume();
	}

	private void getVolume() throws FileNotFoundException {
		File settingsFile = new File("res/settings/settings.txt");
		try (Scanner scanner = new Scanner(settingsFile)) {
			scanner.next();
			scanner.next();
			scanner.next();
			int soundVolumeRead = scanner.nextInt();
			soundVolume = (float) soundVolumeRead / 100;
		}
	}
	
	public void playSound() throws FileNotFoundException {
		getVolume();
		sound.play(1, soundVolume);
	}
	
}
