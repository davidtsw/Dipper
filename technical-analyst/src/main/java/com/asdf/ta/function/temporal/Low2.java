package com.asdf.ta.function.temporal;

import com.asdf.ta.function.FunctionT;
import com.asdf.ta.function.TemporalFn;
import com.asdf.ta.workbook.ColumnT;

public class Low2 extends TemporalFn {
	final FunctionT low;
	double lastLow = 0;

	public Low2(FunctionT low, int lv) {
		super(ColumnT.LOW + lv, low);
		this.low = low;
	}
	public void last() {
		if (Double.isNaN(get())) {
			saveTime(low.getTime());
			saveResult(low.get());
			lastLow = low.get();
		} else if (low.get() < lastLow) {
			saveTime(low.getTime());
			saveResult(lastLow);
			lastLow = low.get();
		}
	}
}