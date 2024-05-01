package com.github.sef24sp4.player;
import com.github.sef24sp4.common.entities.IAttackingEntity;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.projectile.CommonProjectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PlayerTest {
	private Player player;
	private IEntityManager mockEntityManager;

	@BeforeEach
	void setUp() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
		final Constructor<Player> constructor = Player.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		this.player = constructor.newInstance();

		this.mockEntityManager = mock(IEntityManager.class);
	}

	@Test
	void getPlayer() {
		assertInstanceOf(Player.class, Player.getPlayer());
	}
	@Test
	void getHealth() {
		assertEquals(10, this.player.getHealth());
	}
	@Test
	void getMaxHealth() {
		assertEquals(10, this.player.getMaxHealth());
	}
	@Test
	void setWalkSpeed() {
		this.player.setWalkSpeed(100);
		assertEquals(100, this.player.getWalkSpeed());
		this.player.setWalkSpeed(10);
		assertEquals(10, this.player.getWalkSpeed());
	}
	@Test
	void takeDamage() {
		assertEquals(this.player.getMaxHealth(), this.player.getHealth());
		this.player.takeDamage(5, this.mockEntityManager);
		assertEquals(this.player.getMaxHealth() - 5, this.player.getHealth());
	}
	@Test
	void kill() {
		this.mockEntityManager.addEntity(this.player);
		this.player.kill(this.mockEntityManager);
		verify(this.mockEntityManager).removeEntity(this.player);
	}
	@Test
	void takeDamageAndKill() {
		this.mockEntityManager.addEntity(this.player);
		this.player.takeDamage(1000, this.mockEntityManager);
		verify(this.mockEntityManager).removeEntity(this.player);
	}
	@Test
	void collideWithAttackingEntity() {
    //Setup
    ICollidableEntity mockCollidableEntity = mock(ICollidableEntity.class, withSettings().extraInterfaces(IAttackingEntity.class));
    IAttackingEntity mockAttackingEntity = (IAttackingEntity) mockCollidableEntity;
    when(mockAttackingEntity.getAttackDamage()).thenReturn(10.0);

    //Method call
    this.player.collide(this.mockEntityManager, mockCollidableEntity);

    //Verify
    verify(mockAttackingEntity).getAttackDamage();
	}
	@Test
	void collideWithOwnBullet() {
    //Setup
	CommonProjectile mockCommonProjectile = mock(CommonProjectile.class, withSettings().extraInterfaces(ICollidableEntity.class));
    when(mockCommonProjectile.getShooter()).thenReturn(this.player);

    //Method call
    this.player.collide(this.mockEntityManager, mockCommonProjectile);

    //Verify
	verify(mockCommonProjectile).getShooter();
    verifyNoMoreInteractions(this.mockEntityManager, mockCommonProjectile);
	}
}
