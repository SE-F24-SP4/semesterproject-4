package com.github.sef24sp4.common.graphics.label;

import com.github.sef24sp4.common.vector.IVector;

/**
 * A text label to display.
 * <p/>
 * Instances are created through the {@link LabelFactory} methods.
 */
public interface Label {
	public String getText();

	public void setText(final String text);

	public double getFontSize();

	public void setFontSize(final double size);

	public IVector getCoordinates();

	public void setCoordinates(final IVector coordinates);
}
