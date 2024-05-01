package com.github.sef24sp4.healthitem;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.metadata.GameElementType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HealthItemTest {
	@Mock
	private IEntityManager mockEntityManager = mock(IEntityManager.class);

	@Mock
	private ICollidableEntity mockEntity = mock(ICollidableEntity.class);

	@Test
	void collide() {
		//Setup
		HealthItem healthItem = new HealthItem();
		this.mockEntityManager.addEntity(healthItem);
		when(this.mockEntity.getType()).thenReturn(GameElementType.PLAYER);
		//Method call
		healthItem.collide(this.mockEntityManager, this.mockEntity);
		//Verification
		verify(this.mockEntityManager).removeEntity(healthItem);
	}

	@Test
	void collideNotPlayer() {
    //Setup
    HealthItem healthItem = new HealthItem();
		this.mockEntityManager.addEntity(healthItem);
    when(this.mockEntity.getType()).thenReturn(GameElementType.ENEMY);
    //Method call
    healthItem.collide(this.mockEntityManager, this.mockEntity);
    //Verification
    verify(this.mockEntityManager, never()).removeEntity(healthItem);
}

	@Test
	void getHealingAmount() {
		HealthItem healthItem = new HealthItem();
		assertEquals(healthItem.getHealingAmount(), 10);
	}

	@Test
	void getItem() {
		HealthItemProvider healthItemProvider = new HealthItemProvider();
		assertInstanceOf(HealthItem.class, healthItemProvider.getItem());
	}

	@Test
	void getRarity() {
		HealthItemProvider healthItemProvider = new HealthItemProvider();
		assertEquals(healthItemProvider.getRarity(), ItemRarity.COMMON);
	}
}