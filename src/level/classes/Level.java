package level.classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import basic.classes.GraphicObject;

public abstract class Level extends GraphicObject {

	public abstract void update(GameContainer container, int delta);
	public abstract void render(GameContainer container, Graphics g);

}
