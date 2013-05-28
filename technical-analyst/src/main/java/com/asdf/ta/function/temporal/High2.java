package com.asdf.ta.function.temporal;

import com.asdf.ta.function.FunctionT;
import com.asdf.ta.function.TemporalFn;
import com.asdf.ta.workbook.ColumnT;

public class High2 extends TemporalFn {
	final FunctionT high;
	double lastHigh = 0;

	public High2(FunctionT high, int lv) {
		super(ColumnT.HIGH + lv, high);
		this.high = high;
	}
	public void last() {
		if (Double.isNaN(get())) {
			saveTime(high.getTime());
			saveResult(high.get());
			lastHigh = high.get();
		} else if (high.get() > lastHigh) {
			saveTime(high.getTime());
			saveResult(lastHigh);
			lastHigh = high.get();
		}
	}
}