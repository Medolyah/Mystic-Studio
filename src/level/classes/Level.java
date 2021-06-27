package level.classes;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Shape;

import audio.classes.GameMusic;
import audio.classes.GameSound;
import basic.classes.GraphicObject;
import basic.classes.MysticStudioGame;
import player.classes.Player.Movement;

@SuppressWarnings("deprecation")
public abstract class Level {

	public enum LevelType {
		BIRDS_EYE_VIEW,
		PLATFORMER
	}
	
	protected ArrayList<GraphicObject> textures;
	protected ArrayList<Npc> npcs;
	protected Npc boss;
	protected ArrayList<InteractionObject> interactionObjects;
	protected LevelType levelType;
	protected Image background;
	protected String levelName;
	protected GameMusic levelMusic;
	
	
	private MysticStudioGame game;
	
	protected Font textFont;
	protected TrueTypeFont ttTextFont;
	protected Color fontColor;
	
	public Level(MysticStudioGame game) {
		this.game = game;
	}
	
	public void update(GameContainer container, int delta) throws FileNotFoundException, SlickException {
		for (Npc npc : npcs) {
			npc.update(container, delta);
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		
		// background
		if (background != null) {
			g.drawImage(background, 0, 0);			
		}
		
		// textures/object 
		if (getTextures() != null) {
			for (GraphicObject graphicObject : getTextures()) {
				graphicObject.render(container, g);
			}			
		}
		
		// textures/object
		if (getInteractionObjects() != null) {
			for (InteractionObject interactionObject: getInteractionObjects()) {
				interactionObject.render(container, g);
			}			
		}
		
		// textures/object 
		if (npcs != null) {
			for (Npc npc: npcs) {
				npc.render(container, g);
			}			
		}
	}
	
	public void moveObjects(Movement movement) {

		switch (movement) {
		case UP:
			if (getTextures() != null) {
				for (GraphicObject object : getTextures()) {
					object.setyPos(object.getyPos() - 1);
				}
			}
			if (getInteractionObjects() != null) {
				for (InteractionObject object : getInteractionObjects()) {
					object.setyPos(object.getyPos() - 1);
				}
			}
			if (npcs != null) {
				for (Npc npc : npcs) {
					npc.setyPos(npc.getyPos() - 1);
				}
			}
			break;
		case DOWN:
			if (getTextures() != null) {
				for (GraphicObject object : getTextures()) {
					object.setyPos(object.getyPos() + 1);
				}
			}
			if (getInteractionObjects() != null) {
				for (InteractionObject object : getInteractionObjects()) {
					object.setyPos(object.getyPos() + 1);
				}
			}
			if (npcs != null) {
				for (Npc npc : npcs) {
					npc.setyPos(npc.getyPos() + 1);
				}
			}
			break;
		case RIGHT:
			if (getTextures() != null) {
				for (GraphicObject object : getTextures()) {
					object.setxPos(object.getxPos() + 1);
				}
			}
			if (getInteractionObjects() != null) {
				for (InteractionObject object : getInteractionObjects()) {
					object.setxPos(object.getxPos() + 1);
				}
			}
			if (npcs != null) {
				for (Npc npc : npcs) {
					npc.setxPos(npc.getxPos() + 1);
					npc.setxMinPos(npc.getxMinPos() + 1);
					npc.setxMaxPos(npc.getxMaxPos() + 1);
				}
			}
			break;
		case LEFT:
			if (getTextures() != null) {
				for (GraphicObject object : getTextures()) {
					object.setxPos(object.getxPos() - 1);
				}
			}
			if (getInteractionObjects() != null) {
				for (InteractionObject object : getInteractionObjects()) {
					object.setxPos(object.getxPos() - 1);
				}
			}
			if (npcs != null) {
				for (Npc npc : npcs) {
					npc.setxPos(npc.getxPos() - 1);
					npc.setxMinPos(npc.getxMinPos() - 1);
					npc.setxMaxPos(npc.getxMaxPos() - 1);
				}
			}
			break;
		default:
			break;
		}
	}
	
	public void hitEnemy(Shape playerHitbox, int damage) throws FileNotFoundException, SlickException {
		Iterator<Npc> iterator = npcs.iterator();
		boolean kill = false;
		while (iterator.hasNext()) {
			Npc npc = iterator.next();
			if (playerHitbox.intersects(npc.hitbox)) {
				kill = npc.setCurrentHP(damage);
				GameSound attackSound = new GameSound("res/sounds/playerHitsEnemy.wav");
				attackSound.playSound();
				if (kill) {
					if (npc.equals(boss)) {
						GameSound lootDrop = new GameSound("res/sounds/lootDrop.wav");
						lootDrop.playSound();
					}
					iterator.remove();
					break;
				}
			}
		}
	}
	
	public ArrayList<Npc> getEnemyList() {
		return this.npcs;
	}

	public LevelType getLevelType() {
		return levelType;
	}

	public ArrayList<InteractionObject> getInteractionObjects() {
		return interactionObjects;
	}

	public ArrayList<GraphicObject> getTextures() {
		return textures;
	}
}
