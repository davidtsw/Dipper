package com.asdf.ta.function;


abstract public class OperationFn extends AbstractFn {
	protected OperationFn(String name, Function... inputs) {
		super(name, new String[] { name }, inputs);
	}
	final public void last() {
		op();
	}
	final public void next() {
		clearResults();
	}
	final public void reset() {
		clearResults();
	}
	private void op() {
		for (Function in : getInputs()) {
			if (Double.isNaN(in.get())) {
				return;
			}
		}
		saveResult(doOp());
	}
	/**
	 * Does the operation with specified number of arguments
	 * @return calculation result
	 */
	abstract protected double doOp();
}