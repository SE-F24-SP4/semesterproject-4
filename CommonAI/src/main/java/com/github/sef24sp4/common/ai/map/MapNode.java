package com.github.sef24sp4.common.ai.map;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

import java.util.Collection;
import java.util.Optional;

public interface MapNode {

	/**
	 * Get the center {@link IVector coordinates} for the current node.
	 *
	 * @return The {@link IVector coordinates} describing the center of the current node.
	 */
	public IVector getCenterCoordinates();

	/**
	 * Check if {@link IVector coordinates} are contained within the current node.
	 *
	 * @param coordinates The {@link IVector coordinates} to check for.
	 * @return {@code true} if {@link IVector coordinates} are within the current node.
	 * {@code false} otherwise.
	 */
	public boolean containsCoordinates(final IVector coordinates);

	/**
	 * Get all neighboring nodes to this node.
	 *
	 * @return A {@link Collection} of all neighboring nodes {@link MapNode MapNodes}.
	 */
	public Collection<MapNode> getNeighboringNodes();

	/**
	 * Calculate safe {@link IVector coordinates} for {@link ICollidableEntity entity}
	 * given that the entity needs to traverse in a linear fashion from a start {@link IVector position}.
	 * <p/>
	 * If the returned {@link Optional} has some {@link IVector coordinates}, then it is guaranteed that
	 * {@link #containsCoordinates(IVector) currentNode.containsCoordinates(returnedCoordinates) == true}.
	 *
	 * @param entity       To calculate safe coordinates for entity.
	 * @param fromPosition The position from where the entity needs to traverse from in a linear fashion.
	 * @return An {@link Optional} with an {@link IVector} describing safe coordinates for the {@code entity} if such coordinates exists.
	 * Otherwise, returns an {@link Optional#empty empty Optional}.
	 * @throws NotAdjacentNodeException if the implementation requires the node to be adjacent to the current node.
	 *                                  It is implementation specific when a node is considered adjacent.
	 * @see #isNodeAdjacent(MapNode)
	 */
	public Optional<IVector> getSafeCoordinatesForEntity(final ICollidableEntity entity, final IVector fromPosition) throws NotAdjacentNodeException;

	/**
	 * Check if {@link MapNode node} is considered to be adjacent to the current node by the implementation.
	 *
	 * @param node The {@link MapNode} to check for.
	 * @return {@code true} if the {@code node} is considered to be adjacent to the current node.
	 * {@code false} otherwise.
	 */
	public boolean isNodeAdjacent(final MapNode node);

	/**
	 * Calculates heuristics given a {@link MapNode target node}.
	 * Returns an arbitrary {@link Double} describing some sort of distance to a target node from the calling node.
	 *
	 * @param target The {@link MapNode node} to calculate heuristics with.
	 * @return The heuristics as a positive {@link Double}.
	 * {@code 0} if the {@code target} and the calling node is the same.
	 */
	public double calculateHeuristicsFor(final MapNode target);
}
