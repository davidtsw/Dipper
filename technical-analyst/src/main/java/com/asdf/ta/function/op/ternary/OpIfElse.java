package com.asdf.ta.function.op.ternary;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.op.TernaryOp;

public class OpIfElse extends TernaryOp {
	public OpIfElse(Function in1, Function in2, Function in3) {
		super("ifelse", in1, in2, in3);
	}
	@Override
	protected double doOp(double in1, double in2, double in3) {
		return (in1 > 0) ? in2 : in3;
	}
}