package com.github.sef24sp4.common.item.lootTable;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;

import java.util.*;

public class LootTable {
	private final Map<ItemRarity, Double> itemChances;
	private final Collection<ItemSPI> itemProviders;

	/**
     * Constructs a new LootTable with the given item chances.
     * The item providers are loaded using the ServiceLoader mechanism.
     *
     * @param itemChances a map from {@link ItemRarity} to their chances of type double.
     * @throws IllegalArgumentException if the sum of the chances is greater than 1, or any chance is negative or greater than 1
	 * @see ItemRarity
     */
	public LootTable(Map<ItemRarity, Double> itemChances) {
		this(itemChances, ServiceLoader.load(ItemSPI.class).stream().map(ServiceLoader.Provider::get).toList());
	}

    /**
     * Constructs a new LootTable with the given item chances and item providers. Only used for testing.
     *
     * @param itemChances a map from {@link ItemRarity} to their chances of type double.
     * @param itemProviders a collection of item providers
     * @throws IllegalArgumentException if the sum of the chances is greater than 1, or any chance is negative or greater than 1
	 * @see ItemRarity
     */
	LootTable(Map<ItemRarity, Double> itemChances, final Collection<ItemSPI> itemProviders) {
		this.itemProviders = itemProviders;
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

	/**
     * Chooses a rarity based on the item chances. If the sum of the chances is less than 1, the remaining chance is for Optional.empty().
     *
     * @return an Optional containing the chosen rarity, or an empty Optional if no rarity was chosen
	 * @see ItemRarity
	 * @see Optional
     */
	Optional<ItemRarity> chooseRarity() {
		double random = Math.random();
		double sum = 0;
		for (Map.Entry<ItemRarity, Double> entry : this.itemChances.entrySet()) {
			if (random < entry.getValue() + sum) {
				return Optional.of(entry.getKey());
			} else {
				sum += entry.getValue();
			}
		}
		return Optional.empty();
	}

/**
 * Gets an item from the loot table.
 * The rarity of the item is chosen using the item chances, and then an item of that rarity is chosen from the item providers.
 * If {@link #chooseRarity()} returns an Optional.empty(), this method will also return an Optional.empty().
 *
 * @return an Optional containing the chosen item, or an empty Optional if no item was chosen.
 * @throws Exception if no item providers are available for the chosen rarity.
 * @see #chooseRarity()
 * @see Optional
 */
	public Optional<CommonItem> getItem() throws Exception {
		Optional<ItemRarity> chosenRarity = this.chooseRarity();
		if (chosenRarity.isPresent()) {
			List<ItemSPI> itemSPIList = new ArrayList<>();
			this.itemProviders.forEach(itemSPI -> {
						if (itemSPI.getRarity() == chosenRarity.get()) {
							itemSPIList.add(itemSPI);
						}
					}
			);
			if (itemSPIList.isEmpty()) throw new Exception("ItemSPIList is empty");
			Random random = new Random();
			int randomItem = random.nextInt(itemSPIList.size());
			return Optional.ofNullable(itemSPIList.get(randomItem).getItem());
		}
		return Optional.empty();
	}
}
