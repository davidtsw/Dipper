package com.asdf.cm.pane;

import java.util.List;
import com.asdf.cm.drawable.Drawable;

public interface Chart {
	// prop
	public String getName();
	public String getDesc();
	public List<Drawable> getDrawables();

	//
	public void onBoundUpdated(int[][] bounds);

	// mapping of data[index] <-> (x,y)
	public float mapIndexToXPos(int index);
	public int mapXPosToIndex(float xPos);
	public float mapDataToYPos(double data);
	public double mapYPosToValue(float yPos);

	// axis
	public XAxis getXAxis();
	public YAxis getYAxis();
	public long getTime(int index);

}