package com.github.sef24sp4.player;

import com.github.sef24sp4.common.entities.IHealingEntity;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;
import com.github.sef24sp4.common.projectile.CommonProjectile;

public final class Player extends CommonEntity implements ICollidableEntity {
	private final double maxHealth = 10;
	private double health = this.maxHealth;
	private double walkSpeed = 2;
	private final IGameMetadata metadata;
	private static final Player PLAYER = new Player();

	private Player() {
		this.metadata = new MetadataBuilder(GameElementType.PLAYER).
				getMetadata();
		this.setPolygonCoordinates(
				new Coordinates(-5, -5),
				new Coordinates(10, 0),
				new Coordinates(-5, 5),
				new Coordinates(-2, 0)
		);
	}

	/**
	 * The player is a singleton.
	 *
	 * @return The only instance of the player
	 */
	public static Player getPlayer() {
		return PLAYER;
	}

	/**
	 * The amount the entity should move
	 * in the X and Y coordinates per game tick.
	 *
	 * @return The walk speed
	 */
	public double getWalkSpeed() {
		return this.walkSpeed;
	}

	/**
	 * Sets the players movement speed to "speed".
	 *
	 * @param speed The amount the player should move per game tick. Needs to be positive.
	 */
	public void setWalkSpeed(double speed) {
		if (speed < 0) throw new IllegalArgumentException("Speed has to be positive");
		this.walkSpeed = speed;
	}

	/**
	 * When walking diagonally, the X and Y coordinates
	 * should change by the diagonal walk speed instead of the normal speed.
	 *
	 * @return The diagonal walk speed
	 */
	public double getDiagonalWalkSpeed() {
		return this.walkSpeed * (this.walkSpeed / (Math.sqrt(2 * (this.walkSpeed * this.walkSpeed))));
	}

	@Override
	public IGameMetadata getMetadata() {
		return this.metadata;
	}

	public double getHealth() {
		return this.health;
	}

	public double getMaxHealth() {
		return this.maxHealth;
	}

	/**
	 * The player is removed.
	 *
	 * @param entityManager The games entityManager.
	 */
	private void kill(IEntityManager entityManager) {
		entityManager.removeEntity(this);
	}

	/**
	 * Takes the entity's health and subtracts the damage.
	 *
	 * @param damage        The damage of the attacking entity. Has to be positive.
	 * @param entityManager The games entityManager.
	 */
	private void takeDamage(double damage, IEntityManager entityManager) {
			if (damage < 0) throw new IllegalArgumentException("Damage has to be positive");
			this.health -= damage;
			if (this.health <= 0) {
				this.kill(entityManager);
			}
	}
	/**
	 * Takes the entity's health and subtracts the damage.
	 *
	 * @param amount The amount the player is healed. Has to be positive.
	 */
	private void heal(double amount) {
		if (amount < 0) throw new IllegalArgumentException("Amount has to be positive");
		this.health += amount;
		if (this.health >= this.maxHealth) this.health = this.maxHealth;
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof CommonProjectile projectile && projectile.getShooter() == this) return;
		if (otherEntity instanceof IHealingEntity healingEntity) {
			this.heal(healingEntity.getHealingAmount());
		}
		if (otherEntity instanceof IAttackingEntity attackingEntity) {
			this.takeDamage(attackingEntity.getAttackDamage(), entityManager);
		}
	}
}