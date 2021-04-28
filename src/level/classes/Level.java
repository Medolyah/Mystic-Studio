package level.classes;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import basic.classes.GraphicObject;
import player.classes.Player.Movement;

@SuppressWarnings("deprecation")
public abstract class Level {

	public enum LevelType {
		BIRDS_EYE_VIEW,
		PLATFORMER
	}
	
	public ArrayList<GraphicObject> textures;
	public ArrayList<Npc> objectsAndNpcs;
	public LevelType levelType;
	public Image background;
	public String levelName;
	
	public Font textFont;
	public TrueTypeFont ttTextFont;
	public Color fontColor;
	
	public abstract void update(GameContainer container, int delta);
	public abstract void render(GameContainer container, Graphics g);
	
	public void moveObjects(Movement movement) {

		switch (movement) {
		case UP:
			for (GraphicObject object : textures) {
				object.setyPos(object.getyPos() - 1);
			}
			for (Npc npc : objectsAndNpcs) {
				npc.setyPos(npc.getyPos() - 1);
			}
			break;
		case DOWN:
			for (GraphicObject object : textures) {
				object.setyPos(object.getyPos() + 1);
			}
			for (Npc npc : objectsAndNpcs) {
				npc.setyPos(npc.getyPos() + 1);
			}
			break;
		case RIGHT:
			for (GraphicObject object : textures) {
				object.setxPos(object.getxPos() + 1);
			}
			for (Npc npc : objectsAndNpcs) {
				npc.setxPos(npc.getxPos() + 1);
				npc.setxMinPos(npc.getxMinPos() + 1);
				npc.setxMaxPos(npc.getxMaxPos() + 1);
			}
			break;
		case LEFT:
			for (GraphicObject object : textures) {
				object.setxPos(object.getxPos() - 1);
			}
			for (Npc npc : objectsAndNpcs) {
				npc.setxPos(npc.getxPos() - 1);
				npc.setxMinPos(npc.getxMinPos() - 1);
				npc.setxMaxPos(npc.getxMaxPos() - 1);
			}
			break;
		default:
			break;
		}
	}

}
