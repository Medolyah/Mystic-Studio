package player.classes;

import java.io.File;
import java.io.FileNotFoundException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import audio.classes.GameSound;
import basic.classes.GraphicObject;
import level.classes.InteractionObject;
import level.classes.Level;

public class Player extends GraphicObject {

	private int imageDeltaSum;
	private int deltaWalkSound;
	
	private int healCooldown;
	private int manaCooldown;
	
	private Image moveLeftImg1;
	private Image moveLeftImg2;
	private Image moveRightImg1;
	private Image moveRightImg2;	

	private Level environment;
	private int yVelocity;
	private int yAcceleration;
	private PlayerStats stats;
	
	public enum Movement {
		UP, RIGHT, LEFT, DOWN
	}

	public Player(Level environment, int xPos, int yPos, Shape hitbox, Image image) {
		super(xPos, yPos, hitbox, image);
		this.environment = environment;
		
		// set y-velocity and acceleration
		this.yAcceleration = -2;
		this.yVelocity = 0;
		
		// initialize delta sums
		this.imageDeltaSum = 0;
		this.healCooldown = 0;
		this.manaCooldown = 0;
		this.deltaWalkSound = 0;
		
		// load player images (movement animation)
		try {
			this.moveLeftImg1 = new Image("res/images/Knight-left-walk-1.png");
			this.moveLeftImg2 = new Image("res/images/Knight-left-walk-2.png");
			this.moveRightImg1 = new Image("res/images/Knight-right-walk-1.png");
			this.moveRightImg2 = new Image("res/images/Knight-right-walk-2.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws FileNotFoundException, SlickException {

		Input input = container.getInput();
		
		// sum delta sums
		imageDeltaSum += delta;
		deltaWalkSound += delta;
		
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
		
		// interaction
		if (input.isKeyPressed(Input.KEY_E)) {
			if (environment.interactionObjects != null) {
				for (InteractionObject object: environment.interactionObjects) {
					if (super.checkContact(object)) {
						object.interaction();
					}
				}				
			}
		}
	};

	private void platformerMovement(GameContainer container, int delta) throws FileNotFoundException, SlickException {

		Input input = container.getInput();

		// jump, only if the player is standing on the ground
		super.setyPos(super.getyPos() + 1);
		if (input.isKeyPressed(Input.KEY_SPACE) && checkEnvironment(Movement.DOWN)) {
			yVelocity = 38;
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

		// TODO move speed with shift for debugging / level building
		// remove in final version
		if (input.isKeyDown(Input.KEY_A)) {
			if (deltaWalkSound > 500) {
				deltaWalkSound = 0;
				GameSound cooldownSound = new GameSound("res/sounds/stepSound.wav");
				cooldownSound.playSound();	
			}
			if (input.isKeyDown(Input.KEY_LSHIFT)) {
				moveLeft(container, 100);
			} else {
				moveLeft(container, 5);				
			}
		}
		if (input.isKeyDown(Input.KEY_D)) {
			if (deltaWalkSound > 500) {
				deltaWalkSound = 0;
				GameSound cooldownSound = new GameSound("res/sounds/stepSound.wav");
				cooldownSound.playSound();	
			}
			if (input.isKeyDown(Input.KEY_LSHIFT)) {
				moveRight(container, 100);
			} else {
				moveRight(container, 5);				
			}
		}
		
		// life / energy / xp tests
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			setCurrentLife(-5);
		}
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			setCurrentEnergy(-20);
		}
		
		// life heal
		if (healCooldown >= 0) {
			healCooldown -= delta;
		}
		if (input.isKeyPressed(Input.KEY_Q)) {
			if (healCooldown <= 0) {
				setCurrentLife(25);
				healCooldown = 5000;
			} else {
				GameSound cooldownSound = new GameSound("res/sounds/cooldownSound.wav");
				cooldownSound.playSound();
			}
		}
		
		// mana heal
		if (manaCooldown >= 0) {
			manaCooldown -= delta;
		}
		if (input.isKeyPressed(Input.KEY_W)) {
			if (manaCooldown <= 0) {
				setCurrentEnergy(25);
				manaCooldown = 5000;
			} else {
				GameSound cooldownSound = new GameSound("res/sounds/cooldownSound.wav");
				cooldownSound.playSound();
			}
		}
	}

	private void birdsEyeViewMovement(GameContainer container) {

		Input input = container.getInput();

		// check arrow keys and move user
		// move user and check for collisions, undo the movement if necessary
		if (input.isKeyDown(Input.KEY_W)) {
			moveUp(container, 5);
		}
		if (input.isKeyDown(Input.KEY_S)) {
			moveDown(container, 5);
		}
		if (input.isKeyDown(Input.KEY_A)) {
			moveLeft(container, 5);
		}
		if (input.isKeyDown(Input.KEY_D)) {
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
		
		moveLeftAnimation();
		
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
		
		moveRightAnimation();

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
		int minBorderDistanceVertical = 250;
		int minBorderDistanceHorizontal = 750;

		if (movement == Movement.UP) {
			if (super.getyPos() < minBorderDistanceVertical) {
				// move screen up (all objects down)
				environment.moveObjects(Movement.DOWN);
				return true;
			}
		}

		if (movement == Movement.DOWN) {
			if (super.getyPos() > (screenHeight - minBorderDistanceVertical - hitbox.getHeight())) {
				// move screen down (all objects up)
				environment.moveObjects(Movement.UP);
				return true;
			}
		}

		if (movement == Movement.RIGHT) {
			if (super.getxPos() > (screenWidth - minBorderDistanceHorizontal - hitbox.getWidth())) {
				// move screen right (all objects left)
				environment.moveObjects(Movement.LEFT);
				return true;
			}
		}

		if (movement == Movement.LEFT) {
			if (super.getxPos() < minBorderDistanceHorizontal) {
				// move screen left (all objects right)
				environment.moveObjects(Movement.RIGHT);
				return true;
			}
		}

		return false;
	}
	
	private void moveLeftAnimation() {
		
		image = moveLeftImg1;
		
		if (imageDeltaSum > 300) {
			image = moveLeftImg2;
		}
		if (imageDeltaSum > 600) {
			imageDeltaSum = 0;
		}
		
	}
	
	private void moveRightAnimation() {
		
		image = moveRightImg1;
		
		if (imageDeltaSum > 300) {
			image = moveRightImg2;
			
		}
		if (imageDeltaSum > 600) {
			imageDeltaSum = 0;
		}
	}

	public void setPlayerStats(Player player, File saveFile) throws FileNotFoundException {
		stats = new PlayerStats(player, saveFile);
	}
	
	public int getPlayerLevel() {
		return stats.getPlayerLevel();
	}
	public int getMaxLife() {
		return stats.getMaxLife();
	}
	
	public int getCurrentLife() {
		return stats.getCurrentLife();
	}

	private void setCurrentLife(int lifeChange) {
		stats.setCurrentLife(lifeChange);
		
	}

	public int getMaxEnergy() {
		return stats.getMaxEnergy();
	}

	public int getCurrentEnergy() {
		return stats.getCurrentEnergy();
	}
	
	private void setCurrentEnergy(int energyChange) {
		stats.setCurrentEnergy(energyChange);		
	}
	public int getRequiredXP() {
		return stats.getRequiredXP();
	}

	public int getCurrentXP() {
		return stats.getCurrentXP();
	}
	
	private void setCurrentXP(int changeXP) {
		stats.setCurrentXP(changeXP);
	}

	public int getStrengh() {
		return stats.getStrengh();
	}

	public int getIntellegence() {
		return stats.getIntelligence();
	}

	public int getDexterity() {
		return stats.getDexterity();
	}

	public int getPhysArmpur() {
		return stats.getPhysicalArmour();
	}

	public int getMagicArmour() {
		return stats.getMagicalArmour();
	}

	public int getPhysDamage() {
		return stats.getPhysicalAttackDmg();
	}

	public int getMagicDamage() {
		return stats.getMagicalAttackDmg();
	}

	public int getAttackSpeed() {
		return stats.getAttackSpeed();
	}

	public void setEnvironment(Level environment) {
		this.environment = environment;
	}
}
