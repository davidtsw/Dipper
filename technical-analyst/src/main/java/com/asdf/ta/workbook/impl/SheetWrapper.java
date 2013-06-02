package com.asdf.ta.workbook.impl;

import com.asdf.ta.function.TimeSeriesFn;
import com.asdf.ta.workbook.Column;
import com.asdf.ta.workbook.ColumnT;
import com.asdf.ta.workbook.WorkSheetListener;
import com.asdf.ta.workbook.WorkSheetT;

public class SheetWrapper extends ObservableSheet implements WorkSheetT, WorkSheetListener {
	static TimeSeriesFn FN = TimeSeriesFn.getInstance();
	private WorkSheetT ws;
	final private ColumnTWrapper tCol;
	final private ColumnGroup<ColumnWrapper> dSheet;
	public SheetWrapper(WorkSheetT ws) {
		tCol = new ColumnTWrapper();
		dSheet = new ColumnGroup<ColumnWrapper>(FN.getOutputs(), 0) {
			@Override
			protected ColumnWrapper createColumn(String colName, int capacity) {
				return new ColumnWrapper();
			}
		};
		setWorkSheet(ws);
	}
	public void setWorkSheet(WorkSheetT ws) {
		onReload();
		this.ws.dropListener(this);
		tCol.target = ws.getTemporalColumn();
		for (String col : FN.getOutputs()) {
			dSheet.getColumn(col).target = ws.getColumn(col);
		}
		this.ws = ws;
		this.ws.addListener(this);
	}
	@Override
	public String getName() {
		return ws.getName();
	}
	@Override
	public String getDesc() {
		return ws.getDesc();
	}
	@Override
	public long getStartRowNum() {
		return ws.getStartRowNum();
	}
	@Override
	public long getEndRowNum() {
		return ws.getEndRowNum();
	}
	@Override
	public long getSize() {
		return ws.getSize();
	}
	@Override
	public Column getColumn() {
		return dSheet.getColumn();
	}
	@Override
	public Column getColumn(String colName) {
		return dSheet.getColumn(colName);
	}
	@Override
	public ColumnT getTemporalColumn() {
		return tCol;
	}
	@Override
	public ColumnT getTemporalColumn(String colName) {
		return tCol;
	}
	@Override
	public void delete() {
		ws.dropListener(this);
	}
	@Override
	public void onDelete() {
		// ignore it
	}
	@Override
	public boolean isEmpty() {
		return ws.isEmpty();
	}

	static private class ColumnWrapper implements Column {
		private Column target;
		@Override
		public String getName() {
			return target.getName();
		}
		@Override
		public double getValue(long row) {
			return target.getValue(row);
		}
	}

	static private class ColumnTWrapper implements ColumnT {
		private ColumnT target;
		@Override
		public String getName() {
			return target.getName();
		}
		@Override
		public long getValue(long row) {
			return target.getValue(row);
		}
	}
}