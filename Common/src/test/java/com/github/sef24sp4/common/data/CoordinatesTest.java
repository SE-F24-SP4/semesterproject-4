package com.github.sef24sp4.common.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

	private Coordinates coordinates;
	private Coordinates otherCoordinates;

	@BeforeEach
	void setUp() {
		this.coordinates = new Coordinates(3, 4);
		this.otherCoordinates = new Coordinates(5, 8);
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void setX() {
		assertDoesNotThrow(() -> this.coordinates.setX(42));
		assertEquals(42, this.coordinates.getX());
	}

	@Test
	void setY() {
		assertDoesNotThrow(() -> this.coordinates.setY(42));
		assertEquals(42, this.coordinates.getY());
	}

	@Test
	void getVectorTo() {
		assertEquals(new Coordinates(2, 4), this.coordinates.getVectorTo(this.otherCoordinates));
	}

	@Test
	void getRelativeRotationTo() {
		assertEquals(1.1071487177940904, this.coordinates.getRelativeRotationTo(this.otherCoordinates));
	}

	@Test
	void testEquals() {
		assertEquals(new Coordinates(3, 4), this.coordinates);
	}

	@Test
	void testHashCode() {
		assertEquals(new Coordinates(3, 4).hashCode(), this.coordinates.hashCode());
	}

	@Test
	void testClone() {
		final Coordinates clone = this.coordinates.clone();
		assertNotSame(this.coordinates, clone);
		clone.setX(23);
		clone.setY(78);
		assertNotEquals(this.coordinates.getX(), clone.getX());
		assertNotEquals(this.coordinates.getY(), clone.getY());
	}
}