package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.data.EntityManager;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.core.interfaces.EntityToGraphicsMapper;

public class GraphicsOverlayEntityManager extends EntityManager {
	private final EntityToGraphicsMapper<IEntity, ?> graphicsMapping;

	GraphicsOverlayEntityManager(EntityToGraphicsMapper<IEntity, ?> graphicsMapping) {
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
