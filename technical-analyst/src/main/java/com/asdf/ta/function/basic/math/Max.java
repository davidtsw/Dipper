package com.asdf.ta.function.basic.math;

import com.asdf.common.util.FixQueue;
import com.asdf.ta.function.BasicFn;
import com.asdf.ta.function.Function;

public class Max extends BasicFn {
	final private Function in;
	// internal values
	final private FixQueue queue;

	public Max(Function in, int period) {
		super("max", in);
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
			double max = queue.get(0);
			for (int i = 1; i < queue.getCapacity(); i++) {
				double v = queue.get(i);
				if (v > max) {
					max = v;
				}
			}
			saveResult(max);
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