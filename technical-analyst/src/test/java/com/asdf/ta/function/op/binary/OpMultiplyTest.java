package com.asdf.ta.function.op.binary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.Function;

public class OpMultiplyTest {
	@Test
	public void testInputNotValue() {
		Function in1 = mock(Function.class);
		Function in2 = mock(Function.class);
		OpMultiply op = new OpMultiply(in1, in2);
		// both inputs have no value
		when(in1.get()).thenReturn(Double.NaN);
		when(in2.get()).thenReturn(Double.NaN);
		op.last();
		Assert.assertTrue(Double.isNaN(op.get()));
		// either inputs have no value
		when(in1.get()).thenReturn(Double.NaN);
		when(in2.get()).thenReturn(2.345);
		op.last();
		Assert.assertTrue(Double.isNaN(op.get()));
		//
		when(in1.get()).thenReturn(1.234);
		when(in2.get()).thenReturn(Double.NaN);
		op.last();
		Assert.assertTrue(Double.isNaN(op.get()));
	}
	@Test
	public void testInputWithValue() {
		Function in1 = mock(Function.class);
		Function in2 = mock(Function.class);
		OpMultiply op = new OpMultiply(in1, in2);
		//
		when(in1.get()).thenReturn(1.234);
		when(in2.get()).thenReturn(2.345);
		op.last();
		Assert.assertEquals(2.89373, op.get(), 0.0000000001d);
	}
}