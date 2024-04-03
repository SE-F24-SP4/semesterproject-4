package com.github.sef24sp4.common.interfaces;

public interface IVector {

	/**
	 * Get value of x.
	 *
	 * @return Value of x.
	 */
	public double getX();

	/**
	 * Get value of y.
	 *
	 * @return Value of y.
	 */
	public double getY();

	/**
	 * Calculate the norm e.i. the length of the current vector.
	 *
	 * @return The norm.
	 */
	public default double getNorm() {
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
	}

	/**
	 * Calculate the dot product between current vector and the passed in vector.
	 *
	 * @param otherVector The vector to calculate dot product with.
	 * @return The resulting dot product.
	 */
	public default double getDotProduct(IVector otherVector) {
		return this.getX() * otherVector.getX() + this.getY() * otherVector.getY();
	}


	public default double getDeterminant(IVector otherVector) {
		return this.getX() * otherVector.getY() - otherVector.getX() * this.getY();
	}

	/**
	 * Calculates the angle between the current vector and the passed in vector.
	 *
	 * @param otherVector The vector to get the angle in between with.
	 * @return The angle in radians.
	 * @see <a href="https://stackoverflow.com/a/16544330">Stackoverflow: Direct way of computing the clockwise angle between two vectors</a>
	 */
	public default double getAngleBetween(IVector otherVector) {
		return Math.atan2(otherVector.getDeterminant(this), otherVector.getDotProduct(this));
	}
}
