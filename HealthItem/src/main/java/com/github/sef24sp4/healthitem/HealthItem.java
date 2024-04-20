package com.github.sef24sp4.healthitem;


import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IHealingEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.ItemSPI;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.player.Player;

public class HealthItem extends CommonItem implements ItemSPI, IHealingEntity {
	private final double healAmount = 10;
	private  final  ItemRarity rarity = ItemRarity.COMMON;
	public HealthItem() {
		this.setRotation(3.14159265);
		this.setPolygonCoordinates(
				new Coordinates(0, -5),
				new Coordinates(6, 1),
				new Coordinates(5, 4),
				new Coordinates(2, 5),
				new Coordinates(0, 2),
				new Coordinates(-2, 5),
				new Coordinates(-5, 4),
				new Coordinates(-6, 1)
		);
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof Player) {
			entityManager.removeEntity(this);
		}
	}

	@Override
	public double getHealingAmount() {
		return this.healAmount;
	}

	@Override
	public CommonItem getItem() {
		return this;
	}

	@Override
	public ItemRarity getRarity() {
		return rarity;
	}
}