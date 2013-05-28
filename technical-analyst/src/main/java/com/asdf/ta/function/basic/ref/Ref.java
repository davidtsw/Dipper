package com.asdf.ta.function.basic.ref;

import com.asdf.common.util.FixQueue;
import com.asdf.ta.function.BasicFn;
import com.asdf.ta.function.Function;

public class Ref extends BasicFn {
	final private Function in;
	// internal states
	final private FixQueue queue;

	public Ref(Function in, int period) {
		super("ref", in);
		assert period > 0;
		this.in = in;
		queue = new FixQueue(period);
	}
	@Override
	protected void doLast() {
		queue.update(in.get());
	}
	@Override
	protected void doNext() {
		if (queue.isFull()) {
			saveResult(queue.add(in.get()));
		} else {
			queue.add(in.get());
		}
	}
	@Override
	protected void doReset() {
		queue.clear();
	}
}