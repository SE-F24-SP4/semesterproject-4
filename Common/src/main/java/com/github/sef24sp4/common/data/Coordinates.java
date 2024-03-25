package com.github.sef24sp4.common.data;

import com.github.sef24sp4.common.interfaces.IVector;

import java.util.Objects;

public class Coordinates implements IVector {
	private static final IVector UNIT_VECTOR = new Coordinates(1, 0);
	private double x;
	private double y;

	public Coordinates() {
		this(0, 0);
	}

	public Coordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}


	/**
	 * Get a vector between current coordinates and the passed in coordinates.
	 *
	 * @param coordinates The other coordinates. Maybe any type of vector.
	 * @return A vector between the two coordinates.
	 */
	public IVector getVectorTo(IVector coordinates) {
		double deltaX = coordinates.getX() - this.getX();
		double deltaY = coordinates.getY() - this.getY();
		return new Coordinates(deltaX, deltaY);
	}


	/**
	 * Get the relative rotation to the passed in coordinates.
	 *
	 * @param coordinates The coordinates to get relative rotation to.
	 * @return The relative rotation in radians.
	 */
	public double getRelativeRotationTo(IVector coordinates) {
		IVector vector = this.getVectorTo(coordinates);
		return vector.getAngleBetween(Coordinates.UNIT_VECTOR);
	}


	@Override
	public boolean equals(Object o) {
		if (o instanceof Coordinates other) return this.getX() == other.getX() && this.getY() == other.getY();
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.x, this.y);
	}
}
