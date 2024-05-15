package com.github.sef24sp4.weaponpack.shotgun;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;

public class ShotGunItemProvider implements ItemSPI {
	@Override
	public CommonItem getItem() {
		return new ShotGunItem();
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.UNCOMMON;
	}
}
