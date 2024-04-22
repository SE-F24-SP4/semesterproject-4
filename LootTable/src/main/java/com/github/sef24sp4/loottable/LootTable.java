package com.github.sef24sp4.loottable;


import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;
import com.github.sef24sp4.commonloottable.LootTableSPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

public final class LootTable implements LootTableSPI {
	/**
	 * Used to get decide what rarity of item is dropped. Can also be null.
	 * @return Returns Optional<ItemRarity> or null.
	 */
	public Optional<ItemRarity> chooseRarity() {
		double random = Math.random();
		if (random < 0.5) {
			return Optional.empty();
		}
		if (random < 0.75) {
			return Optional.of(ItemRarity.COMMON);
		}
		if (random < 0.90) {
			return Optional.of(ItemRarity.UNCOMMON);
		}
		return Optional.of(ItemRarity.RARE);
	}

	/**
	 * Picks a random rarity with chooseRarity() method and afterward picks an item of that rarity.
	 * @return Returns Optional<CommonItem>.
	 */
	public Optional<CommonItem> getItem() {
		Optional<ItemRarity> chosenRarity = chooseRarity();
		if (chosenRarity.isPresent()) {
			ServiceLoader<ItemSPI> itemProviders = ServiceLoader.load(ItemSPI.class);
			List<ItemSPI> itemSPIList = new ArrayList<>();
			itemProviders.forEach(itemSPI -> {
						if (itemSPI.getRarity() == chosenRarity.get()) {
							itemSPIList.add(itemSPI);
						}
					}
			);
			int randomItem = (int) (Math.random() * itemSPIList.size());
			return Optional.ofNullable(itemSPIList.get(randomItem).getItem());
		}
		return Optional.empty();
	}
}