package com.asdf.ta.function.op;

import com.asdf.ta.function.Function;
import com.asdf.ta.function.OperationFn;

abstract public class UnaryOp extends OperationFn {
	private final Function in;

	protected UnaryOp(String name, Function in) {
		super(name, in);
		this.in = in;
	}
	final protected double doOp() {
		return doOp(in.get());
	}
	/**
	 * Does the unary operation
	 * @param in the operand
	 * @return result
	 */
	abstract protected double doOp(double in);
}
