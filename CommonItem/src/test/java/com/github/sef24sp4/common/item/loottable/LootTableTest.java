package com.github.sef24sp4.common.item.loottable;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LootTableTest {
	@Mock
	private ItemSPI mockItemSPI;
	@Mock
	private CommonItem commonItem;

	private Map<ItemRarity, Double> rarityChances;

	@BeforeEach
	void setUp() {
		this.mockItemSPI = mock(ItemSPI.class);
		this.commonItem = mock(CommonItem.class);
		this.rarityChances = new HashMap<>();
	}

	/**
	 * Argument source for {@link #testConstructorWithInvalidArguments(Map<ItemRarity, Double>)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> testConstructorWithInvalidArguments() {
		return Stream.of(
				Arguments.of(Map.ofEntries(// TEST CASE 1
						Map.entry(ItemRarity.COMMON, 1.2)
				)),
				Arguments.of(Map.ofEntries(// TEST CASE 2
						Map.entry(ItemRarity.COMMON, 0.5),
						Map.entry(ItemRarity.RARE, -0.1)
				)),
				Arguments.of(Map.ofEntries(// TEST CASE 3
						Map.entry(ItemRarity.UNCOMMON, 0.2),
						Map.entry(ItemRarity.COMMON, 0.5),
						Map.entry(ItemRarity.RARE, 0.4)
				))
		);
	}

	@ParameterizedTest
	@MethodSource
	void testConstructorWithInvalidArguments(final Map<ItemRarity, Double> map) {
		assertThrows(IllegalArgumentException.class, () -> new LootTable(map));
	}

	@Test
	void validConstructor() {
		assertDoesNotThrow(() -> new LootTable(this.rarityChances));
	}

	@Test
	void emptyChooseRarity() {
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 0.0);
		LootTable lootTable = new LootTable(chances);
		assertTrue(lootTable.chooseRarity().isEmpty());
	}

	@Test
	void chooseRarity() {
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 1.0);
		LootTable lootTable = new LootTable(chances);
		lootTable = new LootTable(chances);
		assertEquals(lootTable.chooseRarity(), Optional.of(ItemRarity.COMMON));
	}
	@Test
	void testGetEmptyItem() throws Exception {
		//Create LootTable
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 0.0);
		LootTable lootTable = new LootTable(chances);

		// Call getItem() and assert the result
		Optional<CommonItem> item = lootTable.getItem();
		assertTrue(item.isEmpty());
	}

	@Test
	void testSPIListEmpty() throws Exception {
		//Create LootTable with ItemSPI
		List<ItemSPI> itemProviders = new ArrayList<>();
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 1.0);
		LootTable lootTableWithoutSPI = new LootTable(chances, itemProviders);

		// Call getItem() and assert the result
		assertTrue(lootTableWithoutSPI.getItem().isEmpty());
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