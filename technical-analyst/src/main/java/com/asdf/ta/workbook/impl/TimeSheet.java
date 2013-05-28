package com.asdf.ta.workbook.impl;

import java.util.List;
import com.asdf.common.util.RingIndex;
import com.asdf.ta.function.FunctionT;
import com.asdf.ta.workbook.ColumnT;
import com.asdf.ta.workbook.WorkSheetT;

public class TimeSheet extends DataSheet implements WorkSheetT {
	final private ColumnGroup<TimeColumn> tSheet;

	public TimeSheet(String name, String desc, List<String> dColNames, List<String> tColNames) {
		super(name, desc, dColNames);
		int capacity = _WorkSheetCapacity;
		tSheet = new ColumnGroup<TimeColumn>(capacity, tColNames) {
			@Override
			protected TimeColumn createColumn(String colName, int capacity) {
				return new TimeColumn(colName, capacity);
			}
		};
	}
	public ColumnT getTemporalColumn() {
		return tSheet.getColumn();
	}
	public ColumnT getTemporalColumn(String colName) {
		return tSheet.getColumn(colName);
	}
	public void save(long[] ts, double[] val) {
		assert ts.length <= tSheet.getColumnNum();
		super.save(val);
		for (int i = 0; i < ts.length; i++) {
			tSheet.getColumn(i).set(eInd, ts[i]);
		}
	}
	public void nextRow() {
		super.nextRow();
		for (int i = 0; i < tSheet.getColumnNum(); i++) {
			tSheet.getColumn(i).clear(eInd);
		}
	}

	static public class TimeColumn implements ColumnT {
		final private RingIndex index;
		final private long[] timeArr;

		private TimeColumn(String name, int capacity) {
			index = new RingIndex(name, capacity);
			timeArr = new long[index.size()];
			// reset();
		}
		public String getName() {
			return index.getName();
		}
		public long getValue(long seq) {
			return timeArr[index.loc(seq)];
		}
		void set(long seq, long time) {
			timeArr[index.loc(seq)] = time;
		}
		void clear(long seq) {
			set(seq, FunctionT.NO_VALUE);
		}
	}

}