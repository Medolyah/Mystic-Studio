package basic.classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SavingGame {
	
	private MysticStudioGame game;
	
	public SavingGame(MysticStudioGame game) {
		this.game = game;
	}
	
	public void saveGame() {

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
