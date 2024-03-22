package com.github.sef24sp4.common.services;

import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;

@FunctionalInterface
public interface IEntityProcessingService {
	public void process(IEntityManager entityManager, IGameSettings gameSettings);
}
