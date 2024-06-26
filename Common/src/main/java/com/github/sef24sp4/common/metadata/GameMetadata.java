package com.github.sef24sp4.common.metadata;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameMetadata implements IGameMetadata {
	private final Map<String, String> map;
	private final GameElementType type;

	GameMetadata(GameElementType type) {
		this.type = type;
		this.map = new HashMap<>();
	}

	void setProperty(String identifier, String value) {
		this.map.put(identifier, value);
	}

	@Override
	public Optional<String> getProperty(String identifier) {
		return Optional.ofNullable(this.map.get(identifier));
	}

	@Override
	public GameElementType getType() {
		return this.type;
	}
}
