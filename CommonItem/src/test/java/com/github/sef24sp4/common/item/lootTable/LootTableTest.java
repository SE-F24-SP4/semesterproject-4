package com.github.sef24sp4.common.item.lootTable;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LootTableTest {
	@ExtendWith(MockitoExtension.class)
	private LootTable lootTable;
	private Map<ItemRarity, Double> rarityChances;
	/*private Map<ItemRarity, Double> rarityChances = Map.ofEntries(
			Map.entry(ItemRarity.COMMON, 1.0),
			Map.entry(ItemRarity.UNCOMMON, 0.0),
			Map.entry(ItemRarity.RARE, 0.0)
			);

	private LootTable lootTable = new LootTable(rarityChances);
	*/
	@BeforeEach
	void setUp() {
		rarityChances = new HashMap<>();
		rarityChances.put(ItemRarity.COMMON, 0.5);
		rarityChances.put(ItemRarity.RARE, 0.5);
		lootTable = new LootTable(rarityChances);
	}
	@Test
	void testConstructor_invalidChances() {
		rarityChances.put(ItemRarity.COMMON, -0.1);
		assertThrows(IllegalArgumentException.class, () -> new LootTable(rarityChances));
		rarityChances.put(ItemRarity.UNCOMMON, 1.1);
		assertThrows(IllegalArgumentException.class, () -> new LootTable(rarityChances));
		rarityChances.put(ItemRarity.RARE, 0.6);
		assertThrows(IllegalArgumentException.class, () -> new LootTable(rarityChances));
	}
	@Test
	void emptyChooseRarity() {
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 0.0);
		lootTable = new LootTable(chances);
		assertEquals(lootTable.chooseRarity(), Optional.empty());
	}
	@Test
	void testGetItem() {
		ItemSPI itemSPI = mock(ItemSPI.class);
		CommonItem item = mock(CommonItem.class);
		LootTable mockLootTable = mock(LootTable.class);
		when(itemSPI.getRarity()).thenReturn(ItemRarity.COMMON);
		when(itemSPI.getItem()).thenReturn(item);
		when(mockLootTable.chooseRarity()).thenReturn(Optional.of(ItemRarity.COMMON));

		Optional<CommonItem> commonItem = lootTable.getItem();
		assertTrue(commonItem.isPresent());
		//assertEquals(ItemRarity.COMMON, item.);
	}
}