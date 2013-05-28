package com.asdf.ta.function.op.binary;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.op.BinaryOp;

public class OpGt extends BinaryOp {
	final private double margin;
	public OpGt(Function in1, Function in2, double margin) {
		super("gt", in1, in2);
		this.margin = margin;
	}
	@Override
	protected double doOp(double in1, double in2) {
		return (in1 > in2 + margin) ? in1 : in2;
	}
}