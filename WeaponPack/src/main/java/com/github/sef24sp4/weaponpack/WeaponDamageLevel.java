package com.github.sef24sp4.weaponpack;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.projectile.CommonProjectile;

public abstract class WeaponDamageLevel extends CommonProjectile {
	int damageMachineGun = 2;
	int damageShotGun = 3;

	protected WeaponDamageLevel(final IEntity shooter) {
		super(shooter);
	}

	public int getDamageMachineGun() {
		return damageMachineGun;
	}

	public int getDamageShotGun() {
		return damageShotGun;
	}
}
