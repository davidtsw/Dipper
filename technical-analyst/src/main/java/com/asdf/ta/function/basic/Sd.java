package com.asdf.ta.function.basic;

import com.asdf.ta.function.BasicFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.annotation.IndicatorFn;
import com.asdf.ta.function.annotation.IndicatorInput;
import com.asdf.ta.function.annotation.IndicatorParam;
import com.asdf.ta.function.op.unary.OpSq;

public class Sd extends BasicFn {
	static final private String ABBR = "sd";
	static final private double MARGIN = 0.0000001d;
	// internal values
	final private Function avg, sq, sqAvg;

	@IndicatorFn(name = ABBR)
	public Sd(
			@IndicatorInput Function in,
			@IndicatorParam(name = "period", init = 14) int period) {
		super(ABBR, in);
		avg = new Sma(in, period);
		sq = new OpSq(in);
		sqAvg = new Sma(sq, period);
		//
		consist(avg, sq, sqAvg);
		dependOn(avg);
	}
	@Override
	protected void doLast() {
		double m = avg.get();
		double diff = sqAvg.get() - m * m;
		double sd = (diff > MARGIN) ? Math.sqrt(diff) : 0;
		saveResult(sd);
	}
}