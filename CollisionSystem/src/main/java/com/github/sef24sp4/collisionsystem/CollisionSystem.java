package com.github.sef24sp4.collisionsystem;

import com.github.sef24sp4.collisionsystem.map.IGridMap;
import com.github.sef24sp4.common.collisionsystem.CollisionSystemSPI;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

public class CollisionSystem implements CollisionSystemSPI {
	private final Collection<CollidableEntityContainer> collidableEntities = new HashSet<>();
	private final Stack<CollidableEntityContainer> toBeAdded = new Stack<>();
	private final Stack<CollidableEntityContainer> toBeRemoved = new Stack<>();
	private final IGridMap map;

	CollisionSystem(final IGridMap map) {
		this.map = map;
	}

	@Override
	public boolean addEntity(final ICollidableEntity entity) {
		final CollidableEntityContainer container = new CollidableEntityContainer(entity);
		if (this.collidableEntities.contains(container)) return false;
		if (this.toBeAdded.contains(container)) return false;
		this.toBeAdded.push(container);
		return true;
	}

	@Override
	public boolean removeEntity(final ICollidableEntity entity) {
		final CollidableEntityContainer container = new CollidableEntityContainer(entity);

		if (this.toBeRemoved.contains(container)) return false;

		if (this.collidableEntities.contains(container) || this.toBeAdded.contains(container)) {
			this.toBeRemoved.push(container);
			return true;
		}
		return false;
	}

	@Override
	public boolean processCollisions(final IEntityManager entityManager) {
		while (!this.toBeAdded.isEmpty()) {
			final CollidableEntityContainer c = this.toBeAdded.pop();
			if (this.toBeRemoved.contains(c)) continue;
			this.collidableEntities.add(c);
		}


		this.map.clearEntities();

		for (final CollidableEntityContainer entity : this.collidableEntities) {
			this.map.addEntity(entity);

			this.map.getCollidingEntitiesFor(entity).forEach(other -> {
				if (this.toBeRemoved.contains(entity) || this.toBeRemoved.contains(other)) return;

				entity.getEntity().collide(entityManager, other.getEntity());
				other.getEntity().collide(entityManager, entity.getEntity());
			});
		}

		while (!this.toBeRemoved.isEmpty()) {
			this.collidableEntities.remove(this.toBeRemoved.pop());
		}
		return true;
	}
}
