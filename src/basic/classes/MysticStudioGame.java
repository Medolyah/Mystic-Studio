package basic.classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import level.classes.TestLevel1;
import level.classes.Level;
import player.classes.Player;
import player.classes.TestPlayer;

public class MysticStudioGame {
	
	private Input input;
	private Level level;
	private Player player;
	
	public MysticStudioGame(GameContainer container) {
		
		// load game stats
		input = container.getInput();
		level = new TestLevel1();
		player = new TestPlayer();
	}

	public void update(GameContainer container, int delta) {
		level.update(container, delta);
		player.update(container, delta);
		
		// ESC: end game
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			container.exit();
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		level.render(container, g);
		player.render(container, g);
	}
}
