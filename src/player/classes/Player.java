package player.classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Player {

	public abstract void update(GameContainer container, int delta);
	public abstract void render(GameContainer container, Graphics g);
}
