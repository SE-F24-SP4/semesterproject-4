package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.common.ai.map.Map;
import com.github.sef24sp4.common.vector.IVector;

import java.util.Collection;

public interface IGridMap extends Map, IEntityCollection {
	/**
	 * Get a {@link Collection} of {@link INode} around a given {@link INode centerNode} within a {@code radius}.
	 * <p/>
	 * The {@link INode centerNode} is not included in the returned {@link Collection}.
	 *
	 * @param centerNode The {@link INode node} from where to find neighbouring nodes for.
	 * @param radius     The radius to get neighboring nodes for.
	 * @return A {@link Collection} of {@link INode} which surrounds the {@link INode centerNode}.
	 */
	public Collection<INode> getNeighboursTo(final INode centerNode, final int radius);

	/**
	 * Get how big the nodes are within the map.
	 *
	 * @return The size of a single node measured in some arbitrary unit.
	 */
	public int getNodeSize();

	/**
	 * This is an alias for {@link #getPotentiallyOverlappingNodes(CollidableEntityContainer, IVector) getPotentiallyOverlappingNodes(entity, entity.getCoordinates())}.
	 *
	 * @param entity The {@link CollidableEntityContainer entity} to check for.
	 * @return A {@link Collection} of {@link INode} which potentially would overlap with the {@link CollidableEntityContainer entity}.
	 */
	public default Collection<INode> getPotentiallyOverlappingNodes(final CollidableEntityContainer entity) {
		return this.getPotentiallyOverlappingNodes(entity, entity.getCoordinates());
	}

	/**
	 * Get a {@link Collection} of {@link INode nodes} which potentially would overlap with the {@link CollidableEntityContainer entity},
	 * if it were at the given {@link IVector coordinates}.
	 *
	 * @param entity      The {@link CollidableEntityContainer entity} to check for.
	 * @param coordinates The {@link IVector coordinates} to check for.
	 * @return A {@link Collection} of {@link INode} which potentially would overlap with the {@link CollidableEntityContainer entity},
	 * if it were at the given {@link IVector coordinates}.
	 */
	public Collection<INode> getPotentiallyOverlappingNodes(final CollidableEntityContainer entity, final IVector coordinates);
}
