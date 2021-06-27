package menu.classes;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

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
	private File saveFile;

	private Font titleFont;
	private TrueTypeFont ttTitleFont;
	private Font textFont;
	private TrueTypeFont ttTextFont;

	private MysticButton knightButton;
	private MysticButton mageButton;
	private MysticButton rangerButton;

//	private PlayerStats playerStats;

	public CharacterSelection(MysticStudioGame game) throws SlickException, FileNotFoundException {
		this.game = game;
		this.active = true;
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

		Image knightImage = new Image("res/images/Knight_Profile.png");
		Shape knightShape = new Rectangle(700, 700, 150, 250);
		knightButton = new MysticButton(700, 700, knightShape, knightImage);

		Image mageImage = new Image("res/images/Mage_Profile.png");
		Shape mageShape = new Rectangle(900, 700, 150, 250);
		mageButton = new MysticButton(900, 700, mageShape, mageImage);

		Image rangerImage = new Image("res/images/Knight_Profile.png");
		Shape rangerShape = new Rectangle(1100, 700, 150, 250);
		rangerButton = new MysticButton(1100, 700, rangerShape, rangerImage);
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
			ttTitleFont.drawString(690, 600, "SELECT A CHARACTER", textColor);

			knightButton.render(container, g);
			mageButton.render(container, g);
			rangerButton.render(container, g);

			ttTextFont.drawString(850, 1000, "Press ESC to go back", textColor);
		}

	}

	private void checkClicked(GameContainer container) throws FileNotFoundException, SlickException {
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (knightButton.isClicked(container.getInput())) {
				saveFile = new File("res/savegames/save1.txt");
				initializeSaveFile(saveFile, "warrior");
				game.getMainMenu().runGame(0, saveFile);
			} else if (mageButton.isClicked(container.getInput())) {
				saveFile = new File("res/savegames/save2.txt");
				initializeSaveFile(saveFile, "mage");
				game.getMainMenu().runGame(0, saveFile);
			} else if (rangerButton.isClicked(container.getInput())) {
				saveFile = new File("res/savegames/save3.txt");
				initializeSaveFile(saveFile, "ranger");
				game.getMainMenu().runGame(99, saveFile);
			}
		}
	}

	private File initializeSaveFile(File saveFile, String charackterClass) throws FileNotFoundException {

//		Formatter formatter = new Formatter(saveFile);
//		switch (charackterClass) {
//		case "warrior":
//			formatter.format(
//					// lines 1-10
//					"%d%d%d%d%d%d%d%d%d%d"
//					// lines 10 - 20
//					+ "%d%d%d%d%s%d"
//					//+ "%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
//					// lines 1 - 10
//					,0 , 1, 0, 100, 100, 50, 20, 10, 10, 10,
//					// lines 11 -20
//					10, 20, 0, 1, "warrior", 0);
//			break;
//			
//		case "mage":
//			
//			break;
//			
//		case "ranger":
//			
//			break;
//
//		default:
//			break;
//		}
//		formatter.close();

		return saveFile;
	}

	public boolean getActive() {
		return active;
	}

}
