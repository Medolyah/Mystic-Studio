package basic.classes;

import java.io.FileNotFoundException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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

	public void render(Graphics g) {
		g.drawImage(image, xPos, yPos);

		Color transparent = new Color(0.5f, 0.2f, 0.2f, 0.0f);
		g.setColor(transparent);
		g.fill(hitbox);
	}

}
