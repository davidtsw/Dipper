package com.asdf.ta.workbook.fml;

import com.asdf.ta.workbook.Formula;
import com.asdf.ta.workbook.TimeScale;
import com.asdf.ta.workbook.WorkBook;
import com.asdf.ta.workbook.WorkSheetListener;
import com.asdf.ta.workbook.WorkSheetT;
import com.asdf.ta.workbook.impl.SheetWrapper;

public class BarSeries implements Formula, WorkSheetListener {
	final private WorkBook wb;
	final private String symbol;
	final private SheetWrapper own;
	BarSeries(WorkBook wb, String symbol, SheetWrapper own) {
		this.wb = wb;
		this.symbol = symbol;
		this.own = own;
	}
	@Override
	public void onReload() {
		own.onReload();
	}
	@Override
	public void onUpdate() {
		own.onUpdate();
	}
	@Override
	public void onDelete() {
		own.delete();
	}
	public void changeTimeScale(TimeScale tScale) {
		WorkSheetT newWS = wb.getTimeSeries(symbol, tScale);
		own.setWorkSheet(newWS);
	}
	static BarSeries getInstance(WorkBook wb, String symbol) {
		WorkSheetT dailyWS = wb.getTimeSeries(symbol, TimeScale.DAY_1);
		SheetWrapper ws = new SheetWrapper(dailyWS);
		BarSeries bs = new BarSeries(wb, symbol, ws);
		return bs;
	}
}