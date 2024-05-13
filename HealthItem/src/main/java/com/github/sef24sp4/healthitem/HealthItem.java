package com.github.sef24sp4.healthitem;


import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IHealingEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.vector.Coordinates;

public class HealthItem extends CommonItem implements IHealingEntity {
	private final double healAmount = 10;
	public HealthItem() {
		this.setRotation(3.14159265);
		this.setPolygonCoordinates(
				new Coordinates(0, -6),
				new Coordinates(7, 2),
				new Coordinates(6, 5),
				new Coordinates(3, 6),
				new Coordinates(0, 4),
				new Coordinates(-3, 6),
				new Coordinates(-6, 5),
				new Coordinates(-7, 2)
		);
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity.getType() == GameElementType.PLAYER) {
			entityManager.removeEntity(this);
		}
	}

	@Override
	public double getHealingAmount() {
		return this.healAmount;
	}
}