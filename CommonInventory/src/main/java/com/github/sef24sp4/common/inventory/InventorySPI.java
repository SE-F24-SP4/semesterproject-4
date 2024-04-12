package com.github.sef24sp4.common.inventory;

import com.github.sef24sp4.common.item.ItemSPI;
import java.util.LinkedHashMap;

public interface InventorySPI {
	public LinkedHashMap<ItemSPI, Integer> inventory = new LinkedHashMap<>(3);

	/**
	 * Adds item to the inventory.
	 * <p>
	 * The inventory is a linked list, so it's automatically added to last place.
	 *
	 * @param item is the item you add to the inventory.
	 * @param amount is the amount of the item you add to the inventory.
	 * @see ItemSPI
	 */
	public void addItem(ItemSPI item, int amount);

	/**
	 * @return Returns an integer of the currently selected inventory slot.
	 */
	public int getSelectedSlot();

	/**
	 * Selects the next inventory slot in the linked list.
	 */
	public void nextSlot();

	/**
	 * Selects the previous inventory slot in the linked list.
	 */
	public void previousSlot();
}
