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

		try {
			textFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/distantGalaxy.ttf")).deriveFont(25f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(textFont);
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		ttTextFont = new TrueTypeFont(textFont, true);

		try {
			titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/distantGalaxy.ttf")).deriveFont(50f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(titleFont);
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		ttTitleFont = new TrueTypeFont(titleFont, true);
		
		deltaSpeed = 0;
		scrollPosition = 0;
	}

	public void update(GameContainer container, int delta) throws SlickException, FileNotFoundException {
		
		// set frame rate 
		container.setMinimumLogicUpdateInterval(5);
        container.setMaximumLogicUpdateInterval(5);
        
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {

			// reset frame rate 
			container.setMinimumLogicUpdateInterval(15);
	        container.setMaximumLogicUpdateInterval(15);
	        
			active = false;
		}
		if (deltaSpeed > 1) {
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

		ttTitleFont.drawString(875, 1900 + scrollPosition, "* Special Thanks *", textColor);
		ttTextFont.drawString(875, 1950 + scrollPosition, "Fabi", textColor);
		
		ttTextFont.drawString(1500, 1000, "Press ESC to skip", new Color(0.1f,0.1f,0.1f,0.5f));

	}
	
	public boolean getActive() {
		return this.active;
	}
}
