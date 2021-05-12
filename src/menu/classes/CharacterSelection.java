package menu.classes;

import java.awt.Font;
import java.io.FileNotFoundException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import basic.classes.MysticButton;
import basic.classes.MysticStudioGame;

@SuppressWarnings("deprecation")
public class CharacterSelection extends Menu {
	
	private MysticStudioGame game;
	private boolean active = false;

	private Font titleFont;
	private TrueTypeFont ttTitleFont;
	private Font textFont;
	private TrueTypeFont ttTextFont;

	private MysticButton warriorButton;
	private MysticButton mageButton;
	private MysticButton rangerButton;
	
	public CharacterSelection(MysticStudioGame game) throws SlickException, FileNotFoundException {
		this.game = game;
		this.active = true;
		backgroundImage = new Image("res/images/Titelbild_ohne_Menu_FS.png");

		titleFont = new Font("Distant Galaxy", Font.PLAIN, 50);
		ttTitleFont = new TrueTypeFont(titleFont, true);
		textFont = new Font("Distant Galaxy", Font.PLAIN, 25);
		ttTextFont = new TrueTypeFont(textFont, true);

		Image warriorImage = new Image("res/images/Confirm_Button.png");
		Shape warriorShape = new Rectangle(790, 700, 400, 61);
		warriorButton = new MysticButton(790, 700, warriorShape, warriorImage);

		Image mageImage = new Image("res/images/Confirm_Button.png");
		Shape mageShape = new Rectangle(790, 800, 400, 61);
		mageButton = new MysticButton(790, 800, mageShape, mageImage);

		Image rangerImage = new Image("res/images/Confirm_Button.png");
		Shape rangerShape = new Rectangle(790, 900, 400, 61);
		rangerButton = new MysticButton(790, 900, rangerShape, rangerImage);
	}

	public void update(GameContainer container, int delta) throws SlickException, FileNotFoundException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			active = false;
		} else {	
			checkClicked(container);
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		if (active) {
			g.drawImage(backgroundImage, backgroundImagePosX, backgroundImagePosY);

			Color textColor = new Color(0.1f, 0.1f, 0.1f);
			ttTitleFont.drawString(720, 600, "SELECT CHARACTER", textColor);

			warriorButton.render(container, g);
			mageButton.render(container, g);
			rangerButton.render(container, g);

			ttTextFont.drawString(850, 1000, "Press ESC to go back", textColor);
		}

	}

	private void checkClicked(GameContainer container) throws FileNotFoundException, SlickException {
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (warriorButton.isClicked(container.getInput())) {
				
			} else if (mageButton.isClicked(container.getInput())) {

			} else if (rangerButton.isClicked(container.getInput())) {

			}
			game.getMainMenu().runGame(0);
		}
	}

	public boolean getActive() {
		return active;
	}

}
