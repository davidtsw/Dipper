package com.asdf.ta.function.basic.math;

import com.asdf.common.util.FixQueue;
import com.asdf.ta.function.BasicFn;
import com.asdf.ta.function.Function;

public class Min extends BasicFn {
	final private Function in;
	// internal values
	final private FixQueue queue;

	public Min(Function in, int period) {
		super("min", in);
		assert period > 0;
		this.in = in;
		queue = new FixQueue(period);
		//
		dependOn(in);
	}
	@Override
	public void doLast() {
		double val = in.get();
		queue.update(val);
		// XXX optimize it?
		if (queue.isFull()) {
			double min = queue.get(0);
			for (int i = 1; i < queue.getCapacity(); i++) {
				double v = queue.get(i);
				if (v < min) {
					min = v;
				}
			}
			saveResult(min);
		}
	}
	@Override
	public void doNext() {
		queue.add(0d);
	}
	@Override
	public void doReset() {
		queue.clear();
	}
}