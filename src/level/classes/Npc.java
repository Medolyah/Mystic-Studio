package level.classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import basic.classes.GraphicObject;
import basic.classes.MysticStudioGame;

public class Npc extends GraphicObject {

	private MysticStudioGame game;
	private int xMinPos;
	private int xMaxPos;
	private int xVelocity;

	private int currentHP;
	private int maxHP;
	private Circle aggroBox;
	
	// type 0 = object, type 1 = normal enemy, type 2 = boss
	private int type;
	
	private int deltaEnemyHit;

	public Npc(MysticStudioGame game, int xPos, int yPos, Shape hitbox, Image image, int xMinPos, int xMaxPos,
			int xVelocity, int maxHP, int aggroRange, int type) {
		super(xPos, yPos, hitbox, image);
		this.game = game;
		this.xMinPos = xMinPos;
		this.xMaxPos = xMaxPos;
		this.xVelocity = xVelocity;
		this.maxHP = maxHP;
		this.currentHP = this.maxHP;
		aggroBox = new Circle(xPos, yPos, aggroRange);
		this.type = type;
	}

	@Override
	public void update(GameContainer container, int delta) {

		// TODO
		// if enemy is in aggro range it will run to the player
		if (aggroBox.intersects(game.getPlayer().hitbox)) {
			if (aggroBox.getCenterX() >= game.getPlayer().hitbox.getCenterX()) {
				if (xPos - 3 >= xMinPos) {
					xPos -= 2;
					this.hitbox.setX(this.hitbox.getX() - 2);
					aggroBox.setX(aggroBox.getX() - 2);
				}
			} else {
				if (xPos + 3 <= xMaxPos) {
					xPos += 2;
					this.hitbox.setX(this.hitbox.getX() + 2);
					aggroBox.setX(aggroBox.getX() + 2);
				}
			}
			// otherwise the enemy will just move around
		} else {
			if (xPos + xVelocity >= xMaxPos || xPos + xVelocity <= xMinPos) {
				xVelocity *= -1;
			}
			xPos += xVelocity;
			this.hitbox.setX(this.hitbox.getX() + xVelocity);
			aggroBox.setX(aggroBox.getX() + xVelocity);
		}

		// Can the enemy hit the player?
		if (this.hitbox.intersects(game.getPlayer().hitbox)) {
			deltaEnemyHit += delta;
			if (deltaEnemyHit > 1500) {
				switch (type) {
				case 1:
					game.getPlayer().setCurrentLife(-5);
					deltaEnemyHit = 0;
					break;
				case 2:
					game.getPlayer().setCurrentLife(-10);
					deltaEnemyHit = 0;
					break;
				default:
					break;
				}
			}			
		}
	}

	public int getxMinPos() {
		return xMinPos;
	}

	public void setxMinPos(int xMinPos) {
		this.xMinPos = xMinPos;
	}

	public int getxMaxPos() {
		return xMaxPos;
	}

	public void setxMaxPos(int xMaxPos) {
		this.xMaxPos = xMaxPos;
	}

	// set the current HP of an enemy and return true, if it dies
	public boolean setCurrentHP(int damage) {
		currentHP -= damage;
		if (currentHP <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public Circle getAggroR() {
		return this.aggroBox;
	}

	public int getCurrentHP() {
		return currentHP;
	}
}
