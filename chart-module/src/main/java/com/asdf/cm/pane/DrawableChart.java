package com.asdf.cm.pane;

import com.asdf.cm.drawable.DataSequence;
import com.asdf.cm.drawable.HandDrawing;

public interface DrawableChart {
	public void addDataSequence(DataSequence sequence);
	public void dropAllDataSequence();
	public void addHandDrawing(HandDrawing drawing);
	public void dropAllHandDrawing();
	// public List<Drawable> getSelectedDrawable();
}