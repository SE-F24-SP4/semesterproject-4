package com.github.sef24sp4.astarai.caching;

import com.github.sef24sp4.astarai.AStar;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

import java.util.Map;

public class DumbCache implements IPathCaching {

	private final ICollidableEntity entity;
	private AStar aStarSession;

	public DumbCache(final ICollidableEntity entity) {
		this.entity = entity;
	}

	@Override
	public boolean flush() {
		this.aStarSession = null;
	}

	@Override
	public IVector getNextCoordinates(final IVector targetCoordinate) {
		// TODO: check if cache is valid, if not generate new

		// NOT valid generating
		this.aStarSession = new AStar(this.entity, targetCoordinate); //evt factory method
		aStarSession.calculatePath(); //
		// save to cache.
		return null;
	}

}
