package com.github.sef24sp4.common.item;

public interface ItemSPI {
	/**
	 * Get the item from the provider.
	 * @return Returns the Item of type {@link CommonItem}
	 * @see CommonItem
	 */
	public CommonItem getItem();

	/**
	 * Fetch the rarity of the item.
	 * @return Returns the rarity of the item from {@link ItemRarity}.
	 * @see ItemRarity
	 */
	public ItemRarity getRarity();
}
