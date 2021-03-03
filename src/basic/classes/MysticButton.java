package basic.classes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

public class MysticButton extends GraphicObject {

	public MysticButton() {
		super();
	}

	public MysticButton(int xPos, int yPos, Shape hitbox, Image image) {
		super(xPos, yPos, hitbox, image);
	}

	public boolean isClicked(Input input) {
		boolean isClicked = false;
		if (hitbox.contains(input.getMouseX(), input.getMouseY())) {
			isClicked = true;
		} else {
			isClicked = false;
		}
		return isClicked;
	}

	public void render(Graphics g) {
		g.drawImage(image, xPos, yPos);

		Color transparent = new Color(0.5f, 0.2f, 0.2f, 0.3f);
		g.setColor(transparent);
		g.fill(hitbox);
	}

}
