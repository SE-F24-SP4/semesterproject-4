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
			if (entityType.isAssignableFrom(entity.getClass())) output.add(entityType.cast(entity));
		});
		return output;
	}
}
