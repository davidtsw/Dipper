package com.asdf.ta.function.op.binary;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.op.BinaryOp;

public class OpLt extends BinaryOp {
	public OpLt(Function in1, Function in2) {
		super("lt", in1, in2);
	}
	@Override
	protected double doOp(double in1, double in2) {
		return (in1 < in2) ? in1 : in2;
	}
}