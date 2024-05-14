package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.ai.map.NotAdjacentNodeException;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.IVector;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

public class Bucket implements INode {
	private final Collection<CollidableEntityContainer> entities = new HashSet<>();

	private final IGridMap parent;

	private final int row;
	private final int column;

	private final IVector startCoordinates;
	private final IVector endCoordinates;

	public Bucket(final int column, final int row, final IGridMap parent) {
		this.column = column;
		this.row = row;
		this.parent = parent;
		this.startCoordinates = new BasicVector(this.column * this.getSize(), this.row * this.getSize());
		this.endCoordinates = generateEndCoordinatesFrom(this.startCoordinates, this.getSize());
	}

	private static IVector generateEndCoordinatesFrom(final IVector startCoordinates, final int size) {
		final BasicVector vector = BasicVector.valuesOf(startCoordinates);
		vector.add(new BasicVector(size, size));
		return vector;
	}

	public IVector getStartCoordinates() {
		return this.startCoordinates;
	}

	public IVector getEndCoordinates() {
		return this.endCoordinates;
	}

	public int getSize() {
		return this.parent.getNodeSize();
	}

	@Override
	public IVector getCenterCoordinates() {
		final BasicVector crossLine = this.getStartCoordinates().getVectorTo(this.getEndCoordinates());
		crossLine.scale(0.5);
		crossLine.add(this.getStartCoordinates());
		return crossLine;
	}


	@Override
	public boolean containsCoordinates(final IVector coordinates) {
		// System.out.printf("startCoordinates (%.2f, %.2f)\n", this.getStartCoordinates().getX(), this.getStartCoordinates().getY()); //TODO: REMOVE
		// System.out.printf("endCoordinates (%.2f, %.2f)\n---\n", this.getEndCoordinates().getX(), this.getEndCoordinates().getY()); //TODO: REMOVE
		return this.getStartCoordinates().getX() <= coordinates.getX() && this.getStartCoordinates().getY() <= coordinates.getY()
				&& this.getEndCoordinates().getX() >= coordinates.getX() && this.getEndCoordinates().getY() >= coordinates.getY();
	}


	/**
	 * {@inheritDoc}
	 * <br/><br/>
	 * {@link CollidableEntityContainer Entities} satisfy the conditions for being added if its hit-box overlaps with the current node.
	 */
	@Override
	public boolean addEntity(final CollidableEntityContainer entity) {
		if (!this.isEntityOverlapping(entity)) return false;
		this.entities.add(entity);
		return true;
	}

	@Override
	public Collection<CollidableEntityContainer> getCollidingEntitiesFor(final CollidableEntityContainer entity) {
		return this.entities.stream().filter(entity::doesCollideWith).toList();
	}

	@Override
	public boolean containsEntity(final CollidableEntityContainer entityContainer) {
		return this.entities.contains(entityContainer);
	}

	@Override
	public Collection<CollidableEntityContainer> getAllEntities() {
		return Collections.unmodifiableCollection(this.entities);
	}

	@Override
	public void clearEntities() {
		this.entities.clear();
	}

	@Override
	public boolean isEntityOverlapping(final CollidableEntityContainer entityContainer) {
		final BasicVector vectorToCenter = entityContainer.getCoordinates().getVectorTo(this.getCenterCoordinates());
		if (vectorToCenter.getNorm() <= entityContainer.getRadius()) return true;

		final BasicVector vectorToFurthestPoint = vectorToCenter.getNormalizedVector();
		vectorToFurthestPoint.scale(entityContainer.getRadius());
		vectorToFurthestPoint.add(entityContainer.getCoordinates());
		return this.containsCoordinates(vectorToFurthestPoint);
	}


	@Override
	public Collection<MapNode> getNeighboringNodes() {
		return new HashSet<>(this.parent.getNeighboursTo(this, 1));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws NotAdjacentNodeException If the {@link IVector fromPosition} is not in an adjacent node.
	 */
	@Override
	public Optional<IVector> getSafeCoordinatesForEntity(final ICollidableEntity entity, final IVector fromPosition) throws NotAdjacentNodeException {
		if (!this.parent.getNodeContaining(fromPosition).map(this::isNodeAdjacent).orElse(false)) {
			throw new NotAdjacentNodeException(String.format("The fromPosition %s is not in an adjacent node to this node.", fromPosition));
		}

		//TODO:
		/*
		 * If distance to other entity is greater than the sum of both radi and distance to goal node, then it is clear.
		 */


		return Optional.empty();
	}

	@Override
	public boolean isNodeAdjacent(final MapNode node) {
		final IVector distance = this.getCenterCoordinates().getVectorTo(node.getCenterCoordinates());
		return Math.abs(distance.getX()) <= this.getSize() && Math.abs(distance.getY()) <= this.getSize();
	}

	@Override
	public double calculateHeuristicsFor(final MapNode target) {
		return this.getCenterCoordinates().getVectorTo(target.getCenterCoordinates()).getNorm();
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	@Override
	public String toString() {
		return String.format("Bucket: [%d, %d] Entities.size: %d", this.getColumn(), this.getRow(), this.getAllEntities().size());
	}
}
