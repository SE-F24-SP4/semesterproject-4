package com.github.sef24sp4.common.metadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetadataBuilderTest {
	private MetadataBuilder builder;

	@BeforeEach
	void setUp() {
		this.builder = new MetadataBuilder(GameElementType.ITEM);
	}

	@Test
	void getMetadata() {
		assertInstanceOf(MetadataBuilder.class, this.builder.setProperty("test", "hello"));
		IGameMetadata metadata = this.builder.getMetadata();

		assertEquals(GameElementType.ITEM, metadata.getType());

		assertDoesNotThrow(() -> {
			assertEquals("hello", metadata.getProperty("test").get());
		});
	}
}