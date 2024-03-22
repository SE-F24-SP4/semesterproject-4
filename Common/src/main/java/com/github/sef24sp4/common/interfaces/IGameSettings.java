package com.github.sef24sp4.common.interfaces;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.gamecontrol.IGameInput;

public interface IGameSettings {
	public IGameInput getKeys();

	public int getDisplayWidth();

	public int getDisplayHeight();

	public boolean isEntityWithinFrame(IEntity entity);
}
