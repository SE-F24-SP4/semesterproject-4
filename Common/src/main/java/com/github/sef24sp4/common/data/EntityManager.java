package com.github.sef24sp4.common.data;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;

import java.util.ArrayList;
import java.util.List;

public class EntityManager implements IEntityManager {
	private final List<IEntity> entities = new ArrayList<>();

	@Override
	public boolean addEntity(IEntity entity) {
		return this.entities.add(entity);
	}

	@Override
	public boolean removeEntity(IEntity entity) {
		return this.entities.remove(entity);
	}

	@Override
	public List<IEntity> getAllEntities() {
		return this.entities;
	}
}
