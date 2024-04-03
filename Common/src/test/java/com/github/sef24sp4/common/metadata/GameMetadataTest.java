package com.github.sef24sp4.common.metadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMetadataTest {
	private GameMetadata metadata;

	@BeforeEach
	void setUp() {
		this.metadata = new GameMetadata(GameElementType.ENEMY);
	}

	@Test
	void nullProperty() {
		assertTrue(this.metadata.getProperty("test.not_a_key").isEmpty());
	}

	@Test
	void getProperty() {
		this.metadata.setProperty("test", "hello world");
		assertDoesNotThrow(() -> {
			assertEquals("hello world", this.metadata.getProperty("test").get());
		}, "Could not `get` value from returned property");
	}

	@Test
	void getType() {
		assertEquals(GameElementType.ENEMY, this.metadata.getType());
	}
}