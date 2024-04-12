package com.github.sef24sp4.common.item;

import com.github.sef24sp4.common.metadata.IGameMetadata;

public abstract class CommonItem {
	private final IGameMetadata metadata;

	protected CommonItem(IGameMetadata metadata) {
		this.metadata = metadata;
	}
}
