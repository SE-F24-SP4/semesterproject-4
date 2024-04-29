package com.github.sef24sp4.common.entities;

import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;
import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonEntityTest {
	private CommonEntity entity;

	@BeforeEach
	void setUp() {
		this.entity = new CommonEntity() {
			@Override
			public IGameMetadata getMetadata() {
				return new MetadataBuilder(GameElementType.OTHER).getMetadata();
			}
		};
	}

	@Test
	void setX() {
		this.entity.setX(20);
		assertEquals(20, this.entity.getCoordinates().getX());
	}

	@Test
	void setY() {
		this.entity.setY(20);
		assertEquals(20, this.entity.getCoordinates().getY());
	}

	@Test
	void getCoordinates() {
		this.entity.setX(30);
		this.entity.setY(42);
		assertEquals(new Coordinates(30, 42), this.entity.getCoordinates());
	}

	@Test
	void setPolygonCoordinates() {
		final IVector c1 = new BasicVector(3, 4);
		final IVector c2 = new BasicVector(2, -7);
		List<IVector> testCoordinates = new ArrayList<>();
		testCoordinates.add(c1);
		testCoordinates.add(c2);

		final IVector[] actualCoordinates = new IVector[]{c1, c2};
		this.entity.setPolygonCoordinates(testCoordinates);
		assertArrayEquals(actualCoordinates, this.entity.getPolygonCoordinates());
	}

	@Test
	void setPolygonCoordinatesVarargs() {
		final IVector c1 = new BasicVector(3, 4);
		final IVector c2 = new BasicVector(2, -7);
		this.entity.setPolygonCoordinates(
				c1,
				c2
		);
		final IVector[] actualCoordinates = new IVector[]{c1, c2};
		assertArrayEquals(actualCoordinates, this.entity.getPolygonCoordinates());
	}


	@Test
	void getPolygonValuesArray() {
		final IVector c1 = new BasicVector(3, 4);
		final IVector c2 = new BasicVector(2, -7);
		this.entity.setPolygonCoordinates(
				c1,
				c2
		);
		final double[] actualCoordinates = new double[]{c1.getX(), c1.getY(), c2.getX(), c2.getY()};

		assertArrayEquals(actualCoordinates, this.entity.getPolygonValuesArray());
	}

	@Test
	void getRotation() {
		this.entity.setRotation(234);
		assertEquals(234, this.entity.getRotation());
	}

}