package player.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import audio.classes.GameSound;
import basic.classes.GraphicObject;
import basic.classes.MysticStudioGame;
import level.classes.InteractionObject;
import level.classes.Level;
import level.classes.Level.LevelType;

public class Player extends GraphicObject {

	MysticStudioGame game;
	
	private int imageDeltaSum;
//	private int deltaWalkSound;
	private int deltaAttack;
	boolean deltaSwordImage = false;
	boolean rotated = false;
	int deltaAttackDelay = 0;

	private int healCooldown;
	private int manaCooldown;

	private Image moveLeftImg1;
	private Image moveLeftImg2;
	private Image moveRightImg1;
	private Image moveRightImg2;
	
	private Image swordImage;

	private Level environment;
	private int yVelocity;
	private int yAcceleration;
	private PlayerStats stats;

	public enum Movement {
		UP, RIGHT, LEFT, DOWN
	}

	public Player(Level environment, int xPos, int yPos, Shape hitbox, Image image, MysticStudioGame game) {
		super(xPos, yPos, hitbox, image);
		this.environment = environment;

		// set y-velocity and acceleration
		this.yAcceleration = -2;
		this.yVelocity = 0;

		// initialize delta sums
		this.imageDeltaSum = 0;
		this.healCooldown = 0;
		this.manaCooldown = 0;
//		this.deltaWalkSound = 0;

		// load player images (movement animation)
		try {
			this.moveLeftImg1 = new Image("res/images/Knight-left-walk-1.png");
			this.moveLeftImg2 = new Image("res/images/Knight-left-walk-2.png");
			this.moveRightImg1 = new Image("res/images/Knight-right-walk-1.png");
			this.moveRightImg2 = new Image("res/images/Knight-right-walk-2.png");
			this.swordImage = new Image("res/images/Wood-Sword.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.game = game;
	}

	@Override
	public void update(GameContainer container, int delta) throws FileNotFoundException, SlickException {

		Input input = container.getInput();

		// sum delta sums
		imageDeltaSum += delta;
//		deltaWalkSound += delta;
		deltaAttack += delta;

		// movement
		switch (environment.getLevelType()) {
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
			if (environment.getInteractionObjects() != null) {
				for (InteractionObject object : environment.getInteractionObjects()) {
					if (super.checkContact(object)) {
						object.executeInteraction();
					}
				}
			}
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics g) {
		g.drawImage(image, xPos, yPos);
		Color transparent;
		// color for debugging / hitbox visualization during development
		if (container.getInput().isKeyDown(Input.KEY_NUMPAD5)) {
			transparent = new Color(0.2f, 0.5f, 0.5f, 0.4f);
		} else {
			transparent = new Color(0.2f, 0.5f, 0.5f, 0.0f);	
		}
		g.setColor(transparent);
		
		g.fill(hitbox);
		if (deltaAttackDelay > 0 && deltaAttackDelay < 500) {
			g.drawImage(swordImage, xPos, yPos + 75);
		}
	}

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
//			if (deltaWalkSound > 500) {
//				deltaWalkSound = 0;
//				GameSound stepSound = new GameSound("res/sounds/stepSound.wav");
//				stepSound.playSound();
//			}
			if (input.isKeyDown(Input.KEY_LSHIFT)) {
				moveLeft(container, 100);
			} else {
				moveLeft(container, 5);
			}
		}
		if (input.isKeyDown(Input.KEY_D)) {
//			if (deltaWalkSound > 500) {
//				deltaWalkSound = 0;
//				GameSound stepSound = new GameSound("res/sounds/stepSound.wav");
//				stepSound.playSound();
//			}
			if (input.isKeyDown(Input.KEY_LSHIFT)) {
				moveRight(container, 100);
			} else {
				moveRight(container, 5);
			}
		}

		// attack (animation + hit check)
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			deltaSwordImage = true;
		}
		if (deltaSwordImage && deltaAttack > 1000 / getAttackSpeed()) {
			if (deltaAttackDelay == 0) {
				game.getLevel().hitEnemy(hitbox, this.getPhysDamage() + this.getMagicDamage());
				GameSound attackSound = new GameSound("res/sounds/attack.wav");
				attackSound.playSound();
			}
			if (deltaAttackDelay < 250) {
				deltaAttackDelay += delta;
			} else if (deltaAttackDelay < 500) {
				deltaAttackDelay += delta;
				if (!rotated) {
					swordImage.rotate(35);
					rotated = true;
				}
			} else {
				if (rotated) {
					swordImage.rotate(-35);
					rotated = false;
				}
				deltaAttackDelay = 0;
				deltaSwordImage = false;
				deltaAttack = 0;
			}
		}

		// life heal
		if (healCooldown >= 0) {
			healCooldown -= delta;
		}
		if (input.isKeyPressed(Input.KEY_Q)) {
			if (healCooldown <= 0) {
				setCurrentLife(25);
				healCooldown = 5000;
				GameSound potionSound = new GameSound("res/sounds/potion.wav");
				potionSound.playSound();
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
				GameSound potionSound = new GameSound("res/sounds/potion.wav");
				potionSound.playSound();
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
		for (GraphicObject object : environment.getTextures()) {
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
		
		// only for platformer mode
		if (environment.getLevelType() != LevelType.PLATFORMER) {
			return false;
		}

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

	public void setPlayerStats(Player player, File saveFile) {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(saveFile));
			stats = (PlayerStats) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int getGameProgress() {
		return stats.getGameProgress();
	}
	
	public void setGameProgress(int progress) {
		stats.setGameProgress(progress);
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

	public void setCurrentLife(int lifeChange) throws FileNotFoundException, SlickException {
		stats.setCurrentLife(lifeChange);
		if (lifeChange < 0) {
			GameSound attackSound = new GameSound("res/sounds/playerDamage.wav");
			attackSound.playSound();
		}

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

	public void setCurrentXP(int changeXP) {
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
	
	public String getCharacterClass() {
		return stats.getCharacterClass();
	}
	
	public PlayerStats getPlayerStats() {
		return stats;
	}

	public int getFreeStatPoints() {
		return stats.getFreeStatPoints();
	}

	public int getFreeSkillPoints() {
		return stats.getFreeSkillPoints();
	}

	public void setStrengh() {
		stats.setStrengh();		
	}

	public void setIntellegence() {
		stats.setIntelligence();		
	}

	public void setDexterity() {
		stats.setDexterity();		
	}

	public int getCurrenGold() {
		return stats.getCurrentGold();
	}
}
