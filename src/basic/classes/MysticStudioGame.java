package basic.classes;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import level.classes.TestLevel1;
import menu.classes.Intro;
import level.classes.Level;
import player.classes.Player;
import player.classes.TestPlayer;

public class MysticStudioGame {

	private Intro intro;
	private boolean introFinished = false;
	private boolean menuCreated = false;
//	private Level level;
//	private Player player;
	
	public MysticStudioGame() throws SlickException {

		intro = new Intro();
//		level = new TestLevel1();
//		player = new TestPlayer();
		// load game stats
	}

	public void update(GameContainer container, int delta) throws SlickException {
		if (!introFinished) {
			introFinished = intro.update(container, delta);
		} else if (!menuCreated) {
			menuCreated = true;
			System.out.println("create main menu");
		}
//		level.update(container, delta);
//		player.update(container, delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		intro.render(container, g);
//		level.render(container, g);
//		player.render(container, g);
	}
}
