package level.classes;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

import audio.classes.GameMusic;
import basic.classes.GraphicObject;
import basic.classes.MysticStudioGame;

@SuppressWarnings("deprecation")
public class TheBasementLevel extends Level {

	public TheBasementLevel(MysticStudioGame game) {
		super(game);
		levelName = "Level 1: The Basement";
		textFont = new Font("Distant Galaxy", Font.PLAIN, 25);
		ttTextFont = new TrueTypeFont(textFont, true);
		fontColor = new Color(0.9f, 0.9f, 0.9f, 0.9f);

		// level type
		levelType = Level.LevelType.PLATFORMER;
		
		// music
		try {
			levelMusic = new GameMusic("res/music/basementMusic.wav");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (SlickException e1) {
			e1.printStackTrace();
		}

		// textures/objects (with hitbox)
		textures = new ArrayList<GraphicObject>();
		try {
			// platforms
			getTextures().add(new GraphicObject(1000, 800, new Rectangle(1000, 800, 400, 60),
					new Image("res/images/Basement_Platform_01.png")));
			getTextures().add(new GraphicObject(1600, 600, new Rectangle(1600, 600, 400, 60),
					new Image("res/images/Basement_Platform_03.png")));
			getTextures().add(new GraphicObject(2000, 660, new Rectangle(2000, 660, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));
			getTextures().add(new GraphicObject(2400, 660, new Rectangle(2400, 660, 400, 60),
					new Image("res/images/Basement_Platform_01_b.png")));
			getTextures().add(new GraphicObject(2800, 660, new Rectangle(2800, 660, 400, 60),
					new Image("res/images/Basement_Platform_03_b.png")));
			getTextures().add(new GraphicObject(3200, 660, new Rectangle(3200, 660, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));
			getTextures().add(new GraphicObject(4400, 660, new Rectangle(4400, 660, 400, 60),
					new Image("res/images/Basement_Platform_03.png")));
			getTextures().add(new GraphicObject(4000, 360, new Rectangle(4000, 360, 400, 60),
					new Image("res/images/Basement_Platform_03.png")));
			getTextures().add(new GraphicObject(3940, 360, new Rectangle(3940, 360, 60, 60),
					new Image("res/images/Basement_Platform_Block.png")));
			getTextures().add(new GraphicObject(3600, 160, new Rectangle(3600, 160, 400, 60),
					new Image("res/images/Basement_Platform_02.png")));
			getTextures().add(new GraphicObject(3200, 100, new Rectangle(3200, 100, 400, 60),
					new Image("res/images/Basement_Platform_03.png")));
			getTextures().add(new GraphicObject(2800, 40, new Rectangle(2800, 40, 400, 60),
					new Image("res/images/Basement_Platform_02.png")));
			getTextures().add(new GraphicObject(2400, -20, new Rectangle(2400, -20, 400, 60),
					new Image("res/images/Basement_Platform_02.png")));
			getTextures().add(new GraphicObject(2000, -80, new Rectangle(2000, -80, 400, 60),
					new Image("res/images/Basement_Platform_03.png")));
			getTextures().add(new GraphicObject(1400, -80, new Rectangle(1400, -80, 400, 60),
					new Image("res/images/Basement_Platform_01.png")));
			getTextures().add(new GraphicObject(1000, -80, new Rectangle(1000, -80, 400, 60),
					new Image("res/images/Basement_Platform_02.png")));
			getTextures().add(new GraphicObject(600, -80, new Rectangle(600, -80, 400, 60),
					new Image("res/images/Basement_Platform_02.png")));

			getTextures().add(new GraphicObject(800, -600, new Rectangle(800, -600, 400, 60),
					new Image("res/images/Basement_Platform_02.png")));
			getTextures().add(new GraphicObject(1200, -660, new Rectangle(1200, -660, 400, 60),
					new Image("res/images/Basement_Platform_03.png")));
			getTextures().add(new GraphicObject(1600, -720, new Rectangle(1600, -720, 400, 60),
					new Image("res/images/Basement_Platform_02.png")));
			getTextures().add(new GraphicObject(2000, -780, new Rectangle(2000, -780, 400, 60),
					new Image("res/images/Basement_Platform_01.png")));
			getTextures().add(new GraphicObject(2400, -840, new Rectangle(2400, -840, 400, 60),
					new Image("res/images/Basement_Platform_02.png")));
			// boss platform
			getTextures().add(new GraphicObject(2800, -900, new Rectangle(2800, -900, 400, 60),
					new Image("res/images/Basement_Platform_03.png")));
			getTextures().add(new GraphicObject(3200, -900, new Rectangle(3200, -900, 400, 60),
					new Image("res/images/Basement_Platform_01.png")));
			getTextures().add(new GraphicObject(3600, -900, new Rectangle(3600, -900, 400, 60),
					new Image("res/images/Basement_Platform_01.png")));
			getTextures().add(new GraphicObject(4000, -900, new Rectangle(4000, -900, 400, 60),
					new Image("res/images/Basement_Platform_03.png")));
			getTextures().add(new GraphicObject(4400, -900, new Rectangle(4400, -900, 400, 60),
					new Image("res/images/Basement_Platform_01.png")));
			getTextures().add(new GraphicObject(4800, -900, new Rectangle(4800, -900, 400, 60),
					new Image("res/images/Basement_Platform_03.png")));
			getTextures().add(new GraphicObject(5200, -900, new Rectangle(5200, -900, 400, 60),
					new Image("res/images/Basement_Platform_03.png")));
			getTextures().add(new GraphicObject(5600, -900, new Rectangle(5600, -900, 400, 60),
					new Image("res/images/Basement_Platform_02.png")));

			// walls
			getTextures().add(new GraphicObject(-200, 600, new Rectangle(-200, 800, 60, 400),
					new Image("res/images/Basement_Wall_01.png")));
			getTextures().add(new GraphicObject(1000, 800, new Rectangle(1000, 800, 60, 200),
					new Image("res/images/Basement_Half-Wall_01.png")));
			getTextures().add(new GraphicObject(1340, 800, new Rectangle(1340, 800, 60, 200),
					new Image("res/images/Basement_Half-Wall_01.png")));
			getTextures().add(new GraphicObject(1600, 600, new Rectangle(1600, 600, 60, 400),
					new Image("res/images/Basement_Wall_01.png")));
			getTextures().add(new GraphicObject(1940, 600, new Rectangle(1940, 600, 60, 400),
					new Image("res/images/Basement_Wall_01.png")));
			// main floor end wall
			getTextures().add(new GraphicObject(4800, 600, new Rectangle(4800, 600, 60, 400),
					new Image("res/images/Basement_Wall_01.png")));
			getTextures().add(new GraphicObject(4800, 200, new Rectangle(4800, 200, 60, 400),
					new Image("res/images/Basement_Wall_01.png")));

			getTextures().add(new GraphicObject(4400, 975, new Rectangle(4400, 975, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			getTextures().add(new GraphicObject(3940, 160, new Rectangle(3940, 160, 60, 200),
					new Image("res/images/Basement_Half-Wall_01.png")));
			getTextures().add(new GraphicObject(3540, 160, new Rectangle(3540, 160, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			getTextures().add(new GraphicObject(3140, 100, new Rectangle(3140, 100, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			getTextures().add(new GraphicObject(2740, 40, new Rectangle(2740, 40, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			getTextures().add(new GraphicObject(2340, -20, new Rectangle(2340, -20, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			getTextures().add(new GraphicObject(600, -280, new Rectangle(600, -280, 60, 200),
					new Image("res/images/Basement_Half-Wall_01.png")));
			getTextures().add(new GraphicObject(540, -80, new Rectangle(540, -80, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			getTextures().add(new GraphicObject(540, -480, new Rectangle(540, -480, 60, 400),
					new Image("res/images/Basement_Wall_01.png")));
			getTextures().add(new GraphicObject(540, -880, new Rectangle(540, -880, 60, 400),
					new Image("res/images/Basement_Wall_01.png")));
			getTextures().add(new GraphicObject(540, -1280, new Rectangle(540, -1280, 60, 400),
					new Image("res/images/Basement_Wall_01.png")));
			getTextures().add(new GraphicObject(1200, -600, new Rectangle(1200, -600, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			getTextures().add(new GraphicObject(1600, -660, new Rectangle(1600, -660, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			getTextures().add(new GraphicObject(2000, -720, new Rectangle(2000, -720, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			getTextures().add(new GraphicObject(2400, -780, new Rectangle(2400, -780, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			getTextures().add(new GraphicObject(2800, -840, new Rectangle(2800, -840, 60, 60),
					new Image("res/images/Basement_Wall_Block.png")));
			// end boss wall
			getTextures().add(new GraphicObject(6000, -1240, new Rectangle(6000, -1240, 60, 400),
					new Image("res/images/Basement_Wall_01.png")));
			getTextures().add(new GraphicObject(6000, -1640, new Rectangle(6000, -1640, 60, 400),
					new Image("res/images/Basement_Wall_01.png")));

			// main floor
			getTextures().add(new GraphicObject(-1000, 1000, new Rectangle(-1000, 1000, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));
			getTextures().add(new GraphicObject(-600, 1000, new Rectangle(-600, 1000, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));
			getTextures().add(new GraphicObject(-200, 1000, new Rectangle(-200, 1000, 400, 60),
					new Image("res/images/Basement_Platform_01_b.png")));
			getTextures().add(new GraphicObject(200, 1000, new Rectangle(200, 1000, 400, 60),
					new Image("res/images/Basement_Platform_01_b.png")));
			getTextures().add(new GraphicObject(600, 1000, new Rectangle(600, 1000, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));
			getTextures().add(new GraphicObject(1000, 1000, new Rectangle(1000, 1000, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));
			getTextures().add(new GraphicObject(1400, 1000, new Rectangle(1400, 1000, 400, 60),
					new Image("res/images/Basement_Platform_01_b.png")));
			getTextures().add(new GraphicObject(1800, 1000, new Rectangle(1800, 1000, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));
			getTextures().add(new GraphicObject(2200, 1000, new Rectangle(2200, 1000, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));
			getTextures().add(new GraphicObject(2400, 1000, new Rectangle(2400, 1000, 400, 60),
					new Image("res/images/Basement_Platform_01_b.png")));
			getTextures().add(new GraphicObject(2800, 1000, new Rectangle(2800, 1000, 400, 60),
					new Image("res/images/Basement_Platform_03_b.png")));
			getTextures().add(new GraphicObject(3200, 1000, new Rectangle(3200, 1000, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));
			getTextures().add(new GraphicObject(3600, 1000, new Rectangle(3600, 1000, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));
			getTextures().add(new GraphicObject(4000, 1000, new Rectangle(4000, 1000, 400, 60),
					new Image("res/images/Basement_Platform_03_b.png")));
			getTextures().add(new GraphicObject(4400, 1000, new Rectangle(4400, 1000, 400, 60),
					new Image("res/images/Basement_Platform_01_b.png")));
			getTextures().add(new GraphicObject(4800, 1000, new Rectangle(4800, 1000, 400, 60),
					new Image("res/images/Basement_Platform_03_b.png")));
			getTextures().add(new GraphicObject(5200, 1000, new Rectangle(5200, 1000, 400, 60),
					new Image("res/images/Basement_Platform_02_b.png")));

		} catch (SlickException e) {
			e.printStackTrace();
		}

		// objects and NPCs / enemies
		npcs = new ArrayList<Npc>();
		try {
			// enemies
			Image spider2 = new Image("res/images/Spider2.png");
			Image devilEye1 = new Image("res/images/Devil-eye1.png");

			npcs.add(new Npc(game, 1650, 500, new Circle(1650, 910, 60), spider2, 1650, 1900, 2, 100, 200, 1));
			npcs.add(new Npc(game, 1650, 550, new Circle(1650, 910, 60), spider2, 1650, 1900, 3, 100, 200, 1));
			npcs.add(new Npc(game, 1900, 550, new Circle(1900, 910, 60), spider2, 1650, 1900, 2, 100, 200, 1));

			npcs.add(new Npc(game, 2000, 950, new Circle(1900, 910, 60), spider2, 2000, 3500, 2, 100, 200, 1));
			npcs.add(new Npc(game, 2500, 925, new Circle(1900, 910, 60), spider2, 2000, 3500, 2, 100, 200, 1));
			npcs.add(new Npc(game, 3000, 950, new Circle(1900, 910, 60), spider2, 2000, 3500, 4, 100, 200, 1));
			npcs.add(new Npc(game, 3500, 925, new Circle(1900, 910, 60), spider2, 2000, 3500, 2, 100, 200, 1));
			npcs.add(new Npc(game, 2500, 900, new Circle(1900, 910, 60), spider2, 2000, 3500, 1, 100, 200, 1));
			npcs.add(new Npc(game, 2000, 925, new Circle(1900, 910, 60), spider2, 2000, 3500, 3, 100, 200, 1));

			npcs.add(new Npc(game, 4750, 950, new Circle(4750, 910, 60), spider2, 4750, 4750, 0, 100, 200, 1));

			// boss
			npcs.add(boss = new Npc(game, 3000, -1212, new Rectangle(3000, -1212, 300, 145), devilEye1, 3000, 5400, 2, 100,
					200, 2));

		} catch (SlickException e) {
			e.printStackTrace();
		}

		// background
		try {
			this.background = new Image("res/images/Basement_Background.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// interaction objects and NPCs
		interactionObjects = new ArrayList<InteractionObject>();
		try {
			getInteractionObjects().add(new InteractionObject(180, 750, new Rectangle(180, 750, 150, 250),
					new Image("res/images/Basement_Door.png"), game, ""));
			getInteractionObjects().add(new InteractionObject(5750, -1145, new Rectangle(5750, -1145, 150, 250),
					new Image("res/images/Basement_Door.png"), game, "BasementLevel"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {

		super.render(container, g);

		// Level infos
		ttTextFont.drawString(container.getWidth() / 2 - 100, 20, levelName, fontColor);
		ttTextFont.drawString(container.getWidth() / 2 - 100, 50, "Enemy level: 1 - 3", fontColor);

	}
}
