package com.asdf.ta.function.basic;

import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.DummyFn;
import com.asdf.ta.function.FunctionTest;
import com.asdf.ta.function.basic.Ema;
import com.asdf.ta.workbook.fml.IndicatorAssert;

public class EmaTest implements FunctionTest {
	@Test
	public void testExample_1() {
		// ref - Technical Analysis From A to Z, Page XX
		DummyFn dum = new DummyFn();
		Ema ema = new Ema(dum, 5);
		// row 1
		dum.set(Double.NaN);
		ema.next();
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		// row 2
		dum.set(25.0000);
		ema.next();
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		// row 3
		dum.set(24.8750);
		ema.next();
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		// row 4
		dum.set(24.7810);
		ema.next();
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		// row 5
		dum.set(24.5940);
		ema.next();
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		// row 6
		dum.set(24.5000);
		ema.next();
		ema.last();
		Assert.assertEquals(24.6983, ema.get(), ROUNDING_DELTA);
		// row 7
		dum.set(24.6250);
		ema.next();
		ema.last();
		Assert.assertEquals(24.6739, ema.get(), ROUNDING_DELTA);
		// row 8
		dum.set(25.2190);
		ema.next();
		ema.last();
		Assert.assertEquals(24.8556, ema.get(), ROUNDING_DELTA);
		// row 9
		dum.set(27.2500);
		ema.next();
		ema.last();
		Assert.assertEquals(25.6537, ema.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIncrementalUpdate() {
		DummyFn dum = new DummyFn();
		Ema ema = new Ema(dum, 3);
		// a1 - first row
		dum.set(1713.3800);
		ema.next();
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		dum.set(1713.5800);
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		dum.set(1713.5800);
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		// a2 - before full-period
		dum.set(1713.8600);
		ema.next();
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		dum.set(1713.8000);
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		dum.set(1713.8000);
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		// a3 - after full-period
		dum.set(1713.0100);
		ema.next();
		ema.last();
		Assert.assertEquals(1713.3500, ema.get(), ROUNDING_DELTA);
		dum.set(1713.0500);
		ema.last();
		Assert.assertEquals(1713.3700, ema.get(), ROUNDING_DELTA);
		// a4 - after full-period
		dum.set(1712.0500);
		ema.next();
		ema.last();
		Assert.assertEquals(1712.7100, ema.get(), ROUNDING_DELTA);
		dum.set(1712.0500);
		ema.last();
		Assert.assertEquals(1712.7100, ema.get(), ROUNDING_DELTA);
		// reset
		ema.reset();
		// b1
		dum.set(1708.3600);
		ema.next();
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		// b2
		dum.set(1709.4200);
		ema.next();
		ema.last();
		Assert.assertEquals(Double.NaN, ema.get(), ROUNDING_DELTA);
		// b3
		dum.set(1707.7600);
		ema.next();
		ema.last();
		Assert.assertEquals(1708.3250, ema.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIndicatorStuff() {
		IndicatorAssert.checkIndicator(Ema.class);
	}
}
