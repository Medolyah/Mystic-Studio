package player.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlayerStats {
	
	private Player player;
	private File saveFile;
	private Scanner scanner;
	
	private int gameProgress;

	private int playerLevel;
	private int currentXP;
	private int requiredXP;

	private int maxLife;
	private int currentLife;
	private int maxEnergy;
	private int currentEnergy;

	private int strengh;
	private int intelligence;
	private int dexterity;

	private int physicalArmour;
	private int magicalArmour;

	private int physicalAttackDmg;
	private int magicalAttackDmg;
	private int attackSpeed;
	
	private String characterClass;
	private int currentGold;
	
	private String reserve1;
	private String reserve2;
	private String reserve3;
	private String reserve4;
	private String reserve5;

	// player stats for loading a character
	public PlayerStats(Player player, File saveFile) throws FileNotFoundException {
		this.player = player;
		this.saveFile = saveFile;
		readSaveFile(saveFile);
//		player.setPlayerStats(this);
	}

	// playe stats for a new character
	public PlayerStats() {
		maxLife = 100;
		currentLife = 100;
		
		maxEnergy = 100;
		currentEnergy = 100;
		
		requiredXP = 100;
		currentXP = 0;
	}

	private void readSaveFile(File saveFile) throws FileNotFoundException {

		scanner = new Scanner(saveFile);
		
		// save file lines 1 - 5
		gameProgress = scanner.nextInt();
		playerLevel = scanner.nextInt();
		currentXP = scanner.nextInt();
		requiredXP = scanner.nextInt();
		maxLife = scanner.nextInt();
		currentLife = maxLife;
		// lines 6 - 10
		maxEnergy = scanner.nextInt();
		currentEnergy = maxEnergy;
		strengh = scanner.nextInt();
		intelligence = scanner.nextInt();
		dexterity = scanner.nextInt();
		physicalArmour = scanner.nextInt();
		// lines 11 - 15
		magicalArmour = scanner.nextInt();
		physicalAttackDmg = scanner.nextInt();
		magicalAttackDmg = scanner.nextInt();
		attackSpeed = scanner.nextInt();
		characterClass = scanner.next();
		// lines 16 - 17
		currentGold = scanner.nextInt();
		// lines 17 - 19 are empty for reserve
		reserve1 = scanner.next();
		reserve2 = scanner.next();
		reserve3 = scanner.next();
		
	}
	
	public int getGameProgress() {
		return gameProgress;
	}

	public void setGameProgress(int gameProgress) {
		this.gameProgress = gameProgress;
	}

	public int getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}

	public int getCurrentXP() {
		return currentXP;
	}

	public void setCurrentXP(int xpChange) {
		if (currentXP + xpChange >= requiredXP) {
			currentXP = currentXP + xpChange - requiredXP; 
		} else {
			currentXP += xpChange;
		}
	}

	public int getRequiredXP() {
		return requiredXP;
	}

	public void setRequiredXP(int requiredXP) {
		this.requiredXP = requiredXP;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public int getCurrentLife() {
		return currentLife;
	}

	public void setCurrentLife(int lifeChange) {
		if (lifeChange < 0 || lifeChange > 0 && currentLife != maxLife) {
			if (lifeChange > 0 && currentLife + lifeChange > maxLife) {
				currentLife = maxLife;
			} else if (lifeChange < 0 && currentLife + lifeChange <= 0) {
				currentLife = 0;
			} else {
				currentLife += lifeChange;
			}
		}
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public int getCurrentEnergy() {
		return currentEnergy;
	}

	public void setCurrentEnergy(int energyChange) {
		if (energyChange < 0 && currentEnergy + energyChange > 0 || energyChange > 0 && currentEnergy != maxEnergy) {
			if (energyChange > 0 && currentEnergy + energyChange > maxEnergy) {
				currentEnergy = maxEnergy;
			} else {
				currentEnergy += energyChange;
			}
		}
	}

	public int getStrengh() {
		return strengh;
	}

	public void setStrengh(int strengh) {
		this.strengh = strengh;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getPhysicalArmour() {
		return physicalArmour;
	}

	public void setPhysicalArmour(int physicalArmour) {
		this.physicalArmour = physicalArmour;
	}

	public int getMagicalArmour() {
		return magicalArmour;
	}

	public void setMagicalArmour(int magicalArmour) {
		this.magicalArmour = magicalArmour;
	}

	public int getPhysicalAttackDmg() {
		return physicalAttackDmg;
	}

	public void setPhysicalAttackDmg(int physicalAttackDmg) {
		this.physicalAttackDmg = physicalAttackDmg;
	}

	public int getMagicalAttackDmg() {
		return magicalAttackDmg;
	}

	public void setMagicalAttackDmg(int magicakAttackDmg) {
		this.magicalAttackDmg = magicakAttackDmg;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public String getPlayerClass() {
		return characterClass;
	}
	
	public void setCharacterClass(String characterClass) {
		this.characterClass = characterClass;
	}
	
	public int getCurrentGold() {
		return currentGold;
	}
	
	public void setCurrentGold(int gold) {
		this.currentGold = gold;
	}
}
