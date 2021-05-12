package basic.classes;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import audio.classes.GameMusic;
import level.classes.Level;
import menu.classes.Intro;
import menu.classes.MainMenu;
import menu.classes.WindowMenu;
import player.classes.Player;
import player.classes.PlayerStats;
import ui.classes.Overlay;

public class MysticStudioGame {

	private Input input;
	private GameMusic bgMusic;

	// components
	private Intro intro;
	private MainMenu mainMenu;
	private Level level;
	private Player player;
	private PlayerStats playerStats;
	private Overlay overlay;
	private WindowMenu windowMenu;

	public MysticStudioGame(GameContainer container) throws SlickException, FileNotFoundException {

		intro = new Intro(this);
		input = container.getInput();
		bgMusic = new GameMusic("res/music/bgMusic.wav");

		// set frame rate 
		container.setMinimumLogicUpdateInterval(15);
        container.setMaximumLogicUpdateInterval(15);
		
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
		
		// window (main) menu
		if (windowMenu != null) {
			windowMenu.update(container, delta);
		}

		// level
		if (level != null) {
			level.update(container, delta);
		}

		// player
		if (player != null) {
			player.update(container, delta);
		}

		// overlay (UI)
		if (overlay != null) {
			overlay.update(container, delta);
		}
		
		// ESC: end game
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			if (level != null && player != null) {
				windowMenu = new WindowMenu(this);
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
		}

		// level
		if (level != null) {
			level.render(container, g);
		}

		// player
		if (player != null) {
			player.render(container, g);
		}
		
		// overlay (UI)
		if (overlay != null) {
			overlay.render(container, g);
		}
		
		// window menu
		if (windowMenu != null) {
			windowMenu.render(container, g);
		}
	}

	/*
	 * getters and setters
	 */
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void setLevel(boolean unsetLevel) {
		if (unsetLevel) {
			this.level = null;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setPlayer(boolean unsetPlayer) {
		if (unsetPlayer) {
			this.player = null;
		}
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
	
	public GameMusic getMusic() {
		return this.bgMusic;
	}

	public void setOverlay(Overlay overlay) {
		this.overlay = overlay;		
	}

	public void setOverlay(boolean unsetOverlay) {
		if (unsetOverlay) {
			this.overlay = null;		
		}
	}

	public void setPlayerStats(PlayerStats playerStats) {
		this.playerStats = playerStats;		
	}

	public void setPlayerStats(boolean unsetPlayerStats) {
		if (unsetPlayerStats) {
			this.playerStats = null;		
		}
	}
	
	public void setWindowMenu(WindowMenu windowMenu) {
		this.windowMenu = windowMenu;
	}
	
	public void setWindowMenu(boolean unsetWindowMenu) throws FileNotFoundException, SlickException {
		if (unsetWindowMenu) {
			if (windowMenu.getExit()) {
				mainMenu = new MainMenu(this);
			}
			this.windowMenu = null;
		}
	}

}
