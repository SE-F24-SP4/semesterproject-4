package com.github.sef24sp4.player;

import com.github.sef24sp4.common.gamecontrol.IGameInput;
import com.github.sef24sp4.common.gamecontrol.InputAction;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import java.util.Collection;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

public class PlayerControl implements IEntityProcessingService {

	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		Player player = Player.getPlayer();
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

		//Check if it should shoot
		if (keys.isDown(InputAction.SHOOT)) {
			getProjectileSPIs().stream().findFirst().ifPresent(
					projectileSPI -> {entityManager.addEntity(projectileSPI.createProjectile(player));}
			);
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
	
	//Get all ProjectileSPIs
	private Collection<? extends ProjectileSPI> getProjectileSPIs() {
		return ServiceLoader.load(ProjectileSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}
}
