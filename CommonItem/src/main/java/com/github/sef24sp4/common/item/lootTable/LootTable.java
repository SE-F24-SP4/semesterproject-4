package com.github.sef24sp4.common.item.lootTable;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;

import java.util.*;

public class LootTable {
	private final Map<ItemRarity, Double> itemChances;
	private final ServiceLoader<ItemSPI> itemProviders = ServiceLoader.load(ItemSPI.class);

	public LootTable(Map<ItemRarity, Double> itemChances) {
		double sumChance = 0;
		for (Map.Entry<ItemRarity, Double> entry : itemChances.entrySet()) {
			Double chance = entry.getValue();
			if (chance < 0) throw new IllegalArgumentException();
			if (chance > 1) throw new IllegalArgumentException();
			sumChance += chance;
		}
		if (sumChance > 1) throw new IllegalArgumentException();
		this.itemChances = itemChances;
	}

	private Optional<ItemRarity> chooseRarity() {
		double random = Math.random();
		double sum = 0;
		for (Map.Entry<ItemRarity, Double> entry : itemChances.entrySet()) {
			if (random < entry.getValue() + sum) {
				return Optional.of(entry.getKey());
			} else {
				sum += entry.getValue();
			}
		}
		return Optional.empty();
	}

	public Optional<CommonItem> getItem() {
		Optional<ItemRarity> chosenRarity = this.chooseRarity();
		if (chosenRarity.isPresent()) {
			List<ItemSPI> itemSPIList = new ArrayList<>();
			itemProviders.forEach(itemSPI -> {
						if (itemSPI.getRarity() == chosenRarity.get()) {
							itemSPIList.add(itemSPI);
						}
					}
			);
			Random random = new Random();
			int randomItem = random.nextInt(itemSPIList.size());

			return Optional.ofNullable(itemSPIList.get(randomItem).getItem());
		}
		return Optional.empty();
	}
}
