package com.github.sef24sp4.itempack;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.item.itemtypes.ISpeedItem;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.vector.Coordinates;

public class SpeedItem extends CommonItem implements ISpeedItem {
	private final ItemRarity rarity = ItemRarity.UNCOMMON;
	private final double speed = 0.2;
	private final long itemDuration = 1_000_000_000L * 10;

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
		if (otherEntity.getType() == GameElementType.PLAYER) {
			entityManager.removeEntity(this);
		}
	}

	@Override
	public double getSpeedAmount() {
		return this.speed;
	}

	@Override
	public long getUseDuration() {
		return this.itemDuration;
	}
}
