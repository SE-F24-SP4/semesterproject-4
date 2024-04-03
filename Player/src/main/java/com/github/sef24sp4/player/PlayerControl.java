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
					));

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

			//TODO:
			//Check if it should shoot
			if (keys.isDown(InputAction.SHOOT)) {
				System.out.println("Shooting not implemented yet");
				//Shoot code here
			}
			
			//Player movement
			if (keys.isDown(InputAction.UP, InputAction.LEFT)) {
				player.setX(player.getX() - player.getDiagonalWalkSpeed());
				player.setY(player.getY() + player.getDiagonalWalkSpeed());
			}
			if (keys.isDown(InputAction.UP, InputAction.RIGHT)) {
				player.setX(player.getX() + player.getDiagonalWalkSpeed());
				player.setY(player.getY() + player.getDiagonalWalkSpeed());
			}
			if (keys.isDown(InputAction.DOWN, InputAction.LEFT)) {
				player.setX(player.getX() - player.getDiagonalWalkSpeed());
				player.setY(player.getY() - player.getDiagonalWalkSpeed());
			}
			if (keys.isDown(InputAction.DOWN, InputAction.RIGHT)) {
				player.setX(player.getX() + player.getDiagonalWalkSpeed());
				player.setY(player.getY() - player.getDiagonalWalkSpeed());
			}
			else if (keys.isDown(InputAction.LEFT)) {
				player.setX(player.getX() - player.getWalkSpeed());
			}
			else if (keys.isDown(InputAction.RIGHT)) {
				player.setX(player.getX() + player.getWalkSpeed());
			}
			else if (keys.isDown(InputAction.UP)) {
				player.setY(player.getY() + player.getWalkSpeed());
			}
			else if (keys.isDown(InputAction.DOWN)) {
				player.setY(player.getY() - player.getWalkSpeed());
			}
		}
	}
}
