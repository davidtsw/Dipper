package com.asdf.ta.function;

import static org.mockito.Mockito.mock;
import org.junit.Assert;
import org.junit.Test;

public class AbstractFnTest {
	private static final double DELTA = 0.0000000001;

	@Test
	public void testProperties() {
		Function in1 = mock(Function.class);
		Function in2 = mock(Function.class);
		// set 1
		String name = "fn1";
		Function[] inputs = new Function[] { in1 };
		String[] outputs = new String[] { "def" };
		IUT iut1 = new IUT(name, outputs, inputs);
		Assert.assertEquals(name, iut1.getName());
		Assert.assertArrayEquals(inputs, iut1.getInputs().toArray(new Function[0]));
		Assert.assertArrayEquals(outputs, iut1.getOutputs().toArray(new String[0]));
		// set 2
		name = "fn2";
		inputs = new Function[] { in1, in2 };
		outputs = new String[] { "def", "c1", "c2" };
		IUT iut2 = new IUT(name, outputs, inputs);
		Assert.assertEquals(name, iut2.getName());
		Assert.assertArrayEquals(inputs, iut2.getInputs().toArray(new Function[0]));
		Assert.assertArrayEquals(outputs, iut2.getOutputs().toArray(new String[0]));
	}
	@Test
	public void testSavingResult() {
		IUT iut = new IUT("iut", new String[] { "c1", "c2" }, new Function[] {});
		// 1. before saving any values
		Assert.assertArrayEquals(new double[] { Double.NaN, Double.NaN }, iut.getAll(), DELTA);
		// 2. saving values
		iut.saveResult(1.234);
		Assert.assertArrayEquals(new double[] { 1.234, Double.NaN }, iut.getAll(), DELTA);
		iut.saveResult(0, 2.234);
		Assert.assertEquals(2.234, iut.get(), DELTA);
		Assert.assertEquals(Double.NaN, iut.get(1), DELTA);
		iut.saveResult(1, 3.234);
		Assert.assertArrayEquals(new double[] { 2.234, 3.234 }, iut.getAll(), DELTA);
		Assert.assertEquals(2.234, iut.get(), DELTA);
		Assert.assertEquals(2.234, iut.get(0), DELTA);
		Assert.assertEquals(3.234, iut.get(1), DELTA);
		// 3. after clearing
		iut.clearResults();
		Assert.assertArrayEquals(new double[] { Double.NaN, Double.NaN }, iut.getAll(), DELTA);
	}

	static class IUT extends AbstractFn {
		public IUT(String name, String[] outputs, Function[] inputs) {
			super(name, outputs, inputs);
		}
		public void last() {
		}
		public void next() {
		}
		public void reset() {
		}
		/* expose the protected methods for testing */
		public void saveResult(int col, double val) {
			super.saveResult(col, val);
		}
		public void saveResult(double val) {
			super.saveResult(val);
		}
		public void clearResults() {
			super.clearResults();
		}
	}
}