package com.github.sef24sp4.weaponpack.shotgun;

import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.projectile.CommonProjectile;

public class Munition extends CommonProjectile implements ICollidableEntity, IAttackingEntity {
	private final double damage = 3;

	protected Munition(IEntity shooter) {
		super(shooter);
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (this.getShooter() == otherEntity) return;
		if (otherEntity.getType() == GameElementType.PROJECTILE) return;

		entityManager.removeEntity(this);
	}

	@Override
	public double getAttackDamage() {
		return this.damage;
	}
}