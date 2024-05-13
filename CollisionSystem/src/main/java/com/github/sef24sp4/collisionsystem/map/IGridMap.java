package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.common.ai.map.Map;
import com.github.sef24sp4.common.ai.map.MapNode;

import java.util.Collection;

public interface IGridMap extends Map, IEntityCollection {
	/**
	 * TODO:
	 *
	 * @param node
	 * @return
	 */
	public Collection<INode> getNeighboursTo(final INode node, final int radius);

	/**
	 * TODO:
	 *
	 * @return
	 */
	public int getNodeSize();
}
