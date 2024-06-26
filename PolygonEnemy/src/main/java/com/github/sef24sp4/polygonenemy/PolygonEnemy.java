package com.github.sef24sp4.polygonenemy;

import com.github.sef24sp4.common.enemy.CommonEnemy;
import com.github.sef24sp4.common.item.ItemRarity;
import com.github.sef24sp4.common.item.loottable.LootTable;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class PolygonEnemy extends CommonEnemy implements ICollidableEntity, IAttackingEntity {
	private final int edges;
	private final double damage;
	private final double maxHealth;
	private double health;
	private double speed;
	private final IGameMetadata metadata;
	private final LootTable lootTable = new LootTable(Map.ofEntries(
			entry(ItemRarity.COMMON, 0.3),
			entry(ItemRarity.UNCOMMON, 0.2),
			entry(ItemRarity.RARE, 0.1)
	));

	/**
	 * Constructs a Polygon enemy with the specified amount of edges.
	 * <p>
	 * Note that the shape is defined by the amount of edges provided, meaning that the polygon coordinates is defined when initiated.
	 *
	 * @param edges the amount of edges the polygon enemy should start with
	 * @throws IllegalArgumentException if the amount of edges is below 3
	 */
	public PolygonEnemy(int edges) {
		if (edges < 3) throw new IllegalArgumentException("edges must be larger or equal to 3");
		this.edges = edges;
		this.health = edges;
		this.damage = edges;
		this.maxHealth = edges;
		this.speed = 1.0 / Math.log(1.0 + (edges - 2));
		this.metadata = new MetadataBuilder(GameElementType.ENEMY).getMetadata();
		this.setPolygonCoordinates(this.calculatePolygonCoordinates(edges, edges * 3.2));
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

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) {
		if (speed < 0) throw new IllegalArgumentException("speed is negative");
		this.speed = speed;
	}

	@Override
	public double getAttackDamage() {
		return this.damage;
	}

	@Override
	public IGameMetadata getMetadata() {
		return this.metadata;
	}

	@Override
	public long getDifficulty() {
		return Math.round(this.damage * this.maxHealth);
	}

	@Override
	public int getTier() {
		return this.edges - 2;
	}

	private List<Coordinates> calculatePolygonCoordinates(int polygonEdges, double radius) {
		List<Coordinates> polygonCoordinates = new ArrayList<>();
		// loops over all vertices in a regular polygon and creates and adds coordinates for each, based on the radius and the amount of edges
		for (int vertex = 0; vertex < polygonEdges; vertex++) {
			// find x
			double x = radius * Math.cos(2 * Math.PI * vertex / polygonEdges);
			// find y
			double y = radius * Math.sin(2 * Math.PI * vertex / polygonEdges);

			Coordinates point = new Coordinates(x, y);
			polygonCoordinates.add(point);
		}

		return polygonCoordinates;
	}

	// transforms this entity into a lower form
	private void transform(IEntityManager entityManager) {
		// instantiate the lower polygon entity
		PolygonEnemy transformedPolygonEnemy = new PolygonEnemy(this.getEdges() - 1);

		// data transfer from this entity to the lower polygon
		transformedPolygonEnemy.setCoordinates(this.getCoordinates());
		transformedPolygonEnemy.setRotation(this.getRotation());

		// entity switch between this entity and the transformed entity
		entityManager.removeEntity(this);
		entityManager.addEntity(transformedPolygonEnemy);
	}

	private void die(IEntityManager entityManager) {
		entityManager.removeEntity(this);
		this.lootTable.getItem().ifPresent(item -> {
			item.setCoordinates(this.getCoordinates());
			entityManager.addEntity(item);
		});
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof IAttackingEntity attackingEntity && attackingEntity.getAttackDamage() > 0 && otherEntity.getType() != this.getType()) {
			this.health -= attackingEntity.getAttackDamage();
			// death check
			if (this.getHealth() <= 0) {
				// transform or die
				if (this.getEdges() > 3) {
					this.transform(entityManager);
				} else {
					this.die(entityManager);
				}
			}
		}
	}
}
