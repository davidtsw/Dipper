package com.asdf.ta.function.op;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.OperationFn;

abstract public class TernaryOp extends OperationFn {
	private final Function in1, in2, in3;

	protected TernaryOp(String name, Function in1, Function in2, Function in3) {
		super(name, in1, in2, in3);
		this.in1 = in1;
		this.in2 = in2;
		this.in3 = in3;
	}
	final protected double doOp() {
		return doOp(in1.get(), in2.get(), in3.get());
	}
	/**
	 * Does the ternary operation
	 * @param in1 1st operand
	 * @param in2 2nd operand
	 * @param in3 3rd operand
	 * @return result
	 */
	abstract protected double doOp(double in1, double in2, double in3);
}