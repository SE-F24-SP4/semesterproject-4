package com.github.sef24sp4.healthitem;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;

public class HealthItemProvider implements ItemSPI {
	@Override
	public CommonItem getItem() {
		return new HealthItem();
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.COMMON;
	}
}
