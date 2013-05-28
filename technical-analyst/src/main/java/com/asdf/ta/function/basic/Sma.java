package com.asdf.ta.function.basic;

import com.asdf.common.util.FixQueue;
import com.asdf.ta.function.BasicFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.annotation.IndicatorFn;
import com.asdf.ta.function.annotation.IndicatorInput;
import com.asdf.ta.function.annotation.IndicatorParam;

public class Sma extends BasicFn {
	static final private String ABBR = "sma";
	//
	final private Function in;
	final private int period;
	// internal states
	final private FixQueue queue;
	private double sum;

	@IndicatorFn(name = ABBR)
	public Sma(
			@IndicatorInput Function in,
			@IndicatorParam(name = "period", init = 14) int period) {
		super(ABBR, in);
		this.in = in;
		this.period = period;
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
			double avg = sum / period;
			saveResult(avg);
		} else {
			saveResult(NO_VALUE);
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