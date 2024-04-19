package com.github.sef24sp4.healthitem;


import com.github.sef24sp4.common.data.EntityManager;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.entities.IHealingEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemSPI;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.player.Player;

import java.util.Optional;

public class HealthItem extends CommonItem implements ItemSPI, IHealingEntity {
	private final double healAmount = 10;
	protected HealthItem() {
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof Player) {
			despawnItem(entityManager);
		}
	}

	@Override
	public void spawnItem(Coordinates coordinates, IEntityManager entityManager) {
		this.setCoordinates(coordinates);
		HealthItem healthItem = new HealthItem();
		entityManager.addEntity(healthItem);
	}

	@Override
	public void despawnItem(IEntityManager entityManager) {
		entityManager.removeEntity(this);
	}

	@Override
	public double getHealingAmount() {
		return this.healAmount;
	}
}