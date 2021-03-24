package basic.classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;

public class MysticButton extends GraphicObject {

	private Sound clickSound;
	
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
}
