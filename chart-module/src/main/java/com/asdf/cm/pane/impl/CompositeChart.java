package com.asdf.cm.pane.impl;

import java.util.List;
import com.asdf.cm.pane.Chart;
import com.asdf.cm.pane.DrawableChart;
import com.asdf.cm.pane.NavigableChart;
import com.asdf.cm.pane.scale.DataRange;
import com.asdf.cm.pane.scale.DisplayWindow;
import com.asdf.cm.pane.scale.XMapper;
import com.asdf.cm.pane.scale.YMapper;

public abstract class CompositeChart implements Chart, DrawableChart, NavigableChart {
	private CompositeChart parent;
	private List<CompositeChart> child;
	private DisplayWindow dw;
	private DataRange dr;
	private XMapper xm;
	private YMapper ym;

}