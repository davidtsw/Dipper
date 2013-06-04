package com.asdf.cm.pane.scale.impl;

import com.asdf.cm.pane.scale.DisplayWindow;
import com.asdf.cm.pane.scale.XMapper;

public class LinearXMapper implements XMapper {
	@Override
	public float mapIndexToXPos(DisplayWindow window, int index) {
		float xPos = (index - window.getStart()) / window.getSize() * window.getWidth() + window.getLeft();
		return xPos;
	}
	@Override
	public float mapXPosToIndex(DisplayWindow window, float xPos) {
		float index = (xPos - window.getLeft()) / window.getWidth() * window.getSize() + window.getStart();
		return index;
	}
}