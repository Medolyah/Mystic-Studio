import org.newdawn.slick.*;
import basic.classes.MysticStudioGame;

public class Main extends BasicGame {

	private MysticStudioGame game;

	public Main() {
		super("Hallo World");
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Main());
		container.setDisplayMode(1920, 1080, true);
		container.start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		game = new MysticStudioGame(container);
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
