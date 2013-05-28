package com.asdf.ta.function;

public abstract class TemporalFn extends AbstractFn implements FunctionT {
	static private long[] EMPTY = new long[0];
	private long time;

	public TemporalFn(String name) {
		super(name, new String[] { name }, new Function[] {});
	}
	public TemporalFn(String name, FunctionT input) {
		super(name, new String[] { name }, new Function[] { input });
	}
	public TemporalFn(String name, String[] output) {
		super(name, output, new Function[] {});
	}
	public void last() {
		// do nothing
	}
	public void next() {
		reset();
	}
	public void reset() {
		time = FunctionT.NO_VALUE;
		saveResult(Function.NO_VALUE);
	}
	public long getTime() {
		return time;
	}
	public long[] getAllTime() {
		return EMPTY;
	}
	protected void saveTime(long time) {
		this.time = time;
	}
}