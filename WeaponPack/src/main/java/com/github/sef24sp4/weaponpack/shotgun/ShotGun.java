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

//	//ProjectileSpreader method
//	public IEntity projectileSpreader(IEntityManager entityManager, IEntity shooter) {
//		//StaggeredValue fortæller, hvordan vores projektiler er forskudte fra hinanden.
//		//Dette er en eksperiment.
//		ShotGunBullet shotGunBullet = new ShotGunBullet(shooter);
//		double staggeredValue = Math.PI / 4;
//		//Random er brugt til at fortælle, at der skal skyde/spawne 25 projektiler når vi skyder.
//		Random random = new Random();
//		double spreadValue = random.nextInt(0, 5);
//		for (int i = 0; i < spreadValue; i++) {
//			//Mening med staggeredPosition er at skabe afvigelsen af positionen af projektilerne.
//			double staggeredPosition = this.staggeredProjectiles(staggeredValue);
//			//Efter skaber vi changedRotation, hvor vi forsøger at definere den nye changedRotation.
//			double changedRotation = shooter.getRotation() + random.nextDouble(staggeredPosition / 2, staggeredPosition);
//			//Denne her er meget sjov, fordi her sætter jeg shooterens rotation selvom det burde være projektilet.
//			shotGunBullet.setRotation(changedRotation);
//			//Projektilet er tilføjet og det gør at vi skyder.
//			entityManager.addEntity(this.shotGunBulletControlSystem.createProjectile(shooter));
//		}
//		return shooter;
//	}

//	//staggeredProjectiles method
//	public double staggeredProjectiles(double staggeredValue) {
//		Random randomly = new Random();
//		//Hensigten med return-statement er at kunne skabe forskydningen således at vi kan danne et interval.
//		//Arbejdet er stadigvæk igang.
//		return (randomly.nextDouble() + 1) * staggeredValue;
//	}

	private List<ShotGunBullet> createBullets(final IEntity shooter) {
		List<ShotGunBullet> projectiles = new ArrayList<>();
		//Genererering flere projektiler på samme tid.
		for (int i = 0; i < this.ammountOfBulletsPerShot; i++) {
			double changedRotation = this.getRandomRotation(shooter);
			ShotGunBullet shotGunBullet = new ShotGunBullet(this.shotGunBulletControlSystem.createProjectile(shooter));
			shotGunBullet.setRotation(changedRotation);
			projectiles.add(shotGunBullet);
		}
		return projectiles;
		//Genererering flere projektiler på samme tid.
		//Derefter anvend metoden fra getRandomRotation således, at der er afvigelse mellem projektilerne.
		//Derefter lav en liste således, at du kan gemme projektilerne på.
	}

	private double getRandomRotation(final IEntity shooter) {
		double minimumRotation = shooter.getRotation() - this.maxDeviationFromRotation;
		double maximumRotation = shooter.getRotation() + this.maxDeviationFromRotation;
		double changedRotation = this.random.nextDouble(minimumRotation, maximumRotation);
		//Brug random.getDouble med minimum og maximum således at du kan bestemme hvordan afvigelsen er.
		//Det betyder, at shooter.getRotation()-1 for venstre og shooter.getRotation()+1 for højre.
		//Her bestemmes hvordan afvigelsen er mellem projektilerne.
		return changedRotation;
	}

}