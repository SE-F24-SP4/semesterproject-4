package com.github.sef24sp4.player;


import com.github.sef24sp4.common.item.itemtypes.ISpeedItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpeedControlTest {

	private SpeedControl speedControl;
	@Mock
	private ISpeedItem speedItem;

	@BeforeEach
	void setUp() {
		this.speedControl = new SpeedControl(2);
	}

	@Test
	void isSpeedBuffActive() {
		when(this.speedItem.getUseDuration()).thenReturn(1000L);

		assertFalse(this.speedControl.isSpeedBuffActive());
		this.speedControl.setSpeedBuff(this.speedItem);
		assertTrue(this.speedControl.isSpeedBuffActive());
	}

	@Test
	void getSpeedNoBuff() {
		assertEquals(this.speedControl.getDefaultSpeed(), this.speedControl.getSpeed());
	}

	@Test
	void setSpeedBuff() {
		when(this.speedItem.getSpeedAmount()).thenReturn(10.0);
		when(this.speedItem.getUseDuration()).thenReturn(1000L);

		this.speedControl.setSpeedBuff(this.speedItem);
		assertEquals(12.0, this.speedControl.getSpeed());
		assertTrue(this.speedControl.isSpeedBuffActive());
	}

	@Test
	void getDefaultSpeed() {
		assertEquals(2, this.speedControl.getDefaultSpeed());
	}

	@Test
	void getExpireTime() {
		this.speedControl.setSpeedBuff(this.speedItem);
		assertTrue(System.currentTimeMillis() + this.speedItem.getUseDuration() >= this.speedControl.getExpireTime());
	}
}