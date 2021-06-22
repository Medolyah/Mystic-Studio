package level.classes;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Shape;

import basic.classes.GraphicObject;
import basic.classes.MysticStudioGame;
import player.classes.Player.Movement;

@SuppressWarnings("deprecation")
public abstract class Level {

	public enum LevelType {
		BIRDS_EYE_VIEW,
		PLATFORMER
	}
	
	public ArrayList<GraphicObject> textures;
	public ArrayList<Npc> npcs;
	public ArrayList<InteractionObject> interactionObjects;	public LevelType levelType;
	public Image background;
	public String levelName;
	
	private MysticStudioGame game;
	
	public Font textFont;
	public TrueTypeFont ttTextFont;
	public Color fontColor;
	
	public Level(MysticStudioGame game) {
		this.game = game;
	}
	
	public void update(GameContainer container, int delta) {
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
		if (textures != null) {
			for (GraphicObject graphicObject : textures) {
				graphicObject.render(container, g);
			}			
		}
		
		// textures/object
		if (interactionObjects != null) {
			for (InteractionObject interactionObject: interactionObjects) {
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
			if (textures != null) {
				for (GraphicObject object : textures) {
					object.setyPos(object.getyPos() - 1);
				}
			}
			if (interactionObjects != null) {
				for (InteractionObject object : interactionObjects) {
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
			if (textures != null) {
				for (GraphicObject object : textures) {
					object.setyPos(object.getyPos() + 1);
				}
			}
			if (interactionObjects != null) {
				for (InteractionObject object : interactionObjects) {
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
			if (textures != null) {
				for (GraphicObject object : textures) {
					object.setxPos(object.getxPos() + 1);
				}
			}
			if (interactionObjects != null) {
				for (InteractionObject object : interactionObjects) {
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
			if (textures != null) {
				for (GraphicObject object : textures) {
					object.setxPos(object.getxPos() - 1);
				}
			}
			if (interactionObjects != null) {
				for (InteractionObject object : interactionObjects) {
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
	
	public ArrayList<Npc> getEnemyList() {
		return this.npcs;
	}
	
	public void hitEnemy(Shape playerHitbox, int damage) {
		Iterator<Npc> iterator = npcs.iterator();
		boolean kill = false;
		while (iterator.hasNext()) {
			Npc npc = iterator.next();
			if (playerHitbox.intersects(npc.hitbox)) {
				kill = npc.setCurrentHP(damage);
				if (kill) {
					iterator.remove();
					break;
				}
			}
		}
	}
}
