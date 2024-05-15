package com.github.sef24sp4.core.javafxbindings;

import com.github.sef24sp4.core.interfaces.IGameTick;
import com.github.sef24sp4.core.interfaces.IGameTickExecutor;
import javafx.animation.AnimationTimer;

/**
 * Tick Executor based on {@link javafx.animation.AnimationTimer JavaFX's AnimationTimer}.
 */
public class AnimationTimerTickExecutor implements IGameTickExecutor {
	private AnimationTimer animationTimer;

	@Override
	public void stop() {
		this.animationTimer.stop();
	}

	@Override
	public void setGameTick(final IGameTick gameTick) {
		this.animationTimer = new AnimationTimer() {
			@Override
			public void handle(final long now) {
				gameTick.tick(now);
			}
		};
	}

	@Override
	public void start() {
		this.animationTimer.start();
	}

}
