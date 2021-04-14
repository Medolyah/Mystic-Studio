package player.classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

import basic.classes.GraphicObject;
import level.classes.Level;

public class Player extends GraphicObject {

	private Level environment;
	private int yVelocity;
	private int yAcceleration;
	
	enum Movement {
		UP, RIGHT, LEFT, DOWN
	}

	public Player(Level environment, int xPos, int yPos, Shape hitbox, Image image) {
		super(xPos, yPos, hitbox, image);
		this.environment = environment;

		if (environment.levelType == Level.LevelType.PLATFORMER) {
			this.yAcceleration = -2;
			this.yVelocity = 0;
		}
	}

	@Override
	public void update(GameContainer container, int delta) {

		// movement
		switch (environment.levelType) {
		case BIRDS_EYE_VIEW:
			birdsEyeViewMovement(container);
			break;
		case PLATFORMER:
			platformerMovement(container, delta);
		default:
			break;
		}
	};

	private void platformerMovement(GameContainer container, int delta) {

		Input input = container.getInput();

		// jump, only if the player is standing on the ground
		super.setyPos(super.getyPos() + 1);
		if (input.isKeyDown(Input.KEY_UP) && checkEnvironment(Movement.DOWN)) {
			yVelocity = 30;
		}
		super.setyPos(super.getyPos() - 1);

		// increase/decrease y-velocity regarding y-acceleration
		yVelocity += yAcceleration;

		// perform movement
		if (yVelocity < 0) {
			moveDown(container, Math.abs(yVelocity));			
		} else {
			moveUp(container, Math.abs(yVelocity));
		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			moveLeft(container, 5);
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			moveRight(container, 5);
		}
	}

	private void birdsEyeViewMovement(GameContainer container) {

		Input input = container.getInput();

		// check arrow keys and move user
		// move user and check for collisions, undo the movement if necessary
		if (input.isKeyDown(Input.KEY_UP)) {
			moveUp(container, 5);
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			moveDown(container, 5);
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			moveLeft(container, 5);
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			moveRight(container, 5);
		}
	}

	private void moveUp(GameContainer container, int steps) {
		for (int i = 0; i < steps; i++) {
			super.setyPos(super.getyPos() - 1);
			if (checkEnvironment(Movement.UP)) {
				// undo movement
				super.setyPos(super.getyPos() + 1);
				break;
			} else {				
				if (checkBorderDistance(container, Movement.UP)) {
					// undo movement
					super.setyPos(super.getyPos() + 1);
				}
			}
		}
	}

	private void moveDown(GameContainer container, int steps) {
		for (int i = 0; i < steps; i++) {
			super.setyPos(super.getyPos() + 1);
			if (checkEnvironment(Movement.DOWN)) {
				// undo movement
				super.setyPos(super.getyPos() - 1);
				break;
			} else {
				if (checkBorderDistance(container, Movement.DOWN)) {
					// undo movement
					super.setyPos(super.getyPos() - 1);
				}				
			}
		}
	}

	private void moveLeft(GameContainer container, int steps) {
		for (int i = 0; i < steps; i++) {
			super.setxPos(super.getxPos() - 1);
			if (checkEnvironment(Movement.LEFT)) {
				// undo movement
				super.setxPos(super.getxPos() + 1);
				break;
			} else {				
				if (checkBorderDistance(container, Movement.LEFT)) {
					// undo movement
					super.setxPos(super.getxPos() + 1);
				}
			}
		}
	}

	private void moveRight(GameContainer container, int steps) {
		for (int i = 0; i < steps; i++) {
			super.setxPos(super.getxPos() + 1);
			if (checkEnvironment(Movement.RIGHT)) {
				// undo movement
				super.setxPos(super.getxPos() - 1);
				break;
			} else {				
				if (checkBorderDistance(container, Movement.RIGHT)) {
					// undo movement
					super.setxPos(super.getxPos() - 1);
				}
			}
		}
	}

	private boolean checkEnvironment(Movement movement) {

		// check for collisions with environment
		for (GraphicObject object : environment.textures) {
			if (super.checkContact(object)) {
				if (movement == Movement.DOWN || movement == Movement.UP) {
					yVelocity = 0;
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean checkBorderDistance(GameContainer container, Movement movement) {

		// check if the player is to close to the screen border so the screen has to be
		// moved
		int screenHeight = (int) container.getScreenHeight();
		int screenWidth = (int) container.getScreenWidth();
		int minBorderDistanceVertical = 200;
		int minBorderDistanceHorizontal = 300;

		if (movement == Movement.UP) {
			if (super.getyPos() < minBorderDistanceVertical) {
				// move screen up (all objects down)
				for (GraphicObject object : environment.textures) {
					object.setyPos(object.getyPos() + 1);
				}
				return true;
			}
		}

		if (movement == Movement.DOWN) {
			if (super.getyPos() > (screenHeight - minBorderDistanceVertical)) {
				// move screen down (all objects up)
				for (GraphicObject object : environment.textures) {
					object.setyPos(object.getyPos() - 1);
				}
				return true;
			}
		}

		if (movement == Movement.RIGHT) {
			if (super.getxPos() > (screenWidth - minBorderDistanceHorizontal)) {
				// move screen right (all objects left)
				for (GraphicObject object : environment.textures) {
					object.setxPos(object.getxPos() - 1);
				}
				return true;
			}
		}

		if (movement == Movement.LEFT) {
			if (super.getxPos() < minBorderDistanceHorizontal) {
				// move screen left (all objects right)
				for (GraphicObject object : environment.textures) {
					object.setxPos(object.getxPos() + 1);
				}
				return true;
			}
		}

		return false;
	}
}
