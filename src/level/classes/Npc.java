package level.classes;

import java.io.FileNotFoundException;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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
	private int damage;
	private Shape aggroBox;
	
	// type 0 = object, type 1 = normal enemy, type 2 = boss
	private int type;
	
	private int deltaEnemyHit;

	public Npc(MysticStudioGame game, int xPos, int yPos, Shape hitbox, Image image, int xMinPos, int xMaxPos,
			int xVelocity, int maxHP, int damage, Shape aggroBox, int type) {
		super(xPos, yPos, hitbox, image);
		this.game = game;
		this.xMinPos = xMinPos;
		this.xMaxPos = xMaxPos;
		this.xVelocity = xVelocity;
		this.maxHP = maxHP;
		this.currentHP = this.maxHP;
		this.damage = damage;
		this.aggroBox = aggroBox;
		this.type = type;
		
		this.setxPos(xPos);
		this.setyPos(yPos);
	}

	@Override
	public void update(GameContainer container, int delta) {

		// npc movement
		performMovement(delta);
		
		// perform hit/attack (for enemies)
		if (type != 0) {
			attackPlayer(delta);
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics g) {
		super.render(container, g);
		
		Color transparent;
		
		// color for debugging / hitbox visualization during development
		if (container.getInput().isKeyDown(Input.KEY_NUMPAD5)) {
			transparent = new Color(0.5f, 0.2f, 0.5f, 0.4f);
		} else {
			transparent = new Color(0.5f, 0.2f, 0.5f, 0.0f);	
		}
		g.setColor(transparent);
		
		g.fill(aggroBox);
	}
	
	@Override
	public void setxPos(int xPos) {
		super.setxPos(xPos);
		aggroBox.setCenterX(this.hitbox.getCenterX());
	}
	
	@Override
	public void setyPos(int yPos) {
		super.setyPos(yPos);
		aggroBox.setCenterY(this.hitbox.getCenterY());
	}
	
	private void performMovement(int delta) {
		
		// perform aggro movement (only for enemies)
		if (type != 0) {
			// if enemy is in aggro range it will run to the player
			if (aggroBox.intersects(game.getPlayer().hitbox)) {
				if (aggroBox.getCenterX() >= game.getPlayer().hitbox.getCenterX()) {
					if (xPos - 5 >= xMinPos) {
						xPos -= 4;
						setxPos(xPos);
					}
				} else {
					if (xPos + 5 <= xMaxPos) {
						xPos += 4;
						setxPos(xPos);
					}
				}
				return;
			}
		}
		
		// perform general movement
		if (xPos + xVelocity >= xMaxPos || xPos + xVelocity <= xMinPos) {
				xVelocity *= -1;
		}
		xPos += xVelocity;
		setxPos(xPos);
	}
	
	
	private void attackPlayer(int delta) {
		
		// Contact with the player? -> damage the player
		if (checkContact(game.getPlayer())) {
			deltaEnemyHit += delta;
			if (deltaEnemyHit > 1500) {
				switch (type) {
				case 1:
					try {
						game.getPlayer().setCurrentLife(damage);
					} catch (FileNotFoundException | SlickException e) {
						e.printStackTrace();
					}
					deltaEnemyHit = 0;
					break;
				case 2:
					try {
						game.getPlayer().setCurrentLife(damage);
					} catch (FileNotFoundException | SlickException e) {
						e.printStackTrace();
					}
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

	public Shape getAggroBox() {
		return this.aggroBox;
	}

	public int getCurrentHP() {
		return currentHP;
	}
}
