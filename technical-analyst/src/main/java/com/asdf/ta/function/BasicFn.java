package com.asdf.ta.function;

abstract public class BasicFn extends AbstractFn {
	static final private Function[] EMPTY_ARR = new Function[0];

	private Function[] dependencies = EMPTY_ARR;
	private Function[] components = EMPTY_ARR;
	private boolean isLastNull;

	public BasicFn(String name, String[] outputs, Function... inputs) {
		super(name, outputs, inputs);
		isLastNull = false;
	}
	public BasicFn(String name, Function... inputs) {
		this(name, new String[] { name }, inputs);
	}
	@Override
	final public void last() {
		for (Function fn : components) {
			fn.last();
		}
		if (isAllDependenciesHaveValue()) {
			if (isLastNull) {
				doNext();
				isLastNull = false;
			}
			doLast();
		}
	}
	/**
	 * Does calculation to update values in last row.
	 */
	protected void doLast() {
		// default: do nothing
	}
	@Override
	final public void next() {
		for (Function fn : components) {
			fn.next();
		}
		isLastNull = true;
		clearResults();
	}
	/**
	 * Does required actions before a new row is adding.
	 */
	protected void doNext() {
		// default: do nothing
	}
	@Override
	final public void reset() {
		for (Function fn : components) {
			fn.reset();
		}
		doReset();
		clearResults();
	}
	/**
	 * Does cleanup actions of all intermediate variables.
	 */
	protected void doReset() {
		// default: do nothing
	}
	/**
	 * Sets the depending functions of this one
	 * @param dependencies
	 */
	protected void dependOn(Function... dependencies) {
		this.dependencies = dependencies;
	}
	/**
	 * Sets the composing functions of this one
	 * @param components
	 */
	protected void consist(Function... components) {
		this.components = components;
	}
	/**
	 * Checks if all the dependencies have values, i.e. non-NaN
	 */
	private boolean isAllDependenciesHaveValue() {
		for (Function d : dependencies) {
			if (Double.isNaN(d.get())) {
				return false;
			}
		}
		return true;
	}
}