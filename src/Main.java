import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import basic.classes.MysticStudioGame;

public class Main extends BasicGame {

	private MysticStudioGame game;

	public Main() {
		super("Hallo World");
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Main());
		container.setDisplayMode(1780, 1000, false);
		container.start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		game = new MysticStudioGame();
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		game.update(container, delta);
	}
	
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		game.render(container, g);	
	}
}
