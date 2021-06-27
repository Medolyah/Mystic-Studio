package menu.classes;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
import player.classes.PlayerStats;

@SuppressWarnings("deprecation")
public class CharacterSelection extends Menu {

	private MysticStudioGame game;
	private boolean active = false;
	private File saveFile;
	private int saveGameSlot;

	private Font titleFont;
	private TrueTypeFont ttTitleFont;
	private Font textFont;
	private TrueTypeFont ttTextFont;

	private MysticButton knightButton;
	private MysticButton mageButton;
	private MysticButton rangerButton;

//	private PlayerStats playerStats;

	public CharacterSelection(MysticStudioGame game, int saveGameSlot) throws SlickException, FileNotFoundException {
		this.game = game;
		this.saveGameSlot = saveGameSlot;
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
				initializeSaveFile("warrior");
				saveFile = new File("res/savegames/save1.txt");
				game.getMainMenu().runGame(saveFile);
			} else if (mageButton.isClicked(container.getInput())) {
				initializeSaveFile("mage");
				saveFile = new File("res/savegames/save2.txt");
				game.getMainMenu().runGame(saveFile);
			} else if (rangerButton.isClicked(container.getInput())) {
				initializeSaveFile("ranger");
				saveFile = new File("res/savegames/save3.txt");
				game.getMainMenu().runGame(saveFile);
			}
		}
	}

	private void initializeSaveFile(String characterClass) throws FileNotFoundException {
		PlayerStats playerStats = new PlayerStats(characterClass);

		try {
			switch (saveGameSlot) {
			case 1:
				FileOutputStream fileOut1 = new FileOutputStream("res/savegames/save1.txt");
				ObjectOutputStream objectOut1 = new ObjectOutputStream(fileOut1);
				objectOut1.writeObject(playerStats);
				break;
				
			case 2:
				FileOutputStream fileOut2 = new FileOutputStream("res/savegames/save2.txt");
				ObjectOutputStream objectOut2 = new ObjectOutputStream(fileOut2);
				objectOut2.writeObject(playerStats);
				break;
				
			case 3:
				FileOutputStream fileOut3 = new FileOutputStream("res/savegames/save3.txt");
				ObjectOutputStream objectOut3 = new ObjectOutputStream(fileOut3);
				objectOut3.writeObject(playerStats);
				break;
				
			default:
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean getActive() {
		return active;
	}

}
