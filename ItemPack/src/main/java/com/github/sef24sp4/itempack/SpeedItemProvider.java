package com.github.sef24sp4.itempack;

import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;

public class SpeedItemProvider implements ItemSPI {
	@Override
	public CommonItem getItem() {
		return new SpeedItem();
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.UNCOMMON;
	}
}