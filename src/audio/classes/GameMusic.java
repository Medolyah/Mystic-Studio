package audio.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class GameMusic {
	
	private Music music;
	private int musicVolume;
	
	public GameMusic(String musicFilePath) throws FileNotFoundException, SlickException {
		loadMusic(musicFilePath);
	}

	private void loadMusic(String musicFilePath) throws SlickException, FileNotFoundException {

		music = new Music(musicFilePath);
		getVolume();

		Float musicVolumeFloat = (float) musicVolume / 100;
		music.loop();
		music.setVolume(musicVolumeFloat);

	}
	
	public int getVolume() throws FileNotFoundException {

		musicVolume = 0;
		File settingsFile = new File("res/settings/settings.txt");

		try (Scanner scanner = new Scanner(settingsFile)) {
			// int musicVolume = 0;
			int lineNumber = 0;
			while (scanner.hasNext()) {
				scanner.next();
				int settingValue = scanner.nextInt();
				switch (lineNumber) {
				case 0: {
					musicVolume = settingValue;
					lineNumber++;
				}
				default:
					lineNumber++;
				}
			}
		}
		return this.musicVolume;
	}
	
	public void setVolume(float volume) throws FileNotFoundException {
		music.setVolume(volume);
	}

}
