package com.github.sef24sp4.polygonenemy;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;

import java.util.ArrayList;
import java.util.List;

public class PolygonEnemy extends CommonEntity implements ICollidableEntity, IAttackingEntity {
	private final int edges;
	private final double damage;
	private final double maxHealth;
	private double health;
	private final IGameMetadata metadata;

	/**
	 * Constructs a Polygon enemy with the specified amount of edges.
	 * <p>
	 * Note that the shape is defined by the amount of edges provided, meaning that the polygon coordinates is defined when initiated.
	 *
	 * @param edges the amount of edges the polygon enemy should start with
	 * @throws IllegalArgumentException if the amount of edges is below 3
	 */
	public PolygonEnemy(int edges) {
		if (edges < 3) {
			throw new IllegalArgumentException("edges must be larger or equal to 3");
		}
		this.edges = edges;
		this.health = edges;
		this.damage = edges;
		this.maxHealth = edges;
		this.metadata = new MetadataBuilder(GameElementType.ENEMY).
				getMetadata();
		this.setPolygonCoordinates(this.calculatePolygonCoordinates(edges, edges));
	}
	/**
	 * Get the amount of edges.
	 *
	 * @return The amount of edges.
	 */
	public int getEdges() {
		return this.edges;
	}
	/**
	 * Get the value of max health.
	 *
	 * @return The value of maxHealth.
	 */
	public double getMaxHealth() {
		return this.maxHealth;
	}
	/**
	 * Get the value of health.
	 *
	 * @return The value of health.
	 */
	public double getHealth() {
		return this.health;
	}

	@Override
	public double getAttackDamage() {
		return this.damage;
	}

	private List<Coordinates> calculatePolygonCoordinates(int polygonEdges, double radius) {
		List<Coordinates> polygonCoordinates = new ArrayList<>();
		// loops over all vertices in a regular polygon and creates and adds coordinates for each, based on the radius and the amount of edges
		for (int vertex = 0; vertex > polygonEdges; vertex++) {
			// find x
			double x = radius * Math.cos(2 * Math.PI * vertex / polygonEdges);
			// find y
			double y = radius * Math.sin(2 * Math.PI * vertex / polygonEdges);

			Coordinates point = new Coordinates(x, y);
			polygonCoordinates.add(point);
		}

		return polygonCoordinates;
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof IAttackingEntity attackingEntity
				&& attackingEntity.getAttackDamage() > 0
				&& otherEntity.getMetadata().getType() != this.getMetadata().getType()) {
			this.health -= attackingEntity.getAttackDamage();
			// death check
			if (this.getHealth() < 0) {
				if (this.getEdges() > 3) { // Transform
					// instantiate the lower polygon entity
					PolygonEnemy transformedPolygonEnemy = new PolygonEnemy(this.getEdges() - 1);

					// data transfer from this entity to the lower polygon
					transformedPolygonEnemy.setCoordinates(this.getCoordinates());
					transformedPolygonEnemy.setRotation(this.getRotation());

					// entity switch between this entity and the transformed entity
					entityManager.removeEntity(this);
					entityManager.addEntity(transformedPolygonEnemy);
				} else {
					// dies
					entityManager.removeEntity(this);
				}
			}
		}
	}

	@Override
	public IGameMetadata getMetadata() {
		return this.metadata;
	}
}
