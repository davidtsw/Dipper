package com.asdf.cm.drawable;

import java.util.List;
import com.asdf.cm.pane.Chart;

public interface Drawable {
	//
	public Chart getChart();
	public List<Plotter> getPlotters();
	//
	public void delete();
}