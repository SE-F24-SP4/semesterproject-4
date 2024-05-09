package com.github.sef24sp4.weaponpack.shotgun;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.weaponpack.AbstractWeapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShotGun extends AbstractWeapon {
	//Variables defined for ammoCount and projectiles.
	private final Random random = new Random();
	private final double maxDeviationFromRotation = 5;
	private final int ammountOfBulletsPerShot = 7;
	private final ShotGunBulletControlSystem shotGunBulletControlSystem = new ShotGunBulletControlSystem();

	public ShotGun() {
		super(1_000_000_000 / 16, 25);
	}

	//Shoot-method ensures, that if the ticks are high, and if there is no ammunition, then the gun cannot continue.
	//But if this statement needs to be true, then we need to add a bullet, which decreases based on the ammoCount--;
	@Override
	public boolean shoot(IEntityManager entityManager, IEntity shooter) {
		if (!this.prepareShootIfPossible()) return false;
		entityManager.addEntity(this.shotGunBulletControlSystem.createProjectile(shooter));
		return true;
	}

	private List<ShotGunBullet> createBullets(final IEntity shooter) {
		List<ShotGunBullet> projectiles = new ArrayList<>();
		for (int i = 0; i < this.ammountOfBulletsPerShot; i++) {
			double changedRotation = this.getRandomRotation(shooter);
			ShotGunBullet shotGunBullet = new ShotGunBullet(shooter);
			shotGunBullet.setRotation(changedRotation);
			projectiles.add(shotGunBullet);
		}
		return projectiles;
	}

	private double getRandomRotation(final IEntity shooter) {
		double leftRotation = shooter.getRotation() - this.maxDeviationFromRotation;
		double rightRotation = shooter.getRotation() + this.maxDeviationFromRotation;
		double changedRotation = this.random.nextDouble(leftRotation, rightRotation);
		return changedRotation;
	}

}