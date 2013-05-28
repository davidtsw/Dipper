package com.asdf.ta.workbook.fml;

import com.asdf.ta.function.Function;
import com.asdf.ta.workbook.Formula;
import com.asdf.ta.workbook.WorkSheet;
import com.asdf.ta.workbook.WorkSheetListener;
import com.asdf.ta.workbook.impl.DataSheet;

public class Indicator implements Formula, WorkSheetListener {
	final private DataSheet own;
	final private Function fn;
	final private WorkSheet src;
	final private InputCol in;
	private long readPos;

	Indicator(DataSheet own, Function fn, WorkSheet src, InputCol in) {
		this.own = own;
		this.fn = fn;
		this.src = src;
		this.in = in;
		readPos = -1;
	}
	@Override
	public void onDelete() {
		src.dropListener(this);
	}
	@Override
	public void onReload() {
		readPos = -1;
		own.onReload();
	}
	@Override
	public void onUpdate() {
		boolean isDataOutOfSync = readPos < src.getStartRowNum();
		if (isDataOutOfSync) {
			clearWorkSheet();
		}
		updateWorkSheet();
	}
	private void clearWorkSheet() {
		fn.reset();
		own.clear(src.getStartRowNum());
		readPos = src.getStartRowNum();
		moveToNextRow();
	}
	private void updateWorkSheet() {
		updateLastRow();
		while (readPos < src.getEndRowNum()) {
			readPos++;
			moveToNextRow();
			updateLastRow();
		}
		own.onUpdate();
	}
	private void updateLastRow() {
		// 1. read from source
		in.update(readPos);
		// 2. do calculation
		fn.last();
		// 3. save the results
		own.save(fn.getAll());
	}
	private void moveToNextRow() {
		fn.next();
		own.nextRow();
	}
}