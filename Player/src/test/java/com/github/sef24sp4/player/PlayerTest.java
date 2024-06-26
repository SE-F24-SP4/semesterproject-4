package com.github.sef24sp4.player;

import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.GameSettingsLoader;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.item.itemtypes.WeaponItem;
import com.github.sef24sp4.common.projectile.CommonProjectile;
import com.github.sef24sp4.common.weapon.WeaponSPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerTest {
	private Player player;

	@Mock
	private IEntityManager mockEntityManager;

	private static void runWithMockedGameSettings(final Executable executor) throws Throwable {
		try (MockedStatic<GameSettingsLoader> mockedGameSettings = mockStatic(GameSettingsLoader.class)) {
			mockedGameSettings.when(GameSettingsLoader::load).thenReturn(mock(IGameSettings.class));
			executor.execute();
			mockedGameSettings.verify(GameSettingsLoader::load); // If this fails then GameSettings is not needed. Run without this method.
		}
	}

	@BeforeEach
	void setUp() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
		final Constructor<Player> constructor = Player.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		this.player = constructor.newInstance();
	}

	@Test
	void getPlayer() {
		assertInstanceOf(Player.class, Player.getPlayer());
		Player p = Player.getPlayer();
		assertSame(p, Player.getPlayer());
	}

	@Test
	void getHealth() {
		assertEquals(50, this.player.getHealth());
	}

	@Test
	void getMaxHealth() {
		assertEquals(50, this.player.getMaxHealth());
	}

	@Test
	void getWalkSpeed() {
		assertEquals(2, this.player.getWalkSpeed());
	}

	@Test
	void takeDamage() {
		assertEquals(this.player.getMaxHealth(), this.player.getHealth());
		this.player.takeDamage(5, this.mockEntityManager);
		assertEquals(this.player.getMaxHealth() - 5, this.player.getHealth());
	}

	@Test
	void heal() {
		assertEquals(this.player.getMaxHealth(), this.player.getHealth());
		this.player.takeDamage(5, this.mockEntityManager);
		assertEquals(this.player.getMaxHealth() - 5, this.player.getHealth());
		this.player.heal(5);
		assertEquals(this.player.getMaxHealth(), this.player.getHealth());
	}

	@Test
	void testHealNegativeAmount() {
		assertThrows(IllegalArgumentException.class, () -> this.player.heal(-10.0));
	}

	@Test
	void testHealExceedsMaxHealth() {
		double maxHealth = this.player.getMaxHealth();
		this.player.heal(maxHealth + 10.0);
		assertEquals(maxHealth, this.player.getHealth());
	}

	@Test
	void kill() throws Throwable {
		this.mockEntityManager.addEntity(this.player);

		runWithMockedGameSettings(() -> this.player.kill(this.mockEntityManager));

		verify(this.mockEntityManager).removeEntity(this.player);
	}

	@Test
	void takeDamageAndKill() throws Throwable {
		this.mockEntityManager.addEntity(this.player);

		runWithMockedGameSettings(() -> this.player.takeDamage(1000, this.mockEntityManager));

		verify(this.mockEntityManager).removeEntity(this.player);
	}

	@Test
	void collideWithAttackingEntity() {
		//Setup
		ICollidableEntity mockCollidableEntity = mock(ICollidableEntity.class, withSettings().extraInterfaces(IAttackingEntity.class));
		IAttackingEntity mockAttackingEntity = (IAttackingEntity) mockCollidableEntity;
		when(mockAttackingEntity.getAttackDamage()).thenReturn(1.0);

		//Method call
		this.player.collide(this.mockEntityManager, mockCollidableEntity);

		//Verify
		verify(mockAttackingEntity).getAttackDamage();
	}

	@Test
	void collideWithOwnBullet() {
		//Setup
		CommonProjectile mockCommonProjectile = mock(CommonProjectile.class);
		when(mockCommonProjectile.getShooter()).thenReturn(this.player);

		//Method call
		this.player.collide(this.mockEntityManager, mockCommonProjectile);

		//Verify
		verify(mockCommonProjectile).getShooter();
		verify(this.mockEntityManager, never()).removeEntity(this.player);
	}

	@Test
	public void collideWithWeaponItem() {
		// Arrange
		WeaponSPI mockWeaponSPI = mock(WeaponSPI.class);
		WeaponItem weaponItem = mock(WeaponItem.class);
		when(weaponItem.getWeaponSPI()).thenReturn(mockWeaponSPI);

		// Act
		this.player.collide(this.mockEntityManager, weaponItem);

		// Assert
		this.player.getActiveWeapon().ifPresent(weaponSPI -> assertEquals(mockWeaponSPI, weaponSPI));
	}

	@Test
	public void shoot() {
		// Arrange
		WeaponSPI mockWeaponSPI = mock(WeaponSPI.class);
		WeaponItem mockWeaponItem = mock(WeaponItem.class);
		when(mockWeaponItem.getWeaponSPI()).thenReturn(mockWeaponSPI);
		when(mockWeaponSPI.getAmmoCount()).thenReturn(1);

		// Act
		this.player.collide(this.mockEntityManager, mockWeaponItem);
		this.player.shoot(this.mockEntityManager);

		// Assert
		verify(mockWeaponSPI).shoot(this.mockEntityManager, this.player);
	}
}