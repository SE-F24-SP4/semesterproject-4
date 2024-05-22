package com.github.sef24sp4.newbaseweapon.shotgun;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.newbaseweapon.AbstractProjectile;

public class ShotGunBullet extends AbstractProjectile {
	private final int damage = 3;

	/**
	 * Constructs the BaseProjectile with the shooter.
	 *
	 * @param shooter Refereres to the shooter or player, that holds the weapon.
	 */
	protected ShotGunBullet(IEntity shooter) {
		super(shooter);
		this.setPolygonCoordinates(
				new Coordinates(-2, 2),
				new Coordinates(-2, -2),
				new Coordinates(2, -2),
				new Coordinates(2, 2)
		);
	}

	/**
	 * Gets the amount of damage.
	 *
	 * @return The value of damage.
	 */
	@Override
	public double getAttackDamage() {
		return this.damage;
	}
}