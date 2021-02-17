package basic.classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class GraphicObject {

	private int xPos;
	private int yPos;
	private Shape hitbox;
	private Image image;

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

}
