package com.github.sef24sp4.player;

import com.github.sef24sp4.common.item.itemtypes.ISpeedItem;

public class SpeedControl {
	private final double defaultSpeed;
	private double speed;

	private long expireTime;

	SpeedControl(final double defaultSpeed) {
		this.defaultSpeed = defaultSpeed;
		this.speed = defaultSpeed;
	}

	/**
	 * Retrieves the current speed of the SpeedController.
	 * <p>
	 * This method is typically invoked when the player needs to get the speed, the speed is needed for calculations or displayed in the UI.
	 * If {@link #isSpeedBuffActive()} is true, then this returns the current {@link #speed}, otherwise it returns {@link #defaultSpeed}.
	 *
	 * @return The current speed of the entity.
	 */
	public double getSpeed() {
		if (this.isSpeedBuffActive()) return this.speed;
		this.speed = this.defaultSpeed;
		return this.defaultSpeed;
	}

	/**
	 * Sets the speed of the controller and the duration for which this speed should be active.
	 * <p>
	 * This method is typically invoked when a speed buff is applied to the player. The speed and duration is fetched from the speedItem
	 * and is added to the current speed,
	 *
	 * @param speedItem A speedItem that has a speedAmount and useDuration that gets added to the entity's current speed.
	 *                  The duration is added to the current time to determine the time when the speed buff expires.
	 * @see ISpeedItem
	 */
	public void setSpeedBuff(ISpeedItem speedItem) {
		this.speed += speedItem.getSpeedAmount();
		this.expireTime = System.currentTimeMillis() + speedItem.getUseDuration();
	}

	/**
	 * Retrieves the default speed of the controller.
	 * <p>
	 * This method is typically invoked when the player's default speed needs to be used for calculations or reset operations.
	 *
	 * @return The default speed of the entity.
	 */
	public double getDefaultSpeed() {
		return this.defaultSpeed;
	}

	/**
	 * Retrieves the time at which the current speed buff should expire.
	 * <p>
	 * This method is typically invoked when checking if the speed buff duration has passed and the player's speed should be reset to its default value.
	 *
	 * @return The time in milliseconds at which the speed buff should expire.
	 */
	public long getExpireTime() {
		return this.expireTime;
	}

	/**
	 * Checks if a speed buff is currently active on the player.
	 * <p>
	 * This method is typically invoked to check if there is an active speed buff or when determining if the player's speed should be reset to its default value.
	 *
	 * @return True if a speed buff is currently active on the player, false otherwise.
	 */
	public boolean isSpeedBuffActive() {
		return this.expireTime >= System.currentTimeMillis();
	}

	/**
	 * Retrieves the player's speed for when moving diagonally.
	 * <p>
	 * This method is typically invoked when the player is moving in a diagonal direction.
	 * It calculates the speed based on the Pythagorean theorem to ensure the entity moves at the same speed in all directions.
	 *
	 * @return The entity's speed when moving diagonally.
	 */
	public double getDiagonalSpeed() {
		double currentSpeed = this.getSpeed();
		return currentSpeed * (currentSpeed / (Math.sqrt(2 * (currentSpeed * currentSpeed))));
	}
}
