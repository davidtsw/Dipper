package com.asdf.ta.function.basic;

import com.asdf.ta.function.BasicFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.annotation.IndicatorFn;
import com.asdf.ta.function.annotation.IndicatorInput;
import com.asdf.ta.function.annotation.IndicatorParam;

public class Ws extends BasicFn {
	static final private String ABBR = "ws";
	//
	final private Function in;
	final private int period;
	// internal values
	private double pSum; // previous sum
	private double cAvg; // current average
	private double pAvg; // previous average
	private double last; // last data
	private long count; // number of rowsFs

	@IndicatorFn(name = ABBR)
	public Ws(
			@IndicatorInput Function in,
			@IndicatorParam(name = "period", init = 14) int period) {
		super(ABBR, in);
		this.in = in;
		this.period = period;
		//
		dependOn(in);
	}
	@Override
	public void doLast() {
		double val = in.get();
		if (count < period) {
			pSum -= last;
			pSum += val;
			last = val;
		} else if (count == period) {
			pSum -= last;
			pSum += val;
			last = val;
			double avg = pSum / period;
			saveResult(avg);
			cAvg = avg;
		} else {
			double avg = pAvg + (val - pAvg) / period;
			saveResult(avg);
			cAvg = avg;
		}
	}
	@Override
	public void doNext() {
		count++;
		pAvg = cAvg;
		last = 0;
	}
	@Override
	public void doReset() {
		pSum = 0;
		cAvg = 0;
		pAvg = 0;
		last = 0;
		count = 0;
	}
}