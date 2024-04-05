package com.github.sef24sp4.player;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;

public final class Player extends CommonEntity implements ICollidableEntity {
	private double health = 10;
	private double walkSpeed = 2;
	private final IGameMetadata metadata;
	private static final Player PLAYER = new Player();

	private Player() {
		this.metadata = new MetadataBuilder(GameElementType.PLAYER).
				getMetadata();
		this.setPolygonCoordinates(
				new Coordinates(-5, -5),
				new Coordinates(10, 0),
				new Coordinates(-5, 5)
		);
	}

	public static Player getPlayer() {
		return PLAYER;
	}

	public double getWalkSpeed() {
		return this.walkSpeed;
	}
	public void setWalkSpeed(double speed) {
		this.walkSpeed = speed;
	}
	public double getDiagonalWalkSpeed() {
		return this.walkSpeed * (this.walkSpeed / (Math.sqrt(2 * (this.walkSpeed * this.walkSpeed))));
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof IAttackingEntity) {
			this.takeDamage(((IAttackingEntity) otherEntity).getAttackDamage());
			if (this.health <= 0) {
				entityManager.removeEntity(PLAYER);
			}
		}
	}
	@Override
	public IGameMetadata getMetadata() {
		return this.metadata;
	}

	@Override
	public GameElementType getType() {
		return this.metadata.getType();
	}

	public double getHealth() {
		return this.health;
	}

	public void takeDamage(double damage) {
		this.health -= damage;
	}
}