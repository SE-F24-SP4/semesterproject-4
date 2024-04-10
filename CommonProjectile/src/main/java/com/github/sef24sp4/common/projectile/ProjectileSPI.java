package com.github.sef24sp4.common.projectile;

import com.github.sef24sp4.common.entities.ICollidableEntity;

public interface ProjectileSPI {

	public CommonProjectile createProjectile(ICollidableEntity shooter);
}
