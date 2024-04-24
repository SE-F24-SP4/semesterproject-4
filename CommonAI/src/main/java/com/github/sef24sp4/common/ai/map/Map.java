package com.github.sef24sp4.common.ai.map;

import com.github.sef24sp4.common.vector.IVector;

import java.util.Optional;

public interface Map {
	/**
	 * Get the {@link MapNode} which contains the {@link IVector coodinates}.
	 *
	 * @param coordinates The coordinates to check for.
	 * @return An {@link Optional} containing the {@link MapNode} containing {@code coordinates} if such node exists.
	 * Otherwise, an {@link Optional#empty()}.
	 */
	public Optional<MapNode> getNodeContaining(IVector coordinates);
}
