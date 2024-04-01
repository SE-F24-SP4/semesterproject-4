package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.entities.IEntity;

public class EntityManager extends com.github.sef24sp4.common.data.EntityManager {
	private final EntityToGraphicsMapper<IEntity, ?> graphicsMapping;

	EntityManager(EntityToGraphicsMapper<IEntity, ?> graphicsMapping) {
		this.graphicsMapping = graphicsMapping;
	}

	/**
	 * Renders the entities using the {@link EntityToGraphicsMapper#render()} method.
	 */
	public void render() {
		this.graphicsMapping.render();
	}

	@Override
	public boolean addEntity(IEntity entity) {
		this.graphicsMapping.add(entity);
		return super.addEntity(entity);
	}

	@Override
	public boolean removeEntity(IEntity entity) {
		this.graphicsMapping.remove(entity);
		return super.removeEntity(entity);
	}
}
