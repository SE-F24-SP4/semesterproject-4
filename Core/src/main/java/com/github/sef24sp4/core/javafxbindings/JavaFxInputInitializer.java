package com.github.sef24sp4.core.javafxbindings;

import com.github.sef24sp4.common.gamecontrol.IMouseCoordinates;
import com.github.sef24sp4.core.game.GameInput;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.function.BiConsumer;

/**
 * Helper class to initialize {@link GameInput} objects with attached {@link javafx.event.EventHandler JavaFX's event handlers}.
 * This uses the key mappings defined in {@link JavaFXInputMap}
 *
 * @see IMouseCoordinates
 */
public final class JavaFxInputInitializer {
	private final EventTarget target;
	private final IMouseCoordinates mouseCoordinates;
	private final GameInput gameInput;

	private JavaFxInputInitializer(EventTarget target) {
		this.target = target;
		this.mouseCoordinates = new JavaFxMouseCoordinates(this.target);
		this.gameInput = new GameInput(this.mouseCoordinates);
	}

	public static GameInput init(EventTarget target) {
		return new JavaFxInputInitializer(target).init();
	}

	private GameInput init() {
		this.setEvents(MouseEvent.MOUSE_CLICKED, MouseEvent.MOUSE_RELEASED, (event, state) -> {
			JavaFXInputMap.getFromMouseButton(event.getButton()).ifPresent(action -> this.gameInput.setKeyState(action, state));
		});

		this.setEvents(KeyEvent.KEY_PRESSED, KeyEvent.KEY_RELEASED, (event, state) -> {
			JavaFXInputMap.getFromKey(event.getCode()).ifPresent(action -> this.gameInput.setKeyState(action, state));
		});

		return this.gameInput;
	}

	private <E extends Event> void setEvents(EventType<? extends E> activate, EventType<? extends E> deactivate, BiConsumer<? super E, Boolean> consumer) {
		this.target.addEventHandler(activate, event -> consumer.accept(event, true));
		this.target.addEventHandler(deactivate, event -> consumer.accept(event, false));
	}
}
