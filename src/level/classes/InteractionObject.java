package level.classes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import audio.classes.GameSound;
import basic.classes.GraphicObject;
import basic.classes.MysticStudioGame;
import menu.classes.PickLevel;

public class InteractionObject extends GraphicObject {

	private MysticStudioGame game;
	private String interaction;

	public InteractionObject(int xPos, int yPos, Shape hitbox, Image image, MysticStudioGame game, String interaction) {
		super(xPos, yPos, hitbox, image);
		this.game = game;
		this.interaction = interaction;
	}

	public void executeInteraction() throws FileNotFoundException, SlickException {
		// set level
		Level level;
		switch (interaction) {
		case "TestLevel":
			level = new TestLevel1(game);
			game.setLevel(level, 1600, 300);
			break;
		case "BasementLevel":
			saveGame();
			level = new Village(game);
			game.setLevel(level, 1500, 600);
			break;
		case "CaveLevel":
			saveGame();
			level = new Village(game);
			game.setLevel(level, 1500, 600);
			break;
		case "Village":
			level = new Village(game);
			game.setLevel(level, 1500, 600);
			saveGame();
			break;
		case "SelectLevel":
			PickLevel pickLevel = new PickLevel(game);
			game.setPickLevel(pickLevel);
			break;
		case "villager":
			GameSound villagerSound = new GameSound("res/voice-files/goodDay.wav");
			villagerSound.playSound();
			break;
		case "merchant":
			GameSound merchantSound = new GameSound("res/voice-files/merchant.wav");
			merchantSound.playSound();
			break;
		case "Quest01":
			GameSound quest01Sound = new GameSound("res/voice-files/quest01.wav");
			quest01Sound.playSound();
			break;
		default:
			break;
		}
	}

	private void saveGame() {

		FileOutputStream fileOut1 = null;
		try {
			switch (game.getSaveGameNumber()) {
			case 1:
				fileOut1 = new FileOutputStream("res/savegames/save1.txt");
				break;
			case 2:
				fileOut1 = new FileOutputStream("res/savegames/save2.txt");
				break;
			case 3:
				fileOut1 = new FileOutputStream("res/savegames/save3.txt");
				break;
			default:
				break;
			}
			ObjectOutputStream objectOut1 = new ObjectOutputStream(fileOut1);
			objectOut1.writeObject(game.getPlayer().getPlayerStats());
			objectOut1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
