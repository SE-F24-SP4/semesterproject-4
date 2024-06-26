package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.vector.IVector;

import java.util.*;

public final class BucketMap implements IGridMap {
	private final int nodeSize;
	private final INode[][] grid;

	private BucketMap(final int columnNumber, final int rowNumber, final int nodeSize) {
		this.nodeSize = nodeSize;
		this.grid = new INode[columnNumber][rowNumber];
	}

	public static BucketMap generate(final IGameSettings gameSettings, final int nodeSize) {
		final int columnNumber = Math.ceilDiv(gameSettings.getDisplayWidth(), nodeSize);
		final int rowNumber = Math.ceilDiv(gameSettings.getDisplayHeight(), nodeSize);
		final BucketMap map = new BucketMap(columnNumber, rowNumber, nodeSize);
		return map.initializeGrid(Bucket::new);
	}

	public static BucketMap generate(final IGameSettings gameSettings) {
		return generate(gameSettings, 100);
	}

	private BucketMap initializeGrid(final INodeFactory factory) {
		for (int x = 0; x < this.getColumnSize(); x++) {
			for (int y = 0; y < this.getRowSize(); y++) {
				this.grid[x][y] = factory.create(x, y, this);
			}
		}
		return this;
	}

	public int getColumnSize() {
		return this.grid.length;
	}

	public int getRowSize() {
		return this.grid[0].length;
	}

	private double getWidth() {
		return this.getColumnSize() * this.nodeSize;
	}

	private double getHeight() {
		return this.getRowSize() * this.nodeSize;
	}

	private int getColumnNumberFromContinues(final double x) {
		if (x == this.getWidth()) return this.getColumnSize() - 1;
		return Math.floorDiv(Double.valueOf(x).intValue(), this.nodeSize);
	}

	private int getRowNumberFromContinues(final double y) {
		if (y == this.getHeight()) return this.getRowSize() - 1;
		return Math.floorDiv(Double.valueOf(y).intValue(), this.nodeSize);
	}

	private INode getNodeContainingDirectly(final IVector coordinates) throws CoordinatesOutOfBoundsException {
		final int columnNumber = this.getColumnNumberFromContinues(coordinates.getX());
		final int rowNumber = this.getRowNumberFromContinues(coordinates.getY());

		if (columnNumber < 0 || rowNumber < 0 || columnNumber >= this.getColumnSize() || rowNumber >= this.getRowSize()) {
			throw new CoordinatesOutOfBoundsException(coordinates);
		}

		return this.grid[columnNumber][rowNumber];
	}

	@Override
	public Optional<MapNode> getNodeContaining(final IVector coordinates) {
		try {
			return Optional.of(this.getNodeContainingDirectly(coordinates));
		} catch (final CoordinatesOutOfBoundsException e) {
			return Optional.empty();
		}
	}

	@Override
	public Collection<INode> getNeighboursTo(final INode centerNode, final int radius) {
		final Collection<INode> neighbours = new HashSet<>();

		for (final INode[] subRow : Arrays.asList(this.grid).subList(Math.max(0, centerNode.getColumn() - radius), Math.min(centerNode.getColumn() + radius + 1, this.getColumnSize()))) {
			neighbours.addAll(Arrays.asList(subRow).subList(Math.max(0, centerNode.getRow() - radius), Math.min(centerNode.getRow() + radius + 1, this.getRowSize())));
		}

		neighbours.remove(centerNode);
		return neighbours;
	}

	@Override
	public int getNodeSize() {
		return this.nodeSize;
	}

	/**
	 * {@inheritDoc}
	 * <br/><br/>
	 * {@link CollidableEntityContainer Entities} satisfy the conditions for being added
	 * if its {@link CollidableEntityContainer#getCoordinates() coordinates} are within the bounding box of the current map.
	 * And the current map contains at least one {@link INode node} which is willing to accept the {@link CollidableEntityContainer entity}.
	 */
	@Override
	public boolean addEntity(final CollidableEntityContainer entity) {
		boolean hasBeenAdded = false;
		for (final INode node : this.getPotentiallyOverlappingNodes(entity)) {
			if (node.addEntity(entity)) hasBeenAdded = true;
			// Keep iterating, as we need to add entity to all overlapping nodes.
		}
		return hasBeenAdded;
	}

	@Override
	public Collection<CollidableEntityContainer> getCollidingEntitiesFor(final CollidableEntityContainer entity) {
		return this.getPotentiallyOverlappingNodes(entity).parallelStream()
				.<CollidableEntityContainer>mapMulti((node, output) -> node.getAllEntities().forEach(output))
				.distinct()
				.filter(entity::doesCollideWith)
				.toList();
	}

	@Override
	public boolean containsEntity(final CollidableEntityContainer entity) {
		return this.getPotentiallyOverlappingNodes(entity).stream().anyMatch(n -> n.containsEntity(entity));
	}

	@Override
	public Collection<INode> getPotentiallyOverlappingNodes(final CollidableEntityContainer entity, final IVector coordinates) {
		try {
			final INode centerNode = this.getNodeContainingDirectly(coordinates);
			final Collection<INode> nodes = this.getNeighboursTo(centerNode, Math.ceilDiv(Double.valueOf(entity.getRadius()).intValue(), this.getNodeSize()));
			nodes.add(centerNode);
			return nodes;
		} catch (CoordinatesOutOfBoundsException e) {
			return Set.of();
		}
	}

	@Override
	public Collection<CollidableEntityContainer> getAllEntities() {
		final Collection<CollidableEntityContainer> output = new HashSet<>();
		for (final INode[] row : this.grid) {
			for (final INode node : row) {
				output.addAll(node.getAllEntities());
			}
		}
		return Collections.unmodifiableCollection(output);
	}

	@Override
	public void clearEntities() {
		for (final INode[] row : this.grid) {
			for (final INode node : row) {
				node.clearEntities();
			}
		}
	}
}
