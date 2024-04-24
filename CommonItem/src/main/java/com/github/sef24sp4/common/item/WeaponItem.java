package com.github.sef24sp4.common.item;

import com.github.sef24sp4.common.weapon.WeaponSPI;

public interface WeaponItem extends ItemSPI {
	/**
	 * Used to fetch the weaponSPI for a weaponItem.
	 * @return Returns the {@link WeaponSPI} for weaponItem.
	 * @see WeaponSPI
	 */
	WeaponSPI getWeaponSPI();
}
