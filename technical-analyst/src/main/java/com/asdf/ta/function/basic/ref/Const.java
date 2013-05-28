package com.asdf.ta.function.basic.ref;

import com.asdf.ta.function.BasicFn;

public class Const extends BasicFn {
	final private double value;
	public Const(double value) {
		super("const");
		this.value = value;
	}
	@Override
	protected void doLast() {
		saveResult(value);
	}
}