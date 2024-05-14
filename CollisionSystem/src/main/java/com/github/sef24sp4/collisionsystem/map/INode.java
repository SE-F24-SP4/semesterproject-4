package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.common.ai.map.MapNode;

public interface INode extends MapNode, IEntityCollection {

	/**
	 * Check to if {@link CollidableEntityContainer entity} overlaps with the current node.
	 *
	 * @param entity The entity to check for.
	 * @return {@code true} if the {@link CollidableEntityContainer entity} is overlapping with the current node.
	 * {@code false} otherwise.
	 */
	public boolean isEntityOverlapping(final CollidableEntityContainer entity);

	/**
	 * Get the row in which the current node is placed in.
	 *
	 * @return The row number. With {@code 0} being the first row.
	 */
	public int getRow();

	/**
	 * Get the column in which the current node is placed in.
	 *
	 * @return The column number. With {@code 0} being the first column.
	 */
	public int getColumn();
}
