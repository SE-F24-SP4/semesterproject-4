package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.common.vector.IVector;

class CoordinatesOutOfBoundsException extends Exception {
	CoordinatesOutOfBoundsException(final IVector coordinates) {
		this(String.format("Coordinates (%.3f,%.3f) is out of bounds", coordinates.getX(), coordinates.getY()));
	}

	CoordinatesOutOfBoundsException(final String message) {
		super(message);
	}
}
