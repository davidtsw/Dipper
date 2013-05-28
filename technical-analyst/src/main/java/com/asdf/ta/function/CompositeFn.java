package com.asdf.ta.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeFn extends BasicFn {
	private Function[] resultFn;

	public CompositeFn(String name, Function... inputs) {
		super(name, inputs);
	}
	public CompositeFn(String name, String[] outputs, Function... inputs) {
		super(name, outputs, inputs);
	}
	protected void withOutputs(Function... resultFn) {
		assert resultFn.length == getOutputs().size() : "column number is not match! "
				+ resultFn.length + "/" + getOutputs().size();
		this.resultFn = resultFn;
		// find out all the components from the output list
		ArrayList<Function> fnList = new ArrayList<Function>();
		dfsComp(Arrays.asList(resultFn), getInputs(), fnList);
		consist((Function[]) fnList.toArray(new Function[fnList.size()]));
	}
	@Override
	final protected void doLast() {
		saveResultFromComponents();
	}
	@Override
	final protected void doNext() {
		saveResultFromComponents();
	}
	@Override
	final protected void doReset() {
		saveResultFromComponents();
	}
	private void saveResultFromComponents() {
		for (int i = 0; i < resultFn.length; i++) {
			saveResult(i, resultFn[i].get());
		}
	}
	/**
	 * Finds all the components by DFS, starting from the result list
	 * @param compInputs inputs of components
	 * @param thisInput inputs of this functions, which should be excluded
	 * @param fnList function list holding the search result
	 */
	static private void dfsComp(List<Function> compInputs, List<Function> thisInput, List<Function> fnList) {
		for (Function in : compInputs) {
			if (!thisInput.contains(in)) {
				dfsComp(in.getInputs(), thisInput, fnList);
				if (!fnList.contains(in)) {
					fnList.add(in);
				}
			}
		}
	}
}