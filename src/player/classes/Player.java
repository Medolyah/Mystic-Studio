package player.classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

import basic.classes.GraphicObject;
import level.classes.Level;

public class Player extends GraphicObject {

	private Level environment;
	
	enum Movement {
		UP,
		RIGHT,
		LEFT,
		DOWN
	}
	
	public Player(Level environment, int xPos, int yPos, Shape hitbox, Image image) {
		super(xPos, yPos, hitbox, image);
		this.environment = environment;	
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		Input input = container.getInput();
		
		// movement
		// check arrow keys and move user
		// move user and check for collisions, undo the movement if necessary
		if (input.isKeyDown(Input.KEY_UP)) {
			super.setyPos(super.getyPos() - 1);
			if (checkEnvironmentAndUndo(container, Movement.UP)) {
				// undo movement
				super.setyPos(super.getyPos() + 1);
			}
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			super.setyPos(super.getyPos() + 1);
			if (checkEnvironmentAndUndo(container, Movement.DOWN)) {
				// undo movement
				super.setyPos(super.getyPos() - 1);
			}
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			super.setxPos(super.getxPos() - 1);
			if (checkEnvironmentAndUndo(container, Movement.LEFT)) {
				// undo movement
				super.setxPos(super.getxPos() + 1);
			}
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			super.setxPos(super.getxPos() + 1);
			if (checkEnvironmentAndUndo(container, Movement.RIGHT)) {
				// undo movement
				super.setxPos(super.getxPos() - 1);
			}
		}
	};
	
	private boolean checkEnvironmentAndUndo(GameContainer container, Movement movement) {
		
		// check for collisions with environment
		for (GraphicObject object: environment.textures) {
			if (super.checkContact(object)) {
				return true;
			}
		}
				
		// check if the player is to close to the screen border so the screen has to be moved
		int screenHeight = (int) container.getScreenHeight();
		int screenWidth = (int) container.getScreenWidth();
		int minBorderDistance = 600;
		if (movement == Movement.UP) {
			if (super.getyPos() < minBorderDistance) {
				// move screen up (all objects down)
				for (GraphicObject object: environment.textures) {
					object.setyPos(object.getyPos() + 1);
				}
				return true;
			}
		}
		
		if (movement == Movement.DOWN) {
			if (super.getyPos() > (screenHeight - minBorderDistance)) {
				// move screen down (all objects up)
				for (GraphicObject object: environment.textures) {
					object.setyPos(object.getyPos() - 1);
				}
				return true;
			}
		}
		
		if (movement == Movement.RIGHT) {
			if (super.getxPos() > (screenWidth - minBorderDistance)) {
				// move screen right (all objects left)
				for (GraphicObject object: environment.textures) {
					object.setxPos(object.getxPos() - 1);
				}
				return true;
			}
		}
		
		if (movement == Movement.LEFT) {
			if (super.getxPos() < minBorderDistance) {
				// move screen left (all objects right)
				for (GraphicObject object: environment.textures) {
					object.setxPos(object.getxPos() + 1);
				}
				return true;
			}
		}
		
		return false;
	}
}
