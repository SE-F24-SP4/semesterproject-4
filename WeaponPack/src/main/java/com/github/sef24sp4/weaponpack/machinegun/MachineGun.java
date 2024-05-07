package com.github.sef24sp4.weaponpack.machinegun;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.weaponpack.AbstractWeapon;

public class MachineGun extends AbstractWeapon {
	//Variables defined for ammoCount and projectiles.
	private final MachineGunBulletControlSystem machineGunBulletControlSystem = new MachineGunBulletControlSystem();

	public MachineGun() {
		super(1_000_000_000 / 16, 250);
	}

	//Shoot-method ensures, that if the ticks are high, and if there is no ammunition, then the gun cannot continue.
	//But if this statement needs to be true, then we need to add a bullet, which decreases based on the ammoCount--;
	@Override
	public boolean shoot(IEntityManager entityManager, IEntity shooter) {
		if (!this.prepareShootIfPossible()) return false;
		entityManager.addEntity(this.machineGunBulletControlSystem.createProjectile(shooter));
		return true;
	}


}
