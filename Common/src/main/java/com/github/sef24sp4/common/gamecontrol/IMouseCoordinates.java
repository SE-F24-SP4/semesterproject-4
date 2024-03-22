package com.github.sef24sp4.common.gamecontrol;

import com.github.sef24sp4.common.data.Coordinates;

public interface IMouseCoordinates {
	public Coordinates getCoordinates();

	public default double getX() {
		return this.getCoordinates().getX();
	}

	public default double getY() {
		return this.getCoordinates().getY();
	}

}
