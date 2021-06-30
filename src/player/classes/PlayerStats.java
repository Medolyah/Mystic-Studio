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

	private int weapon;
	private int helmet;
	private int bodyArmour;
//	private int pants;
	private int boots;
	private int gloves;
	private int leftRing;
	private int rightRing;

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
		this.weapon = 1;
		this.helmet = 0;
		this.bodyArmour = 0;
//		this.pants = 0;
		this.boots = 0;
		this.gloves = 0;
		this.leftRing = 0;
		this.rightRing = 0;
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
		this.currentGold += gold;
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

	public void upgradeEquipmentItem(String item) {

		switch (item) {
		case "Helmet":
			this.helmet += 1;
			break;
		case "Body armour":
			this.bodyArmour += 1;
			break;
//		case "Pants ":
//				this.pants += 1;
//			break;
		case "Boots":
			this.boots += 1;
			break;
		case "Gloves":
			this.gloves += 1;
			break;
		case "Left ring":
			this.leftRing += 1;
			break;
		case "Right ring":
			this.rightRing += 1;
			break;
		default:
			break;
		}
	}

	public int getWeapon() {
		return weapon;
	}

	public void setWeapon() {
		if (weapon == 0) {
			if (currentGold >= 10) {
				currentGold -= 10;
				this.weapon += 1;
				switch (characterClass) {
				case "warrior":
					physicalAttackDmg += 1;
					break;
				case "mage":
					magicalAttackDmg += 1;
					break;
				case "ranger":
					physicalAttackDmg += 1;
					break;
				default:
					break;
				}
			}
		} else if (currentGold >= weapon * 10) {
			currentGold -= weapon * 10;
			this.weapon += 1;
			switch (characterClass) {
			case "warrior":
				physicalAttackDmg += 1;
				break;
			case "mage":
				magicalAttackDmg += 1;
				break;
			case "ranger":
				physicalAttackDmg += 1;
				break;
			default:
				break;
			}
		}
	}

	public int getHelmet() {
		return helmet;
	}

	public void setHelmet() {
		if (helmet == 0) {
			if (currentGold >= 10) {
				currentGold -= 10;
				this.helmet += 1;
				this.maxLife += 5;
				this.physicalArmour += 1;
				this.magicalArmour += 1;
			}
		} else if (currentGold >= helmet * 10) {
			currentGold -= helmet * 10;
			this.helmet += 1;
			this.maxLife += 5;
			this.physicalArmour += 1;
			this.magicalArmour += 1;
		}
	}

	public int getBodyArmour() {
		return bodyArmour;
	}

	public void setBodyArmour() {
		if (bodyArmour == 0) {
			if (currentGold >= 10) {
				currentGold -= 10;
				this.bodyArmour += 1;
				this.maxLife += 8;
				this.physicalArmour += 1;
				this.magicalArmour += 1;
			}
		} else if (currentGold >= bodyArmour * 10) {
			currentGold -= bodyArmour * 10;
			this.bodyArmour += 1;
			this.maxLife += 8;
			this.physicalArmour += 1;
			this.magicalArmour += 1;
		}
	}

//	public int getPants() {
//		return pants;
//	}

//	public void setPants() {
//		if (pants == 0) {
//			if (currentGold >= 10) {
//				currentGold -= 10;
//				this.pants += 1;
//			}
//		} else if (currentGold >= pants * 10) {
//			currentGold -= pants * 10;
//			this.pants += 1;
//		}
//	}

	public int getBoots() {
		return boots;
	}

	public void setBoots() {
		if (boots == 0) {
			if (currentGold >= 10) {
				currentGold -= 10;
				this.boots += 1;
				this.maxLife += 3;
				this.attackSpeed += 2;
			}
		} else if (currentGold >= boots * 10) {
			currentGold -= boots * 10;
			this.boots += 1;
			this.maxLife += 2;
			this.attackSpeed += 2;
		}
	}

	public int getGloves() {
		return gloves;
	}

	public void setGloves() {
		if (gloves == 0) {
			if (currentGold >= 10) {
				currentGold -= 10;
				this.gloves += 1;
				this.maxLife += 2;
				this.attackSpeed += 3;
			}
		} else if (currentGold >= gloves * 10) {
			currentGold -= gloves * 10;
			this.gloves += 1;
			this.maxLife += 2;
			this.attackSpeed += 3;
		}
	}

	public int getLeftRing() {
		return leftRing;
	}

	public void setLeftRing() {
		if (leftRing == 0) {
			if (currentGold >= 10) {
				currentGold -= 10;
				this.leftRing += 1;
				this.maxEnergy += 5;
				this.physicalArmour += 1;
				this.magicalArmour += 2;
			}
		} else if (currentGold >= leftRing * 10) {
			currentGold -= leftRing * 10;
			this.leftRing += 1;
			this.maxEnergy += 5;
			this.physicalArmour += 1;
			this.magicalArmour += 2;
		}
	}

	public int getRightRing() {
		return rightRing;
	}

	public void setRightRing() {
		if (rightRing == 0) {
			if (currentGold >= 10) {
				currentGold -= 10;
				this.rightRing += 1;
				this.maxEnergy += 5;
				this.physicalArmour += 2;
				this.magicalArmour += 1;
			}
		} else if (currentGold >= rightRing * 10) {
			currentGold -= rightRing * 10;
			this.rightRing += 1;
			this.maxEnergy += 5;
			this.physicalArmour += 2;
			this.magicalArmour += 1;
		}
	}
}
