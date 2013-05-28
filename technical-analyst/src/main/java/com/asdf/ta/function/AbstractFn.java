package com.asdf.ta.function;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Skeletal implementation of {@link Function}.
 */
public abstract class AbstractFn implements Function {
	final private String name;
	final private List<Function> inputs;
	final private List<String> outputs;
	final private double[] results;

	AbstractFn(String name, String[] outputs, Function[] inputs) {
		this.name = name;
		this.inputs = Collections.unmodifiableList(Arrays.asList(inputs));
		this.outputs = Collections.unmodifiableList(Arrays.asList(outputs));
		this.results = new double[this.outputs.size()];
		clearResults();
	}
	@Override
	final public String getName() {
		return name;
	}
	@Override
	final public List<Function> getInputs() {
		return inputs;
	}
	@Override
	final public List<String> getOutputs() {
		return outputs;
	}
	@Override
	final public double get() {
		return results[0];
	}
	@Override
	final public double get(int index) {
		assert index >= 0 && index < results.length : "invalid column: " + index + ", where size = " + results.length;
		return results[index];
	}
	@Override
	final public double[] getAll() {
		return results;
	}
	/**
	 * Saves the nth result of calculation
	 * @param index the result index
	 * @param val the result value
	 */
	protected void saveResult(int index, double val) {
		assert index >= 0 && index < results.length : "invalid column: " + index + ", where size = " + results.length;
		results[index] = val;
	}
	/**
	 * Saves the result of calculation
	 * @param val the result value
	 */
	protected void saveResult(double val) {
		saveResult(0, val);
	}
	/**
	 * Clears all results of calculation
	 */
	protected void clearResults() {
		for (int i = 0; i < outputs.size(); i++) {
			saveResult(i, NO_VALUE);
		}
	}
}
