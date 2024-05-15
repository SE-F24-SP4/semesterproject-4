package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;

import java.util.Collection;

/**
 * This is a collection for {@link CollidableEntityContainer}s,
 * which simplifies interactions between large quantities of {@link CollidableEntityContainer entities}.
 */
public interface IEntityCollection {
	/**
	 * Add {@link CollidableEntityContainer entity} to this collection if it satisfies the conditions required by the implementation.
	 * <p/>
	 * The {@link CollidableEntityContainer entity} will not be added additionally if this node already contains it.
	 * However, this method will still return {@code true} if it otherwise satisfies the conditions for being added.
	 *
	 * @param entity The {@link CollidableEntityContainer entity} to add.
	 * @return {@code true} if the {@link CollidableEntityContainer entity} overlaps the current node.
	 * {@code false} otherwise.
	 */
	public boolean addEntity(final CollidableEntityContainer entity);

	/**
	 * Get all {@link CollidableEntityContainer entities} within this collection which collides with the given {@link CollidableEntityContainer entity}.
	 *
	 * @param entity The {@link CollidableEntityContainer entity} to check for.
	 * @return A {@link Collection} of colliding {@link CollidableEntityContainer entities}.
	 */
	public Collection<CollidableEntityContainer> getCollidingEntitiesFor(final CollidableEntityContainer entity);

	/**
	 * Check to see if the {@link CollidableEntityContainer entity} is contained within the current collection.
	 *
	 * @param entity The {@link CollidableEntityContainer entity} to check for.
	 * @return {@code true} if the {@link CollidableEntityContainer entity} is contained.
	 * {@code false} otherwise.
	 */
	public boolean containsEntity(final CollidableEntityContainer entity);


	/**
	 * Get all unique {@link CollidableEntityContainer entities} within the current collection
	 * <p/>
	 * Uniqueness is defined by {@link Object#hashCode()} method.
	 * Two instances with the same {@link Object#hashCode() hash code} is considered to be duplicates of each other.
	 *
	 * @return A {@link Collection} of all the contained {@link CollidableEntityContainer entities}.
	 * @see #containsEntity(CollidableEntityContainer)
	 * @see #addEntity(CollidableEntityContainer)
	 * @see #clearEntities()
	 */
	public Collection<CollidableEntityContainer> getAllEntities();

	/**
	 * Clear the current collection for all {@link CollidableEntityContainer entities}.
	 *
	 * @see #getAllEntities()
	 * @see #addEntity(CollidableEntityContainer)
	 */
	public void clearEntities();
}
