package com.github.sef24sp4.common.projectile;

import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;

public abstract class Projectile extends CommonEntity implements ICollidableEntity {
	private final IGameMetadata metadata;

	protected Projectile() {
		this.metadata = new MetadataBuilder(GameElementType.PROJECTILE).
				getMetadata();
	}

	@Override
	public IGameMetadata getMetadata() {
		return this.metadata;
	}
}
