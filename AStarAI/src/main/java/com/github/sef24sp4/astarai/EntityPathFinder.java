package com.github.sef24sp4.astarai;

import com.github.sef24sp4.astarai.caching.DumbCache;
import com.github.sef24sp4.astarai.caching.IPathCaching;
import com.github.sef24sp4.common.ai.IPathfindingProvider;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

import java.util.*;

public class EntityPathFinder implements IPathfindingProvider { //extend/imple?
	private static final int MAX_CAPACITY = 10;


	// TODO: Idea, use a queue instead, and if larger than x clear old caches.
	private final SequencedMap<ICollidableEntity, IPathCaching> cachingMap = new LinkedHashMap<>();




	@Override
	public IVector nextCoordinateInPath(final ICollidableEntity entity, final IVector targetCoordinate) {
		while (this.cachingMap.size() > MAX_CAPACITY) {
			this.cachingMap.pollFirstEntry(); // Clear stale cache
		}



		// if cachingMap does not contain entity.
		this.cachingMap.put(entity, new DumbCache(entity));

		return this.cachingMap.get(entity).getNextCoordinates(targetCoordinate);

		// TODO: sometimes invalidate all caches to avoid filling memory.
	}
}
