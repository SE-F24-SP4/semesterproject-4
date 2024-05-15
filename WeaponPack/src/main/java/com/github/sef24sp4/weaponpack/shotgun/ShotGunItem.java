package com.github.sef24sp4.weaponpack.shotgun;

import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.weapon.WeaponSPI;
import com.github.sef24sp4.weaponpack.AbstractWeaponItem;

public class ShotGunItem extends AbstractWeaponItem {
	public ShotGunItem() {
		this.setRotation(3.14);
		this.setPolygonCoordinates(
				new Coordinates(-6, -10),
				new Coordinates(6, -10),
				new Coordinates(6, -8),
				new Coordinates(-2, -8),
				new Coordinates(-2, -6),
				new Coordinates(6, -6),
				new Coordinates(6, 0),
				new Coordinates(-6, 0),
				new Coordinates(-6, -2),
				new Coordinates(2, -2),
				new Coordinates(2, -4),
				new Coordinates(-6, -4),
				new Coordinates(-6, -10)
		);
	}

	@Override
	public WeaponSPI getWeaponSPI() {
		return new ShotGun();
	}
}
