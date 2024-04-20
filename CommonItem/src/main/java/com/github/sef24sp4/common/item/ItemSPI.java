package com.github.sef24sp4.common.item;

public interface ItemSPI {
	/**
	 *
	 * @return Returns the Item.
	 */
	public CommonItem getItem();

	/**
	 *
	 * @return Returns the rarity of the item from ItemRarity enum.
	 */
	public ItemRarity getRarity();
}
