package com.github.sef24sp4.weaponpack.machinegun;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.itemtypes.WeaponItem;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.weapon.WeaponSPI;

public class MachineGunItem extends WeaponItem {

	public WeaponSPI getWeaponSPI() {
		return new MachineGun();
	}

	@Override
	public void collide(final IEntityManager entityManager, final ICollidableEntity otherEntity) {
		if (otherEntity.getType() == GameElementType.OTHER) {
			entityManager.removeEntity(this);
		}
	}

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
}
