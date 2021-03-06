package basic.classes;

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
	
	public MysticStudioGame() throws SlickException {

		intro = new Intro();
		mainMenu = new MainMenu();
//		level = new TestLevel1();
//		player = new TestPlayer();
		// load game stats
		
		bgMusic = new Music("res/music/bgMusic.wav");
		bgMusic.loop();
		bgMusic.setVolume(0.2f);
	}

	public void update(GameContainer container, int delta) throws SlickException {
		if (!introFinished) {
			introFinished = intro.update(container, delta);
		} else {
			mainMenu.update(container, delta);
		}
//		level.update(container, delta);
//		player.update(container, delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (!introFinished) {
			intro.render(container, g);	
		} else {
			mainMenu.render(container, g);
		}
//		level.render(container, g);
//		player.render(container, g);
	}
}
