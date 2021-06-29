package level.classes;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import audio.classes.GameMusic;
import audio.classes.GameSound;
import basic.classes.GraphicObject;
import basic.classes.MysticButton;
import basic.classes.MysticStudioGame;
import itmes.classes.GoldCoin;
import itmes.classes.InGameItem;
import player.classes.Player.Movement;

@SuppressWarnings("deprecation")
public abstract class Level {

	public enum LevelType {
		BIRDS_EYE_VIEW, PLATFORMER
	}

	protected int levelNumber;
	protected ArrayList<GraphicObject> textures;
	protected GraphicObject exitWall;
	protected ArrayList<Npc> npcs;
	protected Npc boss;
	protected ArrayList<InteractionObject> interactionObjects;
	protected ArrayList<InGameItem> inGameItems;
	protected LevelType levelType;
	protected Image background;
	protected String levelName;
	protected GameMusic levelMusic;

	protected MysticStudioGame game;

	protected Font textFont;
	protected TrueTypeFont ttTextFont;
	protected Color fontColor;

	public Level(MysticStudioGame game) {
		this.game = game;
		inGameItems = new ArrayList<InGameItem>();
	}

	public void update(GameContainer container, int delta) throws FileNotFoundException, SlickException {
		for (Npc npc : npcs) {
			npc.update(container, delta);
		}
		for (InteractionObject interactionObject : interactionObjects) {
			interactionObject.update(container, delta);
		}
		lootCollected(container, delta);
		for (InGameItem inGameItem : inGameItems) {
			inGameItem.update(container, delta);
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

		// interaction objects
		if (getInteractionObjects() != null) {
			for (InteractionObject interactionObject : getInteractionObjects()) {
				interactionObject.render(container, g);
			}
		}

		// npcs
		if (npcs != null) {
			for (Npc npc : npcs) {
				npc.render(container, g);
			}
		}
		
		// items
		for (InGameItem inGameItem : inGameItems) {
			inGameItem.render(container, g);;
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
			if (interactionObjects != null) {
				for (InteractionObject object : getInteractionObjects()) {
					object.setyPos(object.getyPos() - 1);
				}
			}
			if (npcs != null) {
				for (Npc npc : npcs) {
					npc.setyPos(npc.getyPos() - 1);
				}
			}
			if (getInGameItems() != null) {
				for (InGameItem inGameItem : inGameItems) {
					inGameItem.getItemIcon().setyPos(inGameItem.getItemIcon().getyPos() - 1);
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
			if (getInGameItems() != null) {
				for (InGameItem inGameItem : inGameItems) {
					inGameItem.getItemIcon().setyPos(inGameItem.getItemIcon().getyPos() + 1);
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
			if (getInGameItems() != null) {
				for (InGameItem inGameItem : inGameItems) {
					inGameItem.getItemIcon().setxPos(inGameItem.getItemIcon().getxPos() + 1);
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
			if (getInGameItems() != null) {
				for (InGameItem inGameItem : inGameItems) {
					inGameItem.getItemIcon().setxPos(inGameItem.getItemIcon().getxPos() - 1);
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
					game.getPlayer().setCurrentXP(levelNumber * 5);
					if (npc.equals(boss)) {
						game.getPlayer().setCurrentXP(levelNumber * 10);
						GameSound lootDrop = new GameSound("res/sounds/lootDrop.wav");
						lootDrop.playSound();
						openExit();
						if (levelNumber > game.getPlayer().getGameProgress()) {
							game.getPlayer().setGameProgress(levelNumber);
						}
					}
					iterator.remove();
					isDrop();
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

	public ArrayList<InGameItem> getInGameItems() {
		return this.inGameItems;
	}

	public Npc getBoss() {
		return this.boss;
	}

	protected void isDrop() {
		Random random = new Random();
		int rn = random.nextInt(11) + 1;
		if (rn > 7) {
			int xPos = game.getPlayer().getxPos() + rn * rn;
			int yPos = game.getPlayer().getyPos() + 150;
			Shape hitbox = new Circle(xPos, yPos, 13);
			try {
				Image itemImage = new Image("res/images/Gold-Coin.png");
				GoldCoin coin = new GoldCoin(game, "Gold", new MysticButton(xPos, yPos, hitbox, itemImage),
						new int[] { 1 });
				inGameItems.add(coin);
			} catch (SlickException | FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	protected void lootCollected(GameContainer container, int delta) {
		Iterator<InGameItem> iterator = inGameItems.iterator();
		while (iterator.hasNext()) {
			InGameItem currentInGameItem = iterator.next();
			try {
				if (currentInGameItem.getItemIcon().isClicked(container.getInput())) {
					iterator.remove();
					game.getPlayer().setCurrentGold(levelNumber);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	protected void openExit() {
		Iterator<GraphicObject> iterator = textures.iterator();
		while (iterator.hasNext()) {
			GraphicObject graphicObject = iterator.next();
			if (graphicObject.equals(exitWall)) {
				iterator.remove();
			}
		}
	}
}
