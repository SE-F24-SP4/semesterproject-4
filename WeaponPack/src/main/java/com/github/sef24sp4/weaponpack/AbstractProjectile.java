package com.github.sef24sp4.weaponpack;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.projectile.CommonProjectile;

public abstract class AbstractProjectile extends CommonProjectile {

	protected AbstractProjectile(final IEntity shooter) {
		super(shooter);
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (this.getShooter() == otherEntity) return;
		if (otherEntity.getType() == GameElementType.PROJECTILE) return;

		entityManager.removeEntity(this);
	}


}
