package level.classes;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import audio.classes.GameMusic;
import basic.classes.GraphicObject;
import basic.classes.MysticStudioGame;

public class Village extends Level {

	public Village(MysticStudioGame game) {
		super(game);

		// level type
		levelType = Level.LevelType.BIRDS_EYE_VIEW;

		// music 
		try {
			levelMusic = new GameMusic("res/music/bgMusic.wav");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
		
		// background
		try {
			this.background = new Image("res/images/Village_Background.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// textures/objects (with hitbox)
		textures = new ArrayList<GraphicObject>();
		try {

			// load required graphics
			Image villageTower = new Image("res/images/Village-Tower.png");
			Image villageHouse01 = new Image("res/images/Village-House-1.png");
			Image villageHouse02 = new Image("res/images/Village-House-2.png");
			
			
			getTextures().add(new GraphicObject(100, 50, new Rectangle(100, 50, 148, 377), villageTower));
			getTextures().add(new GraphicObject(100, 650, new Rectangle(100, 650, 435, 321), villageHouse01));
			getTextures().add(new GraphicObject(1000, 200, new Rectangle(1000, 200, 520, 241), villageHouse02));
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

		// objects and NPCs
		npcs = new ArrayList<Npc>();
		
		// interaction objects and NPCs
		interactionObjects = new ArrayList<InteractionObject>();
		try {
			getInteractionObjects().add(new InteractionObject(600, 0, new Rectangle(600, 0, 150, 250),
					new Image("res/images/Basement_Door.png"), game, "SelectLevel"));
			getInteractionObjects().add(new InteractionObject(535, 800, new Rectangle(535, 800, 100, 200),
					new Image("res/images/smith.png"), game, "Quest01"));
			getInteractionObjects().add(new InteractionObject(1200, 450, new Rectangle(1200, 450, 100, 200),
					new Image("res/images/merchant.png"), game, "merchant"));
			getInteractionObjects().add(new InteractionObject(1600, 900, new Rectangle(1600, 900, 100, 200),
					new Image("res/images/villager01.png"), game, "villager"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
