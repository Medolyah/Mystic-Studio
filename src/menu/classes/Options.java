package menu.classes;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import basic.classes.MysticButton;

@SuppressWarnings("deprecation")
public class Options extends Menu {

	private boolean active = false;

	private int musicVolume;
	private int soundVolume;

	private File settingsFile;
	private Scanner scanner;

	private Font titleFont;
	private TrueTypeFont ttTitleFont;

	private Font textFont;
	private TrueTypeFont ttTextFont;

	private Shape musicBar;
	private Shape musicController;

	private Shape soundBar;
	private Shape soundController;

	private MysticButton confirmButton;

	public Options() throws SlickException, IOException {
		backgroundImage = new Image("res/images/Titelbild_ohne_Menu_FS.png");
		active = true;
		readsettings();

		textFont = new Font("Comic Sans MS", Font.BOLD, 25);
		ttTextFont = new TrueTypeFont(textFont, true);

		titleFont = new Font("Comic Sans MS", Font.BOLD, 50);
		ttTitleFont = new TrueTypeFont(titleFont, true);

		musicBar = new Rectangle(800, 760, 400, 25);
		musicController = new Circle(800 + (musicVolume * 4), 772, 15);

		soundBar = new Rectangle(800, 860, 400, 25);
		soundController = new Circle(800 + (soundVolume * 4), 872, 15);

		Image newButtonImage = new Image("res/images/New_Button.png");
		Shape newButtonShape = new Rectangle(790, 920, 400, 61);
		confirmButton = new MysticButton(790, 920, newButtonShape, newButtonImage);
	}

	public void update(GameContainer container, int delta) throws SlickException, FileNotFoundException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			active = false;
		} else {
			checkClicked(container);	
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		if (active) {
			g.drawImage(backgroundImage, backgroundImagePosX, backgroundImagePosY);

			Color textColor = new Color(0.1f, 0.1f, 0.1f);
			ttTitleFont.drawString(875, 600, "Options", textColor);
			ttTextFont.drawString(600, 750, "Music Volume", textColor);
			ttTextFont.drawString(600, 850, "Sound Volume", textColor);
			ttTextFont.drawString(850, 1000, "Press ESC to go back", textColor);

			g.setColor(Color.darkGray);
			g.fill(musicBar);
			g.fill(soundBar);
			g.setColor(Color.black);
			g.fill(musicController);
			g.fill(soundController);

			confirmButton.render(g);
		}

	}

	private void readsettings() throws IOException {
		settingsFile = new File("res/settings/settings.txt");
		scanner = new Scanner(settingsFile);
		scanner.next();
		musicVolume = scanner.nextInt();
		scanner.next();
		soundVolume = scanner.nextInt();
	}

	public boolean getActive() {
		return this.active;
	}

	public int getMusicVolume() {
		return this.musicVolume;
	}

	public int getSoundVolume() {
		return this.soundVolume;
	}
	
	private void checkClicked(GameContainer container) throws FileNotFoundException {
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (confirmButton.isClicked(container.getInput())) {
				setMusicVolume(musicController.getCenterX());
				setSoundVolume(soundController.getCenterX());
				saveOptions();
			} else if(musicBar.contains(container.getInput().getMouseX(), container.getInput().getMouseY())) {
				musicController.setCenterX(container.getInput().getMouseX());
			} else if(soundBar.contains(container.getInput().getMouseX(), container.getInput().getMouseY())) {
				soundController.setCenterX(container.getInput().getMouseX());
			}
		}
	}
	
	private void setMusicVolume(float volume) {
		this.musicVolume = ((int) volume - 800) / 4;
	}
	
	private void setSoundVolume(float volume) {
		this.soundVolume = ((int) volume - 800) / 4;
	}
	
	private void saveOptions() throws FileNotFoundException {
		System.out.println("music: " + musicVolume + ", sound: " + soundVolume);
		Formatter formatter = new Formatter("res/settings/settings.txt");
		formatter.format("%s%d%s%d", "musicVolume ", musicVolume, "\nsoundVolume ", soundVolume);
		formatter.close();
	}

}
