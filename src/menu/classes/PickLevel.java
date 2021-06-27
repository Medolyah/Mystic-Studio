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
import level.classes.CaveLevel;
import level.classes.Level;
import level.classes.TheBasementLevel;
import player.classes.Player;
import ui.classes.Overlay;

public class PickLevel extends Menu {

	private MysticButton continueButton;
	private MysticButton levelOneButton;
	private MysticButton levelTwoButton;

	private boolean exit = false;

	private MysticStudioGame game;

	public PickLevel(MysticStudioGame game) throws SlickException, FileNotFoundException {
		backgroundImage = new Image("res/images/Window-Menu.png");
		backgroundImagePosX = 710;
		backgroundImagePosY = 240;
		this.game = game;
		menuButtons();
	}

	public void update(GameContainer container, int delta) throws SlickException, IOException {

		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (continueButton.isClicked(container.getInput())) {
				continueGame(container);
			} else if (levelOneButton.isClicked(container.getInput())) {
				levelOne();
			} else if (levelTwoButton != null)
				if (levelTwoButton.isClicked(container.getInput()) && game.getPlayer().getGameProgress() >= 2) {
					levelTwo();
				}
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, backgroundImagePosX, backgroundImagePosY);
		continueButton.render(container, g);
		levelOneButton.render(container, g);
		if (game.getPlayer().getGameProgress() >= 2) {
			levelTwoButton.render(container, g);
		}
	}

	private void menuButtons() throws SlickException, FileNotFoundException {

		Image continueButtonImage = new Image("res/images/Continue_Button.png");
		Shape continueButtonShape = new Rectangle(760, 500, 400, 61);
		continueButton = new MysticButton(760, 500, continueButtonShape, continueButtonImage);

		Image levelOneButtonImage = new Image("res/images/Level1.png");
		Shape levelOneButtonShape = new Rectangle(760, 600, 400, 61);
		levelOneButton = new MysticButton(760, 600, levelOneButtonShape, levelOneButtonImage);

		if (game.getPlayer().getGameProgress() >= 2) {
			Image levelTwoButtonImage = new Image("res/images/Level1.png");
			Shape levelTwoButtonShape = new Rectangle(760, 700, 400, 61);
			levelTwoButton = new MysticButton(760, 700, levelTwoButtonShape, levelTwoButtonImage);
		}

	}

	private void continueGame(GameContainer container) throws FileNotFoundException, SlickException {
		game.setPickLevel(true);
	}

	private void levelOne() throws SlickException, IOException {

		game.setLevel(true);
		game.setPlayer(true);
		game.setOverlay(true);
		game.setPickLevel(true);

		// set level
		Level level = new TheBasementLevel(game);
		game.setLevel(level, 200, 760);

		Player player = game.getPlayer();
		game.setOverlay(new Overlay(player));

	}

	private void levelTwo() throws SlickException, IOException {

		game.setLevel(true);
		game.setPlayer(true);
		game.setOverlay(true);
		game.setPickLevel(true);

		// set level
		Level level = new CaveLevel(game);
		game.setLevel(level, 400, 660);

		Player player = game.getPlayer();
		game.setOverlay(new Overlay(player));

	}

	public boolean getExit() {
		return exit;
	}

}
