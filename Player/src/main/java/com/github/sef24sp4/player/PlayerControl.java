package com.github.sef24sp4.player;

import com.github.sef24sp4.common.gamecontrol.IGameInput;
import com.github.sef24sp4.common.gamecontrol.InputAction;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.weapon.WeaponSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import java.util.ServiceLoader;

public class PlayerControl implements IEntityProcessingService {
	private final ServiceLoader<WeaponSPI> weaponProviders = ServiceLoader.load(WeaponSPI.class);
	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		Player player = Player.getPlayer();
		IGameInput keys = gameSettings.getKeys();
		double playerX = player.getX();
		double playerY = player.getY();

		//Set rotation to look a cursor
		player.setRotation(
				player.getCoordinates().getRelativeRotationTo(
						keys.getMouseCoordinates()
				));

		//Check if it should shoot
		if (keys.isDown(InputAction.SHOOT)) {
			this.weaponProviders.forEach(weaponSPI -> {
						if (weaponSPI.getRemainingCoolDownTicks() <= 0 && weaponSPI.getAmmoCount() > 0) {
							weaponSPI.shoot(entityManager, player);
						}
					}
			);
		}
		if (keys.isDown(InputAction.UP, InputAction.LEFT)) {
			player.setX(playerX - player.getDiagonalWalkSpeed());
			player.setY(playerY - player.getDiagonalWalkSpeed());
		} else if (keys.isDown(InputAction.UP, InputAction.RIGHT)) {
			player.setX(playerX + player.getDiagonalWalkSpeed());
			player.setY(playerY - player.getDiagonalWalkSpeed());
		} else if (keys.isDown(InputAction.DOWN, InputAction.LEFT)) {
			player.setX(playerX - player.getDiagonalWalkSpeed());
			player.setY(playerY + player.getDiagonalWalkSpeed());
		} else if (keys.isDown(InputAction.DOWN, InputAction.RIGHT)) {
			player.setX(playerX + player.getDiagonalWalkSpeed());
			player.setY(playerY + player.getDiagonalWalkSpeed());
		} else if (keys.isDown(InputAction.LEFT)) {
			player.setX(playerX - player.getWalkSpeed());
		} else if (keys.isDown(InputAction.RIGHT)) {
			player.setX(playerX + player.getWalkSpeed());
		} else if (keys.isDown(InputAction.UP)) {
			player.setY(playerY - player.getWalkSpeed());
		} else if (keys.isDown(InputAction.DOWN)) {
			player.setY(playerY + player.getWalkSpeed());
		}
		//Check if player is outside playable area
		if (!gameSettings.isEntityWithinFrame(player)) {
			playerX = player.getX();
			playerY = player.getY();
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
	}
}
