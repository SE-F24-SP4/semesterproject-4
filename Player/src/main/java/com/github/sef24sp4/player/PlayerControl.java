package com.github.sef24sp4.player;

import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.gamecontrol.IGameInput;
import com.github.sef24sp4.common.gamecontrol.InputAction;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IEntityProcessingService;

public class PlayerControl implements IEntityProcessingService {

	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		IGameInput keys = gameSettings.getKeys();
		for (Player player : entityManager.getEntitiesByClass(Player.class)) {
			//Set rotation to look a cursor
			player.setRotation(
					player.getCoordinates().getAngleBetween(
							keys.getMouseCoordinates()
					)
			);
			//TODO:
			//Check if it should shoot
			if (keys.isDown(InputAction.SHOOT)) {
				System.out.println("Shooting not implemented yet");
				//Shoot code here
			}
			//Player movement
			if (keys.isDown(InputAction.UP) && keys.isDown(InputAction.LEFT)) {
				player.setX(player.getX() - player.getDiagonalWalkSpeed());
				player.setY(player.getY() + player.getDiagonalWalkSpeed());
			}
			if (keys.isDown(InputAction.UP) && keys.isDown(InputAction.RIGHT)) {
				player.setX(player.getX() + player.getDiagonalWalkSpeed());
				player.setY(player.getY() + player.getDiagonalWalkSpeed());
			}
			if (keys.isDown(InputAction.DOWN) && keys.isDown(InputAction.LEFT)) {
				player.setX(player.getX() - player.getDiagonalWalkSpeed());
				player.setY(player.getY() - player.getDiagonalWalkSpeed());
			}
			if (keys.isDown(InputAction.DOWN) && keys.isDown(InputAction.RIGHT)) {
				player.setX(player.getX() + player.getDiagonalWalkSpeed());
				player.setY(player.getY() - player.getDiagonalWalkSpeed());
			}
			if (keys.isDown(InputAction.LEFT) && !keys.isDown(InputAction.UP)
					&& !keys.isDown(InputAction.DOWN)) {
				player.setX(player.getX() - player.getWalkSpeed());
			}
			if (keys.isDown(InputAction.RIGHT) && !keys.isDown(InputAction.UP)
					&& !keys.isDown(InputAction.DOWN)) {
				player.setX(player.getX() + player.getWalkSpeed());
			}
			if (keys.isDown(InputAction.UP) && !keys.isDown(InputAction.LEFT)
					&& !keys.isDown(InputAction.RIGHT)) {
				player.setY(player.getY() + player.getWalkSpeed());
			}
			if (keys.isDown(InputAction.DOWN) && !keys.isDown(InputAction.LEFT)
					&& !keys.isDown(InputAction.RIGHT)) {
				player.setY(player.getY() - player.getWalkSpeed());
			}
			//Check if player is outside playable area
			if (!gameSettings.isEntityWithinFrame(player)) {
				if (player.getX() < 0) {
					player.setX(0);
				}
				if (player.getX() > gameSettings.getDisplayWidth()) {
					player.setX(gameSettings.getDisplayWidth());
				}
				if (player.getY() < 0) {
					player.setY(0);
				}
				if (player.getY() > gameSettings.getDisplayHeight()) {
					player.setY(gameSettings.getDisplayHeight());
				}
			}
		}
	}
}
