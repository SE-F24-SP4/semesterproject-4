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
	void getAngleBetween() {
		assertEquals(0.386875717731028, this.vector.getAngleBetween(this.otherVector));
	}
}