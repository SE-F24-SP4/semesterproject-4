package com.github.sef24sp4.common.vector;

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


	public default double getDeterminant(final IVector otherVector) {
		return this.getX() * otherVector.getY() - otherVector.getX() * this.getY();
	}

	/**
	 * Calculates the angle between the current vector and the passed in vector.
	 *
	 * @param otherVector The vector to get the angle in between with.
	 * @return The angle in radians.
	 * @see <a href="https://stackoverflow.com/a/16544330">Stackoverflow: Direct way of computing the clockwise angle between two vectors</a>
	 */
	public default double getAngleBetween(final IVector otherVector) {
		return Math.atan2(otherVector.getDeterminant(this), otherVector.getDotProduct(this));
	}


	/**
	 * Get a normalized version of the current vector.
	 * Not that if the current vector is a zero vector, then a new zero vector is returned.
	 * This is done instead of throwing an error or returning null.
	 *
	 * @return A normalized vector of the current vector. <br/>
	 * Unless current vector is a zero vector, then a new zero vector is returned.
	 * @see <a href="https://stackoverflow.com/a/722087">Stackoverflow: How do you normalize a zero vector</a>
	 */
	public default BasicVector getNormalizedVector() {
		final double norm = this.getNorm();
		if (norm == 0) return new BasicVector();
		return new BasicVector(this.getX() / norm, this.getY() / norm);
	}

	/**
	 * Get a new {@link IVector} whose components are negative value of the original vector. <br/>
	 * E.g. {@code newVector.getX() == -oldVector.getX()}
	 * and {@code newVector.getY() == -oldVector.getY()}.
	 *
	 * @return The negative vector.
	 */
	public default BasicVector getNegative() {
		return new BasicVector(-this.getX(), -this.getY());
	}

	/**
	 * Get a vector between current otherVector and the passed in otherVector.
	 *
	 * @param otherVector The other otherVector. Maybe any type of vector.
	 * @return A vector between the two otherVector.
	 */
	public default BasicVector getVectorTo(final IVector otherVector) {
		final double deltaX = otherVector.getX() - this.getX();
		final double deltaY = otherVector.getY() - this.getY();
		return new BasicVector(deltaX, deltaY);
	}


	/**
	 * Creates a new {@link BasicVector vector} which is perpendicular on the current vector.
	 *
	 * @return A {@link BasicVector vector} which is perpendicular to the current vector.
	 */
	public default BasicVector getOrthogonalVector() {
		return new BasicVector(-this.getY(), this.getX());
	}

	/**
	 * Creates a new {@link BasicVector vector} with {@code angle} to the current vector.
	 * <p/>
	 * That means calling {@link #getAngleBetween(IVector) newVector.getAngleBetween(currentVector)} is equal to {@code angle}.
	 *
	 * @param angle The angle in radians.
	 * @return A new {@link BasicVector vector} which has {@code angle} to the current vector.
	 * @see #getAngleBetween(IVector)
	 */
	public default BasicVector getNewVectorWithAngle(final double angle) {
		final BasicVector scaledVector = BasicVector.valuesOf(this);
		scaledVector.scale(Math.cos(angle));
		final BasicVector orthogonalVector = this.getOrthogonalVector();
		orthogonalVector.scale(Math.sin(angle));
		scaledVector.add(orthogonalVector);
		return scaledVector;
	}
}
