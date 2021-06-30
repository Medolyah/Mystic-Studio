package menu.classes;

import java.io.FileNotFoundException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import basic.classes.MysticStudioGame;

/**
 * The intro is the first thing that is shown in the game. Players can skip it
 * by pressing the ESC key. After the intro the player will be in the main menu.
 * 
 * @author Stephan Manstein
 *
 */
public class Intro {

	private Image introImage;
	private int introImagePosX = 0;
	private int introImagePosY = 1080;
	private int waitForIntro = 0;
	private MysticStudioGame game;

	/**
	 * When creating the intro, load the intro image.
	 * 
	 * @throws SlickException if the image could not be loaded.
	 */
	public Intro(MysticStudioGame game) throws SlickException {
		introImage = new Image("res/images/Intro_Skizze_FS.png");
		this.game = game;
	}

	/**
	 * The render method of the Intro class only draws the current image at the
	 * current position.
	 * 
	 * @param container is the display of the game.
	 * @param g         is the graphics context that can be used to render.
	 * @throws SlickException if display could not be rendered.
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(introImage, introImagePosX, introImagePosY);
		if (waitForIntro > 2000) {
			g.drawString("Press ESC to skip.", container.getWidth() / 2, 900);
		}

	}

	/**
	 * The update method updates the images and their position. Players can skip the
	 * intro by pressing the ESC key.
	 * 
	 * @param container is the display of the game.
	 * @param delta     is the time that expired since the last update (in
	 *                  milliseconds).
	 * @return a boolean to let the calling program know if the intro has finished
	 *         playing or was skipped.
	 * @throws SlickException if the update went wrong
	 * @throws FileNotFoundException 
	 */
	public void update(GameContainer container, int delta) throws SlickException, FileNotFoundException {
		/*
		 * Check if the intro was skipped
		 */
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			introImagePosY = 0;
			waitForIntro = 8000;
		}
		/*
		 * Make the first intro image slide in from bottom to top
		 */
		if (introImagePosY > 0) {
			introImagePosY -= 3;
		} else {
			/*
			 * If intro has finished playing or was skipped set the bool to true
			 */
			if (waitForIntro > 8000) {
				// show main menu
				game.setMainMenu(new MainMenu(game));
				game.setIntro(true);
				/*
				 * If the first intro image has finished playing show the title image
				 */
			} else if (waitForIntro > 2000) {
				waitForIntro += delta;
				introImage = new Image("res/images/Main_Menu.png");
				/*
				 * Otherwise just count the time
				 */
			} else {
				waitForIntro += delta;
			}
		}
	}
}
