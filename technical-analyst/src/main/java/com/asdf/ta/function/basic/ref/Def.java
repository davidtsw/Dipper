package com.asdf.ta.function.basic.ref;

import com.asdf.ta.function.BasicFn;
import com.asdf.ta.function.Function;

public class Def extends BasicFn {
	final private double def;
	final private Function fn;
	public Def(Function fn, double def) {
		super("def", fn);
		this.def = def;
		this.fn = fn;
	}
	@Override
	protected void doLast() {
		saveResult(Double.isNaN(fn.get()) ? def : fn.get());
	}
}