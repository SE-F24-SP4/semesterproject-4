package com.github.sef24sp4.weaponpack.shotgun;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.itemtypes.WeaponItem;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.weapon.WeaponSPI;

public class ShotGunItem extends WeaponItem {
	@Override
	public WeaponSPI getWeaponSPI() {
		return null;
	}

	@Override
	public void collide(final IEntityManager entityManager, final ICollidableEntity otherEntity) {

	}

	@Override
	public double[] getPolygonValuesArray() {
		return super.getPolygonValuesArray();
	}

	@Override
	public double getX() {
		return super.getX();
	}

	@Override
	public double getY() {
		return super.getY();
	}

	@Override
	public GameElementType getType() {
		return super.getType();
	}
}
