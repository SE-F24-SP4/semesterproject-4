package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.common.ai.map.MapNode;

public interface INode extends MapNode, IEntityCollection {

	/**
	 * TODO:
	 *
	 * @param entityContainer
	 * @return
	 */
	boolean isEntityOverlapping(CollidableEntityContainer entityContainer);

	/**
	 * TODO:
	 *
	 * @return
	 */
	public int getRow();

	/**
	 * TODO:
	 *
	 * @return
	 */
	public int getColumn();
}
