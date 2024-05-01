package com.github.sef24sp4.common.item;

import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;
public abstract class CommonItem extends CommonEntity implements ICollidableEntity {
	private final IGameMetadata metadata;
	protected CommonItem() {
		this.metadata = new MetadataBuilder(GameElementType.ITEM).getMetadata();
	}
	@Override
	public IGameMetadata getMetadata() {
		return this.metadata;
	}
}