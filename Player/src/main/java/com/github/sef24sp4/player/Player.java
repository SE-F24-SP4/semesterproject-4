package com.github.sef24sp4.player;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;

public final class Player extends CommonEntity implements ICollidableEntity, IAttackingEntity {
	private double health = 10;
	private double attackDamage = 1;
	private double walkSpeed = 10;
	private final double diagonalWalkSpeed = Math.sqrt(2 * (this.walkSpeed * this.walkSpeed));
	private final IGameMetadata metadata;
	private static Player player;

	private Player() {
		player = new Player();
		this.metadata = new MetadataBuilder(GameElementType.PLAYER).
				getMetadata();
		player.setPolygonCoordinates(
				new Coordinates(-0.5, -0.5),
				new Coordinates(1, 0),
				new Coordinates(-0.5, 0.5)
		);
	}

	public static Player getPlayer() {
		if (player == null) {
			player = new Player();
		}
		return player;
	}

	public double getWalkSpeed() {
		return this.walkSpeed;
	}
	public void setWalkSpeed(double speed) {
		this.walkSpeed = speed;
	}
	public double getDiagonalWalkSpeed() {
		return this.diagonalWalkSpeed;
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		if (otherEntity instanceof IAttackingEntity) {
			Player.getPlayer().setHealth(
					Player.getPlayer().getHealth() - ((IAttackingEntity) otherEntity).getAttackDamage()
					);
					//TODO: Adjust position to not be inside other entity.
			if (Player.getPlayer().getHealth() <= 0) {
				entityManager.removeEntity(Player.getPlayer());
			}
		} else {
			/* TODO:
			 * 	- Collision with wall (adjust position to not be inside wall).
			 * 	- Collision with other HealthEntity (increase health).
			 */
			System.out.println("Collision not implemented yet");
		}
	}

	@Override
	public double[] getPolygonValuesArray() {
		return super.getPolygonValuesArray();
	}

	@Override
	public double getX() {
		return super.getX();
	}

	@Override
	public double getY() {
		return super.getY();
	}

	@Override
	public IGameMetadata getMetadata() {
		return this.metadata;
	}

	@Override
	public GameElementType getType() {
		return this.metadata.getType();
	}

	@Override
	public double getAttackDamage() {
		return this.attackDamage;
	}
	public void setAttackDamage(double damage) {
		this.attackDamage = damage;
	}

	public double getHealth() {
		return this.health;
	}

	public void setHealth(double health) {
		this.health = health;
	}
}