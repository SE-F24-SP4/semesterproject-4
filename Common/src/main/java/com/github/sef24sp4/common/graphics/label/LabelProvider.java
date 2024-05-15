package com.github.sef24sp4.common.graphics.label;

import com.github.sef24sp4.common.vector.IVector;

/**
 * This is an interface for instantiating a {@link Label}.
 * <p/>
 * This interface is not used directly. <br/>
 * Use {@link LabelFactory} instead.
 */
public interface LabelProvider {
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
	public boolean deregisterLabel(Label label);

	public Label create(final IVector coordinates, final String text);

	public Label create(final IVector coordinates);
}
