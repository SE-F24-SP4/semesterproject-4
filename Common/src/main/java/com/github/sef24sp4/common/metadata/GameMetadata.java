package com.github.sef24sp4.common.metadata;

import java.util.HashMap;
import java.util.Map;

public class GameMetadata implements IGameMetadata {
	private GameElementType type;

	private Map<String, String> map;

	GameMetadata(GameElementType type) {
		this.setType(type);
		this.map = new HashMap<>();
	}

	void setProperty(String identifier, String value) {
		this.map.put(identifier, value);
	}

	@Override
	public String getProperty(String identifier) {
		return this.map.get(identifier);
	}


	void setType(GameElementType type) {
		this.type = type;
	}

	@Override
	public GameElementType getType() {
		return null;
	}
}
