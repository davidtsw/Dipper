package com.asdf.cm.pane.scale.impl;

import com.asdf.cm.pane.scale.DataRange;
import com.asdf.cm.pane.scale.YMapper;

public class LinearYMapper implements YMapper {
	@Override
	public float mapValueToYPos(DataRange range, double val) {
		double heightRatio = (range.getMax() - val) / (range.getMax() - range.getMin());
		double pos = range.getTop() + range.getHeight() * heightRatio;
		return (float) pos;
	}
	@Override
	public double mapYPosToValue(DataRange range, int yPos) {
		double heightRatio = 1 - ((yPos - range.getTop()) / (double) range.getHeight());
		double val = range.getMin() + heightRatio * (range.getMax() - range.getMin());
		return val;
	}
}