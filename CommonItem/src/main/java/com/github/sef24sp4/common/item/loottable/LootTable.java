package com.github.sef24sp4.common.item.loottable;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;

import java.util.*;

public class LootTable {
	private final Map<ItemRarity, Double> itemChances;
	private final Map<ItemRarity, Collection<ItemSPI>> providers;

	/**
	 * Generates a map from {@link ItemRarity} to a collection of {@link ItemSPI} instances.
	 *
	 * @param providers A collection of item providers.
	 * @return A map from {@link ItemRarity} to a collection of {@link ItemSPI} instances.
	 */
	private static Map<ItemRarity, Collection<ItemSPI>> generateItemProvidersMap(final Collection<ItemSPI> providers) {
		final Map<ItemRarity, Collection<ItemSPI>> map = new HashMap<>();
		for (ItemSPI provider : providers) {
			map.computeIfAbsent(provider.getRarity(), k -> new ArrayList<>()).add(provider);
		}
		return Collections.unmodifiableMap(map);
	}

	/**
	 * Validates the itemChances map to ensure that all chances are between or equal to 0 and 1 and that the sum of all chances does not exceed 1.
	 *
	 * @param itemChances A map where the keys are {@link ItemRarity} instances and the values are the chances (as doubles) associated with each rarity.
	 * @throws IllegalArgumentException If any chance is less than 0, greater than 1, or if the sum of all chances is greater than 1.
	 */
	private static void itemChanceValidation(Map<ItemRarity, Double> itemChances) {
		double sumChance = 0;
		for (Map.Entry<ItemRarity, Double> entry : itemChances.entrySet()) {
			double chance = entry.getValue();
			if (chance < 0) throw new IllegalArgumentException("The chance of " + entry.getKey() + " is negative");
			if (chance > 1) throw new IllegalArgumentException("The chance of " + entry.getKey() + " is greater than 1");
			sumChance += chance;
		}
		if (sumChance > 1) throw new IllegalArgumentException("The sum of " + itemChances.keySet() + " is greater than 1");
	}

	/**
	 * Constructs a new LootTable with the given item chances.
	 * The item providers are loaded using the ServiceLoader mechanism.
	 *
	 * @param itemChances a map which maps {@link ItemRarity} to their chances. Chances are of type double.
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
		itemChanceValidation(itemChances);
		this.providers = generateItemProvidersMap(itemProviders);
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
			if (random < entry.getValue() + sum) return Optional.of(entry.getKey());
			sum += entry.getValue();
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
	public Optional<CommonItem> getItem() {
		Optional<ItemRarity> chosenRarity = this.chooseRarity();
		if (chosenRarity.isEmpty() || this.providers.get(chosenRarity.get()) == null) return Optional.empty();

		Collection<ItemSPI> itemSPIs = this.providers.get(chosenRarity.get());
		int randomInt = new Random().nextInt(itemSPIs.size());
		Iterator<ItemSPI> iterator = itemSPIs.iterator();
		for (int i = 0; i < randomInt; i++) {
			iterator.next();
		}
		return Optional.ofNullable(iterator.next().getItem());
	}
}
