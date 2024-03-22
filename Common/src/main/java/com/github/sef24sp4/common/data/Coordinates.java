package com.github.sef24sp4.common.data;

public class Coordinates {
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

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}


	public double getRelativeRotationTo(Coordinates otherCoordinates) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
