package com.asdf.ta.function;

import com.asdf.ta.function.temporal.Close;
import com.asdf.ta.function.temporal.High;
import com.asdf.ta.function.temporal.High2;
import com.asdf.ta.function.temporal.Low;
import com.asdf.ta.function.temporal.Low2;
import com.asdf.ta.function.temporal.Open;

public class TimeSeriesFn extends TemporalFn {
	private Close cFn;
	private Function[] dFnList;
	private FunctionT[] tFnList;
	private long[] tsList;

	TimeSeriesFn(Close cFn, FunctionT[] tFn, Function[] dFn, String[] ns) {
		super("ts", ns);
		this.cFn = cFn;
		this.dFnList = dFn;
		this.tFnList = tFn;
		this.tsList = new long[tFn.length + 1];
	}
	public void last(long time, double price) {
		cFn.set(time, price);
		for (int i = 0; i < dFnList.length; i++) {
			dFnList[i].last();
		}
		saveResult();
	}
	public void next(long time) {
		saveTime(time);
		super.next();
	}
	@Override
	public void reset() {
		saveTime(0);
		for (int i = 0; i < dFnList.length; i++) {
			dFnList[i].reset();
		}
		saveResult();
	}
	private void saveResult() {
		for (int i = 0; i < dFnList.length; i++) {
			saveResult(i, dFnList[i].get());
		}
		tsList[0] = this.getTime();
		for (int i = 0; i < dFnList.length; i++) {
			tsList[i + 1] = tFnList[i].getTime();
		}

	}
	@Override
	public long[] getAllTime() {
		return tsList;
	}
	//
	static public TimeSeriesFn getInstance() {
		Close c = new Close();
		FunctionT[] fn = prepareFnObject(c);
		String[] ns = extractFnNames(fn);
		return new TimeSeriesFn(c, fn, fn, ns);
	}
	static private String[] extractFnNames(Function[] fn) {
		String[] colNames = new String[fn.length];
		for (int i = 0; i < fn.length; i++) {
			colNames[i] = fn[i].getName();
		}
		return colNames;
	}
	static private FunctionT[] prepareFnObject(Close c) {
		Open o = new Open(c);
		High h = new High(c);
		High2 h2 = new High2(h, 2);
		High2 h3 = new High2(h2, 3);
		Low l = new Low(c);
		Low2 l2 = new Low2(l, 2);
		Low2 l3 = new Low2(l2, 3);
		// TODO +volume, sma, etc
		return new FunctionT[] { o, h, l, c, h2, h3, l2, l3 };
	}
}