package com.github.sef24sp4.itempack;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.ISpeedModifyingEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.player.Player;

public class SpeedItem extends CommonItem implements ItemSPI, ISpeedModifyingEntity {
	private final ItemRarity rarity = ItemRarity.UNCOMMON;
	private final double speed = 0.5;

	public SpeedItem() {
		this.setRotation(3.14159265);
		this.setPolygonCoordinates(
				new Coordinates(6, 8),
				new Coordinates(11, 8),
				new Coordinates(11, -4),
				new Coordinates(-4, -4),
				new Coordinates(-6, -3),
				new Coordinates(-6, -3),
				new Coordinates(4, 2)
		);
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof Player) {
			entityManager.removeEntity(this);
		}
	}

	@Override
	public CommonItem getItem() {
		return this;
	}

	@Override
	public ItemRarity getRarity() {
		return this.rarity;
	}

	@Override
	public double getSpeedAmount() {
		return this.speed;
	}
}
