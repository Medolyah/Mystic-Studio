import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.*;

import basic.classes.MysticStudioGame;

public class Main extends BasicGame {

	private static String gameName = "Mysitc Heroes of Melodies";

	private MysticStudioGame game;

	public Main() {
		super(gameName);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Main());
		container.setDisplayMode(1920, 1080, true);
		container.start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		try {
			this.game = new MysticStudioGame(container);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		try {
			game.update(container, delta);
		} catch (SlickException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {

		try {
			game.render(container, g);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}
