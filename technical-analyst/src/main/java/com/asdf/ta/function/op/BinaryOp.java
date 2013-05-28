package com.asdf.ta.function.op;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.OperationFn;

abstract public class BinaryOp extends OperationFn {
	private final Function in1, in2;

	protected BinaryOp(String name, Function in1, Function in2) {
		super(name, in1, in2);
		this.in1 = in1;
		this.in2 = in2;
	}
	final protected double doOp() {
		return doOp(in1.get(), in2.get());
	}
	/**
	 * Does the binary operation
	 * @param in1 1st operand
	 * @param in2 2nd operand
	 * @return result
	 */
	abstract protected double doOp(double in1, double in2);
}
