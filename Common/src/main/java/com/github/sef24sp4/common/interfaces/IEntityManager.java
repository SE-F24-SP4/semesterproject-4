package com.github.sef24sp4.common.interfaces;

import com.github.sef24sp4.common.entities.IEntity;

import java.util.ArrayList;
import java.util.List;

public interface IEntityManager {

	public boolean addEntity(IEntity entity);

	public boolean removeEntity(IEntity entity);

	public List<IEntity> getAllEntities();

	public default <E extends IEntity> List<E> getEntitiesByClass(Class<E> entityType) {
		List<E> output = new ArrayList<>();
		this.getAllEntities().forEach((entity) -> {
			if (entityType.isInstance(entity)) output.add(entityType.cast(entity));
		});
		return output;
	}

	/**
	 * Remove all entities of a specific type.
	 *
	 * @param entityType The type of entities to remove.
	 * @param <E>        The entity type.
	 * @return A {@link List} of the removed entities.
	 */
	public default <E extends IEntity> List<E> removeEntitiesByClass(Class<E> entityType) {
		List<E> entities = this.getEntitiesByClass(entityType);
		entities.forEach(this::removeEntity);
		return entities;
	}
}
