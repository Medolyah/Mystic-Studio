package basic.classes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class GraphicObject {

	public int xPos;
	public int yPos;
	public Shape hitbox;
	public Image image;

	public GraphicObject(int xPos, int yPos, Shape hitbox, Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.hitbox = hitbox;
		this.image = image;
	}
	
	public void update(GameContainer container, int delta) {
		
	}
	
	public void render(GameContainer container, Graphics g) {
		g.drawImage(image, xPos, yPos);

		Color transparent = new Color(0.5f, 0.2f, 0.2f, 0.0f);
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
