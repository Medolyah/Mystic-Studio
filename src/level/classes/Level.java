package level.classes;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import basic.classes.GraphicObject;

public abstract class Level {

	public ArrayList<GraphicObject> textures;
	public abstract void update(GameContainer container, int delta);
	public abstract void render(GameContainer container, Graphics g);

}
