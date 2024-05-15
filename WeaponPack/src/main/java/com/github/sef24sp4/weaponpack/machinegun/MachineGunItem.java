package com.github.sef24sp4.weaponpack.machinegun;

import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.weapon.WeaponSPI;
import com.github.sef24sp4.weaponpack.AbstractWeaponItem;

public class MachineGunItem extends AbstractWeaponItem {

	public MachineGunItem() {
		this.setRotation(3.14);
		this.setPolygonCoordinates(
				new Coordinates(-10, -10),
				new Coordinates(-6, -10),
				new Coordinates(-6, 6),
				new Coordinates(-2, 6),
				new Coordinates(-2, -8),
				new Coordinates(2, -8),
				new Coordinates(2, 6),
				new Coordinates(6, 6),
				new Coordinates(6, -10),
				new Coordinates(10, -10),
				new Coordinates(10, 10),
				new Coordinates(-10, 10),
				new Coordinates(-10, -10)
		);
	}

	@Override
	public WeaponSPI getWeaponSPI() {
		return new MachineGun();
	}
}
