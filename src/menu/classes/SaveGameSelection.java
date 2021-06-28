package menu.classes;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
public class SaveGameSelection extends Menu {

	private MysticStudioGame game;
	private boolean loadGame = false;
	private boolean active = false;

	private File saveFile;

	private Font titleFont;
	private TrueTypeFont ttTitleFont;
	private Font textFont;
	private TrueTypeFont ttTextFont;

	private MysticButton saveGame1Button;
	private MysticButton saveGame2Button;
	private MysticButton saveGame3Button;

	private CharacterSelection characterSelection;
	private boolean characterSelectionMenu = false;

	public SaveGameSelection(MysticStudioGame game, boolean loadGame) throws SlickException, FileNotFoundException {
		this.game = game;
		this.loadGame = loadGame;
		active = true;
		backgroundImage = new Image("res/images/Main_Menu.png");

		try {
			textFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/distantGalaxy.ttf")).deriveFont(25f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(textFont);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		ttTextFont = new TrueTypeFont(textFont, true);

		try {
			titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/distantGalaxy.ttf")).deriveFont(50f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(titleFont);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		ttTitleFont = new TrueTypeFont(titleFont, true);

		Image save1Image = new Image("res/images/Confirm_Button.png");
		Shape save1Shape = new Rectangle(790, 700, 400, 61);
		saveGame1Button = new MysticButton(790, 700, save1Shape, save1Image);

		Image save2Image = new Image("res/images/Confirm_Button.png");
		Shape save2Shape = new Rectangle(790, 800, 400, 61);
		saveGame2Button = new MysticButton(790, 800, save2Shape, save2Image);

		Image save3Image = new Image("res/images/Confirm_Button.png");
		Shape save3Shape = new Rectangle(790, 900, 400, 61);
		saveGame3Button = new MysticButton(790, 900, save3Shape, save3Image);

	}

	public void update(GameContainer container, int delta) throws SlickException, FileNotFoundException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			active = false;
		} else {
			if (characterSelection != null) {
				characterSelectionMenu = characterSelection.getActive();
			}
			if (characterSelectionMenu) {
				characterSelection.update(container, delta);
			} else {
				checkClicked(container);
			}
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		if (active) {

			if (characterSelectionMenu) {
				characterSelection.render(container, g);
			} else {

				g.drawImage(backgroundImage, backgroundImagePosX, backgroundImagePosY);

				Color textColor = new Color(0.1f, 0.1f, 0.1f);
				ttTitleFont.drawString(750, 600, "SELECT SAVEGAME", textColor);

				saveGame1Button.render(container, g);
				saveGame2Button.render(container, g);
				saveGame3Button.render(container, g);

				ttTextFont.drawString(850, 1000, "Press ESC to go back", textColor);
			}
		}

	}

	private void checkClicked(GameContainer container) throws FileNotFoundException, SlickException {
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (saveGame1Button.isClicked(container.getInput())) {
				if (loadGame) {
					loadGame(1);
				} else {
					newGame(1);
				}
			} else if (saveGame2Button.isClicked(container.getInput())) {

				if (loadGame) {
					loadGame(2);
				} else {
					newGame(2);
				}
			} else if (saveGame3Button.isClicked(container.getInput())) {

				if (loadGame) {
					loadGame(3);
				} else {
					newGame(3);
				}
			}
		}
	}

	private void loadGame(int saveGameSlot) throws FileNotFoundException, SlickException {
		switch (saveGameSlot) {
		case 1:
			saveFile = new File("res/savegames/save1.txt");
			game.setSaveGameNumber(1);
			break;
		case 2:
			saveFile = new File("res/savegames/save2.txt");
			game.setSaveGameNumber(2);
			break;
		case 3:
			saveFile = new File("res/savegames/save3.txt");
			game.setSaveGameNumber(3);
			break;
		default:
			break;
		}
		game.getMainMenu().runGame(saveFile);
	}

	private void newGame(int saveGameSlot) throws SlickException, FileNotFoundException {
		characterSelection = new CharacterSelection(game, saveGameSlot);
		characterSelectionMenu = true;

	}

	public boolean getActive() {
		return this.active;
	}

}
