package itmes.classes;

import java.io.FileNotFoundException;

import org.newdawn.slick.SlickException;

import audio.classes.GameSound;
import basic.classes.MysticButton;
import basic.classes.MysticStudioGame;

public class GoldCoin extends InGameItem {

	private GameSound coinDropSound;

	public GoldCoin(MysticStudioGame game, String itemName, MysticButton itemIcon, int[] itemValues) {
		super(game, itemName, itemIcon, itemValues);
		try {
			coinDropSound = new GameSound("res/sounds/coinDrop.wav");
			coinDropSound.playSound();
		} catch (SlickException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
