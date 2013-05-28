package com.asdf.ta.function.composite;

import com.asdf.ta.function.CompositeFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.annotation.IndicatorFn;
import com.asdf.ta.function.annotation.IndicatorInput;
import com.asdf.ta.function.annotation.IndicatorParam;
import com.asdf.ta.function.basic.Ws;
import com.asdf.ta.function.basic.ref.Const;
import com.asdf.ta.function.basic.ref.Ref;
import com.asdf.ta.function.op.binary.OpDivide;
import com.asdf.ta.function.op.binary.OpGt;
import com.asdf.ta.function.op.binary.OpMinus;
import com.asdf.ta.function.op.binary.OpPlus;
import com.asdf.ta.function.op.ternary.OpIfElse;

public class Rsi extends CompositeFn {
	static final private double DIFF_MARGIN = 0.000000001d;
	static final private String ABBR = "rsi";
	//
	@IndicatorFn(name = ABBR)
	public Rsi(
			@IndicatorInput Function in,
			@IndicatorParam(name = "period", init = 14) int period) {
		super(ABBR, in);
		Function c0 = new Const(0), c1 = new Const(1), c100 = new Const(100);
		Function prev = new Ref(in, 1);
		Function dSum = new Ws(new OpGt(new OpMinus(prev, in), c0, DIFF_MARGIN), period);
		Function uSum = new Ws(new OpGt(new OpMinus(in, prev), c0, DIFF_MARGIN), period);
		Function rsi = new OpMinus(c100, new OpDivide(c100, new OpPlus(c1, new OpDivide(uSum, dSum))));
		//
		withOutputs(new OpIfElse(dSum, rsi, c100));
	}
}