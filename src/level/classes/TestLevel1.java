package level.classes;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import basic.classes.GraphicObject;
//import player.classes.Player.Movement;

public class TestLevel1 extends Level {
	
	public TestLevel1() {
		
		// level type
		levelType = Level.LevelType.PLATFORMER;
		
		// textures/objects
		int dummyInt = 1;
		textures = new ArrayList<GraphicObject>();
		try {
			textures.add(new GraphicObject(100, 100, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_01.png")));
			textures.add(new GraphicObject(400, 400, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_02.png")));
			textures.add(new GraphicObject(550, 550, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_03.png")));
			textures.add(new GraphicObject(700, 700, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_02.png")));
			textures.add(new GraphicObject(1100, 700, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_01_b.png")));
			textures.add(new GraphicObject(1500, 700, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(1900, 700, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_03_b.png")));
			textures.add(new GraphicObject(2300, 700, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_01_b.png")));
			textures.add(new GraphicObject(2700, 700, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_02_b.png")));
			textures.add(new GraphicObject(3100, 700, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_01_b.png")));
			textures.add(new GraphicObject(800, 800, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_01.png")));
			textures.add(new GraphicObject(1000, 1000, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_03.png")));
			textures.add(new GraphicObject(100, 1000, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_01.png")));
			textures.add(new GraphicObject(4000, 400, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_02.png")));
			textures.add(new GraphicObject(8000, 800, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_03.png")));
			textures.add(new GraphicObject(1000, 1000, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_02.png")));
			textures.add(new GraphicObject(4000, 4080, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_01.png")));
			textures.add(new GraphicObject(8000, 8040, new Rectangle(dummyInt, dummyInt, 400, 60), new Image("res/images/Basement_Platform_03.png")));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		// npcs
		objectsAndNpcs = new ArrayList<Npc>();
		try {
			objectsAndNpcs.add(new Npc(1000, 600, new Rectangle(100, 100, 400, 61), new Image("res/images/New_Button.png"), 900, 1800, 3));				
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		// background
		try {
			this.background = new Image("res/images/Level_Test_Background.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(GameContainer container, int delta) {

		for (Npc npc : objectsAndNpcs) {
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
		for (Npc npc: objectsAndNpcs) {
			npc.render(container, g);
		}
	}
}
