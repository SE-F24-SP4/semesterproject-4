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

	private ItemSPI mockItemSPI;
	private CommonItem commonItem;

	@BeforeEach
	void setUp() {
		this.rarityChances = new HashMap<>();
		this.lootTable = new LootTable(this.rarityChances);
		this.mockItemSPI = mock(ItemSPI.class);
		this.commonItem = mock(CommonItem.class);
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
	void chooseRarity() {
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 1.0);
		this.lootTable = new LootTable(chances);
		assertEquals(this.lootTable.chooseRarity(), Optional.of(ItemRarity.COMMON));
	}
	@Test
	void testGetEmptyItem() throws Exception {
		//Create LootTable with ItemSPI
		List<ItemSPI> itemProviders = new ArrayList<>();
		itemProviders.add(this.mockItemSPI);
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 0.0);
		LootTable lootTableWithSPI = new LootTable(chances, itemProviders);

		// Call getItem() and assert the result
		Optional<CommonItem> item = lootTableWithSPI.getItem();
		assertTrue(item.isEmpty());
	}

	@Test
	void testSPIListEmpty() throws Exception {
		Exception emptyItemSPIList = new Exception("ItemSPIList is empty");
		//Create LootTable with ItemSPI
		List<ItemSPI> itemProviders = new ArrayList<>();
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 1.0);
		LootTable lootTableWithoutSPI = new LootTable(chances, itemProviders);

		// Call getItem() and assert the result
		assertThrows(Exception.class, lootTableWithoutSPI::getItem);
	}

	@Test
	void testGetItem() throws Exception {
		//Mock
		when(this.mockItemSPI.getRarity()).thenReturn(ItemRarity.COMMON);
		when(this.mockItemSPI.getItem()).thenReturn(this.commonItem);

		//Create LootTable with ItemSPI
		List<ItemSPI> itemProviders = new ArrayList<>();
		itemProviders.add(this.mockItemSPI);
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 1.0);
		LootTable lootTableWithSPI = new LootTable(chances, itemProviders);

		// Call getItem() and assert the result
		Optional<CommonItem> item = lootTableWithSPI.getItem();
		assertTrue(item.isPresent());
		assertSame(this.commonItem, item.get());
	}
}