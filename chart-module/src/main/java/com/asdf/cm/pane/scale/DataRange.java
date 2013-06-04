package com.asdf.cm.pane.scale;

/**
 * DataRange represents the data range of the chart.
 */
public interface DataRange {
	/**
	 * Returns the maximum value of the range
	 * @return the maximum value of the range
	 */
	public double getMax();
	/**
	 * Returns the minimum value of the range
	 * @return the minimum value of the range
	 */
	public double getMin();
	/**
	 * Returns the top-y-position of the range
	 * @return the top-y-position of the range
	 */
	public float getTop();
	/**
	 * Returns the y-height of the range
	 * @return the y-height of the range
	 */
	public float getHeight();
}