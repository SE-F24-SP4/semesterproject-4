package com.github.sef24sp4.common.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IVectorTest {

	private IVector vector;
	private IVector otherVector;

	@BeforeEach
	void setUp() {
		this.vector = new IVector() {
			@Override
			public double getX() {
				return 3;
			}

			@Override
			public double getY() {
				return 4;
			}
		};

		this.otherVector = new IVector() {
			@Override
			public double getX() {
				return 5;
			}

			@Override
			public double getY() {
				return 3;
			}
		};
	}

	@Test
	void getX() {
		assertEquals(3, this.vector.getX());
	}

	@Test
	void getY() {
		assertEquals(4, this.vector.getY());
	}

	@Test
	void getNorm() {
		assertEquals(5, this.vector.getNorm());
	}

	@Test
	void getDotProduct() {
		assertEquals(27, this.vector.getDotProduct(this.otherVector));
	}

	@Test
	void getDeterminant() {
		assertEquals(-11, this.vector.getDeterminant(this.otherVector));
	}

	@Test
	void getAngleBetween() {
		assertEquals(0.3868757177310281, this.vector.getAngleBetween(this.otherVector));
	}


	@Test
	void getNormalizedVector() {
		final IVector normalizedVector = this.vector.getNormalizedVector();
		assertEquals(0.6, normalizedVector.getX());
		assertEquals(0.8, normalizedVector.getY());
	}

	@Test
	void getGetNegative() {
		final IVector negative = this.vector.getNegative();
		assertEquals(-3, negative.getX());
		assertEquals(-4, negative.getY());
	}
}