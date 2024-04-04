package com.github.sef24sp4.player;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;

public class Player extends CommonEntity implements ICollidableEntity, IAttackingEntity {
	private double health = 10;
	private double attackDamage = 1;
	private double walkSpeed = 10;
	private final double diagonalWalkSpeed = Math.sqrt(2 * (this.walkSpeed * this.walkSpeed));
	private final IGameMetadata metadata;
	private static Player player;

	private Player () {
		player = new Player();
		this.metadata = new MetadataBuilder(GameElementType.PLAYER).
				getMetadata();
		player.setPolygonCoordinates(
				new Coordinates(-5, -5),
				new Coordinates(10, 0),
				new Coordinates(-5, 5)
		);
	}

	public static Player getPlayer(){
		if(player == null) {
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
		return diagonalWalkSpeed;
	}

	@Override
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity) {
		/* if (otherEntity.getMetadata().getType() == GameElementType.ENEMY)
		Player.getPlayer().setHealth(
				Player.getPlayer().getHealth() - otherEntity.getAttackDamage());
		if(Player.getPlayer().getHealth() <= 0) {
			//IDK
		}
		 */
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
		return metadata;
	}

	@Override
	public GameElementType getType() {
		return ICollidableEntity.super.getType();
	}

	@Override
	public double getAttackDamage() {
		return attackDamage;
	}
	public void setAttackDamage(double damage) {
		this.attackDamage = damage;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}
}