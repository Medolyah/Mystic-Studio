import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import basic.classes.MysticStudioGame;

public class Main extends BasicGame {

	private static String gameName = "Mysitc Heroes of Melodies";

	private Input input;
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
		this.input = container.getInput();
		try {
			this.game = new MysticStudioGame();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		try {
			game.update(container, delta);
		} catch (SlickException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {

		game.render(container, g);

	}
}
