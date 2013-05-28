package com.asdf.ta.function.basic;

import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.DummyFn;
import com.asdf.ta.function.FunctionTest;
import com.asdf.ta.function.basic.Sma;
import com.asdf.ta.workbook.fml.IndicatorAssert;

public class SmaTest implements FunctionTest {
	@Test
	public void testExample_1() {
		// ref - Technical Analysis From A to Z, Page XX
		DummyFn dum = new DummyFn();
		Sma sma = new Sma(dum, 5);
		// row 1
		dum.set(Double.NaN);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 2
		dum.set(25.0000);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 3
		dum.set(24.8750);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 4
		dum.set(24.7810);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 5
		dum.set(24.5940);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 6
		dum.set(24.5000);
		sma.next();
		sma.last();
		Assert.assertEquals(24.7500, sma.get(), ROUNDING_DELTA);
		// row 7
		dum.set(24.6250);
		sma.next();
		sma.last();
		Assert.assertEquals(24.6750, sma.get(), ROUNDING_DELTA);
		// row 8
		dum.set(25.2190);
		sma.next();
		sma.last();
		Assert.assertEquals(24.7438, sma.get(), ROUNDING_DELTA);
		// row 9
		dum.set(27.2500);
		sma.next();
		sma.last();
		Assert.assertEquals(25.2376, sma.get(), ROUNDING_DELTA);
	}
	@Test
	public void testExample_2() {
		// ref - ChartSchool,
		// http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:moving_averages
		DummyFn dum = new DummyFn();
		Sma sma = new Sma(dum, 10);
		// row 1
		dum.set(Double.NaN);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 2
		dum.set(22.2734);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 3
		dum.set(22.1940);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 4
		dum.set(22.0847);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 5
		dum.set(22.1741);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 6
		dum.set(22.1840);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 7
		dum.set(22.1344);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 8
		dum.set(22.2337);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 9
		dum.set(22.4323);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 10
		dum.set(22.2436);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// row 11
		dum.set(22.2933);
		sma.next();
		sma.last();
		Assert.assertEquals(22.2248, sma.get(), ROUNDING_DELTA);
		// row 12
		dum.set(22.1542);
		sma.next();
		sma.last();
		Assert.assertEquals(22.2128, sma.get(), ROUNDING_DELTA);
		// row 13
		dum.set(22.3926);
		sma.next();
		sma.last();
		Assert.assertEquals(22.2327, sma.get(), ROUNDING_DELTA);
		// row 14
		dum.set(22.3816);
		sma.next();
		sma.last();
		Assert.assertEquals(22.2624, sma.get(), ROUNDING_DELTA);
		// row 15
		dum.set(22.6109);
		sma.next();
		sma.last();
		Assert.assertEquals(22.3061, sma.get(), ROUNDING_DELTA);
		// row 16
		dum.set(23.3558);
		sma.next();
		sma.last();
		Assert.assertEquals(22.4232, sma.get(), ROUNDING_DELTA);
		// row 17
		dum.set(24.0519);
		sma.next();
		sma.last();
		Assert.assertEquals(22.6150, sma.get(), ROUNDING_DELTA);
		// row 18
		dum.set(23.7530);
		sma.next();
		sma.last();
		Assert.assertEquals(22.7669, sma.get(), ROUNDING_DELTA);
		// row 19
		dum.set(23.8324);
		sma.next();
		sma.last();
		Assert.assertEquals(22.9069, sma.get(), ROUNDING_DELTA);
		// row 20
		dum.set(23.9516);
		sma.next();
		sma.last();
		Assert.assertEquals(23.0777, sma.get(), ROUNDING_DELTA);
		// row 21
		dum.set(23.6338);
		sma.next();
		sma.last();
		Assert.assertEquals(23.2118, sma.get(), ROUNDING_DELTA);
		// row 22
		dum.set(23.8225);
		sma.next();
		sma.last();
		Assert.assertEquals(23.3786, sma.get(), ROUNDING_DELTA);
		// row 23
		dum.set(23.8722);
		sma.next();
		sma.last();
		Assert.assertEquals(23.5266, sma.get(), ROUNDING_DELTA);
		// row 24
		dum.set(23.6537);
		sma.next();
		sma.last();
		Assert.assertEquals(23.6538, sma.get(), ROUNDING_DELTA);
		// row 25
		dum.set(23.1870);
		sma.next();
		sma.last();
		Assert.assertEquals(23.7114, sma.get(), ROUNDING_DELTA);
		// row 26
		dum.set(23.0976);
		sma.next();
		sma.last();
		Assert.assertEquals(23.6856, sma.get(), ROUNDING_DELTA);
		// row 27
		dum.set(23.3260);
		sma.next();
		sma.last();
		Assert.assertEquals(23.6130, sma.get(), ROUNDING_DELTA);
		// row 28
		dum.set(22.6805);
		sma.next();
		sma.last();
		Assert.assertEquals(23.5057, sma.get(), ROUNDING_DELTA);
		// row 29
		dum.set(23.0976);
		sma.next();
		sma.last();
		Assert.assertEquals(23.4323, sma.get(), ROUNDING_DELTA);
		// row 30
		dum.set(22.4025);
		sma.next();
		sma.last();
		Assert.assertEquals(23.2773, sma.get(), ROUNDING_DELTA);
		// row 31
		dum.set(22.1725);
		sma.next();
		sma.last();
		Assert.assertEquals(23.1312, sma.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIncrementalUpdate() {
		DummyFn dum = new DummyFn();
		Sma sma = new Sma(dum, 3);
		// a1 - before full-period, w/ change value
		dum.set(1713.3800);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		dum.set(1713.5800);
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// a2 - before full-period, w/o change value
		dum.set(1713.8000);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		dum.set(1713.8000);
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// a3 - after full-period, w/ change value
		dum.set(1713.2500);
		sma.next();
		sma.last();
		Assert.assertEquals(1713.5433, sma.get(), ROUNDING_DELTA);
		dum.set(1713.0500);
		sma.last();
		Assert.assertEquals(1713.4767, sma.get(), ROUNDING_DELTA);
		// a4 - after full-period, w/o change value
		dum.set(1712.0500);
		sma.next();
		sma.last();
		Assert.assertEquals(1712.9667, sma.get(), ROUNDING_DELTA);
		dum.set(1712.0500);
		sma.last();
		Assert.assertEquals(1712.9667, sma.get(), ROUNDING_DELTA);
		// a5 - successive new rows
		dum.set(1708.3600);
		sma.next();
		sma.last();
		Assert.assertEquals(1711.1533, sma.get(), ROUNDING_DELTA);
		// a6
		dum.set(1709.4200);
		sma.next();
		sma.last();
		Assert.assertEquals(1709.9433, sma.get(), ROUNDING_DELTA);
		// reset
		sma.reset();
		// b1
		dum.set(1707.7600);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// b2
		dum.set(1704.5400);
		sma.next();
		sma.last();
		Assert.assertEquals(Double.NaN, sma.get(), ROUNDING_DELTA);
		// b3
		dum.set(1703.9600);
		sma.next();
		sma.last();
		Assert.assertEquals(1705.4200, sma.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIndicatorStuff() {
		IndicatorAssert.checkIndicator(Sma.class);
	}
}