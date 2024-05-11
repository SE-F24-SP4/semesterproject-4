package com.github.sef24sp4.player;


import com.github.sef24sp4.common.item.itemtypes.ISpeedItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpeedControlTest {
	private SpeedControl speedControl;
	private ISpeedItem speedItem;

	@BeforeEach
	void setUp() {
		this.speedControl = new SpeedControl(2);
		this.speedItem = mock(ISpeedItem.class);
		when(this.speedItem.getSpeedAmount()).thenReturn(10.0);
		when(this.speedItem.getUseDuration()).thenReturn(1000L);
	}

	@Test
	void getSpeedNoBuff() {
		assertEquals(this.speedControl.getDefaultSpeed(), this.speedControl.getSpeed());
	}

	@Test
	void setSpeedBuff() {
		this.speedControl.setSpeedBuff(this.speedItem);

		assertEquals(12.0, this.speedControl.getSpeed());
		//Assert that speed buff is active
		assertTrue(this.speedControl.isSpeedBuffActive());
		//Assert that speed buff isn't active after use duration
		assertFalse(this.speedControl.getExpireTime() > System.currentTimeMillis() + this.speedItem.getUseDuration());
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

	@Test
	void isSpeedBuffActive() {
		assertFalse(this.speedControl.isSpeedBuffActive());
		this.speedControl.setSpeedBuff(this.speedItem);
		assertTrue(this.speedControl.isSpeedBuffActive());
	}
}