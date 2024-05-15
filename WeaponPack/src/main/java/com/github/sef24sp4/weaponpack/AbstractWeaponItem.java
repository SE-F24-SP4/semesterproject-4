package com.github.sef24sp4.weaponpack;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.itemtypes.WeaponItem;
import com.github.sef24sp4.common.metadata.GameElementType;

public abstract class AbstractWeaponItem extends WeaponItem {
	@Override
	public void collide(final IEntityManager entityManager, final ICollidableEntity otherEntity) {
		if (otherEntity.getType() == GameElementType.PLAYER) {
			entityManager.removeEntity(this);
		}
	}
}
