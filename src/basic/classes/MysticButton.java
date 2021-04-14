package basic.classes;

import java.io.FileNotFoundException;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import audio.classes.GameSound;

public class MysticButton extends GraphicObject {

	private GameSound clickSound;
	
	public MysticButton(int xPos, int yPos, Shape hitbox, Image image) throws SlickException, FileNotFoundException {
		super(xPos, yPos, hitbox, image);
		clickSound = new GameSound("res/sounds/click.wav");
	}

	public boolean isClicked(Input input) throws FileNotFoundException {
		boolean isClicked = false;
		if (hitbox.contains(input.getMouseX(), input.getMouseY())) {
			isClicked = true;
			clickSound.playSound();
		} else {
			isClicked = false;
		}
		return isClicked;
	}
}
