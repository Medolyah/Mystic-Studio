package ui.classes;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

import player.classes.Player;

@SuppressWarnings("deprecation")
public class Overlay {

	private Player player;
	
	private Image background; 
	private Image testItem1;
	private Image testItem2;
	
	private Font textFont;
	private TrueTypeFont ttTextFont;
	private Color fontColor;

	private Rectangle maxLifeBar;
	private Rectangle currentLifeBar;

	private Rectangle maxEnergyBar;
	private Rectangle currentEnergyBar;

	private Rectangle reuqiredXPBar;
	private Rectangle currentXPBar;

	public Overlay(Player player) throws SlickException {
		this.player = player;
		background = new Image("res/images/Overlay-bar.png");
		textFont = new Font("AcmeFont", Font.PLAIN, 15);
		ttTextFont = new TrueTypeFont(textFont, true);
		fontColor =  new Color(0.75f, 0.75f, 0.75f);

		maxLifeBar = new Rectangle(175, 1040, 400, 10);
		float currentLifeFloat = (float) player.getCurrentLife() / (float) player.getMaxLife() * 400;
		int currentLife400 = (int) currentLifeFloat;
		currentLifeBar = new Rectangle(175, 1040, currentLife400, 10);

		maxEnergyBar = new Rectangle(175, 1052, 400, 10);
		float currentEnergyFloat = (float) player.getCurrentEnergy() / (float) player.getMaxEnergy() * 400;
		int currentEnergy400 = (int) currentEnergyFloat;
		currentEnergyBar = new Rectangle(175, 1052, currentEnergy400, 10);

		reuqiredXPBar = new Rectangle(175, 1064, 400, 10);
		float currentXPFloat = (float) player.getCurrentXP() / (float) player.getRequiredXP() * 400;
		int currentXP400 = (int) currentXPFloat;
		currentXPBar = new Rectangle(175, 1064, currentXP400, 10);
		
		// test item
		testItem1 = new Image("res/images/Healing_small.png");
		testItem2 = new Image("res/images/Mana_small.png");
	}

	public void update(GameContainer container, int delta) {
		float currentLifeFloat = (float) player.getCurrentLife() / (float) player.getMaxLife() * 400;
		int currentLife400 = (int) currentLifeFloat;
		currentLifeBar = new Rectangle(175, 1040, currentLife400, 10);

		float currentEnergyFloat = (float) player.getCurrentEnergy() / (float) player.getMaxEnergy() * 400;
		int currentEnergy400 = (int) currentEnergyFloat;
		currentEnergyBar = new Rectangle(175, 1052, currentEnergy400, 10);

		float currentXPFloat = (float) player.getCurrentXP() / (float) player.getRequiredXP() * 400;
		int currentXP400 = (int) currentXPFloat;
		currentXPBar = new Rectangle(175, 1064, currentXP400, 10);
	}

	public void render(GameContainer container, Graphics g) {
		
		g.drawImage(background, 103, 1002, new Color(0.75f, 0.75f, 0.75f, 0.9f));
		
		g.setColor(Color.darkGray);
		g.drawLine(175, 1038, 575, 1038);
		g.drawLine(175, 1039, 575, 1039);
		g.drawLine(175, 1050, 575, 1050);
		g.drawLine(175, 1051, 575, 1051);
		g.drawLine(175, 1062, 575, 1062);
		g.drawLine(175, 1063, 575, 1063);
		g.drawLine(175, 1074, 575, 1074);
		g.drawLine(175, 1075, 575, 1075);
		
		g.drawLine(173, 1038, 173, 1075);
		g.drawLine(174, 1038, 174, 1075);
		g.drawLine(574, 1038, 574, 1075);
		g.drawLine(575, 1038, 575, 1075);

		g.setColor(new Color(0.6f, 0.1f, 0.1f, 0.6f));
		g.fill(maxLifeBar);

		g.setColor(new Color(0.1f, 0.6f, 0.1f, 0.9f));
		g.fill(currentLifeBar);

		g.setColor(new Color(0.1f, 0.1f, 0.4f, 0.6f));
		g.fill(maxEnergyBar);

		g.setColor(new Color(0.1f, 0.1f, 0.7f, 0.9f));
		g.fill(currentEnergyBar);

		g.setColor(new Color(0.4f, 0.4f, 0.1f, 0.6f));
		g.fill(reuqiredXPBar);

		g.setColor(new Color(0.7f, 0.7f, 0.1f, 0.9f));
		g.fill(currentXPBar);
		
		g.setColor(Color.black);
		// slot Q
		g.drawRect(600, 1038, 37, 37);
		// slot W
		g.drawRect(650, 1038, 37, 37);
		// slot 1
		g.drawRect(750, 1038, 37, 37);
		// slot 2
		g.drawRect(800, 1038, 37, 37);
		// slot 3
		g.drawRect(850, 1038, 37, 37);
		// attack left mouse button
		g.drawRect(950, 1038, 37, 37);
		// attack right mouse button
		g.drawRect(1000, 1038, 37, 37);
		
		g.drawImage(testItem1, 600, 1038);
		g.drawImage(testItem2, 650, 1038);
		
		ttTextFont.drawString(627, 1062, "Q", fontColor);
		ttTextFont.drawString(672, 1062, "W", fontColor);

		ttTextFont.drawString(772, 1062, "1", fontColor);
		ttTextFont.drawString(822, 1062, "2", fontColor);
		ttTextFont.drawString(872, 1062, "3", fontColor);
		
		ttTextFont.drawString(955, 1062, "LMB", fontColor);
		ttTextFont.drawString(1005, 1062, "RMB", fontColor);
	}

}
