package com.github.sef24sp4.astarai.caching;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

public interface IPathCaching {

	public boolean flush();

	public IVector getNextCoordinates(ICollidableEntity entity, final IVector targetCoordinates);
}
