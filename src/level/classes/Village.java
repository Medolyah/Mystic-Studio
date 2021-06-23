package level.classes;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import basic.classes.GraphicObject;
import basic.classes.MysticStudioGame;

public class Village extends Level {

	public Village(MysticStudioGame game) {
		super(game);

		// level type
		levelType = Level.LevelType.BIRDS_EYE_VIEW;

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
			
			
			textures.add(new GraphicObject(100, 50, new Rectangle(100, 50, 148, 377), villageTower));
			textures.add(new GraphicObject(100, 650, new Rectangle(100, 650, 435, 321), villageHouse01));
			textures.add(new GraphicObject(1000, 200, new Rectangle(1000, 200, 520, 241), villageHouse02));
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

		// objects and NPCs
		npcs = new ArrayList<Npc>();
		
		// interaction objects
		interactionObjects = new ArrayList<InteractionObject>();
		try {
			interactionObjects.add(new InteractionObject(600, 0, new Rectangle(600, 0, 150, 250),
					new Image("res/images/Basement_Door.png"), game, "SelectLevel"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
