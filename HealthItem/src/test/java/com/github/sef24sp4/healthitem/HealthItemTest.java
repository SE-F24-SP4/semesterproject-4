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

	@Mock
	private HealthItem healthItem = new HealthItem();

	@Mock
	private HealthItemProvider healthItemProvider = new HealthItemProvider();

	@Test
	void collide() {
		//Setup
		this.mockEntityManager.addEntity(this.healthItem);
		when(this.mockEntity.getType()).thenReturn(GameElementType.PLAYER);
		//Method call
		this.healthItem.collide(this.mockEntityManager, this.mockEntity);
		//Verification
		verify(this.mockEntityManager).removeEntity(this.healthItem);
	}

	@Test
	void collideNotPlayer() {
	    //Setup
	    this.mockEntityManager.addEntity(this.healthItem);
		when(this.mockEntity.getType()).thenReturn(GameElementType.ENEMY);
		//Method call
		this.healthItem.collide(this.mockEntityManager, this.mockEntity);
	    //Verification
	    verify(this.mockEntityManager, never()).removeEntity(this.healthItem);
	}

	@Test
	void getHealingAmount() {
		assertEquals(this.healthItem.getHealingAmount(), 10);
	}

	@Test
	void getItem() {
		assertInstanceOf(HealthItem.class, this.healthItemProvider.getItem());
	}

	@Test
	void getRarity() {
		assertEquals(this.healthItemProvider.getRarity(), ItemRarity.COMMON);
	}
}