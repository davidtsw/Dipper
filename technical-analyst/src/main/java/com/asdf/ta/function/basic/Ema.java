package com.asdf.ta.function.basic;

import com.asdf.ta.function.BasicFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.annotation.IndicatorFn;
import com.asdf.ta.function.annotation.IndicatorInput;
import com.asdf.ta.function.annotation.IndicatorParam;

public class Ema extends BasicFn {
	static final private String ABBR = "ema";
	final private Function in;
	final private int period;
	final private double ratio;
	// internal states
	private double pAvg, cAvg; // prev & curr average
	private long count; // number of rows

	@IndicatorFn(name = ABBR)
	public Ema(
			@IndicatorInput Function in,
			@IndicatorParam(name = "period", init = 14) int period) {
		this(in, period, 2.0 / (period + 1));
	}
	public Ema(Function in, double ratio) {
		this(in, (int) Math.round(2 / ratio - 1), ratio);
	}
	private Ema(Function in, int period, double ratio) {
		super(ABBR, in);
		this.in = in;
		this.period = period;
		this.ratio = ratio;
		//
		dependOn(in);
	}
	@Override
	public void doLast() {
		double val = in.get();
		cAvg = (count == 1) ? val : (pAvg + (val - pAvg) * ratio);
		if (count >= period) {
			saveResult(cAvg);
		}
	}
	@Override
	public void doNext() {
		count++;
		pAvg = cAvg;
	}
	@Override
	public void doReset() {
		count = 0;
		pAvg = 0;
		cAvg = 0;
	}
}