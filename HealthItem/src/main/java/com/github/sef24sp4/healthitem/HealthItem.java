package com.github.sef24sp4.healthitem;


import com.github.sef24sp4.common.data.EntityManager;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.entities.IHealingEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemSPI;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.player.Player;

import java.util.Optional;

public class HealthItem extends CommonItem implements ItemSPI, IHealingEntity {
	private final double healAmount = 10;
	protected HealthItem(String identifier, String value) {
		super(identifier, value);
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof Player) {
			useItem(otherEntity, entityManager);
		}
	}

	@Override
	public void spawnItem(Coordinates coordinates, IEntityManager entityManager) {
		this.setCoordinates(coordinates);
		HealthItem healthItem = new HealthItem("HealthItem","1");
		entityManager.addEntity(healthItem);
	}

	@Override
	public void despawnItem(IEntityManager entityManager) {
		entityManager.removeEntity(this);
	}

	@Override
	public void collectItem(ICollidableEntity entity) {

	}

	@Override
	public void useItem(IEntity targetEntity, IEntityManager entityManager) {
		((Player) targetEntity).heal(this.healAmount);
		this.despawnItem(entityManager);
	}

	@Override
	public double getHealingAmount() {
		return this.healAmount;
	}
}