package com.asdf.ta.function.composite;

import com.asdf.ta.function.CompositeFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.annotation.IndicatorFn;
import com.asdf.ta.function.annotation.IndicatorInput;
import com.asdf.ta.function.annotation.IndicatorParam;
import com.asdf.ta.function.basic.Sd;
import com.asdf.ta.function.basic.Sma;
import com.asdf.ta.function.basic.ref.Const;
import com.asdf.ta.function.op.binary.OpMinus;
import com.asdf.ta.function.op.binary.OpMultiply;
import com.asdf.ta.function.op.binary.OpPlus;

public class Bb extends CompositeFn {
	static final private String ABBR = "bb";
	static final private String[] LABELS = { "average", "upper", "lower" };
	//
	@IndicatorFn(name = ABBR)
	public Bb(
			@IndicatorInput Function in,
			@IndicatorParam(name = "period", init = 14) int period,
			@IndicatorParam(name = "deviation", init = 20, dp = 1) int deviation) {
		super(ABBR, LABELS, in);
		//
		double dev = deviation / 10d;
		Function avg = new Sma(in, period);
		Function sd = new OpMultiply(new Sd(in, period), new Const(dev));
		Function upper = new OpPlus(avg, sd);
		Function lower = new OpMinus(avg, sd);
		//
		withOutputs(avg, upper, lower);
	}
}