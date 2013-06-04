package com.asdf.cm.pane.scale;

public interface YMapper {
	/**
	 * Maps a data to the y-position
	 * @param range the data range of the chart
	 * @param data a data to be mapped
	 * @return the y-position of the corresponding data
	 */
	public float mapValueToYPos(DataRange range, double data);
	/**
	 * Maps a y-position to the data
	 * @param range the data range of the chart
	 * @param yPos a y-position on plotting area
	 * @return data of the corresponding y-position
	 */
	public double mapYPosToValue(DataRange range, int yPos);
}