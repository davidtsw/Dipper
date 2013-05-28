package com.asdf.ta.function;

public interface FunctionT extends Function {
	public final long NO_VALUE = 0;
	/**
	 * Returns the time of the corresponding result
	 * @return the time
	 */
	public long getTime();
	/**
	 * Returns all the time of results
	 * @return the time list
	 */
	public long[] getAllTime();
}
