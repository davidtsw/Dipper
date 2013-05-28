package com.asdf.ta.function.op.binary;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.op.BinaryOp;

public class OpMultiply extends BinaryOp {
	public OpMultiply(Function in1, Function in2) {
		super("*", in1, in2);
	}
	@Override
	protected double doOp(double in1, double in2) {
		return in1 * in2;
	}
}