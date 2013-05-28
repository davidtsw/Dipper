package com.asdf.ta.function.temporal;

import com.asdf.ta.function.FunctionT;
import com.asdf.ta.function.TemporalFn;
import com.asdf.ta.workbook.ColumnT;

public class High extends TemporalFn {
	final FunctionT last;

	public High(FunctionT last) {
		super(ColumnT.HIGH, last);
		this.last = last;
	}
	public void last() {
		if (Double.isNaN(get())
				|| last.get() > get()) {
			saveTime(last.getTime());
			saveResult(last.get());
		}
	}
}