package com.github.sef24sp4.common.entities;

import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;

import java.util.Collection;

public abstract class CommonEntity implements IEntity {
	private Coordinates coordinates;

	private IVector[] polygonCoordinates;

	private double rotation;

	public CommonEntity() {
		this.coordinates = new Coordinates();
	}

	public void setX(final double x) {
		this.getCoordinates().setX(x);
	}

	public void setY(final double y) {
		this.getCoordinates().setY(y);
	}

	@Override
	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(final Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public void setCoordinates(final IVector coordinates) {
		this.setCoordinates(Coordinates.valuesOf(coordinates));
	}

	@Override
	public IVector[] getPolygonCoordinates() {
		return this.polygonCoordinates;
	}

	public void setPolygonCoordinates(final Collection<? extends IVector> polygonCoordinates) {
		this.setPolygonCoordinates(polygonCoordinates.toArray(new IVector[0]));
	}

	public final void setPolygonCoordinates(final IVector... polygonCoordinates) {
		this.polygonCoordinates = polygonCoordinates;
	}

	@Override
	public double getRotation() {
		return this.rotation;
	}

	public void setRotation(final double rotation) {
		this.rotation = rotation;
	}
}
