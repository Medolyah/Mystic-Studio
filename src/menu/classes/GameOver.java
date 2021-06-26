package menu.classes;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import audio.classes.GameSound;
import basic.classes.MysticButton;
import basic.classes.MysticStudioGame;

public class GameOver extends Menu {

	private MysticButton exitButton;

	private boolean exit = false;

	public GameOver(MysticStudioGame game) throws SlickException, FileNotFoundException {
		backgroundImage = new Image("res/images/Game-Over.png");
		backgroundImagePosX = 710;
		backgroundImagePosY = 240;
		GameSound attackSound = new GameSound("res/sounds/gameOver.wav");
		attackSound.playSound();
		menuButtons();
	}

	public void update(GameContainer container, int delta) throws SlickException, IOException {
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (exitButton.isClicked(container.getInput())) {
				exitGame();
			}
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, backgroundImagePosX, backgroundImagePosY);
		exitButton.render(container, g);
	}

	private void menuButtons() throws SlickException, FileNotFoundException {

		Image exitButtonImage = new Image("res/images/Exit_Button.png");
		Shape exitButtonShape = new Rectangle(760, 700, 400, 61);
		exitButton = new MysticButton(760, 700, exitButtonShape, exitButtonImage);

	}

	private void exitGame() throws FileNotFoundException, SlickException {
		exit = true;
	}

	public boolean getExit() {
		return exit;
	}

}
