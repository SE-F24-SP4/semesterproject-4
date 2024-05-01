package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.vector.IVector;

import java.util.*;

/* TODO: make a public static provider method.
 * https://docs.oracle.com/en/java/javase/21/docs/api//java.base/java/util/ServiceLoader.html
 */

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
		return generate(gameSettings, 10);
	}

	private BucketMap initializeGrid(final INodeFactory factory) {
		for (int x = 0; x < this.getColumnSize(); x++) {
			for (int y = 0; y < this.getRowSize(); y++) {
				grid[x][y] = factory.create(x, y, this);
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

	@Override
	public Optional<MapNode> getNodeContaining(final IVector coordinates) {
		final int columnNumber = this.getColumnNumberFromContinues(coordinates.getX());
		final int rowNumber = this.getRowNumberFromContinues(coordinates.getY());

		// System.out.printf("requested (%.2f, %.2f) [%d, %d]\n", coordinates.getX(), coordinates.getY(), columnNumber, rowNumber); //TODO: REMOVE
		if (columnNumber < 0 || rowNumber < 0) return Optional.empty();
		if (columnNumber >= this.getColumnSize() || rowNumber >= this.getRowSize()) return Optional.empty();

		return Optional.of(this.grid[columnNumber][rowNumber]);
	}

	@Override
	public Collection<MapNode> getNeighboursTo(final INode node, final int radius) {
		final Collection<MapNode> neighbours = new ArrayList<>();
		// for (int x = Math.max(0, node.getColumn() - 1); x < Math.min(node.getColumn() + 1, this.getColumnSize()); x++) { //TODO: REMOVE

		for (final INode[] subRow : Arrays.asList(grid).subList(Math.max(0, node.getColumn() - radius), Math.min(node.getColumn() + radius + 1, this.getColumnSize()))) {
			System.out.printf("Node %d,%d\nTotal size %d,%d\nRadius: %d\nMax/min %d/%d\n---\n", node.getColumn(), node.getRow(), this.getColumnSize(), this.getRowSize(), radius, Math.max(0, node.getColumn() - radius), Math.min(node.getColumn() + radius, this.getColumnSize())); //TODO: REMOVE
			neighbours.addAll(Arrays.asList(subRow).subList(Math.max(0, node.getRow() - radius), Math.min(node.getRow() + radius + 1, this.getRowSize())));
		}

		neighbours.remove(node);
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
	 * And the current map contains at least one {@link INode node} which willing to accept the {@link CollidableEntityContainer entity}.
	 */
	@Override
	public boolean addEntity(final CollidableEntityContainer entity) {
		// TODO:
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CollidableEntityContainer> getCollidingEntitiesFor(final CollidableEntityContainer entity) {
		// TODO:
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsEntity(final CollidableEntityContainer entityContainer) {
		// TODO: Make intelligent approach based on coordinates.
		for (final INode[] row : this.grid) {
			for (final INode node : row) {
				if (node.containsEntity(entityContainer)) return true;
			}
		}
		return false;
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
