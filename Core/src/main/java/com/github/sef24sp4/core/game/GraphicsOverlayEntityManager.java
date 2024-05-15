package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.collisionsystem.CollisionSystemSPI;
import com.github.sef24sp4.common.data.EntityManager;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.core.interfaces.EntityToGraphicsMapper;

public class GraphicsOverlayEntityManager extends EntityManager {
	private final EntityToGraphicsMapper<IEntity, ?> graphicsMapping;
	private final CollisionSystemSPI collisionSystem;

	GraphicsOverlayEntityManager(final EntityToGraphicsMapper<IEntity, ?> graphicsMapping, final CollisionSystemSPI collisionSystem) {
		this.graphicsMapping = graphicsMapping;
		this.collisionSystem = collisionSystem;
	}

	/**
	 * Renders the entities using the {@link EntityToGraphicsMapper#render()} method.
	 */
	public void render() {
		this.graphicsMapping.render();
	}

	@Override
	public boolean addEntity(final IEntity entity) {
		this.graphicsMapping.add(entity);
		if (entity instanceof final ICollidableEntity collidableEntity) {
			this.collisionSystem.addEntity(collidableEntity);
		}
		return super.addEntity(entity);
	}

	@Override
	public boolean removeEntity(final IEntity entity) {
		this.graphicsMapping.remove(entity);
		if (entity instanceof final ICollidableEntity collidableEntity) {
			this.collisionSystem.removeEntity(collidableEntity);
		}
		return super.removeEntity(entity);
	}
}
