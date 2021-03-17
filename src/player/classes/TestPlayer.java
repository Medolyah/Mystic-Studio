package player.classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

import basic.classes.GraphicObject;

public class TestPlayer extends Player {
	
	public TestPlayer() {
		
		// test data
		Shape hitbox = new Ellipse(200, 200, 100, 25);
		Image image = null;
		try {
			image = new Image("testdata/circle_orange_basis_t_75.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}		
		
		GraphicObject graphicObject = new GraphicObject(300, 300, hitbox, image);
		super.graphicObject = graphicObject;
	}


	@Override
	public void render(GameContainer container, Graphics g) {
		g.drawImage(graphicObject.getImage(), graphicObject.getX(), graphicObject.getY());
		
	}

}
