package player.classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import basic.classes.GraphicObject;

public abstract class Player {

	GraphicObject graphicObject;
	
	public void update(GameContainer container, int delta) {
		Input input = container.getInput();
		
		// movement
		// check arrow keys and move user
		if (input.isKeyDown(Input.KEY_UP) == true) {
			graphicObject.setY(graphicObject.getY() - 1);
		}
		if (input.isKeyDown(Input.KEY_DOWN) == true) {
			graphicObject.setY(graphicObject.getY() + 1);
		}
		if (input.isKeyDown(Input.KEY_LEFT) == true) {
			graphicObject.setX(graphicObject.getX() - 1);
		}
		if (input.isKeyDown(Input.KEY_RIGHT) == true) {
			graphicObject.setX(graphicObject.getX() + 1);
		}
	};
	
	public abstract void render(GameContainer container, Graphics g);
}
