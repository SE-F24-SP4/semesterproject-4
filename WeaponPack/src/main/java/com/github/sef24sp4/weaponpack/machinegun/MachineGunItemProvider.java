package com.github.sef24sp4.weaponpack.machinegun;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;

public class MachineGunItemProvider implements ItemSPI {
	@Override
	public CommonItem getItem() {
		return new MachineGunItem();
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.RARE;
	}
}
