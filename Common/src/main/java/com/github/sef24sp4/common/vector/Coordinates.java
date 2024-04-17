package com.github.sef24sp4.common.vector;

public class Coordinates extends BasicVector implements Cloneable {
	private static final IVector UNIT_VECTOR = new Coordinates(1, 0);

	public Coordinates() {
		super();
	}

	public Coordinates(final double x, final double y) {
		super(x, y);
	}

	/**
	 * Get a vector between current coordinates and the passed in coordinates.
	 *
	 * @param coordinates The other coordinates. Maybe any type of vector.
	 * @return A vector between the two coordinates.
	 */
	public IVector getVectorTo(final IVector coordinates) {
		final double deltaX = coordinates.getX() - this.getX();
		final double deltaY = coordinates.getY() - this.getY();
		return new Coordinates(deltaX, deltaY);
	}

	/**
	 * Get the relative rotation to the passed in coordinates.
	 *
	 * @param coordinates The coordinates to get relative rotation to.
	 * @return The relative rotation in radians.
	 */
	public double getRelativeRotationTo(final IVector coordinates) {
		final IVector vector = this.getVectorTo(coordinates);
		return vector.getAngleBetween(Coordinates.UNIT_VECTOR);
	}

	@Override
	public Coordinates clone() {
		return (Coordinates) super.clone();
	}
}
