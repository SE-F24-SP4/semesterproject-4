package com.github.sef24sp4.common.projectile;

import com.github.sef24sp4.common.entities.IEntity;

public interface ProjectileSPI {

	public CommonProjectile createProjectile(IEntity shooter);
}
