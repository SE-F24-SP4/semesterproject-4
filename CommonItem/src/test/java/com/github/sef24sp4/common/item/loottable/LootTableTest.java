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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LootTableTest {


	private Map<ItemRarity, Double> rarityChances;

	@BeforeEach
	void setUp() {
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

	/**
	 * Argument source for {@link #testValidConstructor(<ItemRarity, Double>)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> testValidConstructor() {
		return Stream.of(
				Arguments.of(Map.ofEntries(// TEST CASE 1
						Map.entry(ItemRarity.COMMON, 0.2)
				)),
				Arguments.of(Map.ofEntries(// TEST CASE 2
						Map.entry(ItemRarity.COMMON, 0.5),
						Map.entry(ItemRarity.RARE, 0.1)
				)),
				Arguments.of(Map.ofEntries(// TEST CASE 3
						Map.entry(ItemRarity.UNCOMMON, 0.2),
						Map.entry(ItemRarity.COMMON, 0.5),
						Map.entry(ItemRarity.RARE, 0.3)
				))
		);
	}

	@ParameterizedTest
	@MethodSource
	void testConstructorWithInvalidArguments(final Map<ItemRarity, Double> map) {
		assertThrows(IllegalArgumentException.class, () -> new LootTable(map));
	}
	@ParameterizedTest
	@MethodSource
	void testValidConstructor(final Map<ItemRarity, Double> map) {
		assertDoesNotThrow(() -> new LootTable(map));
	}

	@Test
	void testEmptyChooseRarity() {
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 0.0);
		LootTable lootTable = new LootTable(chances);
		assertTrue(lootTable.chooseRarity().isEmpty());
	}

	@Test
	void testChooseRarity() {
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 1.0);
		LootTable lootTable = new LootTable(chances);
		assertEquals(lootTable.chooseRarity(), Optional.of(ItemRarity.COMMON));
	}
	@Test
	void testGetEmptyItem() {
		//Create LootTable
		Map<ItemRarity, Double> chances = new HashMap<>();
		chances.put(ItemRarity.COMMON, 0.0);
		LootTable lootTable = new LootTable(chances);

		// Call getItem() and assert the result
		Optional<CommonItem> item = lootTable.getItem();
		assertTrue(item.isEmpty());
	}


	@Test
	void testGetItem() {
		//Mock
		ItemSPI mockItemSPI = mock(ItemSPI.class);
		CommonItem commonItem = mock(CommonItem.class);
		when(mockItemSPI.getRarity()).thenReturn(ItemRarity.COMMON);
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