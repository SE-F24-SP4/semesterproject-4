package com.github.sef24sp4.common.vector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BasicVectorTest {
	private BasicVector vector;

	@BeforeEach
	void setUp() {
		this.vector = new BasicVector(3, 4);
	}

	@Test
	void valuesOf() {
		final BasicVector actual = new BasicVector(42, 12);
		final IVector testVector = new TestVector();
		assertNotEquals(actual, testVector);
		assertEquals(actual, BasicVector.valuesOf(testVector));
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
	void scale() {
		final BasicVector actual = new BasicVector(12, 16);
		this.vector.scale(4);
		assertEquals(actual, this.vector);
	}

	@Test
	void add() {
		final BasicVector actual = new BasicVector(12, 16);
		this.vector.add(new BasicVector(9, 12));
		assertEquals(actual, this.vector);
	}

	@Test
	void subtract() {
		final BasicVector actual = new BasicVector(-12, -16);
		this.vector.subtract(new BasicVector(15, 20));
		assertEquals(actual, this.vector);
	}


	private static final class TestVector implements IVector {
		@Override
		public double getX() {
			return 42;
		}

		@Override
		public double getY() {
			return 12;
		}
	}
}