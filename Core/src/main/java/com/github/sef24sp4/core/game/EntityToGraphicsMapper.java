package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.entities.IEntity;

import java.util.Map;

/**
 * Used to map between game entities and the elements rendered on the screen.
 *
 * @param <E> The entity type. Must be a subclass of {@link IEntity}.
 * @param <G> The type of graphics element. This is dependent on the graphics framework.
 */
public interface EntityToGraphicsMapper<E extends IEntity, G> {

	/**
	 * Add entity to the mapping. The method should transparently create an object of the graphical corresponding element.
	 *
	 * @param entity The entity to be added.
	 * @return {@code true} if successful, {@code false} otherwise.
	 */
	boolean add(E entity);

	/**
	 * Remove entity from the mapping. The method should transparently remove the corresponding graphical element.
	 *
	 * @param entity The entity to be removed.
	 * @return {@code true} if successful, {@code false} otherwise.
	 */
	boolean remove(E entity);

	/**
	 * Get the current mapping between {@link IEntity entities} and graphical objects.
	 *
	 * @return A {@link Map} containing {@link IEntity entities} as keys and the graphical objects as values.
	 */

	Map<? extends E, ? extends G> getMap();

	/**
	 * Render all graphical elements according to the entities current state.
	 */
	void render();
}
