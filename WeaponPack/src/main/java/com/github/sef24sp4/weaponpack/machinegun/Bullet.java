package com.github.sef24sp4.weaponpack.machinegun;

import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.weaponpack.AbstractProjectile;

public class Bullet extends AbstractProjectile implements ICollidableEntity, IAttackingEntity {
	private final int damage = 2;

	/**
	 * Constructs the BaseProjectile with the shooter.
	 * @param shooter Refereres to the shooter or player, that holds the weapon.
	 */
	protected Bullet(IEntity shooter) {
		super(shooter);
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
