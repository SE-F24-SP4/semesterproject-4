package com.github.sef24sp4.common.metadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GameMetadataTest {
	private GameMetadata metadata;

	@BeforeEach
	void setUp() {
		this.metadata = new GameMetadata(GameElementType.ENEMY);
	}

	@Test
	void nullProperty() {
		assertNull(this.metadata.getProperty("test.not_a_key"));
	}

	@Test
	void getProperty() {
		this.metadata.setProperty("test", "hello world");
		assertEquals("hello world", this.metadata.getProperty("test"));
	}

	@Test
	void getType() {
		assertEquals(GameElementType.ENEMY, this.metadata.getType());
	}
}