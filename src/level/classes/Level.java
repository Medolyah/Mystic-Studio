package level.classes;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import basic.classes.GraphicObject;

public abstract class Level {

	public enum LevelType {
		BIRDS_EYE_VIEW,
		PLATFORMER
	}
	
	public ArrayList<GraphicObject> textures;
	public LevelType levelType;
	
	public abstract void update(GameContainer container, int delta);
	public abstract void render(GameContainer container, Graphics g);

}
