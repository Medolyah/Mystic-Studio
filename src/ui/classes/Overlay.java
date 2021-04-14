package ui.classes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import player.classes.Player;

public class Overlay {

	private Player player;

	private Rectangle maxLifeBar;
	private Rectangle currentLifeBar;

	private Rectangle maxEnergyBar;
	private Rectangle currentEnergyBar;

	public Overlay(Player player) {
		this.player = player;

		maxLifeBar = new Rectangle(200, 1000, 400, 20);
		float currentLifeFloat = (float) player.getCurrentLife() / (float) player.getMaxLife() * 400;
		int currentLife400 = (int) currentLifeFloat;
		currentLifeBar = new Rectangle(200, 1000, currentLife400, 20);

		maxEnergyBar = new Rectangle(700, 1000, 400, 20);
		float currentEnergyFloat = (float) player.getCurrentEnergy() / (float) player.getMaxEnergy() * 400;
		int currentEnergy400 = (int) currentEnergyFloat;
		currentEnergyBar = new Rectangle(700, 1000, currentEnergy400, 20);
	}

	public void update(GameContainer container, int delta) {
		float currentLifeFloat = (float) player.getCurrentLife() / (float) player.getMaxLife() * 400;
		int currentLife400 = (int) currentLifeFloat;
		currentLifeBar = new Rectangle(200, 1000, currentLife400, 20);

		float currentEnergyFloat = (float) player.getCurrentEnergy() / (float) player.getMaxEnergy() * 400;
		int currentEnergy400 = (int) currentEnergyFloat;
		currentEnergyBar = new Rectangle(700, 1000, currentEnergy400, 20);
	}

	public void render(GameContainer container, Graphics g) {

		g.setColor(new Color(0.6f, 0.1f, 0.1f, 0.6f));
		g.fill(maxLifeBar);

		g.setColor(new Color(0.1f, 0.6f, 0.1f, 0.9f));
		g.fill(currentLifeBar);

		g.setColor(new Color(0.1f, 0.1f, 0.4f, 0.6f));
		g.fill(maxEnergyBar);

		g.setColor(new Color(0.1f, 0.1f, 0.7f, 0.9f));
		g.fill(currentEnergyBar);
	}

}
