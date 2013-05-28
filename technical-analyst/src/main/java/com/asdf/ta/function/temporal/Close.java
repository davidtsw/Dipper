package com.asdf.ta.function.temporal;

import com.asdf.ta.function.TemporalFn;
import com.asdf.ta.workbook.ColumnT;

public class Close extends TemporalFn {

	public Close() {
		super(ColumnT.CLOSE);
	}
	public void last() {
		// do nothing
	}
	public void set(long time, double price) {
		saveTime(time);
		saveResult(price);
	}
}