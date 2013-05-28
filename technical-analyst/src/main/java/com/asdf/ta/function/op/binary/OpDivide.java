package com.asdf.ta.function.op.binary;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.op.BinaryOp;

public class OpDivide extends BinaryOp {
	final private double ifInfinity;
	public OpDivide(Function in1, Function in2, double ifInfinity) {
		super("/", in1, in2);
		this.ifInfinity = ifInfinity;
	}
	public OpDivide(Function in1, Function in2) {
		this(in1, in2, Double.NaN);
	}
	@Override
	protected double doOp(double in1, double in2) {
		if (in2 == 0) {
			return ifInfinity;
		}
		return in1 / in2;
	}
}