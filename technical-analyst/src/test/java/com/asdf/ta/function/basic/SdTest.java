package com.asdf.ta.function.basic;

import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.DummyFn;
import com.asdf.ta.function.FunctionTest;
import com.asdf.ta.function.basic.Sd;
import com.asdf.ta.workbook.fml.IndicatorAssert;

public class SdTest implements FunctionTest {
	@Test
	public void testExample_1() {
		// ref - Technical Analysis From A to Z, Page XX
		DummyFn dum = new DummyFn();
		Sd sd = new Sd(dum, 5);
		// row 1
		dum.set(Double.NaN);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 2
		dum.set(21.4375);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 3
		dum.set(21.6875);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 4
		dum.set(22.1250);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 5
		dum.set(21.5625);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 6
		dum.set(21.8125);
		sd.next();
		sd.last();
		Assert.assertEquals(.2358, sd.get(), ROUNDING_DELTA);
		// row 7
		dum.set(21.4375);
		sd.next();
		sd.last();
		Assert.assertEquals(.2358, sd.get(), ROUNDING_DELTA);
		// row 8
		dum.set(21.5000);
		sd.next();
		sd.last();
		Assert.assertEquals(.2531, sd.get(), ROUNDING_DELTA);
		// row 9
		dum.set(21.1875);
		sd.next();
		sd.last();
		Assert.assertEquals(.2016, sd.get(), ROUNDING_DELTA);
		// row 10
		dum.set(20.6875);
		sd.next();
		sd.last();
		Assert.assertEquals(.3758, sd.get(), ROUNDING_DELTA);
		// row 11
		dum.set(20.9375);
		sd.next();
		sd.last();
		Assert.assertEquals(.3052, sd.get(), ROUNDING_DELTA);
	}
	@Test
	public void testExample_2() {
		// ref - ChartSchool,
		// http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:standard_deviation_v
		DummyFn dum = new DummyFn();
		Sd sd = new Sd(dum, 10);
		// row 1
		dum.set(Double.NaN);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 2
		dum.set(52.2200);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 3
		dum.set(52.7800);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 4
		dum.set(53.0200);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 5
		dum.set(53.6700);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 6
		dum.set(53.6700);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 7
		dum.set(53.7375);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 8
		dum.set(53.4500);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 9
		dum.set(53.7150);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 10
		dum.set(53.3850);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// row 11
		dum.set(52.5100);
		sd.next();
		sd.last();
		Assert.assertEquals(.5230, sd.get(), ROUNDING_DELTA);
		// row 12
		dum.set(52.3150);
		sd.next();
		sd.last();
		Assert.assertEquals(.5054, sd.get(), ROUNDING_DELTA);
		// row 13
		dum.set(51.4500);
		sd.next();
		sd.last();
		Assert.assertEquals(.7301, sd.get(), ROUNDING_DELTA);
		// row 14
		dum.set(51.6000);
		sd.next();
		sd.last();
		Assert.assertEquals(.8574, sd.get(), ROUNDING_DELTA);
		// row 15
		dum.set(52.4300);
		sd.next();
		sd.last();
		Assert.assertEquals(.8336, sd.get(), ROUNDING_DELTA);
		// row 16
		dum.set(52.4700);
		sd.next();
		sd.last();
		Assert.assertEquals(.7887, sd.get(), ROUNDING_DELTA);
		// row 17
		dum.set(52.9100);
		sd.next();
		sd.last();
		Assert.assertEquals(.7163, sd.get(), ROUNDING_DELTA);
		// row 18
		dum.set(52.0700);
		sd.next();
		sd.last();
		Assert.assertEquals(.6755, sd.get(), ROUNDING_DELTA);
		// row 19
		dum.set(53.1200);
		sd.next();
		sd.last();
		Assert.assertEquals(.5847, sd.get(), ROUNDING_DELTA);
		// row 20
		dum.set(52.7700);
		sd.next();
		sd.last();
		Assert.assertEquals(.5079, sd.get(), ROUNDING_DELTA);
		// row 21
		dum.set(52.7300);
		sd.next();
		sd.last();
		Assert.assertEquals(.5184, sd.get(), ROUNDING_DELTA);
		// row 22
		dum.set(52.0850);
		sd.next();
		sd.last();
		Assert.assertEquals(.5261, sd.get(), ROUNDING_DELTA);
		// row 23
		dum.set(53.1900);
		sd.next();
		sd.last();
		Assert.assertEquals(.4810, sd.get(), ROUNDING_DELTA);
		// row 24
		dum.set(53.7300);
		sd.next();
		sd.last();
		Assert.assertEquals(.4902, sd.get(), ROUNDING_DELTA);
		// row 25
		dum.set(53.8700);
		sd.next();
		sd.last();
		Assert.assertEquals(.5784, sd.get(), ROUNDING_DELTA);
		// row 26
		dum.set(53.8450);
		sd.next();
		sd.last();
		Assert.assertEquals(.6229, sd.get(), ROUNDING_DELTA);
		// row 27
		dum.set(53.8800);
		sd.next();
		sd.last();
		Assert.assertEquals(.6701, sd.get(), ROUNDING_DELTA);
		// row 28
		dum.set(54.0800);
		sd.next();
		sd.last();
		Assert.assertEquals(.6220, sd.get(), ROUNDING_DELTA);
		// row 29
		dum.set(54.1350);
		sd.next();
		sd.last();
		Assert.assertEquals(.6611, sd.get(), ROUNDING_DELTA);
		// row 30
		dum.set(54.4950);
		sd.next();
		sd.last();
		Assert.assertEquals(.6904, sd.get(), ROUNDING_DELTA);
		// row 31
		dum.set(54.3000);
		sd.next();
		sd.last();
		Assert.assertEquals(.6512, sd.get(), ROUNDING_DELTA);
		// row 32
		dum.set(54.3950);
		sd.next();
		sd.last();
		Assert.assertEquals(.3605, sd.get(), ROUNDING_DELTA);
		// row 33
		dum.set(54.1600);
		sd.next();
		sd.last();
		Assert.assertEquals(.2430, sd.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIncrementalUpdate() {
		DummyFn dum = new DummyFn();
		Sd sd = new Sd(dum, 3);
		// a1 - before full-period, w/ change value
		dum.set(1713.3800);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		dum.set(1713.5800);
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// a2 - before full-period, w/o change value
		dum.set(1713.8000);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		dum.set(1713.8000);
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// a3 - after full-period, w/ change value
		dum.set(1713.2500);
		sd.next();
		sd.last();
		Assert.assertEquals(.2260, sd.get(), ROUNDING_DELTA);
		dum.set(1713.0500);
		sd.last();
		Assert.assertEquals(.3148, sd.get(), ROUNDING_DELTA);
		// a4 - after full-period, w/o change value
		dum.set(1712.0500);
		sd.next();
		sd.last();
		Assert.assertEquals(.7169, sd.get(), ROUNDING_DELTA);
		dum.set(1712.0500);
		sd.last();
		Assert.assertEquals(.7169, sd.get(), ROUNDING_DELTA);
		// a5 - successive new rows
		dum.set(1708.3600);
		sd.next();
		sd.last();
		Assert.assertEquals(2.0169, sd.get(), ROUNDING_DELTA);
		// a6
		dum.set(1709.4200);
		sd.next();
		sd.last();
		Assert.assertEquals(1.5512, sd.get(), ROUNDING_DELTA);
		// reset
		sd.reset();
		// b1
		dum.set(1707.7600);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// b2
		dum.set(1704.5400);
		sd.next();
		sd.last();
		Assert.assertEquals(Double.NaN, sd.get(), ROUNDING_DELTA);
		// b3
		dum.set(1703.9600);
		sd.next();
		sd.last();
		Assert.assertEquals(1.6715, sd.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIndicatorStuff() {
		IndicatorAssert.checkIndicator(Sd.class);
	}
}