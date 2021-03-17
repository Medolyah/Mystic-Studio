package basic.classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import level.classes.TestLevel1;
import level.classes.Level;
import player.classes.Player;
import player.classes.TestPlayer;
import menu.classes.Intro;
import menu.classes.MainMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MysticStudioGame {
	
	private Input input;
	private Music bgMusic;
	
	// components
	private Intro intro;
	private MainMenu mainMenu;
	private Level level;
	private Player player;


	public MysticStudioGame(GameContainer container) throws SlickException, FileNotFoundException {

		intro = new Intro(this);
		input = container.getInput();
		loadMusic();
	}
	
	public void update(GameContainer container, int delta) throws SlickException, IOException {
		
		// intro
		if (intro != null) {
			intro.update(container, delta);
		}
		
		// main menu
		if (mainMenu != null) {			
			mainMenu.update(container, delta);
		}

		// level 
		if (level != null) {			
			level.update(container, delta);
		}
		
		// player
		if (player != null) {
			player.update(container, delta);			
		}
		
		// ESC: end game
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			if (level != null && player != null) {
				level = null;
				player = null;
				mainMenu = new MainMenu(this);
			}
		}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException, FileNotFoundException {
		
		// intro
		if (intro != null) {
			intro.render(container, g);			
		}
			
		// main menu
		if (mainMenu != null) {
			mainMenu.render(container, g);
			bgMusic.setVolume((float) getVolume() / 100);				
		}

		// level
		if (level != null) {
			level.render(container, g);			
		}
		
		// player
		if (player != null) {
			player.render(container, g);			
		}
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
		return musicVolume;
	}

	/*
	 getters and setters
	*/
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}
	
	public void setMainMenu(boolean unsetMenu) {
		if (unsetMenu) {
			this.mainMenu = null;
		}
	}

	public Intro getIntro() {
		return intro;
	}

	public void setIntro(Intro intro) {
		this.intro = intro;
	}
	
	public void setIntro(boolean unsetIntro) {
		if (unsetIntro) {
			this.intro = null;
		}
	}
}
