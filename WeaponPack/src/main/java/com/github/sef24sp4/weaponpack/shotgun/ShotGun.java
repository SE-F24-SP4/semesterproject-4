package com.github.sef24sp4.weaponpack.shotgun;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.weapon.WeaponSPI;

import java.util.Random;

public class ShotGun implements WeaponSPI {
	//Variables defined for ammoCount and projectiles.
	private int ammoCount = 25;
	private final MunitionControlSystem munitionControlSystem = new MunitionControlSystem();
	private final long maxCountDownTicks = 1_000_000_000 / 16;
	private long timeOfLastShot;

	public ShotGun() {
		/*
		 * Allow shooting as soon the MachineGun is loaded.
		 * This works by artificially setting the timeOfLastShot to before {@code now - maxCoolDownTicks}.
		 */
		this.timeOfLastShot = System.nanoTime() - this.maxCountDownTicks;
	}

	//Shoot-method ensures, that if the ticks are high, and if there is no ammunition, then the gun cannot continue.
	//But if this statement needs to be true, then we need to add a bullet, which decreases based on the ammoCount--;
	@Override
	public boolean shoot(IEntityManager entityManager, IEntity shooter) {
		if (this.ammoCount <= 0 || this.getRemainingCoolDownTicks() > 0) return false;
		this.timeOfLastShot = System.nanoTime();
		entityManager.addEntity(this.munitionControlSystem.createProjectile(shooter));
		this.ammoCount--;
		return true;
	}

	public IEntity projectileSpreader(IEntityManager entityManager, IEntity shooter) {
		//StaggeredValue fortæller, hvordan vores projektiler er forskudte fra hinanden.
		//Dette er en eksperiment.
		double staggeredValue = Math.PI / 4;
		//Random er brugt til at fortælle, at der skal skyde/spawne 25 projektiler når vi skyder.
		Random random = new Random();
		double spreadValue = random.nextInt(0, 25);
		for (int i = 0; i < spreadValue; i++) {
			//Mening med staggeredPosition er at skabe afvigelsen af positionen af projektilerne.
			double staggeredPosition = this.staggeredProjectiles(staggeredValue);
			//Efter skaber vi changedRotation, hvor vi forsøger at definere den nye changedRotation.
			double changedRotation = shooter.getRotation() + random.nextDouble(staggeredPosition / 2, staggeredPosition);
			//Denne her er meget sjov, fordi her sætter jeg shooterens rotation selvom det burde være projektilet.
			shooter.setRotation(changedRotation);
			//Projektilet er tilføjet og det gør at vi skyder.
			entityManager.addEntity(this.munitionControlSystem.createProjectile(shooter));
		}
		return shooter;
	}

	public double staggeredProjectiles(double staggeredValue) {
		Random randomly = new Random();
		//Hensigten med return-statement er at kunne skabe forskydningen således at vi kan danne et interval.
		//Arbejdet er stadigvæk igang.
		return (randomly.nextDouble() + 1) * staggeredValue;
	}

	/**
	 * Gets the amount of ammunition.
	 *
	 * @return The value of ammunition.
	 */
	@Override
	public int getAmmoCount() {
		return this.ammoCount;
	}

	/**
	 * The method defines remainingCoolDownTicks, which is the time from last shot and to the current time.
	 *
	 * @return The value of remainingCoolDownTicks.
	 */
	@Override
	public long getRemainingCoolDownTicks() {
		final long remainingCoolDownTicks = this.maxCountDownTicks - (System.nanoTime() - this.timeOfLastShot);
		if (remainingCoolDownTicks <= 0) return 0;
		return remainingCoolDownTicks;
	}
}
