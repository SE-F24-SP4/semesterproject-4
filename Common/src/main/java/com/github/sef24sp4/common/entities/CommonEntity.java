package com.github.sef24sp4.common.entities;

import com.github.sef24sp4.common.vector.Coordinates;

import java.util.List;

public abstract class CommonEntity implements IEntity {
	private Coordinates coordinates;

	private Coordinates[] polygonCoordinates;

	private double rotation;

	public CommonEntity() {
		this.coordinates = new Coordinates();
	}

	public void setX(double x) {
		this.getCoordinates().setX(x);
	}

	public void setY(double y) {
		this.getCoordinates().setY(y);
	}

	@Override
	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public Coordinates[] getPolygonCoordinates() {
		return this.polygonCoordinates;
	}

	public void setPolygonCoordinates(List<Coordinates> polygonCoordinates) {
		this.polygonCoordinates = polygonCoordinates.toArray(new Coordinates[0]);
	}

	public void setPolygonCoordinates(Coordinates... polygonCoordinates) {
		this.polygonCoordinates = polygonCoordinates;
	}

	@Override
	public double getRotation() {
		return this.rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
}
