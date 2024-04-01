package com.github.sef24sp4.common.entities;

import com.github.sef24sp4.common.data.Coordinates;

import java.util.Arrays;
import java.util.List;

public class CommonEntity implements IEntity {
	private Coordinates coordinates;

	private List<Coordinates> polygonCoordinates;

	private double rotation;

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
	public List<Coordinates> getPolygonCoordinates() {
		return this.polygonCoordinates;
	}

	public void setPolygonCoordinates(List<Coordinates> polygonCoordinates) {
		this.polygonCoordinates = polygonCoordinates;
	}

	public void setPolygonCoordinates(Coordinates... polygonCoordinates) {
		this.setPolygonCoordinates(Arrays.asList(polygonCoordinates));
	}

	@Override
	public double getRotation() {
		return this.rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
}
