package basic.classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class GraphicObject {

	protected int xPos;
	protected int yPos;
	protected Shape hitbox;
	protected Image image;

	public GraphicObject() {
		this.xPos = 0;
		this.yPos = 0;
		this.hitbox = hitbox;
		this.image = image;
	}

	public GraphicObject(int xPos, int yPos, Shape hitbox, Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.hitbox = hitbox;
		this.image = image;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public Shape getHitbox() {
		return hitbox;
	}

	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	
	
}
