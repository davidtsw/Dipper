package com.asdf.ta.function.op.unary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.Function;

public class OpSqrtTest {
	@Test
	public void testInputNotValue() {
		Function in1 = mock(Function.class);
		OpSqrt op = new OpSqrt(in1);
		// inputs have no value
		when(in1.get()).thenReturn(Double.NaN);
		op.last();
		Assert.assertTrue(Double.isNaN(op.get()));
	}
	@Test
	public void testInputWithValue() {
		Function in1 = mock(Function.class);
		OpSqrt op = new OpSqrt(in1);
		//
		when(in1.get()).thenReturn(1.234);
		op.last();
		Assert.assertEquals(1.1108555261d, op.get(), 0.0000000001d);
	}
}