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

import basic.classes.MysticButton;
import basic.classes.MysticStudioGame;
import basic.classes.SavingGame;

@SuppressWarnings("deprecation")
public class Shop extends Menu {

	private MysticStudioGame game;
	private SavingGame savingGame;

	private Font textFont;
	private TrueTypeFont ttTextFont;
	private Color fontColor;
	private Color valueColor;

	private Image weaponButtonImage;
	private Image helmetButtonImage;
	private Image bodyArmourButtonImage;
//	private Image pantsButtonImage;
	private Image bootsButtonImage;
	private Image glovesButtonImage;
	private Image leftRingButtonImage;
	private Image rightRingButtonImage;

	private MysticButton weaponButton;
	private MysticButton helmetButton;
	private MysticButton bodyArmourButton;
//	private MysticButton pantsButton;
	private MysticButton bootsButton;
	private MysticButton glovesButton;
	private MysticButton leftRingButton;
	private MysticButton rightRingButton;

	public Shop(MysticStudioGame game) throws SlickException, FileNotFoundException {
		this.game = game;
		this.savingGame = new SavingGame(game);

		backgroundImagePosX = 0;
		backgroundImagePosY = 0;
		backgroundImage = new Image("res/images/Shop-Menu.png");

		try {
			textFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/distantGalaxy.ttf")).deriveFont(18f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(textFont);

			weaponButtonImage = new Image("res/images/Wood-Sword-Shop.png");
			helmetButtonImage = new Image("res/images/Helmet-Shop.png");
			bodyArmourButtonImage = new Image("res/images/BodyArmour-Shop.png");
//			pantsButtonImage = new Image("res/images/Pants-Shop.png");
			bootsButtonImage = new Image("res/images/Boots-Shop.png");
			glovesButtonImage = new Image("res/images/Gloves-Shop.png");
			leftRingButtonImage = new Image("res/images/Ring1-Shop.png");
			rightRingButtonImage = new Image("res/images/Ring2-Shop.png");
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		ttTextFont = new TrueTypeFont(textFont, true);
		fontColor = new Color(0.75f, 0.75f, 0.75f);
		valueColor = new Color(0.75f, 0.25f, 0.25f);

		weaponButton = new MysticButton(100, 200, new Rectangle(100, 200, 50, 50), weaponButtonImage);
		helmetButton = new MysticButton(300, 200, new Rectangle(300, 200, 50, 50), helmetButtonImage);
		bodyArmourButton = new MysticButton(100, 300, new Rectangle(100, 300, 50, 50), bodyArmourButtonImage);
//		pantsButton = new MysticButton(300, 300, new Rectangle(300, 300, 50, 50), pantsButtonImage);
		bootsButton = new MysticButton(100, 400, new Rectangle(100, 400, 50, 50), bootsButtonImage);
		glovesButton = new MysticButton(300, 400, new Rectangle(300, 400, 50, 50), glovesButtonImage);
		leftRingButton = new MysticButton(100, 500, new Rectangle(100, 500, 50, 50), leftRingButtonImage);
		rightRingButton = new MysticButton(300, 500, new Rectangle(300, 500, 50, 50), rightRingButtonImage);
	}

	public void update(GameContainer container, int delta) throws SlickException, IOException {
		if (game.getPlayer() != null) {
			if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (weaponButton.isClicked(container.getInput())) {
					upgradeWeapon();
					savingGame.saveGame();
				}
				if (helmetButton.isClicked(container.getInput())) {
					buyUpgradeHelmet();
					savingGame.saveGame();
				}
				if (bodyArmourButton.isClicked(container.getInput())) {
					buyUpgradeBodyArmour();
					savingGame.saveGame();
				}
//				if (pantsButton.isClicked(container.getInput())) {
//					buyUpgradePants();
//					savingGame.saveGame();
//				}
				if (bootsButton.isClicked(container.getInput())) {
					buyUpgradeBoots();
					savingGame.saveGame();
				}
				if (glovesButton.isClicked(container.getInput())) {
					buyUpgradeGloves();
					savingGame.saveGame();
				}
				if (leftRingButton.isClicked(container.getInput())) {
					buyUpgradeLeftRing();
					savingGame.saveGame();
				}
				if (rightRingButton.isClicked(container.getInput())) {
					buyUpgradeRightRing();
					savingGame.saveGame();
				}
			}
		}

	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, backgroundImagePosX, backgroundImagePosY);

