package com.asdf.ta.function.basic;

import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.DummyFn;
import com.asdf.ta.function.FunctionTest;
import com.asdf.ta.workbook.fml.IndicatorAssert;

public class WsTest implements FunctionTest {
	@Test
	public void testExample_1() {
		// ref - Technical Analysis From A to Z, Page XX
		DummyFn dum = new DummyFn();
		Ws ws = new Ws(dum, 5);
		// row 1
		dum.set(Double.NaN);
		ws.next();
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		// row 2
		dum.set(62.1250);
		ws.next();
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		// row 3
		dum.set(61.1250);
		ws.next();
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		// row 4
		dum.set(62.3438);
		ws.next();
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		// row 5
		dum.set(65.3125);
		ws.next();
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		// row 6
		dum.set(63.9688);
		ws.next();
		ws.last();
		Assert.assertEquals(62.9750, ws.get(), ROUNDING_DELTA);
		// row 7
		dum.set(63.4375);
		ws.next();
		ws.last();
		Assert.assertEquals(63.0675, ws.get(), ROUNDING_DELTA);
		// row 8
		dum.set(63.0000);
		ws.next();
		ws.last();
		Assert.assertEquals(63.0540, ws.get(), ROUNDING_DELTA);
		// row 9
		dum.set(63.7812);
		ws.next();
		ws.last();
		Assert.assertEquals(63.1995, ws.get(), ROUNDING_DELTA);
		// row 10
		dum.set(63.4062);
		ws.next();
		ws.last();
		Assert.assertEquals(63.2408, ws.get(), ROUNDING_DELTA);
		// row 11
		dum.set(63.4062);
		ws.next();
		ws.last();
		Assert.assertEquals(63.2739, ws.get(), ROUNDING_DELTA);
		// row 12
		dum.set(62.4375);
		ws.next();
		ws.last();
		Assert.assertEquals(63.1066, ws.get(), ROUNDING_DELTA);
		// row 13
		dum.set(61.8438);
		ws.next();
		ws.last();
		Assert.assertEquals(62.8540, ws.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIncrementalUpdate() {
		DummyFn dum = new DummyFn();
		Ws ws = new Ws(dum, 3);
		// a1 - before full-period, w/ change value
		dum.set(1713.3800);
		ws.next();
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		dum.set(1713.5800);
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		// a2 - before full-period, w/o change value
		dum.set(1713.8000);
		ws.next();
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		dum.set(1713.8000);
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		// a3 - at full-period, w/ change value
		dum.set(1713.1200);
		ws.next();
		ws.last();
		Assert.assertEquals(1713.5000, ws.get(), ROUNDING_DELTA);
		dum.set(1713.0500);
		ws.last();
		Assert.assertEquals(1713.4767, ws.get(), ROUNDING_DELTA);
		dum.set(1713.0500);
		ws.last();
		Assert.assertEquals(1713.4767, ws.get(), ROUNDING_DELTA);
		// a4 - after full-period, w/ change value
		dum.set(1708.1600);
		ws.next();
		ws.last();
		Assert.assertEquals(1711.7044, ws.get(), ROUNDING_DELTA);
		dum.set(1708.3600);
		ws.last();
		Assert.assertEquals(1711.7711, ws.get(), ROUNDING_DELTA);
		// a5 - after full-period, w/o change value
		dum.set(1709.4200);
		ws.next();
		ws.last();
		Assert.assertEquals(1710.9874, ws.get(), ROUNDING_DELTA);
		dum.set(1709.4200);
		ws.last();
		Assert.assertEquals(1710.9874, ws.get(), ROUNDING_DELTA);
		// a6 - successive new rows
		dum.set(1707.7600);
		ws.next();
		ws.last();
		Assert.assertEquals(1709.9116, ws.get(), ROUNDING_DELTA);
		// a7
		dum.set(1704.5400);
		ws.next();
		ws.last();
		Assert.assertEquals(1708.1211, ws.get(), ROUNDING_DELTA);
		// reset
		ws.reset();
		// b1
		dum.set(1707.7600);
		ws.next();
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		// b2
		dum.set(1704.5400);
		ws.next();
		ws.last();
		Assert.assertEquals(Double.NaN, ws.get(), ROUNDING_DELTA);
		// b3
		dum.set(1703.9600);
		ws.next();
		ws.last();
		Assert.assertEquals(1705.4200, ws.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIndicatorStuff() {
		IndicatorAssert.checkIndicator(Ws.class);
	}
}