package basic.classes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;

public class MysticButton extends GraphicObject {

	private Sound clickSound;
	
	public MysticButton() {
		super();
	}

	public MysticButton(int xPos, int yPos, Shape hitbox, Image image) throws SlickException {
		super(xPos, yPos, hitbox, image);
		clickSound = new Sound ("res/sounds/click.wav");
	}

	public boolean isClicked(Input input) {
		boolean isClicked = false;
		if (hitbox.contains(input.getMouseX(), input.getMouseY())) {
			isClicked = true;
			clickSound.play(1, 0.25f);
		} else {
			isClicked = false;
		}
		return isClicked;
	}

	public void render(Graphics g) {
		g.drawImage(image, xPos, yPos);

		Color transparent = new Color(0.5f, 0.2f, 0.2f, 0.0f);
		g.setColor(transparent);
		g.fill(hitbox);
	}

}
