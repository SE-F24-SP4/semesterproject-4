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
	 * Constructs a new {@link Coordinates} using the values from generic {@link IVector vector}.
	 *
	 * @param vector The {@link IVector} to construct from.
	 * @return The newly constructed {@link Coordinates}.
	 */
	public static Coordinates valuesOf(final IVector vector) {
		return new Coordinates(vector.getX(), vector.getY());
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
