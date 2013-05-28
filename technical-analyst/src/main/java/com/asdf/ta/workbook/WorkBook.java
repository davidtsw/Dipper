package com.asdf.ta.workbook;

public interface WorkBook {
	public WorkSheetT getTick(String symbol);
	public WorkSheetT getTimeSeries(String symbol, TimeScale scale);
}