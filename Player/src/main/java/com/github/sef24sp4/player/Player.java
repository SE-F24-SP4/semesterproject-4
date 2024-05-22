package com.github.sef24sp4.player;

import com.github.sef24sp4.common.entities.IHealingEntity;
import com.github.sef24sp4.common.interfaces.GameSettingsLoader;
import com.github.sef24sp4.common.item.itemtypes.ISpeedItem;
import com.github.sef24sp4.common.item.itemtypes.WeaponItem;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;
import com.github.sef24sp4.common.projectile.CommonProjectile;
import com.github.sef24sp4.common.weapon.WeaponSPI;

import java.util.Optional;
import java.util.ServiceLoader;

public final class Player extends CommonEntity implements ICollidableEntity {
	private final double maxHealth = 50;
	private double health = this.maxHealth;
	private final IGameMetadata metadata;
	private static Player activePlayerInstance = reinitializePlayer();
	private final SpeedControl speedControl = new SpeedControl(2);

	private final Optional<WeaponSPI> baseWeapon = ServiceLoader.load(WeaponSPI.class).findFirst();
	private WeaponSPI currentWeapon;
	//Last time hit by an IAttackingEntity in milliseconds.
	private long timeOfLastCollision;
	//Cooldown time between getting hit by IAttackingEntity in milliseconds.
	private long collisionCooldown = 1000;

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

	public static Player getPlayer() {
		return activePlayerInstance;
	}

	public static Player reinitializePlayer() {
		activePlayerInstance = new Player();
		return activePlayerInstance;
	}

	/**
	 * The amount the entity should move
	 * in the X and Y coordinates per game tick.
	 *
	 * @return The walk speed
	 */
	public double getWalkSpeed() {
		return this.speedControl.getSpeed();
	}

	/**
	 * When walking diagonally, the X and Y coordinates
	 * should change by the diagonal walk speed instead of the normal speed.
	 *
	 * @return The diagonal walk speed
	 */
	public double getDiagonalWalkSpeed() {
		return this.speedControl.getDiagonalSpeed();
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
	void kill(IEntityManager entityManager) {
		entityManager.removeEntity(this);
		GameSettingsLoader.load().stopGame();
	}

	/**
	 * Takes the entity's health and subtracts the damage.
	 *
	 * @param damage        The damage of the attacking entity. Has to be positive.
	 * @param entityManager The games entityManager.
	 */
	void takeDamage(double damage, IEntityManager entityManager) {
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
	void heal(double amount) {
		if (amount < 0) throw new IllegalArgumentException("Amount has to be positive");
		this.health += amount;
		if (this.health >= this.maxHealth) this.health = this.maxHealth;
	}

	public Optional<WeaponSPI> getActiveWeapon() {
		if (this.currentWeapon != null && this.currentWeapon.getAmmoCount() <= 0) this.currentWeapon = null;
		if (this.currentWeapon == null) return this.baseWeapon;
		return Optional.of(this.currentWeapon);
	}

	public boolean shoot(final IEntityManager entityManager) {
		return this.getActiveWeapon().map(weaponSPI -> weaponSPI.shoot(entityManager, this)).orElse(false);
	}


	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof CommonProjectile projectile && projectile.getShooter() == this) return;
		if (otherEntity instanceof IHealingEntity healingEntity) {
			this.heal(healingEntity.getHealingAmount());
		}
		if (otherEntity instanceof ISpeedItem speedItem) {
			this.speedControl.setSpeedBuff(speedItem);
		}
		if (otherEntity instanceof WeaponItem weaponItem) {
			this.currentWeapon = weaponItem.getWeaponSPI();
		}
		if (otherEntity instanceof IAttackingEntity attackingEntity) {
			long currentTime = System.currentTimeMillis();
			if (this.timeOfLastCollision + this.collisionCooldown < currentTime) {
				this.takeDamage(attackingEntity.getAttackDamage(), entityManager);
				this.timeOfLastCollision = System.currentTimeMillis();
			}
		}
	}
}