package com.github.sef24sp4.itempack;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.metadata.GameElementType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpeedItemTest {
	@Mock
	private IEntityManager mockEntityManager;

	@Mock
	private ICollidableEntity mockEntity;


	private SpeedItemProvider speedItemProvider = new SpeedItemProvider();


	private SpeedItem speedItem = new SpeedItem();

	@Test
	void collide() {
		//Setup
		this.mockEntityManager.addEntity(this.speedItem);
		when(this.mockEntity.getType()).thenReturn(GameElementType.PLAYER);
		//Method call
		this.speedItem.collide(this.mockEntityManager, this.mockEntity);
		//Verification
		verify(this.mockEntityManager).removeEntity(this.speedItem);
	}

	@Test
	void collideNotPlayer() {
		//Setup
		this.mockEntityManager.addEntity(this.speedItem);
		when(this.mockEntity.getType()).thenReturn(GameElementType.ENEMY);
		//Method call
		this.speedItem.collide(this.mockEntityManager, this.mockEntity);
		//Verification
		verify(this.mockEntityManager, never()).removeEntity(this.speedItem);
	}

	@Test
	void getSpeedAmount() {
		assertEquals(1.2, this.speedItem.getSpeedAmount());
	}

	@Test
	void getItem() {
		assertInstanceOf(SpeedItem.class, this.speedItemProvider.getItem());
	}

	@Test
	void getRarity() {
		assertEquals(this.speedItemProvider.getRarity(), ItemRarity.UNCOMMON);
	}
}