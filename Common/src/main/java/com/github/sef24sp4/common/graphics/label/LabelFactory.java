package com.github.sef24sp4.common.graphics.label;

import com.github.sef24sp4.common.vector.IVector;

import java.util.ServiceLoader;

/**
 * This provides static methods for creating {@link Label labels} using a default {@link LabelProvider}.
 * <p/>
 * Creation fails with a {@link ClassNotFoundException} wrapped in a {@link RuntimeException},
 * if no suitable {@link LabelProvider} is found.
 */
public final class LabelFactory {
	private static LabelProvider defaultProvider;

	private LabelFactory() {
	}

	public static Label create(final IVector coordinates, final String text, final double fontSize) {
		final Label label = fetchProvider().create(coordinates, text);
		label.setFontSize(fontSize);
		return label;
	}

	public static Label create(final IVector coordinates, final String text) {
		return fetchProvider().create(coordinates, text);
	}

	public static Label create(final IVector coordinates) {
		return fetchProvider().create(coordinates);
	}

	/**
	 * Removes the provided {@link Label label} from the graphics.
	 * <p/>
	 * The label cannot be registered again.
	 * <p/>
	 * Use {@link LabelFactory#create(IVector, String)} to create a new label.
	 *
	 * @param label The {@link Label} to remove.
	 * @return {@code true} if successfully removed from graphics.
	 * {@code false} otherwise.
	 */
	public static boolean deregisterLabel(Label label) {
		return fetchProvider().deregisterLabel(label);
	}

	private static LabelProvider fetchProvider() {
		if (defaultProvider == null) {
			defaultProvider = ServiceLoader.load(LabelProvider.class).findFirst().orElseThrow(() -> new RuntimeException(new ClassNotFoundException("Failed to find a LabelProvider")));
		}
		return defaultProvider;
	}
}
