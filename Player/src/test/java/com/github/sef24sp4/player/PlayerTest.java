package com.github.sef24sp4.player;
import com.github.sef24sp4.common.data.EntityManager;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	/**
	 * Need to implement healing
	@Test
	void getHealth() {
		assertEquals(p.getMaxHealth(), p.getHealth());
	}
	 **/
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
		p.takeDamage(5, em);
		assertEquals(p.getMaxHealth()-5, p.getHealth());
	}
	@Test
	void kill() {
		em.addEntity(p);
		assertEquals(1, em.getAllEntities().stream().filter(e -> e instanceof Player).toList().size());
		p.kill(em);
		assertEquals(0, em.getAllEntities().stream().filter(e -> e instanceof Player).toList().size());
	}
	@Test
	void takeDamageAndKill() {
		em.addEntity(p);
		assertEquals(1, em.getAllEntities().stream().filter(e -> e instanceof Player).toList().size());
		p.takeDamage(1000, em);
		assertEquals(0, em.getAllEntities().stream().filter(e -> e instanceof Player).toList().size());
	}
}
