package com.github.sef24sp4.commonloottable;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;

import java.util.Optional;

public interface LootTableSPI {
	/**
	 * Used to get decide what rarity of item is dropped. Can also be null.
	 * @return Returns {@link Optional}<{@link ItemRarity}> or null.
	 */
	 Optional<ItemRarity> chooseRarity();

	/**
	 * Picks a random rarity with chooseRarity() method and afterward picks an item of that rarity.
	 * @return Returns {@link Optional}<{@link CommonItem}>.
	 */
	Optional<CommonItem> getItem();
}
