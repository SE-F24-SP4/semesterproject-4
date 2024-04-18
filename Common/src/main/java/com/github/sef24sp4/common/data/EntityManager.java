package com.github.sef24sp4.common.data;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class EntityManager implements IEntityManager {
	private final Collection<IEntity> entities = new HashSet<>();

	@Override
	public boolean addEntity(final IEntity entity) {
		return this.entities.add(entity);
	}

	@Override
	public boolean removeEntity(final IEntity entity) {
		return this.entities.remove(entity);
	}

	@Override
	public Collection<IEntity> getAllEntities() {
		return Collections.unmodifiableCollection(this.entities);
	}
}
