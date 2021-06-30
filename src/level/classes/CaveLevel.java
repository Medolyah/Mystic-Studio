package level.classes;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

import audio.classes.GameMusic;
import basic.classes.GraphicObject;
import basic.classes.MysticStudioGame;

@SuppressWarnings("deprecation")
public class CaveLevel extends Level {

	public CaveLevel(MysticStudioGame game) {
		super(game);
		levelName = "Level 2: Cave";
		levelNumber = 2;

		try {
			textFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/distantGalaxy.ttf")).deriveFont(25f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(textFont);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		ttTextFont = new TrueTypeFont(textFont, true);
		fontColor =  new Color(0.9f, 0.9f, 0.9f, 0.9f);

		// level type
		levelType = Level.LevelType.PLATFORMER;
		
		// music
		try {
			levelMusic = new GameMusic("res/music/caveMusic.wav");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (SlickException e1) {
			e1.printStackTrace();
		}

		// textures/objects (with hitbox)
		textures = new ArrayList<GraphicObject>();
		try {
			
			// load required graphics
			Image caveWall01 = new Image("res/images/Cave_Wall_01.png");
			Image caveHalfWall01 = new Image("res/images/Cave_Half_Wall_01.png");
			Image cavePlattform01 = new Image("res/images/Cave_Platform_01.png");
			Image caveStalactite01 = new Image("res/images/Cave_Stalactite_01.png");
			Image caveStalactite02 = new Image("res/images/Cave_Stalactite_02.png");
			Image caveStalagmite01 = new Image("res/images/Cave_Stalagmite_01.png");
			Image caveStalagmite02 = new Image("res/images/Cave_Stalagmite_02.png");
			
			// walls
			// left level wall
			getTextures().add(new GraphicObject(-200, 610, new Rectangle(-200, 610, 60, 400), caveWall01));
			// right level wall 1
			getTextures().add(new GraphicObject(10350, 740, new Rectangle(10350, 740, 60, 400), caveWall01));
			getTextures().add(new GraphicObject(10350, 970, new Rectangle(10350, 970, 60, 400), caveWall01));
			// right level wall 2
			getTextures().add(new GraphicObject(10350, 1370, new Rectangle(10350, 1370, 60, 400), caveWall01));
			getTextures().add(new GraphicObject(10350, 1670, new Rectangle(10350, 1670, 60, 400), caveWall01));
			// right level wall 3 - end wall
			getTextures().add(new GraphicObject(10400, 2050, new Rectangle(10400, 2050, 60, 400), caveWall01));
			getTextures().add(new GraphicObject(10400, 2450, new Rectangle(10400, 2450, 60, 400), caveWall01));
			getTextures().add(exitWall = new GraphicObject(10400, 2750, new Rectangle(10400, 2750, 60, 400), caveWall01));
			
			getTextures().add(new GraphicObject(11200, 2050, new Rectangle(11200, 2050, 60, 400), caveWall01));
			getTextures().add(new GraphicObject(11200, 2450, new Rectangle(11200, 2450, 60, 400), caveWall01));
			getTextures().add(new GraphicObject(11200, 2750, new Rectangle(11200, 2750, 60, 400), caveWall01));
			// first down stairs wall
			getTextures().add(new GraphicObject(2540, 550, new Rectangle(2540, 550, 60, 200), caveHalfWall01));
			getTextures().add(new GraphicObject(2540, 1000, new Rectangle(2540, 1000, 60, 200), caveHalfWall01));
			// hole 1 down
			getTextures().add(new GraphicObject(5940, 1350, new Rectangle(5940, 1350, 60, 200), caveHalfWall01));
			getTextures().add(new GraphicObject(5980, 1500, new Rectangle(5980, 1500, 60, 200), caveHalfWall01));
			getTextures().add(new GraphicObject(6020, 1650, new Rectangle(6020, 1650, 60, 200), caveHalfWall01));
			getTextures().add(new GraphicObject(6060, 1800, new Rectangle(6060, 1800, 60, 200), caveHalfWall01));
			getTextures().add(new GraphicObject(6100, 1950, new Rectangle(6100, 1950, 60, 200), caveHalfWall01));
			// hole 1 down up
			getTextures().add(new GraphicObject(6500, 1950, new Rectangle(6500, 1950, 60, 200), caveHalfWall01));
			getTextures().add(new GraphicObject(6540, 1800, new Rectangle(6540, 1800, 60, 200), caveHalfWall01));
			getTextures().add(new GraphicObject(6580, 1650, new Rectangle(6580, 1650, 60, 200), caveHalfWall01));
			getTextures().add(new GraphicObject(6920, 1500, new Rectangle(6920, 1500, 60, 200), caveHalfWall01));
			getTextures().add(new GraphicObject(6960, 1350, new Rectangle(6960, 1350, 60, 200), caveHalfWall01));
			// hole 2 down
			getTextures().add(new GraphicObject(7300, 1350, new Rectangle(7300, 1350, 60, 400), caveWall01));
			getTextures().add(new GraphicObject(7340, 1650, new Rectangle(7340, 1650, 60, 400), caveWall01));
			getTextures().add(new GraphicObject(7380, 1950, new Rectangle(7380, 1950, 60, 400), caveWall01));
			getTextures().add(new GraphicObject(7420, 2250, new Rectangle(7420, 2250, 60, 400), caveWall01));
			getTextures().add(new GraphicObject(7460, 2550, new Rectangle(7460, 2550, 60, 400), caveWall01));
			getTextures().add(new GraphicObject(7500, 2850, new Rectangle(7500, 2850, 60, 400), caveWall01));
			
			// stalagmites and stalactites
			getTextures().add(new GraphicObject(950, 950, new Rectangle(3930, 1200, 37, 99), caveStalagmite02));
			getTextures().add(new GraphicObject(2515, 575, new Rectangle(2515, 575, 60, 200), caveStalactite01));
			getTextures().add(new GraphicObject(2575, 675, new Rectangle(2575, 675, 37, 99), caveStalactite02));
			getTextures().add(new GraphicObject(2965, 675, new Rectangle(3965, 675, 37, 99), caveStalactite02));
			getTextures().add(new GraphicObject(3355, 700, new Rectangle(3355, 700, 60, 200), caveStalactite01));
			getTextures().add(new GraphicObject(3745, 750, new Rectangle(3745, 750, 37, 99), caveStalactite02));
			
			getTextures().add(new GraphicObject(3930, 1200, new Rectangle(3930, 1200, 60, 200), caveStalagmite01));
			getTextures().add(new GraphicObject(4300, 750, new Rectangle(4300, 750, 37, 99), caveStalactite02));
			getTextures().add(new GraphicObject(4400, 750, new Rectangle(4400, 750, 60, 200), caveStalactite01));
			getTextures().add(new GraphicObject(4450, 770, new Rectangle(4450, 770, 37, 99), caveStalactite02));
			getTextures().add(new GraphicObject(4750, 1300, new Rectangle(4750, 1300, 44, 101), caveStalagmite02));

			// platforms
			// start floor
			getTextures().add(new GraphicObject(-1000, 1000, new Rectangle(-1000, 1000, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(-600, 1000, new Rectangle(-600, 1000, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(-200, 1000, new Rectangle(-200, 1000, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(200, 1000, new Rectangle(200, 1000, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(600, 1000, new Rectangle(600, 1000, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(1000, 1000, new Rectangle(1000, 1000, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(1400, 1000, new Rectangle(1400, 1000, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(1800, 1000, new Rectangle(1800, 1000, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(2200, 1000, new Rectangle(2200, 1000, 400, 60), cavePlattform01));
			// first down stairs
			getTextures().add(new GraphicObject(2540, 1170, new Rectangle(2540, 1170, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(2640, 1200, new Rectangle(2640, 1200, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(2740, 1230, new Rectangle(2740, 1230, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(2840, 1260, new Rectangle(2840, 1260, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(2940, 1290, new Rectangle(2940, 1290, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(3040, 1320, new Rectangle(3040, 1320, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(3140, 1350, new Rectangle(3140, 1350, 400, 60), cavePlattform01));
			// walkway 1
			getTextures().add(new GraphicObject(3540, 1350, new Rectangle(3540, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(3940, 1350, new Rectangle(3940, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(4340, 1350, new Rectangle(4340, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(4740, 1350, new Rectangle(4740, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(5140, 1380, new Rectangle(5140, 1380, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(5540, 1350, new Rectangle(5540, 1350, 400, 60), cavePlattform01));
			// hole 1 floors
			getTextures().add(new GraphicObject(6150, 2100, new Rectangle(6150, 2100, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(6580, 1650, new Rectangle(6580, 1650, 400, 60), cavePlattform01));
			// walkway hole 1-2
			getTextures().add(new GraphicObject(6960, 1350, new Rectangle(6960, 1350, 400, 60), cavePlattform01));
			// last floor / bottom 2.3 / boss
			getTextures().add(new GraphicObject(7500, 3140, new Rectangle(7500, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(7900, 3140, new Rectangle(7900, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(8300, 3140, new Rectangle(8300, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(8700, 3140, new Rectangle(8700, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(9100, 3140, new Rectangle(9100, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(9500, 3140, new Rectangle(9500, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(9900, 3140, new Rectangle(9900, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(10300, 3140, new Rectangle(10300, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(10700, 3140, new Rectangle(10700, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(11100, 3140, new Rectangle(11100, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(11500, 3140, new Rectangle(11500, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(11900, 3140, new Rectangle(11900, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(12300, 3140, new Rectangle(12300, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(12700, 3140, new Rectangle(12700, 3140, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(13100, 3140, new Rectangle(13100, 3140, 400, 60), cavePlattform01));
			
			// "ceiling"
			// ceiling 1
			getTextures().add(new GraphicObject(-200, 600, new Rectangle(-200, 600, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(200, 600, new Rectangle(200, 600, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(600, 600, new Rectangle(600, 600, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(1000, 600, new Rectangle(1000, 600, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(1400, 600, new Rectangle(1400, 600, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(1800, 600, new Rectangle(1800, 600, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(2200, 600, new Rectangle(2200, 600, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(2590, 660, new Rectangle(2600, 660, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(2980, 690, new Rectangle(2980, 690, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(3370, 720, new Rectangle(3370, 720, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(3760, 750, new Rectangle(3760, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(4160, 750, new Rectangle(4160, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(4560, 750, new Rectangle(4560, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(4960, 750, new Rectangle(4960, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(5360, 750, new Rectangle(5360, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(5760, 750, new Rectangle(5760, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(6160, 750, new Rectangle(6160, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(6560, 750, new Rectangle(6560, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(6960, 750, new Rectangle(6960, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(7360, 750, new Rectangle(7360, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(7760, 750, new Rectangle(7760, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(8160, 750, new Rectangle(8160, 750, 400, 60), cavePlattform01));			
			getTextures().add(new GraphicObject(8560, 750, new Rectangle(8560, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(8960, 750, new Rectangle(8960, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(9360, 750, new Rectangle(9360, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(9760, 750, new Rectangle(9760, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(10160, 750, new Rectangle(10160, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(10560, 750, new Rectangle(10560, 750, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(10960, 750, new Rectangle(10960, 750, 400, 60), cavePlattform01));
			// ceiling 2 / bottom 2.1
			getTextures().add(new GraphicObject(7550, 1350, new Rectangle(7550, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(7950, 1350, new Rectangle(7950, 1350, 400, 60), cavePlattform01));			
			getTextures().add(new GraphicObject(8350, 1350, new Rectangle(8350, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(8750, 1350, new Rectangle(8750, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(9150, 1350, new Rectangle(9150, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(9550, 1350, new Rectangle(9550, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(9950, 1350, new Rectangle(9950, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(10350, 1350, new Rectangle(10350, 1350, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(10750, 1350, new Rectangle(10750, 1350, 400, 60), cavePlattform01));
			// ceiling 3 / bottom 2.2
			getTextures().add(new GraphicObject(7600, 2050, new Rectangle(7600, 2050, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(8000, 2050, new Rectangle(7800, 2050, 400, 60), cavePlattform01));			
			getTextures().add(new GraphicObject(8400, 2050, new Rectangle(8400, 2050, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(8800, 2050, new Rectangle(8800, 2050, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(9200, 2050, new Rectangle(9200, 2050, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(9600, 2050, new Rectangle(9600, 2050, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(10000, 2050, new Rectangle(10000, 2050, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(10400, 2050, new Rectangle(10400, 2050, 400, 60), cavePlattform01));
			getTextures().add(new GraphicObject(10800, 2050, new Rectangle(10800, 2050, 400, 60), cavePlattform01));
			
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

		// objects and NPCs
		npcs = new ArrayList<Npc>();
		try {
			
			// load enemy graphics
			Image spider2 = new Image("res/images/Spider2.png");
			Image devilEye1 = new Image("res/images/Devil-eye1.png");
			Image magicMushroom = new Image("res/images/Magic-mushroom1.png");
			
			// enemies
			// first floor
			npcs.add(new Npc(game, 1000, 910, new Circle(1000, 910, 60), spider2, 1000, 2000, 2, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 1500, 910, new Circle(1500, 910, 60), spider2, 1000, 2000, 3, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 2000, 910, new Circle(2000, 910, 60), spider2, 1500, 2200, 1, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 1300, 910, new Circle(3000, 910, 60), spider2, 1200, 1900, 2, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 1400, 910, new Circle(1400, 910, 60), spider2, 1100, 1750, 2, 175, -10, new Circle(0, 0, 200), 1));
			//stairs + stala*
			npcs.add(new Npc(game, 3140, 1000, new Rectangle(3140, 1000, 300, 145), devilEye1, 3100, 4000, 2, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 4900, 1000, new Rectangle(4900, 1000, 300, 145), devilEye1, 4500, 5400, 2, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 4500, 1250, new Circle(4500, 1250, 50), magicMushroom, 4500, 4505, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 4600, 1250, new Circle(4600, 1250, 50), magicMushroom, 4600, 4605, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 4650, 1250, new Circle(4650, 1250, 50), magicMushroom, 4650, 4655, 1, 175, -10, new Circle(0, 0, 200), 1));
			
			npcs.add(new Npc(game, 5000, 1250, new Circle(5000, 1250, 50), magicMushroom, 5000, 5005, 1, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 5050, 1250, new Circle(5050, 1250, 50), magicMushroom, 5050, 5055, 1, 175, -10, new Circle(0, 0, 200), 1));
			// hole 1
			npcs.add(new Npc(game, 6150, 2000, new Circle(6210, 2000, 50), magicMushroom, 6150, 6155, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 6210, 2000, new Circle(6210, 2000, 50), magicMushroom, 6210, 6215, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 6410, 2000, new Circle(6410, 2000, 50), magicMushroom, 6410, 6415, 1, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 6150, 2000, new Circle(6150, 2000, 60), spider2, 6150, 6390, 1, 175, -10, new Circle(0, 0, 200), 1));		
			npcs.add(new Npc(game, 6800, 1550, new Circle(6800, 1550, 50), magicMushroom, 6800, 6805, 1, 175, -10, new Circle(0, 0, 200), 1));
			// hole 1-2
			npcs.add(new Npc(game, 6900, 1100, new Rectangle(6900, 1100, 300, 145), devilEye1, 6900, 8100, 2, 175, -10, new Circle(0, 0, 200), 1));
			
			// floor 1			
			npcs.add(new Npc(game, 9500, 1250, new Circle(9500, 1250, 50), magicMushroom, 9500, 9505, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 9600, 1250, new Circle(9600, 1250, 50), magicMushroom, 9600, 9605, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 9650, 1250, new Circle(9650, 1250, 50), magicMushroom, 9650, 9655, 1, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 10000, 1250, new Circle(10000, 1250, 50), magicMushroom, 10000, 10005, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 10050, 1260, new Circle(10050, 1260, 50), magicMushroom, 10050, 10055, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 10100, 1250, new Circle(10100, 1250, 50), magicMushroom, 10100, 10105, 1, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 10200, 1250, new Circle(10200, 1250, 50), magicMushroom, 10200, 10205, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 10230, 1250, new Circle(10230, 1250, 50), magicMushroom, 10230, 10235, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 10250, 1250, new Circle(10250, 1250, 50), magicMushroom, 10250, 10255, 1, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 10200, 1225, new Circle(10200, 1200, 50), magicMushroom, 10200, 10205, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 10230, 1200, new Circle(10230, 1180, 50), magicMushroom, 10230, 10235, 1, 175, -10, new Circle(0, 0, 200), 1));			
			npcs.add(new Npc(game, 10250, 1225, new Circle(10250, 1200, 50), magicMushroom, 10250, 10255, 1, 175, -10, new Circle(0, 0, 200), 1));
			
			// floor 2
			npcs.add(new Npc(game, 10250, 1950, new Circle(10250, 1950, 60), spider2, 8500, 10250, 2, 175, -10, new Circle(0, 0, 200), 1));		
			npcs.add(new Npc(game, 9000, 1960, new Circle(10250, 1950, 60), spider2, 8500, 10250, 2, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 10000, 1970, new Circle(10250, 1950, 60), spider2, 8500, 10250, 3, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 10250, 1980, new Circle(10250, 1950, 60), spider2, 8500, 10250, 1, 175, -10, new Circle(0, 0, 200), 1));		
			npcs.add(new Npc(game, 9000, 1970, new Circle(10250, 1950, 60), spider2, 8500, 10250, 1, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 10000, 1950, new Circle(10250, 1950, 60), spider2, 8500, 10250, 1, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 7600, 1960, new Circle(7600, 1950, 60), spider2, 7600, 9000, 1, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 7600, 1970, new Circle(7600, 1950, 60), spider2, 7600, 9000, 2, 175, -10, new Circle(0, 0, 200), 1));
			npcs.add(new Npc(game, 7600, 1980, new Circle(7600, 1950, 60), spider2, 7600, 9000, 3, 175, -10, new Circle(0, 0, 200), 1));
			
			// boss
			npcs.add(boss = new Npc(game, 9000, 2850, new Rectangle(9000, 2850, 300, 145), devilEye1, 9000, 10000, 3, 500, -20, new Circle(0, 0, 200), 2));
			npcs.add(new Npc(game, 10000, 2850, new Rectangle(9000, 2850, 300, 145), devilEye1, 9000, 10000, 3, 500, -20, new Circle(0, 0, 200), 2));
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

		// background
		try {
			this.background = new Image("res/images/Cave_Background.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// interaction objects
		interactionObjects = new ArrayList<InteractionObject>();
		try {
			getInteractionObjects().add(new InteractionObject(200, 700, new Rectangle(200, 700, 300, 300),
					new Image("res/images/Cave-Entry.png"), game, "CaveLevel"));
			getInteractionObjects().add(new InteractionObject(10500, 2845, new Rectangle(10500, 2845, 300, 300),
					new Image("res/images/Cave-Entry.png"), game, "CaveLevel"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {

		super.render(container, g);

		// Level infos
		ttTextFont.drawString(container.getWidth() / 2 - 100, 20, levelName, fontColor);
		ttTextFont.drawString(container.getWidth() / 2 - 100, 50, "Enemy level: 3 - 5", fontColor);

	}
}
