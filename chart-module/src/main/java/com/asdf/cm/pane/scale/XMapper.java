package com.asdf.cm.pane.scale;

public interface XMapper {
	/**
	 * Maps a data sequence index to the x-position
	 * @param window the display window of the data sequence
	 * @param index the index of the data sequence
	 * @return the x-position of the corresponding index
	 */
	public float mapIndexToXPos(DisplayWindow window, int index);
	/**
	 * Maps a x-position to the data sequence index
	 * @param window the display window of the data sequence
	 * @param xPos x-position on plotting area
	 * @return sequence index of the corresponding x-position
	 */
	public float mapXPosToIndex(DisplayWindow window, float xPos);
}