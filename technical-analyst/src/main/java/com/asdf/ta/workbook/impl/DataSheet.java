package com.asdf.ta.workbook.impl;

import java.util.List;
import com.asdf.common.util.RingIndex;
import com.asdf.ta.function.Function;
import com.asdf.ta.workbook.Column;
import com.asdf.ta.workbook.Formula;
import com.asdf.ta.workbook.WorkSheet;

public class DataSheet extends ObservableSheet implements WorkSheet {
	static protected int _WorkSheetCapacity = 1000;
	static public void setDefaultCapacity(int capacity) {
		_WorkSheetCapacity = capacity;
	}

	final private String name, desc;
	final private int capacity;
	final private ColumnGroup<DataColumn> dSheet;
	protected long sInd, eInd;
	private Formula fml = NULL_FML;

	public DataSheet(String name, String desc, List<String> colNames) {
		this.name = name;
		this.desc = desc;
		this.capacity = _WorkSheetCapacity;
		dSheet = new ColumnGroup<DataColumn>(capacity, colNames) {
			@Override
			protected DataColumn createColumn(String colName, int capacity) {
				return new DataColumn(colName, capacity);
			}
		};
		sInd = 1;
		eInd = 0;
	}
	public void setFml(Formula fml) {
		this.fml = fml;
	}
	public void save(double[] val) {
		assert val.length <= dSheet.getColumnNum();
		for (int i = 0; i < val.length; i++) {
			dSheet.getColumn(i).set(eInd, val[i]);
		}
	}
	public void nextRow() {
		eInd++;
		// TODO check if sInd has to move as well
		if (sInd + capacity <= eInd) {
			sInd = eInd - capacity + 1;
		}
		for (int i = 0; i < dSheet.getColumnNum(); i++) {
			dSheet.getColumn(i).clear(eInd);
		}
	}

	public void clear() {
		// move the range forwards
		clear(eInd + 2);
	}
	public void clear(long newStartIndex) {
		sInd = newStartIndex;
		eInd = sInd - 1;
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getDesc() {
		return desc;
	}
	@Override
	public long getStartRowNum() {
		return sInd;
	}
	@Override
	public long getEndRowNum() {
		return eInd;
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
	public boolean isEmpty() {
		return eInd < sInd;
	}
	@Override
	public void delete() {
		fml.onDelete();
		this.onDelete();
	}

	static public class DataColumn implements Column {
		final private RingIndex index;
		final private double[] dataArr;

		private DataColumn(String name, int capacity) {
			index = new RingIndex(name, capacity);
			dataArr = new double[index.size()];
			// reset();
		}
		public String getName() {
			return index.getName();
		}
		public double getValue(long seq) {
			return dataArr[index.loc(seq)];
		}
		void set(long seq, double data) {
			dataArr[index.loc(seq)] = data;
		}
		void clear(long seq) {
			set(seq, Function.NO_VALUE);
		}
	}

	static final protected Formula NULL_FML = new Formula() {
		public void onDelete() {
			// do nothing
		}
	};
}