package com.github.sef24sp4.common.ai;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IVector;

public interface IPathfindingProvider {


	public IVector calculateNextMove(IEntity entity, IVector targetCoordinate); //goalX and goalY beeing position of player.
}

