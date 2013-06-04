package com.asdf.cm.pane.scale;

/**
 * DisplayWindow represents the visible window of data sequences in the chart.
 */
public interface DisplayWindow {
	/**
	 * Returns the start index
	 * @return the start index
	 */
	public float getStart();
	/**
	 * Returns number of index within the window
	 * @return the size
	 */
	public float getSize();
	/**
	 * Returns the left-x-position of the window
	 * @return the left-x-position of the window
	 */
	public float getLeft();
	/**
	 * Returns the x-width of the window
	 * @return the x-width of the window
	 */
	public float getWidth();
}