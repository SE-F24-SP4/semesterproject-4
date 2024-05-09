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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LootTableTest {
	private LootTable lootTable;
	private Map<ItemRarity, Double> rarityChances;

	@BeforeEach
	void setUp() {
		this.rarityChances = new HashMap<>();
		this.lootTable = new LootTable(this.rarityChances);
	}

	@Test
	void testConstructorWithInvalidArguments() {
		this.rarityChances.put(ItemRarity.COMMON, -0.1);
		assertThrows(IllegalArgumentException.class, () -> new LootTable(this.rarityChances));
		this.rarityChances.put(ItemRarity.UNCOMMON, 1.1);
		assertThrows(IllegalArgumentException.class, () -> new LootTable(this.rarityChances));
		this.rarityChances.put(ItemRarity.RARE, 0.6);
		assertThrows(IllegalArgumentException.class, () -> new LootTable(this.rarityChances));
	}

	@Test
	void emptyChooseRarity() {
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 0.0);
		this.lootTable = new LootTable(chances);
		assertEquals(this.lootTable.chooseRarity(), Optional.empty());
	}

	@Test
	void testGetItem() throws Exception {
		//Mock
		ItemSPI mockItemSPI = mock(ItemSPI.class);
		when(mockItemSPI.getRarity()).thenReturn(ItemRarity.COMMON);
		CommonItem commonItem = mock(CommonItem.class);
		when(mockItemSPI.getItem()).thenReturn(commonItem);

		//Create LootTable with ItemSPI
		List<ItemSPI> itemProviders = new ArrayList<>();
		itemProviders.add(mockItemSPI);
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 1.0);
		LootTable lootTableWithSPI = new LootTable(chances, itemProviders);

		// Call getItem() and assert the result
		Optional<CommonItem> item = lootTableWithSPI.getItem();
		assertTrue(item.isPresent());
		assertSame(commonItem, item.get());
	}
}