package com.asdf.ta.function;

import java.util.List;

public interface Function {
	public final double NO_VALUE = Double.NaN;
	/**
	 * Returns the name of the function
	 * @return the name of the function
	 */
	public String getName();
	/**
	 * Returns all the inputs of the function
	 * @return list of all inputs
	 */
	public List<Function> getInputs();
	/**
	 * Returns all the outputs form the functions
	 * @return list of all outputs names
	 */
	public List<String> getOutputs();
	/**
	 * Returns the result of calculation
	 * @return the result
	 */
	public double get();
	/**
	 * Returns the nth result of calculation
	 * @param index the index of results
	 * @return the result
	 */
	public double get(int index);
	/**
	 * Returns all the results of calculation
	 * @return the result array
	 */
	public double[] getAll();
	/**
	 * Performs calculation to get last row results
	 */
	public void last();
	/**
	 * Performs action when a next row is about to add. {@link #last()} should follow to do the
	 * calculation of the new added row.
	 */
	public void next();
	/**
	 * Performs action to clear all the intermediate states
	 */
	public void reset();
}