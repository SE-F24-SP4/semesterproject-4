package com.github.sef24sp4.common.ai.map;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

import java.util.Collection;
import java.util.Optional;

public interface MapNode {

	/**
	 * Get all neighboring nodes to this node.
	 *
	 * @return A {@link Collection} of all neighboring nodes {@link MapNode MapNodes}.
	 */
	public Collection<MapNode> getNeighboringNodes();

	/**
	 * Calculate safe {@link IVector coordinates} for {@link ICollidableEntity entity}
	 * given that the entity needs to traverse in a linear fashion from a start {@link IVector position}.
	 *
	 * @param entity       To calculate safe coordinates for entity.
	 * @param fromPosition The position from where the entity needs to traverse from in a linear fashion.
	 * @return An {@link Optional Optional} with an {@link IVector} describing safe coordinates for the {@code entity} if such coordinates exists.
	 * Otherwise, returns an {@link Optional#empty empty Optional}.
	 */
	public Optional<IVector> getSafeCoordinatesForEntity(final ICollidableEntity entity, final IVector fromPosition);

	/**
	 * Calculates heuristics given a {@link MapNode target node}.
	 * Returns an arbitrary {@link Integer} describing some sort of distance to a target node from the calling node.
	 *
	 * @param target The {@link MapNode node} to calculate heuristics with.
	 * @return The heuristics as a positive {@link Integer}.
	 * {@code 0} if the {@code target} and the calling node is the same.
	 */
	public int calculateHeuristicsFor(final MapNode target);
}
