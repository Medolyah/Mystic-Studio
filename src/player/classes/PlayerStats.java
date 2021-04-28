package player.classes;

public class PlayerStats {

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
	private int magicakAttackDmg;
	private int attackSpeed;

	public PlayerStats() {
		maxLife = 100;
		currentLife = 75;
		
		maxEnergy = 100;
		currentEnergy = 22;
		
		requiredXP = 100;
		currentXP = 50;
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

	public int getMagicakAttackDmg() {
		return magicakAttackDmg;
	}

	public void setMagicakAttackDmg(int magicakAttackDmg) {
		this.magicakAttackDmg = magicakAttackDmg;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

}
