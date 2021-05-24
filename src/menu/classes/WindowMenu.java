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

import basic.classes.MysticButton;
import basic.classes.MysticStudioGame;

public class WindowMenu extends Menu {

	private MysticButton continueButton;
	private MysticButton optionsButton;
	private MysticButton exitButton;

	private Options options;
	private boolean optionsMenu = false;

	private boolean exit = false;

	private MysticStudioGame game;

	public WindowMenu(MysticStudioGame game) throws SlickException, FileNotFoundException {
		backgroundImage = new Image("res/images/Window-Menu.png");
		backgroundImagePosX = 710;
		backgroundImagePosY = 240;
		menuButtons();
		this.game = game;
	}

	public void update(GameContainer container, int delta) throws SlickException, IOException {
		if (options != null) {
			optionsMenu = options.getActive();
		}
		if (optionsMenu) {
			options.update(container, delta);
		} else {
			options = null;
			System.gc();
			checkClick(container);
		}

	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		if (options != null) {
			options.render(container, g);
		} else {
			g.drawImage(backgroundImage, backgroundImagePosX, backgroundImagePosY);
			continueButton.render(container, g);
			optionsButton.render(container, g);
			exitButton.render(container, g);
		}
	}

	private void menuButtons() throws SlickException, FileNotFoundException {

		Image continueButtonImage = new Image("res/images/Continue_Button.png");
		Shape continueButtonShape = new Rectangle(760, 500, 400, 61);
		continueButton = new MysticButton(760, 500, continueButtonShape, continueButtonImage);

		Image optionButtonImage = new Image("res/images/Options_Button.png");
		Shape optionButtonShape = new Rectangle(760, 600, 400, 61);
		optionsButton = new MysticButton(760, 600, optionButtonShape, optionButtonImage);

		Image exitButtonImage = new Image("res/images/Exit_Button.png");
		Shape exitButtonShape = new Rectangle(760, 700, 400, 61);
		exitButton = new MysticButton(760, 700, exitButtonShape, exitButtonImage);

	}

	private void checkClick(GameContainer container) throws SlickException, IOException {

		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (continueButton.isClicked(container.getInput())) {
				continueGame(container);
			} else if (optionsButton.isClicked(container.getInput())) {
				gameOptions();
			} else if (exitButton.isClicked(container.getInput())) {
				exitGame();
			}
		}
	}

	private void continueGame(GameContainer container) throws FileNotFoundException, SlickException {
		game.setWindowMenu(true);
	}

	private void gameOptions() throws SlickException, IOException {
		optionsMenu = true;
		options = new Options(game);
	}

	private void exitGame() throws FileNotFoundException, SlickException {
		exit = true;
		game.setLevel(true);
		game.setPlayer(true);
		game.setOverlay(true);
		game.setWindowMenu(true);
	}

	public boolean getExit() {
		return exit;
	}

}
