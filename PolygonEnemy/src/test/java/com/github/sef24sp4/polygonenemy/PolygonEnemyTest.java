package com.github.sef24sp4.polygonenemy;

import com.github.sef24sp4.common.metadata.GameElementType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolygonEnemyTest {

	private PolygonEnemy polygonEnemy;

	@BeforeEach
	void setUp() {
		this.polygonEnemy = new PolygonEnemy(5);
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void getEdges() {
		assertEquals(5, this.polygonEnemy.getEdges());
	}

	@Test
	void getMaxHealth() {
		assertEquals(5, this.polygonEnemy.getMaxHealth());
	}

	@Test
	void getHealth() {
		assertEquals(5, this.polygonEnemy.getHealth());
	}

	@Test
	void getAttackDamage() {
		assertEquals(5, this.polygonEnemy.getAttackDamage());
	}

	@Test
	void collide() {

	}

	@Test
	void getMetadata() {
		assertEquals(GameElementType.ENEMY, this.polygonEnemy.getType());
	}
}