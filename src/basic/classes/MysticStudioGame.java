package basic.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import menu.classes.Intro;
import menu.classes.MainMenu;

public class MysticStudioGame {

	private Intro intro;
	private boolean introFinished = false;
	private MainMenu mainMenu;
//	private Level level;
//	private Player player;

	private Music bgMusic;

	public MysticStudioGame() throws SlickException, FileNotFoundException {

		intro = new Intro();
		mainMenu = new MainMenu();
//		level = new TestLevel1();
//		player = new TestPlayer();
		// load game stats

		loadMusic();
	}

	public void update(GameContainer container, int delta) throws SlickException, IOException {
		if (!introFinished) {
			introFinished = intro.update(container, delta);
		} else {
			mainMenu.update(container, delta);
		}
//		level.update(container, delta);
//		player.update(container, delta);
	}

	public void render(GameContainer container, Graphics g) throws SlickException, FileNotFoundException {
		if (!introFinished) {
			intro.render(container, g);
		} else {
			mainMenu.render(container, g);
			bgMusic.setVolume((float) getVolume() / 100);
		}
//		level.render(container, g);
//		player.render(container, g);
	}

	private void loadMusic() throws SlickException, FileNotFoundException {

		int musicVolume = 0;
		musicVolume = getVolume();

		bgMusic = new Music("res/music/bgMusic.wav");
		Float musicVolumeFloat = (float) musicVolume / 100;
		bgMusic.loop();
		bgMusic.setVolume(musicVolumeFloat);

	}

	private int getVolume() throws FileNotFoundException {

		int musicVolume = 0;
		File settingsFile = new File("res/settings/settings.txt");

		try (Scanner scanner = new Scanner(settingsFile)) {
//			int musicVolume = 0;
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
		return musicVolume;
	}
}
