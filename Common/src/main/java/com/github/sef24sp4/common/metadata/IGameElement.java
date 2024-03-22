package com.github.sef24sp4.common.metadata;

public interface IGameElement {
	public IGameMetadata getMetadata();

	public default GameElementType getType() {
		return this.getMetadata().getType();
	}
}
