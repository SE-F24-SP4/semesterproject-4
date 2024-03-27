package com.github.sef24sp4.player;

import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.gamecontrol.IMouseCoordinates;
import com.github.sef24sp4.common.gamecontrol.InputAction;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IEntityProcessingService;

public class PlayerControl implements IEntityProcessingService {
	private IMouseCoordinates mouse;
	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		for (CommonEntity player : entityManager.getEntitiesByClass(Player.class)) {
			player.setRotation(this.getRotationToCursor(player, this.mouse));

			if (gameSettings.getKeys().isDown(InputAction.SHOOT)) {
				System.out.println("Shooting not implemented yet");
				//Shoot
			}
			if (gameSettings.getKeys().isDown(InputAction.LEFT)) {
				player.setX(player.getX() - 1);
			}
			if (gameSettings.getKeys().isDown(InputAction.RIGHT)) {
				player.setX(player.getX() + 1);
			}
			if (gameSettings.getKeys().isDown(InputAction.UP)) {
				player.setY(player.getY() + 1);
			}
			if (gameSettings.getKeys().isDown(InputAction.DOWN)) {
				player.setY(player.getY() - 1);
			}
			if (player.getX() < 0) {
				player.setX(1);
			}
			if (player.getX() > gameSettings.getDisplayWidth()) {
				player.setX(gameSettings.getDisplayWidth() - 1);
			}
			if (player.getY() < 0) {
				player.setY(1);
			}
			if (player.getY() > gameSettings.getDisplayHeight()) {
				player.setY(gameSettings.getDisplayHeight() - 1);
			}
		}
	}

	public double getRotationToCursor(CommonEntity entity, IMouseCoordinates cursor) {
		double xDistance = cursor.getX() - entity.getX();
		double yDistance = cursor.getY() - entity.getY();

		double rotation = Math.toDegrees(Math.atan2(yDistance, xDistance));

		return  rotation;
	}
}
