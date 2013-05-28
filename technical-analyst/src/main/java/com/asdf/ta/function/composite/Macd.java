package com.asdf.ta.function.composite;

import com.asdf.ta.function.CompositeFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.annotation.*;
import com.asdf.ta.function.basic.Ema;
import com.asdf.ta.function.op.binary.OpMinus;

public class Macd extends CompositeFn {
	static final private String ABBR = "macd";
	static final private String[] LABELS = { "macd", "signal", "diff" };
	//
	@IndicatorFn(name = ABBR)
	public Macd(
			@IndicatorInput Function in,
			@IndicatorParam(name = "f.period", init = 12) int fastPeriod,
			@IndicatorParam(name = "s.period", init = 26) @IndicatorRelation(gt = "f.period") int slowPeriod,
			@IndicatorParam(name = "signal", init = 9) int signalPeriod) {
		super(ABBR, LABELS, in);
		//
		Function fsMinus = new OpMinus(
				new Ema(in, fastPeriod),
				new Ema(in, slowPeriod));
		Function mEma = new Ema(fsMinus, signalPeriod);
		Function diff = new OpMinus(fsMinus, mEma);
		//
		withOutputs(fsMinus, mEma, diff);
	}
	// for testing against example
	Macd(Function in, double fastRatio, double slowRatio, double signalRatio) {
		super(ABBR, LABELS, in);
		//
		Function fsMinus = new OpMinus(
				new Ema(in, fastRatio),
				new Ema(in, slowRatio));
		Function mEma = new Ema(fsMinus, signalRatio);
		Function diff = new OpMinus(fsMinus, mEma);
		//
		withOutputs(fsMinus, mEma, diff);
	}
}