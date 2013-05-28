package com.asdf.ta.function.basic.math;

import com.asdf.common.util.FixQueue;
import com.asdf.ta.function.BasicFn;
import com.asdf.ta.function.Function;

public class Sum extends BasicFn {
	final private Function in;
	// internal values
	final private FixQueue queue;
	private double sum;

	public Sum(Function in, int period) {
		super("sum", in);
		this.in = in;
		queue = new FixQueue(period);
		//
		dependOn(in);
	}
	@Override
	public void doLast() {
		double val = in.get();
		double last = queue.update(val);
		sum += val - last;
		if (queue.isFull()) {
			saveResult(sum);
		}
	}
	@Override
	public void doNext() {
		double old = queue.add(0d);
		sum -= old;
	}
	@Override
	public void doReset() {
		queue.clear();
		sum = 0;
	}
}