package com.github.sef24sp4.player;

public class SpeedControl {
	private double defaultSpeed = 2;
	private double speed;
	private long expireTime;
	private boolean active = false;

	SpeedControl(double defaultSpeed) {
		this.defaultSpeed = defaultSpeed;
		this.speed = defaultSpeed;
	}

	/**
	 * Retrieves the current speed of the SpeedController.
	 * <p>
	 * This method is typically invoked when the player needs to get the speed, the speed is needed for calculations or displayed in the UI.
	 *
	 * @return The current speed of the entity.
	 */
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Sets the speed of the controller and the duration for which this speed should be active.
	 * <p>
	 * This method is typically invoked when a speed buff is applied to the player. The speed parameter is added to the current speed,
	 * and the duration parameter determines how long this speed buff should last.
	 * Also "active" is set to true to indicate that a buff is active.
	 * @param speedBonus The additional speed units to be added to the entity's current speed.
	 * @param duration The duration in nanoseconds for which the speed buff should be active.
	 * The duration is added to the current time to determine the time when the speed buff expires.
	 */
	public void setSpeed(double speedBonus, long duration) {
		this.speed += speedBonus;
		this.expireTime = System.nanoTime() + duration;
		this.active = true;
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
	 * This method is typically invoked when checking if the speed buff duration has passed and the players's speed should be reset to its default value.
	 *
	 * @return The time in nanoseconds at which the speed buff should expire.
	 */
	public long getExpireTime() {
		return this.expireTime;
	}

	/**
	 * Checks if a speed buff is currently active on the player.
	 * <p>
	 * This method is typically invoked to check if thhere is an active speed buff or when determining if the player's speed should be reset to its default value.
	 *
	 * @return True if a speed buff is currently active on the player, false otherwise.
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * Updates the player's speed based on the current time.
	 * <p>
	 * This method is typically invoked in the game update loop. If a speed buff is active and its duration has passed,
	 * the entity's speed is reset to its default value and the active flag is set to false.
	 *
	 * @param currentTime The current time in nanoseconds.
	 */
	public void updateSpeed(long currentTime) {
		if (this.active & this.expireTime < currentTime) {
			this.active = false;
			this.speed = this.defaultSpeed;
		}
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
		return this.speed * (this.speed / (Math.sqrt(2 * (this.speed * this.speed))));
	}
}
