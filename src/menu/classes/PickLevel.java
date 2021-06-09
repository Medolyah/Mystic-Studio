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
import level.classes.Level;
import level.classes.TheBasementLevel;
import player.classes.Player;
import ui.classes.Overlay;

public class PickLevel extends Menu {

	private MysticButton continueButton;
	private MysticButton levelOneButton;
	private MysticButton exitButton;

	private Options options;
	private boolean optionsMenu = false;

	private boolean exit = false;

	private MysticStudioGame game;

	public PickLevel(MysticStudioGame game) throws SlickException, FileNotFoundException {
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
			levelOneButton.render(container, g);
			exitButton.render(container, g);
		}
	}

	private void menuButtons() throws SlickException, FileNotFoundException {

		Image continueButtonImage = new Image("res/images/Continue_Button.png");
		Shape continueButtonShape = new Rectangle(760, 500, 400, 61);
		continueButton = new MysticButton(760, 500, continueButtonShape, continueButtonImage);

		Image levelOneButtonImage = new Image("res/images/Level1.png");
		Shape levelOneButtonShape = new Rectangle(760, 600, 400, 61);
		levelOneButton = new MysticButton(760, 600, levelOneButtonShape, levelOneButtonImage);

		Image exitButtonImage = new Image("res/images/Exit_Button.png");
		Shape exitButtonShape = new Rectangle(760, 700, 400, 61);
		exitButton = new MysticButton(760, 700, exitButtonShape, exitButtonImage);

	}

	private void checkClick(GameContainer container) throws SlickException, IOException {

		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (continueButton.isClicked(container.getInput())) {
				continueGame(container);
			} else if (levelOneButton.isClicked(container.getInput())) {
				levelOne();
			} else if (exitButton.isClicked(container.getInput())) {
				exitGame();
			}
		}
	}

	private void continueGame(GameContainer container) throws FileNotFoundException, SlickException {
		game.setPickLevel(true);
	}

	private void levelOne() throws SlickException, IOException {

		// set level
		Level level = new TheBasementLevel(game);
		game.setLevel(level, 200, 760);

		Player player = game.getPlayer();
		game.setOverlay(new Overlay(player));

		// unset main menu
		game.setMainMenu(true);

	}

	private void exitGame() throws FileNotFoundException, SlickException {
		exit = true;
		game.setLevel(true);
		game.setPlayer(true);
		game.setOverlay(true);
		game.setPickLevel(true);
	}

	public boolean getExit() {
		return exit;
	}

}
