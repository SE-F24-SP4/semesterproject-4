package com.github.sef24sp4.newbaseweapon;

import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.projectile.CommonProjectile;

public abstract class AbstractProjectile extends CommonProjectile implements IAttackingEntity {

	protected AbstractProjectile(final IEntity shooter) {
		super(shooter);
		this.setCoordinates(shooter.getCoordinates().clone());
		this.setRotation(shooter.getRotation());
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (this.getShooter() == otherEntity) return;
		if (otherEntity.getType() == GameElementType.PROJECTILE) return;

		entityManager.removeEntity(this);
	}

}
