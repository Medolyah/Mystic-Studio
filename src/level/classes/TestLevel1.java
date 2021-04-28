package level.classes;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import basic.classes.GraphicObject;

public class TestLevel1 extends Level {
	
	public TestLevel1() {
		
		// level type
		levelType = Level.LevelType.PLATFORMER;
		
		// textures/objects
		textures = new ArrayList<GraphicObject>();
		try {
			textures.add(new GraphicObject(100, 100, new Rectangle(100, 100, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(400, 400, new Rectangle(400, 400, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(550, 550, new Rectangle(550, 550, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(700, 700, new Rectangle(700, 700, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(1100, 700, new Rectangle(1000, 700, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(1500, 700, new Rectangle(1300, 700, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(1900, 700, new Rectangle(1600, 700, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(2300, 700, new Rectangle(1600, 700, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(2700, 700, new Rectangle(1600, 700, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(3100, 700, new Rectangle(1600, 700, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(800, 800, new Rectangle(800, 800, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(1000, 1000, new Rectangle(1000, 1000, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(100, 1000, new Rectangle(100, 100, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(4000, 400, new Rectangle(400, 400, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(8000, 800, new Rectangle(800, 800, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(1000, 1000, new Rectangle(100, 100, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(4000, 4080, new Rectangle(400, 400, 400, 61), new Image("res/images/New_Button.png")));
			textures.add(new GraphicObject(8000, 8040, new Rectangle(800, 800, 400, 61), new Image("res/images/New_Button.png")));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		// npcs
		npcs = new ArrayList<Npc>();
		try {
			npcs.add(new Npc(1000, 600, new Rectangle(100, 100, 400, 61), new Image("res/images/New_Button.png"), 900, 1800, 3));				
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
		
		for (Npc npc: npcs) {
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
		for (Npc npc: npcs) {
			npc.render(container, g);
		}
	}
}
