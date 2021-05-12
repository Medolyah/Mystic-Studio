package level.classes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import basic.classes.GraphicObject;
import basic.classes.MysticStudioGame;
import player.classes.Player;
import ui.classes.Overlay;

public class InteractionObject extends GraphicObject {
	
	private MysticStudioGame game;


	public InteractionObject(int xPos, int yPos, Shape hitbox, Image image, MysticStudioGame game) {
		super(xPos, yPos, hitbox, image);
		this.game = game;
	}
	
	public void interaction() {
		// set level
		Level level = new TestLevel1(game);
		game.setLevel(level, 1600, 300);
	}
}
