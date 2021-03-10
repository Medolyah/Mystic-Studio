package menu.classes;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

@SuppressWarnings("deprecation")
public class Credits extends Menu {

	private boolean active = false;

	private Font titleFont;
	private TrueTypeFont ttTitleFont;

	private Font textFont;
	private TrueTypeFont ttTextFont;
	
	private int deltaSpeed;
	private int scrollPosition;

	public Credits() throws SlickException, IOException {
		backgroundImage = new Image("res/images/Credits_Background.png");
		active = true;

		textFont = new Font("Distant Galaxy", Font.PLAIN, 25);
		ttTextFont = new TrueTypeFont(textFont, true);

		titleFont = new Font("Distant Galaxy", Font.PLAIN, 50);
		ttTitleFont = new TrueTypeFont(titleFont, true);
		
		deltaSpeed = 0;
		scrollPosition = 0;
	}

	public void update(GameContainer container, int delta) throws SlickException, FileNotFoundException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			active = false;
		}
		if (deltaSpeed > 10) {
			scrollPosition--;
			deltaSpeed = 0;
		} else {
			deltaSpeed++;
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, backgroundImagePosX, backgroundImagePosY);
		Color textColor = new Color(0.1f, 0.1f, 0.1f);
		
		ttTitleFont.drawString(875, 1100 + scrollPosition, "Programming", textColor);
		ttTextFont.drawString(875, 1150 + scrollPosition, "Stephan Manstein", textColor);
		ttTextFont.drawString(875, 1180 + scrollPosition, "Maximilian Sopp", textColor);
		ttTextFont.drawString(875, 1210 + scrollPosition, "Michael Jäckel", textColor);
		
		ttTitleFont.drawString(875, 1300 + scrollPosition, "Animation", textColor);
		ttTextFont.drawString(875, 1350 + scrollPosition, "Michael Jäckel", textColor);
		ttTextFont.drawString(875, 1380 + scrollPosition, "Maximilian Sopp", textColor);
		ttTextFont.drawString(875, 1410 + scrollPosition, "Stephan Manstein", textColor);
		
		ttTitleFont.drawString(875, 1500 + scrollPosition, "Sound", textColor);
		ttTextFont.drawString(875, 1550 + scrollPosition, "Michael Jäckel", textColor);
		ttTextFont.drawString(875, 1580 + scrollPosition, "Stephan Manstein", textColor);
		ttTextFont.drawString(875, 1610 + scrollPosition, "Maximilian Sopp", textColor);
		
		ttTitleFont.drawString(875, 1700 + scrollPosition, "Software", textColor);
		ttTextFont.drawString(875, 1750 + scrollPosition, "Eclipse by Eclipse Foundation", textColor);
		ttTextFont.drawString(875, 1780 + scrollPosition, "Slick2D by Ninjacave", textColor);
		ttTextFont.drawString(875, 1810 + scrollPosition, "Git / Github by Junio Hamano", textColor);
		
		ttTextFont.drawString(1500, 1000, "Press ESC to skip", new Color(0.1f,0.1f,0.1f,0.5f));

	}
	
	public boolean getActive() {
		return this.active;
	}
}
