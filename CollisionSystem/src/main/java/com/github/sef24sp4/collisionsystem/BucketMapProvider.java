package com.github.sef24sp4.collisionsystem;

import com.github.sef24sp4.collisionsystem.map.BucketMap;
import com.github.sef24sp4.collisionsystem.map.IGridMap;
import com.github.sef24sp4.common.ai.map.Map;
import com.github.sef24sp4.common.ai.map.MapProvider;
import com.github.sef24sp4.common.interfaces.IGameSettings;

public class BucketMapProvider implements MapProvider {
	private static IGridMap map;

	public static IGridMap getInstance(final IGameSettings gameSettings) {
		if (map == null) map = BucketMap.generate(gameSettings);
		return map;
	}

	@Override
	public Map fetch(final IGameSettings gameSettings) {
		return getInstance(gameSettings);
	}
}
