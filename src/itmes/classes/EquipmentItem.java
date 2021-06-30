package itmes.classes;

import basic.classes.MysticButton;

public class EquipmentItem extends InGameItem {
	
	private int itemLevel;

	public EquipmentItem(String itemName, MysticButton itemIcon, int[] itemValues, int itemLevel) {
		super(itemName, itemIcon, itemValues);
		this.itemLevel = itemLevel;
	}

	public int getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}
	
	

}
