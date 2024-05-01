package com.github.sef24sp4.healthitem;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.metadata.GameElementType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HealthItemTest {
	@Mock
	IEntityManager mockEntityManager;

	@Mock
	ICollidableEntity mockEntity;
	@Test
	void collide() {
		HealthItem healthItem = new HealthItem();
		when(mockEntity.getType()).thenReturn(GameElementType.PLAYER);

		healthItem.collide(mockEntityManager, mockEntity);

		verify(mockEntityManager).removeEntity(healthItem);
	}

	@Test
	void getHealingAmount() {
		HealthItem healthItem = new HealthItem();
		assertEquals(healthItem.getHealingAmount(), 10);
	}

	@Test
	void getItem() {
		HealthItemProvider healthItemProvider = new HealthItemProvider();
		HealthItem healthItem = new HealthItem();
		assertEquals(healthItemProvider.getItem(), healthItem);
	}

	@Test
	void getRarity() {
		HealthItemProvider healthItemProvider = new HealthItemProvider();
		HealthItem healthItem = new HealthItem();
		assertEquals(healthItemProvider.getRarity(), ItemRarity.COMMON);
	}
}