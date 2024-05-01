package com.github.sef24sp4.weaponpack.machinegun;

import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.weaponpack.AbstractProjectile;

public class MachineGunBullet extends AbstractProjectile {
	private final int damage = 2;

	/**
	 * Constructs the BaseProjectile with the shooter.
	 * @param shooter Refereres to the shooter or player, that holds the weapon.
	 */
	protected MachineGunBullet(IEntity shooter) {
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
	 * @return The value of damage.
	 */
	@Override
	public double getAttackDamage() {
		return this.damage;
	}
}
