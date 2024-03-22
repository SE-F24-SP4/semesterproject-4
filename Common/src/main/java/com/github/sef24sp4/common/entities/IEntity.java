package com.github.sef24sp4.common.entities;

import com.github.sef24sp4.common.data.Coordinates;

import java.util.List;

public interface IEntity {

	public List<Coordinates> getPolygonCoordinates();

	public double getRotation();

	public Coordinates getCoordinates();

	public default double getX() {
		return this.getCoordinates().getX();
	}

	public default double getY() {
		return this.getCoordinates().getY();
	}
}
