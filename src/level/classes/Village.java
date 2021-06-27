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
			Image villageWallLeftRight = new Image("res/images/Village-Wall-LR.png");
			Image villageWallTopBottom = new Image("res/images/Village-Wall-TB.png");
			Image gateRight1 = new Image("res/images/Gate-Right1.png");
			Image gateRight2 = new Image("res/images/Gate-Right2.png");
			
			// houses
			getTextures().add(new GraphicObject(100, 50, new Rectangle(100, 50, 148, 377), villageTower));
			getTextures().add(new GraphicObject(100, 650, new Rectangle(100, 650, 435, 321), villageHouse01));
			getTextures().add(new GraphicObject(1000, 200, new Rectangle(1000, 200, 520, 241), villageHouse02));
			
			// walls
			getTextures().add(new GraphicObject(0, 0, new Rectangle(0, 0, 60, 400), villageWallLeftRight));
			getTextures().add(new GraphicObject(0, 400, new Rectangle(0, 400, 60, 400), villageWallLeftRight));
			getTextures().add(new GraphicObject(0, 800, new Rectangle(0, 0, 60, 800), villageWallLeftRight));
			
			getTextures().add(new GraphicObject(1860, 0, new Rectangle(1860, 0, 60, 400), villageWallLeftRight));
			getTextures().add(new GraphicObject(1860, 300, new Rectangle(1860, 300, 60, 400), villageWallLeftRight));
			getTextures().add(new GraphicObject(1860, 700, new Rectangle(1860, 700, 60, 100), gateRight1));
			getTextures().add(new GraphicObject(1860, 800, new Rectangle(1860, 800, 60, 100), gateRight2));
			getTextures().add(new GraphicObject(1860, 900, new Rectangle(1860, 900, 60, 400), villageWallLeftRight));

			getTextures().add(new GraphicObject(0, 1040, new Rectangle(0, 1040, 400, 60), villageWallTopBottom));
			getTextures().add(new GraphicObject(400, 1040, new Rectangle(400, 1040, 400, 60), villageWallTopBottom));
			getTextures().add(new GraphicObject(800, 1040, new Rectangle(800, 1040, 400, 60), villageWallTopBottom));
			getTextures().add(new GraphicObject(1200, 1040, new Rectangle(1200, 1040, 400, 60), villageWallTopBottom));
			getTextures().add(new GraphicObject(1600, 1040, new Rectangle(1600, 1040, 400, 60), villageWallTopBottom));

			getTextures().add(new GraphicObject(0, -20, new Rectangle(0, -20, 400, 60), villageWallTopBottom));
			getTextures().add(new GraphicObject(300, -20, new Rectangle(300, -20, 500, 60), villageWallTopBottom));
			getTextures().add(new GraphicObject(800, -20, new Rectangle(800, -20, 400, 60), villageWallTopBottom));
			getTextures().add(new GraphicObject(1200, -20, new Rectangle(1200, -20, 400, 60), villageWallTopBottom));
			getTextures().add(new GraphicObject(1600, -20, new Rectangle(1600, -20, 400, 60), villageWallTopBottom));
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

		// objects and NPCs
		npcs = new ArrayList<Npc>();
		
		// interaction objects and NPCs
		interactionObjects = new ArrayList<InteractionObject>();
		try {
			getInteractionObjects().add(new InteractionObject(700, 10, new Rectangle(700, 10, 100, 250),
					new Image("res/images/Gate-Top.png"), game, "SelectLevel"));
			getInteractionObjects().add(new InteractionObject(535, 800, new Rectangle(535, 800, 100, 200),
					new Image("res/images/smith.png"), game, "Quest01"));
			getInteractionObjects().add(new InteractionObject(1200, 450, new Rectangle(1200, 450, 100, 200),
					new Image("res/images/merchant.png"), game, "merchant"));
			getInteractionObjects().add(new InteractionObject(1600, 800, new Rectangle(1600, 800, 100, 200),
					new Image("res/images/villager01.png"), game, "villager"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
