package com.github.sef24sp4.player;

import com.github.sef24sp4.common.gamecontrol.IGameInput;
import com.github.sef24sp4.common.gamecontrol.InputAction;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.weapon.WeaponSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import java.util.ServiceLoader;

public class PlayerControl implements IEntityProcessingService {
	private final Player player = Player.getPlayer();

	private final ServiceLoader<WeaponSPI> weaponProviders = ServiceLoader.load(WeaponSPI.class);
	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		double speed = this.player.getWalkSpeed();
		double diagonalSpeed = this.player.getDiagonalWalkSpeed();
		IGameInput keys = gameSettings.getKeys();
		double playerX = this.player.getX();
		double playerY = this.player.getY();

		//Set rotation to look a cursor
		this.player.setRotation(
				this.player.getCoordinates().getRelativeRotationTo(
						keys.getMouseCoordinates()
				));

		//Check if it should shoot
		if (keys.isDown(InputAction.SHOOT)) {
			this.weaponProviders.forEach(weaponSPI -> {
						if (weaponSPI.getRemainingCoolDownTicks() <= 0 && weaponSPI.getAmmoCount() > 0) {
							weaponSPI.shoot(entityManager, this.player);
						}
					}
			);
		}
		if (keys.isDown(InputAction.UP, InputAction.LEFT)) {
			this.player.setX(playerX - diagonalSpeed);
			this.player.setY(playerY - diagonalSpeed);
		} else if (keys.isDown(InputAction.UP, InputAction.RIGHT)) {
			this.player.setX(playerX + diagonalSpeed);
			this.player.setY(playerY - diagonalSpeed);
		} else if (keys.isDown(InputAction.DOWN, InputAction.LEFT)) {
			this.player.setX(playerX - diagonalSpeed);
			this.player.setY(playerY + diagonalSpeed);
		} else if (keys.isDown(InputAction.DOWN, InputAction.RIGHT)) {
			this.player.setX(playerX + diagonalSpeed);
			this.player.setY(playerY + diagonalSpeed);
		} else if (keys.isDown(InputAction.LEFT)) {
			this.player.setX(playerX - speed);
		} else if (keys.isDown(InputAction.RIGHT)) {
			this.player.setX(playerX + speed);
		} else if (keys.isDown(InputAction.UP)) {
			this.player.setY(playerY - speed);
		} else if (keys.isDown(InputAction.DOWN)) {
			this.player.setY(playerY + speed);
		}
		//Check if player is outside playable area
		if (!gameSettings.isEntityWithinFrame(this.player)) {
			playerX = this.player.getX();
			playerY = this.player.getY();
			if (playerX < 0) {
				this.player.setX(0);
			}
			if (playerX > gameSettings.getDisplayWidth()) {
				this.player.setX(gameSettings.getDisplayWidth());
			}
			if (playerY < 0) {
				this.player.setY(0);
			}
			if (playerY > gameSettings.getDisplayHeight()) {
				this.player.setY(gameSettings.getDisplayHeight());
			}
		}
	}
}
