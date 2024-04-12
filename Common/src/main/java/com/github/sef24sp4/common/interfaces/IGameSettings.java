package com.github.sef24sp4.common.interfaces;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.gamecontrol.IGameInput;

public interface IGameSettings {
	public IGameInput getKeys();

	public int getDisplayWidth();

	public int getDisplayHeight();

	public void setDisplayWidth(int displayWidth);

	public void setDisplayHeight(int displayHeight);


	public default boolean isEntityWithinFrame(IEntity entity) {
		final int displayStart = 0;
		return entity.getX() >= displayStart
				&& entity.getY() >= displayStart
				&& entity.getX() <= this.getDisplayWidth()
				&& entity.getY() <= this.getDisplayHeight();
	}
}

