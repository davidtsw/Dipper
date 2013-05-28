package com.asdf.ta.function.composite;

import com.asdf.ta.function.CompositeFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.annotation.IndicatorFn;
import com.asdf.ta.function.annotation.IndicatorInput;
import com.asdf.ta.function.annotation.IndicatorParam;
import com.asdf.ta.function.basic.Sma;
import com.asdf.ta.function.basic.math.Max;
import com.asdf.ta.function.basic.math.Min;
import com.asdf.ta.function.basic.math.Sum;
import com.asdf.ta.function.basic.ref.Const;
import com.asdf.ta.function.op.binary.OpDivide;
import com.asdf.ta.function.op.binary.OpMinus;
import com.asdf.ta.function.op.binary.OpMultiply;
import com.asdf.ta.workbook.ColumnT;

public class Ss extends CompositeFn {
	static final private String ABBR = "ss";
	static final private String[] LABELS = { "k", "d" };
	//
	@IndicatorFn(name = ABBR)
	public Ss(
			@IndicatorInput(req = ColumnT.CLOSE) Function iC,
			@IndicatorInput(req = ColumnT.HIGH) Function iH,
			@IndicatorInput(req = ColumnT.LOW) Function iL,
			@IndicatorParam(name = "k.period", init = 14) int kPeriod,
			@IndicatorParam(name = "d.period", init = 3) int dPeriod,
			@IndicatorParam(name = "k.slow", init = 3) int kSlow) {
		super(ABBR, LABELS, iC, iH, iL);
		Function c100 = new Const(100);
		Function kMin = new Min(iL, kPeriod);
		Function kMax = new Max(iH, kPeriod);
		Function kCloseDiff = new Sum(new OpMinus(iC, kMin), kSlow);
		Function kHighDiff = new Sum(new OpMinus(kMax, kMin), kSlow);
		Function k = new OpMultiply(new OpDivide(kCloseDiff, kHighDiff, 1d), c100);
		Function d = new Sma(k, dPeriod);
		//
		withOutputs(k, d);
	}
}