package basic.classes;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import level.classes.TestLevel1;
import level.classes.Level;
import player.classes.Player;
import player.classes.TestPlayer;

public class MysticStudioGame {
	
	private Level level;
	private Player player;
	
	public MysticStudioGame() {
		
		// load game stats
	}

	public void init(GameContainer container) {
		level = new TestLevel1();
		player = new TestPlayer();
	}

	public void update(GameContainer container, int delta) {
		level.update(container, delta);
		player.update(container, delta);
	}
	
	public void render(GameContainer container, Graphics g) {
		level.render(container, g);
		player.render(container, g);
	}
}
