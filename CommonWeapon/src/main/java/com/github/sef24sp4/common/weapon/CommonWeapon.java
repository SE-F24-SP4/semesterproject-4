package com.github.sef24sp4.common.weapon;

import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;

public abstract class CommonWeapon extends CommonEntity implements ICollidableEntity {
	private final IGameMetadata metadata;

	protected CommonWeapon() {
		this.metadata = new MetadataBuilder(GameElementType.WEAPON)
				.getMetadata();
	}

	@Override
	public IGameMetadata getMetadata() {
		return this.metadata;
	}
}
