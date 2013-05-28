package com.asdf.ta.function.composite;

import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.DummyFn;
import com.asdf.ta.function.FunctionTest;
import com.asdf.ta.function.composite.Macd;
import com.asdf.ta.workbook.fml.IndicatorAssert;

public class MacdTest implements FunctionTest {
	@Test
	public void testExample_1() {
		// ref - Technical Analysis From A to Z, Page XX
		DummyFn dum = new DummyFn();
		Macd macd = new Macd(dum, 0.15, 0.075, 0.2);
		// row 1
		dum.set(Double.NaN);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 2
		dum.set(63.7500);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 3
		dum.set(63.6250);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 4
		dum.set(63.0000);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 5
		dum.set(62.7500);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 6
		dum.set(63.2500);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 7
		dum.set(65.3750);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 8
		dum.set(66.0000);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 9
		dum.set(65.0000);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 10
		dum.set(64.8750);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 11
		dum.set(64.7500);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 12
		dum.set(64.3750);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 13
		dum.set(64.3750);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 14
		dum.set(64.6250);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 15
		dum.set(64.3750);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 16
		dum.set(64.5000);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 17
		dum.set(65.2500);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 18
		dum.set(67.8750);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 19
		dum.set(68.0000);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 20
		dum.set(66.8750);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 21
		dum.set(66.2500);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 22
		dum.set(65.8750);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 23
		dum.set(66.0000);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 24
		dum.set(65.8750);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 25
		dum.set(64.7500);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 26
		dum.set(63.0000);
		macd.next();
		macd.last();
		Assert.assertEquals(Double.NaN, macd.get(), ROUNDING_DELTA);
		// row 27
		dum.set(63.3750);
		macd.next();
		macd.last();
		Assert.assertEquals(.0692, macd.get(), ROUNDING_DELTA);
		// row 28
		dum.set(63.3750);
		macd.next();
		macd.last();
		Assert.assertEquals(-.0567, macd.get(), ROUNDING_DELTA);
		// row 29
		dum.set(63.3750);
		macd.next();
		macd.last();
		Assert.assertEquals(-.1552, macd.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIndicatorStuff() {
		IndicatorAssert.checkIndicator(Macd.class);
	}
}