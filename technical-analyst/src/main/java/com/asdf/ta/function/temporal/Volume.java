package com.asdf.ta.function.temporal;

import com.asdf.ta.function.TemporalFn;
import com.asdf.ta.workbook.ColumnT;

public class Volume extends TemporalFn {

	public Volume() {
		super(ColumnT.VOLUME);
	}
	@Override
	public void reset() {
		super.reset();
		saveResult(0);
	}
	public void set(long time, double vol) {
		saveTime(time);
		saveResult(get() + vol);
	}
}