		if (game.getPlayer() != null) {
			weaponButton.render(container, g);
			if (game.getPlayer().getWeapon() == 0) {
				ttTextFont.drawString(155, 200, "Buy:", fontColor);
				ttTextFont.drawString(155, 220, "10", valueColor);
			} else {
				ttTextFont.drawString(155, 200, "Upgrade:", fontColor);
				ttTextFont.drawString(155, 220, "" + game.getPlayer().getWeapon() * 10, valueColor);
			}
			helmetButton.render(container, g);
			if (game.getPlayer().getHelmet() == 0) {
				ttTextFont.drawString(355, 200, "Buy:", fontColor);
				ttTextFont.drawString(355, 220, "10", valueColor);
			} else {
				ttTextFont.drawString(355, 200, "Upgrade:", fontColor);
				ttTextFont.drawString(355, 220, "" + game.getPlayer().getHelmet() * 10, valueColor);
			}
			bodyArmourButton.render(container, g);
			if (game.getPlayer().getBodyArmour() == 0) {
				ttTextFont.drawString(155, 300, "Buy:", fontColor);
				ttTextFont.drawString(155, 320, "10", valueColor);
			} else {
				ttTextFont.drawString(155, 300, "Upgrade:", fontColor);
				ttTextFont.drawString(155, 320, "" + game.getPlayer().getBodyArmour() * 10, valueColor);
			}
//				pantsButton.render(container, g);
			bootsButton.render(container, g);
			if (game.getPlayer().getBoots() == 0) {
				ttTextFont.drawString(155, 400, "Buy:", fontColor);
				ttTextFont.drawString(155, 420, "10", valueColor);
			} else {
				ttTextFont.drawString(155, 400, "Upgrade:", fontColor);
				ttTextFont.drawString(155, 420, "" + game.getPlayer().getBoots() * 10, valueColor);
			}
			glovesButton.render(container, g);
			if (game.getPlayer().getGloves() == 0) {
				ttTextFont.drawString(355, 400, "Buy:", fontColor);
				ttTextFont.drawString(355, 420, "10", valueColor);
			} else {
				ttTextFont.drawString(355, 400, "Upgrade:", fontColor);
				ttTextFont.drawString(355, 420, "" + game.getPlayer().getGloves() * 10, valueColor);
			}
			leftRingButton.render(container, g);
			if (game.getPlayer().getLeftRing() == 0) {
				ttTextFont.drawString(155, 500, "Buy:", fontColor);
				ttTextFont.drawString(155, 520, "10", valueColor);
			} else {
				ttTextFont.drawString(155, 500, "Upgrade:", fontColor);
				ttTextFont.drawString(155, 520, "" + game.getPlayer().getLeftRing() * 10, valueColor);
			}
			rightRingButton.render(container, g);
			if (game.getPlayer().getRightRing() == 0) {
				ttTextFont.drawString(355, 500, "Buy:", fontColor);
				ttTextFont.drawString(355, 520, "10", valueColor);
			} else {
				ttTextFont.drawString(355, 500, "Upgrade:", fontColor);
				ttTextFont.drawString(355, 520, "" + game.getPlayer().getRightRing() * 10, valueColor);
			}
			ttTextFont.drawString(100, 255, "+DMG", fontColor);
			ttTextFont.drawString(300, 255, "+HP +PA +MA", fontColor);
			ttTextFont.drawString(100, 355, "+HP +PA + MA", fontColor);
			// insert pants here
			ttTextFont.drawString(100, 455, "+HP +AS", fontColor);
			ttTextFont.drawString(300, 455, "+HP +AS", fontColor);
			ttTextFont.drawString(100, 555, "+MP +PA +MA", fontColor);
			ttTextFont.drawString(300, 555, "+MP +PA +MA", fontColor);			
		}

	}

	private void upgradeWeapon() {
		game.getPlayer().setWeapon();
	}

	private void buyUpgradeHelmet() {
		game.getPlayer().setHelmet();
	}

	private void buyUpgradeBodyArmour() {
		game.getPlayer().setBodyArmour();
	}

//	private void buyUpgradePants() {
//		game.getPlayer().setPants();
//	}

	private void buyUpgradeBoots() {
		game.getPlayer().setBoots();
	}

	private void buyUpgradeGloves() {
		game.getPlayer().setGloves();
	}

	private void buyUpgradeLeftRing() {
		game.getPlayer().setLeftRing();
	}

	private void buyUpgradeRightRing() {
		game.getPlayer().setRightRing();
	}
}
