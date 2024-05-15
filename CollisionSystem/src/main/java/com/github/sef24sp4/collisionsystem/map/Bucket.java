package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.collisionsystem.map.util.MathTools;
import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.ai.map.NotAdjacentNodeException;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.IVector;

import java.util.*;
import java.util.stream.Stream;

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

	static Optional<IVector> calculateSafeCoordinatesFor(final CollidableEntityContainer mainEntity, final IVector fromPosition, final CollidableEntityContainer otherEntity, final IVector directionToCenter) {
		final IVector directionToOtherEntity = fromPosition.getVectorTo(otherEntity.getCoordinates());
		final double clearance = mainEntity.getRadius() + otherEntity.getRadius();
		final double angleToOtherEntity = directionToOtherEntity.getAngleBetween(directionToCenter);

		final double otherEntityPerpendicularDistanceToPath = Math.sin(angleToOtherEntity) * directionToOtherEntity.getNorm();

		final double beta = Math.acos(otherEntityPerpendicularDistanceToPath / clearance);
		if (Double.isNaN(beta)) return Optional.empty();

		final double angleToSafeCoordinates = (Math.PI / 2) - angleToOtherEntity - beta;

		final BasicVector safeCoordinates = directionToOtherEntity.getNegative().getNewVectorWithAngle(angleToSafeCoordinates).getNormalizedVector();
		safeCoordinates.scale(clearance);
		safeCoordinates.add(otherEntity.getCoordinates());
		return Optional.of(safeCoordinates);
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
	public boolean containsEntity(final CollidableEntityContainer entity) {
		return this.entities.contains(entity);
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
	public boolean isEntityOverlapping(final CollidableEntityContainer entity) {
		final BasicVector vectorToCenter = entity.getCoordinates().getVectorTo(this.getCenterCoordinates());
		if (vectorToCenter.getNorm() <= entity.getRadius()) return true;

		final BasicVector vectorToFurthestPoint = vectorToCenter.getNormalizedVector();
		vectorToFurthestPoint.scale(entity.getRadius());
		vectorToFurthestPoint.add(entity.getCoordinates());
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
		final CollidableEntityContainer mainEntity = new CollidableEntityContainer(entity);
		final IVector center = this.getCenterCoordinates();

		final IVector directionToCenter = fromPosition.getVectorTo(center);

		final Stream<CollidableEntityContainer> potentialCollidingEntities = this.streamOfNodesOverlapping(mainEntity, fromPosition, center)
				.<CollidableEntityContainer>mapMulti((n, output) -> n.getAllEntities().forEach(output))
				.distinct()
				.filter(e -> {
					System.out.println(e);
					return true;
				});

		final Collection<IVector> candidateCoordinates = potentialCollidingEntities
				.<IVector>mapMulti((e, output) -> calculateSafeCoordinatesFor(mainEntity, fromPosition, e, directionToCenter).ifPresent(output))
				.filter(e -> {
					System.out.println(e);
					return true;
				})
				.filter(c -> MathTools.isPointBetween(fromPosition, c, center))
				.toList();

		if (candidateCoordinates.isEmpty()) return Optional.of(center);
		if (candidateCoordinates.stream().anyMatch(c -> !this.containsCoordinates(c))) return Optional.empty();
		return candidateCoordinates.stream().max(Comparator.comparingDouble(c -> c.getVectorTo(center).getNorm()));
	}

	private Stream<INode> streamOfNodesOverlapping(final CollidableEntityContainer entity, final IVector... coordinates) {
		return Arrays.stream(coordinates)
				.<INode>mapMulti((c, output) -> this.parent.getPotentiallyOverlappingNodes(entity, c).forEach(output))
				.distinct()
				.filter(e -> {
					System.out.println(e);
					return true;
				});
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
