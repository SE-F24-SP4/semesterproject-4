package com.github.sef24sp4.player;

import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.gamecontrol.InputAction;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IEntityProcessingService;

public class PlayerControl implements IEntityProcessingService {
	private double walkSpeed = 10;
	private double diagonalWalkSpeed = Math.sqrt(2 * (this.walkSpeed * this.walkSpeed));

	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		for (CommonEntity player : entityManager.getEntitiesByClass(Player.class)) {
			//Set rotation to look a cursor
			player.getCoordinates().getAngleBetween(gameSettings.getKeys().getMouseCoordinates());
			//Check if it should shoot
			if (gameSettings.getKeys().isDown(InputAction.SHOOT)) {
				System.out.println("Shooting not implemented yet");
				//Shoot code here
			}
			//Player movement
			if (gameSettings.getKeys().isDown(InputAction.UP) && gameSettings.getKeys().isDown(InputAction.LEFT)) {
				player.setX(player.getX() - this.diagonalWalkSpeed);
				player.setY(player.getY() + this.diagonalWalkSpeed);
			}
			if (gameSettings.getKeys().isDown(InputAction.UP) && gameSettings.getKeys().isDown(InputAction.RIGHT)) {
				player.setX(player.getX() + this.diagonalWalkSpeed);
				player.setY(player.getY() + this.diagonalWalkSpeed);
			}
			if (gameSettings.getKeys().isDown(InputAction.DOWN) && gameSettings.getKeys().isDown(InputAction.LEFT)) {
				player.setX(player.getX() - this.diagonalWalkSpeed);
				player.setY(player.getY() - this.diagonalWalkSpeed);
			}
			if (gameSettings.getKeys().isDown(InputAction.DOWN) && gameSettings.getKeys().isDown(InputAction.RIGHT)) {
				player.setX(player.getX() + this.diagonalWalkSpeed);
				player.setY(player.getY() - this.diagonalWalkSpeed);
			}
			if (gameSettings.getKeys().isDown(InputAction.LEFT) && !gameSettings.getKeys().isDown(InputAction.UP)
					&& !gameSettings.getKeys().isDown(InputAction.DOWN)) {
				player.setX(player.getX() - this.walkSpeed);
			}
			if (gameSettings.getKeys().isDown(InputAction.RIGHT) && !gameSettings.getKeys().isDown(InputAction.UP)
					&& !gameSettings.getKeys().isDown(InputAction.DOWN)) {
				player.setX(player.getX() + this.walkSpeed);
			}
			if (gameSettings.getKeys().isDown(InputAction.UP) && !gameSettings.getKeys().isDown(InputAction.LEFT)
					&& !gameSettings.getKeys().isDown(InputAction.RIGHT)) {
				player.setY(player.getY() + this.walkSpeed);
			}
			if (gameSettings.getKeys().isDown(InputAction.DOWN) && !gameSettings.getKeys().isDown(InputAction.LEFT)
					&& !gameSettings.getKeys().isDown(InputAction.RIGHT)) {
				player.setY(player.getY() - this.walkSpeed);
			}
			//Check if player is outside playable area
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
}
