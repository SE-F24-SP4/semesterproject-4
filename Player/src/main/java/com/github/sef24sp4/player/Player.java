package com.github.sef24sp4.player;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;

public class Player extends CommonEntity implements ICollidableEntity, IAttackingEntity {
	/*TODO:
implement health and damage taking
Interface with Vivek
 */
	private double walkSpeed = 10;
	private final double diagonalWalkSpeed = Math.sqrt(2 * (this.walkSpeed * this.walkSpeed));
	private final IGameMetadata metadata;
	private static Player player;

	private Player (IGameSettings gameSettings) {
		player = new Player(gameSettings);
		this.metadata = new MetadataBuilder(GameElementType.PLAYER).
				getMetadata();
		player.setX(gameSettings.getDisplayWidth() / 2.0);
		player.setY(gameSettings.getDisplayHeight() / 2.0);
		player.setPolygonCoordinates(
				new Coordinates(-5, -5),
				new Coordinates(10, 0),
				new Coordinates(-5, 5)
		);
	}

	public static Player getPlayer(IGameSettings gameSettings){
		if(player == null) {
			player = new Player(gameSettings);
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
		return null;
	}

	@Override
	public GameElementType getType() {
		return ICollidableEntity.super.getType();
	}

	@Override
	public double getAttackDamage() {
		return 0;
	}
}