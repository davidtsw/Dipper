package com.asdf.ta.workbook.impl;

import java.util.Map;
import com.asdf.ta.workbook.TimeScale;
import com.asdf.ta.workbook.WorkBook;
import com.asdf.ta.workbook.WorkSheetT;

public class SimpleWorkBook implements WorkBook {

	private Map<String, WorkSheetT> tickSheetMap;
	private Map<String, Map<TimeScale, SheetWrapper>> barSheetMap;

	@Override
	public WorkSheetT getTick(String symbol) {
		WorkSheetT tSheet = tickSheetMap.get(symbol);
		if (tSheet == null) {
			// XXX who determine if symbol supported?
		}
		return tSheet;
	}

	@Override
	public WorkSheetT getTimeSeries(String symbol, TimeScale tScale) {
		Map<TimeScale, SheetWrapper> bMap = barSheetMap.get(symbol);
		if (bMap == null) {
			// XXX reject with exception?
		}
		SheetWrapper bSheet = bMap.get(tScale);
		if (bSheet == null) {
			// XXX who determine if time-scale supported?
		}
		return null;
	}
}
