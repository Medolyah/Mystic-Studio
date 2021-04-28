package level.classes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

import basic.classes.GraphicObject;

public class Npc extends GraphicObject {
	
	private int xMinPos;
	private int xMaxPos;
	private int xVelocity;

	public Npc(int xPos, int yPos, Shape hitbox, Image image, int xMinPos, int xMaxPos, int xVelocity) {
		super(xPos, yPos, hitbox, image);
		this.xMinPos = xMinPos;
		this.xMaxPos = xMaxPos;
		this.xVelocity = xVelocity;
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		
		if (xPos + xVelocity >= xMaxPos || xPos + xVelocity <= xMinPos) {
			xVelocity *= -1;
		}
		
		xPos += xVelocity;
	}

	public int getxMinPos() {
		return xMinPos;
	}

	public void setxMinPos(int xMinPos) {
		this.xMinPos = xMinPos;
	}

	public int getxMaxPos() {
		return xMaxPos;
	}

	public void setxMaxPos(int xMaxPos) {
		this.xMaxPos = xMaxPos;
	}
}
