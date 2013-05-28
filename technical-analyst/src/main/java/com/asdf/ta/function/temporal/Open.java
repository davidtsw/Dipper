package com.asdf.ta.function.temporal;

import com.asdf.ta.function.FunctionT;
import com.asdf.ta.function.TemporalFn;
import com.asdf.ta.workbook.ColumnT;

public class Open extends TemporalFn {
	final FunctionT last;

	public Open(FunctionT last) {
		super(ColumnT.OPEN, last);
		this.last = last;
	}
	public void last() {
		if (Double.isNaN(get())) {
			saveTime(last.getTime());
			saveResult(last.get());
		}
	}
}