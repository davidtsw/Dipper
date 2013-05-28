package com.asdf.ta.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.asdf.ta.function.Function;

public class DummyFn implements Function {
	static final private List<Function> in = new ArrayList<Function>(0);
	static final private List<String> out = Arrays.asList("val");
	private double[] value = new double[1];

	public void set(double value) {
		this.value[0] = value;
	}
	@Override
	public String getName() {
		return "dummy";
	}
	@Override
	public List<Function> getInputs() {
		return in;
	}
	@Override
	public List<String> getOutputs() {
		return out;
	}
	@Override
	public double get() {
		return value[0];
	}
	@Override
	public double get(int index) {
		return value[0];
	}
	@Override
	public double[] getAll() {
		return value;
	}
	@Override
	public void last() {
		// do nothing
	}
	@Override
	public void next() {
		// do nothing
	}
	@Override
	public void reset() {
		// do nothing
	}
}