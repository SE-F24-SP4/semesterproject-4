package com.github.sef24sp4.player;

import com.github.sef24sp4.common.item.itemtypes.ISpeedItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeedControlTest {
	private SpeedControl speedControl;
	private ISpeedItem speedItem;

	@BeforeEach
	void setUp() {
		this.speedControl = new SpeedControl(2);

		// Initialize a mock ISpeedItem
		this.speedItem = new ISpeedItem() {

			@Override
			public double getSpeedAmount() {
				return 10;
			}

			@Override
			public long getUseDuration() {
				return 1000; // 1 second
			}
		};
	}

	@Test
	void getSpeedNoBuff() {
		assertEquals(this.speedControl.getDefaultSpeed(), this.speedControl.getSpeed());
	}

	@Test
	void getSpeedWithBuff() {
		this.speedControl.setSpeedBuff(this.speedItem);
		assertEquals(this.speedControl.getDefaultSpeed() + this.speedItem.getSpeedAmount(), this.speedControl.getSpeed());
	}

	@Test
	void setSpeedBuff() {
		this.speedControl.setSpeedBuff(this.speedItem);

		assertEquals(12.0, this.speedControl.getSpeed());
		//Assert that speed buff is active
		assertTrue(this.speedControl.getExpireTime() > System.currentTimeMillis());
		//Assert that spped buff isn't active after use duration
		assertFalse(this.speedControl.getExpireTime() > System.currentTimeMillis() + this.speedItem.getUseDuration());
	}

	@Test
	void getDefaultSpeed() {
		assertEquals(2, this.speedControl.getDefaultSpeed());
	}

	@Test
	void getExpireTime() {
		this.speedControl.setSpeedBuff(this.speedItem);
		assertEquals(System.currentTimeMillis() + this.speedItem.getUseDuration(), this.speedControl.getExpireTime());
	}

	@Test
	void isSpeedBuffActive() {
		assertFalse(this.speedControl.isSpeedBuffActive());
		this.speedControl.setSpeedBuff(this.speedItem);
		assertTrue(this.speedControl.isSpeedBuffActive());
	}
}