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
		for (Player player : entityManager.getEntitiesByClass(Player.class)) {
			IGameInput keys = gameSettings.getKeys();
			double playerX = player.getX();
			double playerY = player.getY();

			//Set rotation to look a cursor
			player.setRotation(
					player.getCoordinates().getAngleBetween(
							keys.getMouseCoordinates()
					));

			//Check if player is outside playable area
			if (!gameSettings.isEntityWithinFrame(player)) {
				if (playerX < 0) {
					player.setX(0);
				}
				if (playerX > gameSettings.getDisplayWidth()) {
					player.setX(gameSettings.getDisplayWidth());
				}
				if (playerY < 0) {
					player.setY(0);
				}
				if (playerY > gameSettings.getDisplayHeight()) {
					player.setY(gameSettings.getDisplayHeight());
				}
			}

			//TODO:
			//Check if it should shoot
			if (keys.isDown(InputAction.SHOOT)) {
				System.out.println("Shooting not implemented yet");
				//Shoot code here
			}

			//Player movement
			else if (keys.isDown(InputAction.UP, InputAction.LEFT)) {
				player.setX(playerX - player.getDiagonalWalkSpeed());
				player.setY(playerY + player.getDiagonalWalkSpeed());
			}
			else if (keys.isDown(InputAction.UP, InputAction.RIGHT)) {
				player.setX(playerX + player.getDiagonalWalkSpeed());
				player.setY(playerY + player.getDiagonalWalkSpeed());
			}
			else if (keys.isDown(InputAction.DOWN, InputAction.LEFT)) {
				player.setX(playerX - player.getDiagonalWalkSpeed());
				player.setY(playerY - player.getDiagonalWalkSpeed());
			}
			else if (keys.isDown(InputAction.DOWN, InputAction.RIGHT)) {
				player.setX(playerX + player.getDiagonalWalkSpeed());
				player.setY(playerY - player.getDiagonalWalkSpeed());
			}
			else if (keys.isDown(InputAction.LEFT)) {
				player.setX(playerX - player.getWalkSpeed());
			}
			else if (keys.isDown(InputAction.RIGHT)) {
				player.setX(playerX + player.getWalkSpeed());
			}
			else if (keys.isDown(InputAction.UP)) {
				player.setY(playerY + player.getWalkSpeed());
			}
			else if (keys.isDown(InputAction.DOWN)) {
				player.setY(playerY - player.getWalkSpeed());
			}
		}
	}
}
