package basic.classes;

import java.io.FileNotFoundException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class GraphicObject {

	public int xPos;
	public int yPos;
	public Shape hitbox;
	public Image image;
	
	// color for debugging / hitbox visualization during development
	private Color transparent;

	public GraphicObject(int xPos, int yPos, Shape hitbox, Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.hitbox = hitbox;
		this.image = image;
		
		// set hitbox position
		this.hitbox.setX(xPos);
		this.hitbox.setY(yPos);
	}
	
	public void update(GameContainer container, int delta) throws FileNotFoundException, SlickException {
		
	}
	
	public void render(GameContainer container, Graphics g) {
		g.drawImage(image, xPos, yPos);

		// color for debugging / hitbox visualization during development
		if (container.getInput().isKeyDown(Input.KEY_NUMPAD5)) {
			transparent = new Color(0.2f, 0.5f, 0.5f, 0.4f);
		} else {
			transparent = new Color(0.2f, 0.5f, 0.5f, 0.0f);	
		}
		g.setColor(transparent);
		
		g.fill(hitbox);
	}
	
	public boolean checkContact(GraphicObject otherObject) {
		if (hitbox.intersects(otherObject.hitbox)) {
			return true;
		}
		return false;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
		hitbox.setX(xPos);
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
		hitbox.setY(yPos);
	}
}
