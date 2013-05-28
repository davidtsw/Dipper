package com.asdf.ta.function.op.unary;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.op.UnaryOp;

public class OpSq extends UnaryOp {
	public OpSq(Function in) {
		super("sq", in);
	}
	@Override
	protected double doOp(double in) {
		return in * in;
	}
}