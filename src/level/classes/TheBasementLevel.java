package level.classes;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import basic.classes.GraphicObject;

public class TheBasementLevel extends Level {

	public TheBasementLevel() {

		// level type
		levelType = Level.LevelType.PLATFORMER;

		// textures/objects
		textures = new ArrayList<GraphicObject>();
		try {
			// build main floor
			textures.add(new GraphicObject(-1000, 1000, new Rectangle(-1000, 1000, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(-600, 1000, new Rectangle(-600, 1000, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(-200, 1000, new Rectangle(-200, 1000, 400, 60), new Image("res/images/Basement_Platform_01_b.png")));
			textures.add(new GraphicObject(200, 1000, new Rectangle(200, 1000, 400, 60), new Image("res/images/Basement_Platform_01_b.png")));
			textures.add(new GraphicObject(600, 1000, new Rectangle(600, 1000, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(1000, 1000, new Rectangle(1000, 1000, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(1400, 1000, new Rectangle(1400, 1000, 400, 60), new Image("res/images/Basement_Platform_01_b.png")));
			textures.add(new GraphicObject(1800, 1000, new Rectangle(1800, 1000, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(2200, 1000, new Rectangle(2200, 1000, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(2400, 1000, new Rectangle(2400, 1000, 400, 60), new Image("res/images/Basement_Platform_01_b.png")));
			textures.add(new GraphicObject(2800, 1000, new Rectangle(2800, 1000, 400, 60), new Image("res/images/Basement_Platform_03_b.png")));
			textures.add(new GraphicObject(3200, 1000, new Rectangle(3200, 1000, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(3600, 1000, new Rectangle(3600, 1000, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(4000, 1000, new Rectangle(4000, 1000, 400, 60), new Image("res/images/Basement_Platform_03_b.png")));
			textures.add(new GraphicObject(4400, 1000, new Rectangle(4400, 1000, 400, 60), new Image("res/images/Basement_Platform_01_b.png")));
			textures.add(new GraphicObject(4800, 1000, new Rectangle(4800, 1000, 400, 60), new Image("res/images/Basement_Platform_03_b.png")));
			textures.add(new GraphicObject(5200, 1000, new Rectangle(5200, 1000, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			
			// walls
			textures.add(new GraphicObject(-200, 600, new Rectangle(-200, 800, 60, 400), new Image("res/images/Basement_Wall_01.png")));
			textures.add(new GraphicObject(1000, 800, new Rectangle(1000, 800, 60, 200), new Image("res/images/Basement_Half-Wall_01.png")));
			textures.add(new GraphicObject(1340, 800, new Rectangle(1340, 800, 60, 200), new Image("res/images/Basement_Half-Wall_01.png")));
			textures.add(new GraphicObject(1500, 600, new Rectangle(1500, 600, 60, 400), new Image("res/images/Basement_Wall_01.png")));
			textures.add(new GraphicObject(1840, 600, new Rectangle(1840, 600, 60, 400), new Image("res/images/Basement_Wall_01.png")));
			textures.add(new GraphicObject(3500, 560, new Rectangle(3500, 560, 60, 200), new Image("res/images/Basement_Half-Wall_01.png")));
			
			// platforms
			textures.add(new GraphicObject(1000, 800, new Rectangle(1000, 800, 400, 60), new Image("res/images/Basement_Platform_01.png")));
			textures.add(new GraphicObject(1500, 600, new Rectangle(1500, 600, 400, 60), new Image("res/images/Basement_Platform_03.png")));
			textures.add(new GraphicObject(1900, 660, new Rectangle(1900, 660, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(2300, 660, new Rectangle(2300, 660, 400, 60), new Image("res/images/Basement_Platform_01_b.png")));
			textures.add(new GraphicObject(2700, 660, new Rectangle(2700, 660, 400, 60), new Image("res/images/Basement_Platform_03_b.png")));
			textures.add(new GraphicObject(3100, 660, new Rectangle(3100, 660, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

		// npcs
		npcs = new ArrayList<Npc>();
		try {
			npcs.add(new Npc(1450, 550, new Rectangle(1450, 100, 400, 61), new Image("testdata/homeranim.png"), 1450, 1650, 2));
		} catch (SlickException e) {
			e.printStackTrace();
		}

		// background
		try {
			this.background = new Image("res/images/Basement_Background.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer container, int delta) {

		for (Npc npc : npcs) {
			npc.update(container, delta);
		}

	}

	@Override
	public void render(GameContainer container, Graphics g) {

		// background
		if (background != null) {
			g.drawImage(background, 0, 0);
		}

		// textures/object
		for (GraphicObject graphicObject : textures) {
			graphicObject.render(container, g);
		}

		// textures/object
		for (Npc npc : npcs) {
			npc.render(container, g);
		}

	}

}
