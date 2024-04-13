package com.github.sef24sp4.common.vector;

import com.github.sef24sp4.common.interfaces.IVector;

import java.util.Objects;

/**
 * A basic {@link IVector} implementation.
 * It is provided as a default implementation of {@link IVector}.
 */
public class BasicVector implements IVector, Cloneable {
	private double x;
	private double y;

	public BasicVector() {
		this(0, 0);
	}

	public BasicVector(final double x, final double y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Constructs a new {@link BasicVector} using the values from generic {@link IVector vector}.
	 *
	 * @param vector The {@link IVector} to construct from.
	 * @return The newly constructed {@link BasicVector}.
	 */
	public static BasicVector from(final IVector vector) {
		return new BasicVector(vector.getX(), vector.getY());
	}

	@Override
	public double getX() {
		return this.x;
	}

	public void setX(final double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	public void setY(final double y) {
		this.y = y;
	}

	/**
	 * Scale the current vector with {@code factor}.
	 *
	 * @param factor The factor to scale the current vector with.
	 */
	public void scale(final double factor) {
		this.x *= factor;
		this.y *= factor;
	}

	/**
	 * Add the {@code vector} to the current vector.
	 * This is done by adding the individual components from {@code vector}
	 * to the current vectors components.
	 *
	 * @param vector The vector whose components will be added with.
	 * @see #subtract(IVector)
	 */
	public void add(final IVector vector) {
		this.x += vector.getX();
		this.y += vector.getY();
	}

	/**
	 * Subtract the components of {@code vector} from the current vectors components.
	 * This is exactly the same as calling {@link #add(IVector)} with {@link IVector#negative() vector.negative()}.
	 *
	 * @param vector The vector whose components will be subtracted with.
	 * @see #add(IVector)
	 * @see IVector#negative()
	 */
	public void subtract(final IVector vector) {
		this.add(vector.negative());
	}

	@Override
	public boolean equals(final Object o) {
		if (o instanceof final BasicVector other) return this.getX() == other.getX() && this.getY() == other.getY();
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getX(), this.getY());
	}

	@Override
	public BasicVector clone() {
		try {
			return (BasicVector) super.clone();
		} catch (final CloneNotSupportedException e) {
			throw new AssertionError("Clone operation failed for Class: " + this.getClass().getName());
		}
	}
}
