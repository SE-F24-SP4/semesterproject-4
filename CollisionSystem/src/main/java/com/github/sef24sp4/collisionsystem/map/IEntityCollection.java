package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;

import java.util.Collection;

/**
 * TODO:
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
	 * TODO:
	 *
	 * @param entity
	 * @return
	 */
	public Collection<CollidableEntityContainer> getCollidingEntitiesFor(final CollidableEntityContainer entity);

	/**
	 * TODO:
	 *
	 * @param entityContainer
	 * @return
	 */
	public boolean containsEntity(final CollidableEntityContainer entityContainer);


	/**
	 * TODO:
	 *
	 * @return
	 */
	public Collection<CollidableEntityContainer> getAllEntities();

	/**
	 * TODO:
	 */
	public void clearEntities();
}
