package player.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class PlayerStats implements Serializable {

	private static final long serialVersionUID = 1L;

	private int gameProgress;

	private int playerLevel;
	private int currentXP;
	private int requiredXP;
	private int freeStatPoints;
	private int freeSkillPoints;

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

//	private String reserve1;
//	private String reserve2;
//	private String reserve3;
//	private String reserve4;
//	private String reserve5;

	// player stats for loading a character
	public PlayerStats(Player player, File saveFile) throws FileNotFoundException {
	}

	// player stats for a new character
	public PlayerStats(String characterClass) {
		this.gameProgress = 0;
		this.playerLevel = 1;
		
		this.freeSkillPoints = 0;
		this.freeStatPoints = 0;

		this.strengh = 10;
		this.intelligence = 10;
		this.dexterity = 10;

		this.currentXP = 0;
		this.requiredXP = 100;

		this.maxLife = 100;
		this.currentLife = 100;

		this.maxEnergy = 50;
		this.currentEnergy = 50;

		this.physicalArmour = 10;
		this.magicalArmour = 10;
		this.attackSpeed = 1;
		this.currentGold = 0;

		switch (characterClass) {
		case "warrior":
			this.physicalAttackDmg = 25;
			this.magicalAttackDmg = 0;
			this.characterClass = "warrior";
			break;
		case "mage":
			this.physicalAttackDmg = 0;
			this.magicalAttackDmg = 25;
			this.characterClass = "mage";
			break;
		case "ranger":
			this.physicalAttackDmg = 25;
			this.magicalAttackDmg = 0;
			this.characterClass = "ranger";
			break;
		default:
			break;
		}

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

	private void setPlayerLevel() {
		playerLevel += 1;
		requiredXP = playerLevel * 100;
		freeStatPoints += 10;
		freeSkillPoints += 1;
		currentLife = maxLife;
		currentEnergy = maxEnergy;
	}

	public int getCurrentXP() {
		return currentXP;
	}

	public void setCurrentXP(int xpChange) {
		if (currentXP + xpChange >= requiredXP) {
			currentXP = currentXP + xpChange - requiredXP;
			setPlayerLevel();
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

	public void setStrengh() {
		this.strengh += 1;
		this.freeStatPoints -= 1;
		updateStats();
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence() {
		this.intelligence += 1;
		this.freeStatPoints -= 1;
		updateStats();
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity() {
		this.dexterity += 1;
		this.freeStatPoints -= 1;
		updateStats();
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

	public String getCharacterClass() {
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

	public int getFreeStatPoints() {
		return freeStatPoints;
	}

	public void setFreeStatPoints() {
		if (freeStatPoints - 1 >= 0) {
			this.freeStatPoints -= 1;	
		}
	}

	public int getFreeSkillPoints() {
		return freeSkillPoints;
	}

	public void setFreeSkillPoints() {
		if (freeSkillPoints - 1 >= 0) {
			this.freeSkillPoints -= 1;	
		}
	}
	
	private void updateStats() {
		maxLife = 100 + 10 * (strengh - 10);
		maxEnergy = 50 + 5 * (intelligence - 10);
		physicalArmour = strengh - 10;
		magicalArmour = intelligence - 10;
		attackSpeed = dexterity / 10;
		switch (characterClass) {
		case "warrior":
			physicalAttackDmg = 25 + 5 * (strengh - 10);
			magicalAttackDmg = 0 + 5 * (intelligence - 10);
			break;
		case "mage":
			physicalAttackDmg = 0 + 5 * (strengh - 10);
			magicalAttackDmg = 25 + 5 * (intelligence - 10);
			break;
		case "ranger":
			physicalAttackDmg = 25 + 5 * (strengh - 10);
			magicalAttackDmg = 0 + 5 * (intelligence - 10);
			break;
		default:
			break;
		}
	}
}
