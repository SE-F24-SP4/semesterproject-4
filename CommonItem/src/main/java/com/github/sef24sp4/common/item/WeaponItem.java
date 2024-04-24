package com.github.sef24sp4.common.item;

import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;
import com.github.sef24sp4.common.weapon.CommonWeapon;
import com.github.sef24sp4.common.weapon.WeaponSPI;

public abstract class WeaponItem extends CommonEntity implements ICollidableEntity {
	private final WeaponSPI weaponSPI;
	private final IGameMetadata metadata;
	public WeaponItem(CommonWeapon weapon) {
		this.weaponSPI = (WeaponSPI) weapon;
		this.metadata = new MetadataBuilder(GameElementType.ITEM).getMetadata();
	}

	@Override
	public IGameMetadata getMetadata() {
		return this.metadata;
	}

	/**
	 * Used to fetch the weaponSPI for a weaponItem.
	 *
	 * @return Returns the {@link WeaponSPI} for weaponItem.
	 * @see WeaponSPI
	 */
	public WeaponSPI getWeaponSPI() {
		return this.weaponSPI;
	}
}
