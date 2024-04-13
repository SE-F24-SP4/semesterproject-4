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
