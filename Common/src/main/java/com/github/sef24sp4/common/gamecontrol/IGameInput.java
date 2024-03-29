package com.github.sef24sp4.common.gamecontrol;

public interface IGameInput {

	public boolean isDown(InputAction key);

	public boolean isPressed(InputAction key);

	public IMouseCoordinates getMouseCoordinates();
}
