package com.github.sef24sp4.common.interfaces;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.metadata.GameElementType;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * An EntityManager used to keep track of all active {@link IEntity entities} in the game.
 */
public interface IEntityManager {

	/**
	 * Add {@link IEntity} to the EntityManager if not already present in the manager.
	 * This means there may never be duplicate entities within the same manager.
	 * Specifically that no two objects ({@code e}, {@code e2}) exists such that {@code e.equals(e2)} is {@code true}.
	 *
	 * @param entity The {@link IEntity entity} to be added.
	 * @return {@code true} if manager did not already contain {@code entity}, and the {@code entity} was added successfully.
	 * {@code false} if the manager already contained the {@code entity}.
	 */
	public boolean addEntity(final IEntity entity);

	/**
	 * Removes the {@code entity} from the manager if present, according to {@link Object#equals entity.equals()} method.
	 *
	 * @param entity The {@link IEntity entity} to be removed.
	 * @return {@code true} if {@code entity} was present and successfully removed.
	 * {@code false} if the manager did not contain the {@code entity}.
	 */
	public boolean removeEntity(final IEntity entity);

	/**
	 * Get a {@link Collections#unmodifiableCollection read-only Collection} with all entities contained within the manager.
	 *
	 * @return A {@link Collections#unmodifiableCollection read-only Collection} with the entities.
	 */
	public Collection<IEntity> getAllEntities();

	/**
	 * Get a {@link Collections#unmodifiableCollection read-only Collection} containing all entities from this manager of the {@code entityType}.
	 *
	 * @param entityType A {@link Class} for the type of entities to retrieve.
	 * @param <E>        The entity type.
	 * @return A {@link Collections#unmodifiableCollection read-only Collection} with the entities of {@code entityType}.
	 */
	public default <E extends IEntity> Collection<E> getEntitiesByClass(final Class<E> entityType) {
		final Collection<E> output = new HashSet<>();
		this.getAllEntities().forEach((entity) -> {
			if (entityType.isInstance(entity)) output.add(entityType.cast(entity));
		});
		return Collections.unmodifiableCollection(output);
	}

	/**
	 * Remove all entities of a specific type.
	 *
	 * @param entityType The type of entities to remove.
	 * @param <E>        The entity type.
	 * @return A {@link Collection} of the removed entities.
	 */
	public default <E extends IEntity> Collection<E> removeEntitiesByClass(final Class<E> entityType) {
		final Collection<E> entities = this.getEntitiesByClass(entityType);
		entities.forEach(this::removeEntity);
		return entities;
	}

	/**
	 * Get all entities which match a specified {@link GameElementType} and has a specific class type {@code T}.
	 *
	 * @param <T>         The {@code classType} that is queried for.
	 * @param elementType The {@link GameElementType} to query for.
	 * @param classType   The classType to match.
	 * @return A {@link Collection} with all {@link IEntity entities} which match the two conditions.
	 * @see #getEntitiesByGameElementType(GameElementType)
	 */
	public default <T extends IEntity> Collection<T> getEntitiesByGameElementType(final GameElementType elementType, final Class<T> classType) {
		return this.getEntitiesByClass(classType).stream().filter(e -> e.getType() == elementType).toList();
	}

	/**
	 * Get all entities which match a specified {@link GameElementType} among all {@link IEntity entities}.
	 * <p/>
	 * It is the same as calling {@link #getEntitiesByGameElementType(GameElementType, Class) getEntitiesByGameElementType(GameElementType, IEntity.class)}.
	 *
	 * @param elementType The {@link GameElementType} to query for.
	 * @return A {@link Collection} with all {@link IEntity entities} which match the {@code elementType}.
	 * @see #getEntitiesByGameElementType(GameElementType, Class)
	 */
	public default Collection<IEntity> getEntitiesByGameElementType(final GameElementType elementType) {
		return this.getEntitiesByGameElementType(elementType, IEntity.class);
	}
}
