package com.github.sef24sp4.fpscounter;

import com.github.sef24sp4.common.graphics.label.Label;
import com.github.sef24sp4.common.graphics.label.LabelFactory;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.common.vector.BasicVector;

public class FPSCounter implements IGamePluginService, IEntityProcessingService {
	private static final EvictingQueue<Long> FPS_COUNTER = new EvictingQueue<>(100);
	private static Label label;

	@Override
	public void launch(final IEntityManager entityManager, final IGameSettings gameSettings) {

	}

	@Override
	public void gameStart(final IEntityManager entityManager, final IGameSettings gameSettings) {
		FPS_COUNTER.clear();
		label = LabelFactory.create(new BasicVector(0, 70), "FPS: n/A", 30);
	}

	@Override
	public void process(final IEntityManager entityManager, final IGameSettings gameSettings) {
		FPS_COUNTER.add(System.nanoTime());
		final long nanosPerFrame = (FPS_COUNTER.getNewest() - FPS_COUNTER.getOldest()) / FPS_COUNTER.size();
		final double fps = 1_000_000_000.0 / nanosPerFrame;
		label.setText(String.format("FPS: %.1f", fps));
	}

	@Override
	public void gameStop(final IEntityManager entityManager, final IGameSettings gameSettings) {

	}
}
