package com.github.sef24sp4.common.metadata;

public class MetadataBuilder {
	private GameMetadata metadata;


	public MetadataBuilder(GameElementType type) {
		this.metadata = new GameMetadata(type);
	}

	public MetadataBuilder setProperty(String identifier, String value) {
		this.metadata.setProperty(identifier, value);
		return this;
	}


	public IGameMetadata getMetadata() {
		return this.metadata;
	}
}
