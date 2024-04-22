package com.github.sef24sp4.commonloottable;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

public interface LootTableSPI {
	/**
	 * Used to get decide what rarity of item is dropped. Can also be null.
	 * @return Returns a ItemRarity or null.
	 */
	Optional<ItemRarity> chooseRarity();

	/**
	 * Picks a random rarity of afterward picks an item of that rarity.
	 * @return Returns a CommonItem.
	 */
	Optional<CommonItem> getItem();
}
