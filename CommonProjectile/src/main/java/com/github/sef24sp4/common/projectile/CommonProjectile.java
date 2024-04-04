package com.github.sef24sp4.common.projectile;

import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;

public abstract class CommonProjectile extends CommonEntity implements ICollidableEntity {
	private final IGameMetadata metadata;
	private final ICollidableEntity shooter;

	protected CommonProjectile(final ICollidableEntity shooter) {
		this.metadata = new MetadataBuilder(GameElementType.PROJECTILE).
				getMetadata();
		this.shooter = shooter;
	}

	/**
	 * Query the entity who shot the projectile.
	 *
	 * @return The entity who shot the projectile.
	 */
	public final ICollidableEntity getShooter() {
		return this.shooter;
	}

	@Override
	public IGameMetadata getMetadata() {
		return this.metadata;
	}
}
