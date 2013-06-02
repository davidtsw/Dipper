package com.asdf.ta.function.op.binary;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.op.BinaryOp;

public class OpGt extends BinaryOp {
	public OpGt(Function in1, Function in2) {
		super("gt", in1, in2);
	}
	@Override
	protected double doOp(double in1, double in2) {
		return (in1 > in2) ? in1 : in2;
	}
}