package com.github.sef24sp4.common.metadata;

import java.util.Optional;

public interface IGameMetadata {
	public Optional<String> getProperty(String identifier);

	public GameElementType getType();
}
