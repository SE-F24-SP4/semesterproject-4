package com.github.sef24sp4.collisionsystem;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * A decorator for {@link ICollidableEntity} which simplifies calculations of hit-box,
 * using a circle encompassing the furthest point found in {@link ICollidableEntity#getPolygonCoordinates()}.
 */
public class CollidableEntityContainer {
	private final ICollidableEntity entity;
	private final double radius;


	public CollidableEntityContainer(final ICollidableEntity entity) {
		this.entity = entity;
		// Set the radius to the polygon point which is the furthest from its center.
		this.radius = Arrays.stream(this.entity.getPolygonCoordinates()).max(Comparator.comparingDouble(IVector::getNorm)).orElseThrow().getNorm();
	}

	public ICollidableEntity getEntity() {
		return this.entity;
	}

	public IVector getCoordinates() {
		return this.getEntity().getCoordinates();
	}

	public double getRadius() {
		return this.radius;
	}

	/**
	 * Check if the contained {@link ICollidableEntity entity} collides with the {@link ICollidableEntity entity} in the given {@code entityContainer}.
	 *
	 * @param entityContainer The {@link CollidableEntityContainer} object containing the other {@link ICollidableEntity entity}.
	 * @return {@code true} if the
	 */
	public boolean collidesWith(final CollidableEntityContainer entityContainer) {
		if (this.equals(entityContainer)) return false;
		return this.getCoordinates().getVectorTo(entityContainer.getCoordinates()).getNorm() <= this.getRadius() + entityContainer.getRadius();
	}

	/**
	 * Computes a {@link Object#hashCode() hashcode} based on the class type of {@link CollidableEntityContainer},
	 * and the contained {@link ICollidableEntity entity}.
	 * <p/>
	 * This means that two container objects containing the same {@link ICollidableEntity entity} will generate the same hashcode.
	 *
	 * @return The computed hashcode.
	 * @see #equals(Object)
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		/* Compute hash combined with class type and contained entity,
		 * to avoid collisions with the raw value of entity.hashCode();
		 * While still making two independent containers hashCode directly correlated to the contained entity.
		 */
		return Objects.hash(this.getClass(), this.getEntity());
	}

	/**
	 * Tests whether some {@link Object} is "equal" to the current object.
	 * <p/>
	 * The {@link Object obj} is considered "equal" to this object if it is of type {@link CollidableEntityContainer},
	 * and the contained {@link ICollidableEntity entity} "equals" with the current containers contained {@link ICollidableEntity entity}.
	 *
	 * @param obj The object to test with.
	 * @return {@code true} if the {@code obj} "equals" the current object.
	 * {@code false} otherwise.
	 * @see #hashCode()
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof final CollidableEntityContainer c) return this.getEntity().equals(c.getEntity());
		return false;
	}

	@Override
	public String toString() {
		return String.format("Coordinates: %s, Radius: %.5f", this.getCoordinates(), this.getRadius());
	}
}
