package com.asdf.ta.workbook.impl;

import com.asdf.ta.workbook.Column;
import com.asdf.ta.workbook.WorkSheet;
import com.asdf.ta.workbook.impl.ObservableSheet;

public class DummyWS extends ObservableSheet implements WorkSheet {
	private long rowNo = 0;
	@Override
	public String getName() {
		return "dummy";
	}
	@Override
	public String getDesc() {
		return "dummy";
	}
	@Override
	public long getStartRowNum() {
		return rowNo;
	}
	@Override
	public long getEndRowNum() {
		return rowNo;
	}
	@Override
	public Column getColumn() {
		return null;
	}
	@Override
	public Column getColumn(String colName) {
		return null;
	}
	@Override
	public void delete() {
		// do nothing
	}
	@Override
	public boolean isEmpty() {
		return false;
	}
}
