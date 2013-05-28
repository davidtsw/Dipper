package com.asdf.ta.function.op.binary;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.op.BinaryOp;

public class OpLt extends BinaryOp {
	final private double margin;
	public OpLt(Function in1, Function in2, double margin) {
		super("lt", in1, in2);
		this.margin = margin;
	}
	@Override
	protected double doOp(double in1, double in2) {
		return (in1 < in2 + margin) ? in1 : in2;
	}
}