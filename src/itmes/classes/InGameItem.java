package itmes.classes;

import java.io.FileNotFoundException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import basic.classes.MysticButton;

public abstract class InGameItem {
	private String itemName;
	private MysticButton itemIcon;
	private int[] itemValues;
	
	public InGameItem(String itemName, MysticButton itemIcon, int[] itemValues) {
		this.itemName = itemName;
		this.itemIcon = itemIcon;
		this.itemValues = itemValues;
	}

	public void update(GameContainer container, int delta) {
		try {
			itemIcon.update(container, delta);
		} catch (SlickException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	public void render(GameContainer container, Graphics g) {
		itemIcon.render(container, g);
	}

	public String getItemName() {
		return itemName;
	}

	public MysticButton getItemIcon() {
		return itemIcon;
	}

	public int[] getItemValues() {
		return itemValues;
	}
	
}
