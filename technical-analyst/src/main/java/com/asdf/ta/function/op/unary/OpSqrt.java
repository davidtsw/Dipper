package com.asdf.ta.function.op.unary;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.op.UnaryOp;

public class OpSqrt extends UnaryOp {
	public OpSqrt(Function in) {
		super("sqrt", in);
	}
	@Override
	protected double doOp(double in) {
		return Math.sqrt(in);
	}
}