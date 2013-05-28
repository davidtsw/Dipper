package com.asdf.ta.function.op.binary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.Function;

public class OpMinusTest {
	@Test
	public void testInputNotValue() {
		Function in1 = mock(Function.class);
		Function in2 = mock(Function.class);
		OpMinus op = new OpMinus(in1, in2);
		// both inputs have no value
		when(in1.get()).thenReturn(Double.NaN);
		when(in2.get()).thenReturn(Double.NaN);
		op.last();
		Assert.assertTrue(Double.isNaN(op.get()));
		// either inputs have no value
		when(in1.get()).thenReturn(Double.NaN);
		when(in2.get()).thenReturn(1.234);
		op.last();
		Assert.assertTrue(Double.isNaN(op.get()));
	}
	@Test
	public void testInputWithValue() {
		Function in1 = mock(Function.class);
		Function in2 = mock(Function.class);
		OpMinus op = new OpMinus(in1, in2);
		//
		when(in1.get()).thenReturn(1.234);
		when(in2.get()).thenReturn(2.345);
		op.last();
		Assert.assertEquals(-1.111, op.get(), 0.0000000001d);
	}
}