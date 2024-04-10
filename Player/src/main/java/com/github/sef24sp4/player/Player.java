package com.github.sef24sp4.player;

import com.github.sef24sp4.common.data.Coordinates;
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
	private double health = 10;
	private double walkSpeed = 2;
	private final IGameMetadata metadata;
	private static final Player PLAYER = new Player();

	private Player() {
		this.metadata = new MetadataBuilder(GameElementType.PLAYER).
				getMetadata();
		this.setPolygonCoordinates(
				new Coordinates(-5, -5),
				new Coordinates(10, 0),
				new Coordinates(-5, 5)
		);
	}
	/**
	 * The player is a singleton.
	 * @return The only instance of the player
	 */
	public static Player getPlayer() {
		return PLAYER;
	}
	/**
	 * The amount the entity should move
	 * in the X and Y cords per game tick.
	 * @return The walk speed
	 */
	public double getWalkSpeed() {
		return this.walkSpeed;
	}
	public void setWalkSpeed(double speed) {
		this.walkSpeed = speed;
	}

	/**
	 * When walking diagonally, the X and Y coordinates
	 * should change by the diagonal walk speed instead of the normal speed.
	 * @return The diagonal walk speed
	 */
	public double getDiagonalWalkSpeed() {
		return this.walkSpeed * (this.walkSpeed / (Math.sqrt(2 * (this.walkSpeed * this.walkSpeed))));
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof CommonProjectile projectile && projectile.getShooter() == this) return;
		if (otherEntity instanceof IAttackingEntity attackingEntity) {
			this.takeDamage(attackingEntity.getAttackDamage());
		}
	}

	public IGameMetadata getMetadata() {
		return this.metadata;
	}

	@Override
	public GameElementType getType() {
		return this.metadata.getType();
	}

	public double getHealth() {
		return this.health;
	}

	public double getMaxHealth() {
		return this.maxHealth;
	}
	/**
	 * Takes the entity's health and subtracts the damage.
	 *
	 * @param damage The damage of the attacking entity.
	 */
	public void takeDamage(double damage) {
		this.health -= damage;
	}
}