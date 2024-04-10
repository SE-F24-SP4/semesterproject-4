package com.github.sef24sp4.weaponpack.machinegun;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.weapon.WeaponSPI;

public class BaseMachineGun implements WeaponSPI{
	//Husk, at denne Machine Gun skal skyde med (begrænset antal) bullets.
	//Kan du bruge MIN_VALUE istedet for MAX, siden det repræsenterer det mindste værdi for integer??
	private final int ammoCount = Integer.MIN_VALUE;
	private final BulletControlSystem bulletControlSystem = new BulletControlSystem();

	private final long maxCountDownTicks = 1_000_000_000 / 4;
	private long timeOfLastShot;

	public BaseMachineGun(){
		this.timeOfLastShot = System.nanoTime() - this.maxCountDownTicks;
	}

	@Override
	public boolean shoot(IEntityManager entityManager, IEntity shooter) {
		if (this.getRemainingCoolDownTicks() > 0) return false;
		this.timeOfLastShot = System.nanoTime();
		entityManager.addEntity(this.bulletControlSystem.createProjectile(shooter));
		return true;
	}

	@Override
	public int getAmmoCount() {
		return this.ammoCount;
	}

	@Override
	public long getRemainingCoolDownTicks() {
		final long remainingCoolDownTicks = this.maxCountDownTicks - (System.nanoTime() - this.timeOfLastShot);
		if (remainingCoolDownTicks <= 0) return 0;
		return remainingCoolDownTicks;
	}
}
