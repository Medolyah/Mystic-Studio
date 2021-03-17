package menu.classes;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import basic.classes.MysticButton;

public class MainMenu extends Menu {

	private MysticButton newButton;
	private MysticButton loadButton;
	private MysticButton optionsButton;
	private MysticButton creditsButton;
	private MysticButton exitButton;

	private Options options;
	private boolean optionsMenu = false;

	private Credits credits;
	private boolean creditsMenu = false;

	public MainMenu() throws SlickException {
		backgroundImage = new Image("res/images/Titelbild_ohne_Menu_FS.png");

		menuButtons();
	}

	public void update(GameContainer container, int delta) throws SlickException, IOException {
		if (options != null) {
			optionsMenu = options.getActive();
		}
		if (credits != null) {
			creditsMenu = credits.getActive();
		}
		if (optionsMenu) {
			options.update(container, delta);
		} else if (creditsMenu) {			
			credits.update(container, delta);
		} else {
			options = null;
			credits = null;
			System.gc();
			checkClick(container);
		}

	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, backgroundImagePosX, backgroundImagePosY);

		if (!optionsMenu && !creditsMenu) {
			newButton.render(g);
			loadButton.render(g);
			optionsButton.render(g);
			creditsButton.render(g);
			exitButton.render(g);
		} else if (optionsMenu){
			options.render(container, g);
		} else if (creditsMenu) {
			credits.render(container, g);
		}
	}

	private void menuButtons() throws SlickException {

		Image newButtonImage = new Image("res/images/New_Button.png");
		Shape newButtonShape = new Rectangle(760, 625, 400, 61);
		newButton = new MysticButton(760, 625, newButtonShape, newButtonImage);

		Image loadButtonImage = new Image("res/images/Load_Button.png");
		Shape loadButtonShape = new Rectangle(760, 700, 400, 61);
		loadButton = new MysticButton(760, 700, loadButtonShape, loadButtonImage);

		Image optionButtonImage = new Image("res/images/Options_Button.png");
		Shape optionButtonShape = new Rectangle(760, 775, 400, 61);
		optionsButton = new MysticButton(760, 775, optionButtonShape, optionButtonImage);

		Image creditsButtonImage = new Image("res/images/Credits_Button.png");
		Shape creditsButtonShape = new Rectangle(760, 850, 400, 61);
		creditsButton = new MysticButton(760, 850, creditsButtonShape, creditsButtonImage);

		Image exitButtonImage = new Image("res/images/Exit_Button.png");
		Shape exitButtonShape = new Rectangle(760, 925, 400, 61);
		exitButton = new MysticButton(760, 925, exitButtonShape, exitButtonImage);

	}

	private void checkClick(GameContainer container) throws SlickException, IOException {

		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (newButton.isClicked(container.getInput())) {
				newGame();
			} else if (loadButton.isClicked(container.getInput())) {
				loadGame();
			} else if (optionsButton.isClicked(container.getInput())) {
				gameOptions();
			} else if (creditsButton.isClicked(container.getInput())) {
				gameCredits();
			} else if (exitButton.isClicked(container.getInput())) {
				container.exit();
			}
		}
	}

	private void newGame() {
		System.out.println("New Game");
	}

	private void loadGame() {
		System.out.println("Load Game");
	}

	private void gameOptions() throws SlickException, IOException {
		optionsMenu = true;
		options = new Options();
	}

	private void gameCredits() throws SlickException, IOException {
		creditsMenu = true;
		credits = new Credits();
	}
}