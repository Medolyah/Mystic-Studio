package basic.classes;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import audio.classes.GameMusic;
import level.classes.Level;
import menu.classes.Intro;
import menu.classes.MainMenu;
import player.classes.Player;
import player.classes.PlayerStats;
import ui.classes.Overlay;

public class MysticStudioGame {

	private Input input;
	private GameMusic bgMusic;
	private Player playerInstance;

	// components
	private Intro intro;
	private MainMenu mainMenu;
	private Level level;
	private Player player;
	private PlayerStats playerStats;
	private Overlay overlay;

	public MysticStudioGame(GameContainer container) throws SlickException, FileNotFoundException {

		intro = new Intro(this);
		input = container.getInput();
		bgMusic = new GameMusic("res/music/bgMusic.wav");

		// set frame rate 
		container.setMinimumLogicUpdateInterval(15);
        container.setMaximumLogicUpdateInterval(15);
        
        // create player instance
 		int xPos = 0;
 		int yPos = 0;
 		Shape hitbox = new Rectangle(xPos, yPos, 75, 220); 		
 		Image playerImage = null;
 		try {
 			playerImage = new Image("res/images/Knight-right-stay.png");
 		} catch (SlickException e) {
 			e.printStackTrace();
 		}
 		playerInstance = new Player(null, xPos, yPos, hitbox, playerImage);
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

		// overlay (UI)
		if (overlay != null) {
			overlay.update(container, delta);
		}
		
		// ESC: end game
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			if (level != null && player != null) {
				level = null;
				player = null;
				overlay = null;
				playerStats = null;
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
	}

	/*
	 * getters and setters
	 */
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level, int playerPosX, int playerPosY) {
		this.level = level;
		this.player = playerInstance;
		this.player.setxPos(playerPosX);
		this.player.setyPos(playerPosY);
		this.player.setEnvironment(level);
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
}
