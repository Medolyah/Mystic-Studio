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
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import basic.classes.MysticStudioGame;

@SuppressWarnings("deprecation")
public class InventoryAndStats extends Menu {

	private MysticStudioGame game;
	
	private Font textFont;
	private TrueTypeFont ttTextFont;
	private Color fontColor;
	private Color valueColor;

	public InventoryAndStats(MysticStudioGame game) throws SlickException, FileNotFoundException {
		switch (game.getPlayer().getCharacterClass()) {
		case "warrior":
			backgroundImage = new Image("res/images/Inventory-Warrior.png");
			break;
		case "mage":
			backgroundImage = new Image("res/images/Inventory-Mage.png");
			break;
		case "ranger":
			backgroundImage = new Image("res/images/Inventory-Ranger.png");
			break;
		default:
			break;
		}
		backgroundImagePosX = 1420;
		backgroundImagePosY = 0;


		try {
			textFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/distantGalaxy.ttf")).deriveFont(18f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(textFont);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		ttTextFont = new TrueTypeFont(textFont, true);
		fontColor =  new Color(0.75f, 0.75f, 0.75f);
		valueColor =  new Color(0.75f, 0.25f, 0.25f);
		
		this.game = game;
	}

	public void update(GameContainer container, int delta) throws SlickException, IOException {
		

	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, backgroundImagePosX, backgroundImagePosY);
		
		g.setColor(Color.black);
		// equipment
		renderEquipment(container, g);

		// inventory
		renderInventory(container, g);
		
		// statistics
		renderStatistics(container, g);
		
	}
	
	private void renderEquipment(GameContainer container, Graphics g) {
		// helmet
		g.drawRect(1600, 70, 37, 37);
		// amulet
		g.drawRect(1650, 70, 37, 37);
		// body armor
		g.drawRect(1600, 120, 37, 37);
		// gloves
		g.drawRect(1650, 120, 37, 37);
		// left ring
		g.drawRect(1600, 170, 37, 37);
		// right ring
		g.drawRect(1650, 170, 37, 37);
		// pants
		g.drawRect(1600, 220, 37, 37);
		// boots
		g.drawRect(1650, 220, 37, 37);
		
	}
	
	private void renderInventory(GameContainer container, Graphics g) {
		// helmet
		g.drawRect(1750, 70, 37, 37);
		// amulet
		g.drawRect(1800, 70, 37, 37);
		// body armor
		g.drawRect(1750, 120, 37, 37);
		// gloves
		g.drawRect(1800, 120, 37, 37);
		// left ring
		g.drawRect(1750, 170, 37, 37);
		// right ring
		g.drawRect(1800, 170, 37, 37);
		// pants
		g.drawRect(1750, 220, 37, 37);
		// boots
		g.drawRect(1800, 220, 37, 37);
		
	}
	
	private void renderStatistics(GameContainer container, Graphics g) {
		
		// text labels
		ttTextFont.drawString(1620, 304, "Level:", fontColor);
		ttTextFont.drawString(1750, 304, "XP:", fontColor);
		ttTextFont.drawString(1450, 350, "Maximum Life:", fontColor);
		ttTextFont.drawString(1450, 375, "Current Life:", fontColor);
		ttTextFont.drawString(1450, 400, "Maximum Energy:", fontColor);
		ttTextFont.drawString(1450, 425, "Current Energy:", fontColor);
		ttTextFont.drawString(1450, 450, "Strengh:", fontColor);
		ttTextFont.drawString(1450, 475, "Intelligence:", fontColor);
		ttTextFont.drawString(1450, 500, "Dexterity:", fontColor);
		ttTextFont.drawString(1450, 525, "Physical Armour:", fontColor);
		ttTextFont.drawString(1450, 550, "Magical Armour:", fontColor);
		ttTextFont.drawString(1450, 575, "Physical Damage:", fontColor);
		ttTextFont.drawString(1450, 600, "Magical Damage:", fontColor);
		ttTextFont.drawString(1450, 625, "Attack Speed:", fontColor);
		
		// value labels
		ttTextFont.drawString(1690, 304, "" + game.getPlayer().getPlayerLevel(), valueColor);
		ttTextFont.drawString(1790, 304, "" + game.getPlayer().getCurrentXP() + "/" + game.getPlayer().getRequiredXP(), valueColor);
		ttTextFont.drawString(1650, 350, "" + game.getPlayer().getMaxLife(), valueColor);
		ttTextFont.drawString(1650, 375, "" + game.getPlayer().getCurrentLife(), valueColor);
		ttTextFont.drawString(1650, 400, "" + game.getPlayer().getMaxEnergy(), valueColor);
		ttTextFont.drawString(1650, 425, "" + game.getPlayer().getCurrentEnergy(), valueColor);
		ttTextFont.drawString(1650, 450, "" + game.getPlayer().getStrengh(), valueColor);
		ttTextFont.drawString(1650, 475, "" + game.getPlayer().getIntellegence(), valueColor);
		ttTextFont.drawString(1650, 500, "" + game.getPlayer().getDexterity(), valueColor);
		ttTextFont.drawString(1650, 525, "" + game.getPlayer().getPhysArmpur(), valueColor);
		ttTextFont.drawString(1650, 550, "" + game.getPlayer().getMagicArmour(), valueColor);
		ttTextFont.drawString(1650, 575, "" + game.getPlayer().getPhysDamage(), valueColor);
		ttTextFont.drawString(1650, 600, "" + game.getPlayer().getMagicDamage(), valueColor);
		ttTextFont.drawString(1650, 625, "" + game.getPlayer().getAttackSpeed(), valueColor);
		
	}
	
}
