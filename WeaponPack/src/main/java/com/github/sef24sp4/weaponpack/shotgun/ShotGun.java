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
	private final int numberOfbulletsPerShot = 7;
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

	//This method generates 7 bullets scattered in different rotations.
	//This method is inspired by {@link Viveks AsteroidsFX Project}
	private List<ShotGunBullet> createShotGunBullets(final IEntity shooter) {
		List<ShotGunBullet> projectiles = new ArrayList<>();
		for (int i = 0; i < this.numberOfbulletsPerShot; i++) {
			double deviatedRotation = this.getRandomDeviatedRotationBetweenBullets(shooter);
			ShotGunBullet shotGunBullet = new ShotGunBullet(shooter);
			shotGunBullet.setRotation(deviatedRotation);
			projectiles.add(shotGunBullet);
		}
		return projectiles;
	}


	//This method ensures, that there is a deviation between the projectiles when shot.
	//return-statement has been a bit inspired from {@link https://stackoverflow.com/questions/22110772/how-do-i-get-a-random-double-within-a-specified-range}
	private double getRandomDeviatedRotationBetweenBullets(final IEntity shooter) {
		double leftMininumRotation = shooter.getRotation() - this.maxDeviationFromRotation;
		double rightMaximumRotation = shooter.getRotation() + this.maxDeviationFromRotation;
		return this.random.nextDouble(leftMininumRotation, rightMaximumRotation);
	}
}