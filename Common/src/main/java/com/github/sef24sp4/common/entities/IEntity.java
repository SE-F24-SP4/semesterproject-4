package com.github.sef24sp4.common.entities;

import com.github.sef24sp4.common.metadata.IGameElement;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;

import java.util.ArrayList;
import java.util.Collection;

public interface IEntity extends IGameElement {

	public IVector[] getPolygonCoordinates();

	public default double[] getPolygonValuesArray() {
		final Collection<Double> output = new ArrayList<>(this.getPolygonCoordinates().length * 2);
		for (final IVector coordinates : this.getPolygonCoordinates()) {
			output.add(coordinates.getX());
			output.add(coordinates.getY());
		}
		// See: https://stackoverflow.com/a/23945015
		return output.stream().mapToDouble(Double::doubleValue).toArray();
	}

	public double getRotation();

	public Coordinates getCoordinates();

	public default double getX() {
		return this.getCoordinates().getX();
	}

	public default double getY() {
		return this.getCoordinates().getY();
	}
}
