package com.github.sef24sp4.player;
import com.github.sef24sp4.common.data.EntityManager;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
	private Player p;
	private final IEntityManager em = new EntityManager();

	@BeforeEach
	void setUp() {
		this.p = Player.getPlayer();
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void getPlayer() {
		assertEquals(p, Player.getPlayer());
	}
	@Test
	void getHealth() {
		assertEquals(p.getMaxHealth(), p.getHealth());
	}
	@Test
	void setWalkSpeed() {
		p.setWalkSpeed(100);
		assertEquals(100, p.getWalkSpeed());
		p.setWalkSpeed(10);
		assertEquals(10, p.getWalkSpeed());
	}
	@Test
	void takeDamage() {
		assertEquals(p.getMaxHealth(), p.getHealth());
		p.takeDamage(1, em);
		assertEquals(p.getMaxHealth()-1, p.getHealth());
	}
}
