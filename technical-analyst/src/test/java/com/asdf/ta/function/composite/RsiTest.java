package com.asdf.ta.function.composite;

import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.DummyFn;
import com.asdf.ta.function.FunctionTest;
import com.asdf.ta.workbook.fml.IndicatorAssert;

public class RsiTest implements FunctionTest {
	@Test
	public void testExample_1() {
		// ref - Technical Analysis From A to Z, Page XX
		DummyFn dum = new DummyFn();
		Rsi rsi = new Rsi(dum, 5);
		// row 1
		dum.set(Double.NaN);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 2
		dum.set(37.8750);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 3
		dum.set(39.5000);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 4
		dum.set(38.7500);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 5
		dum.set(39.8125);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 6
		dum.set(40.0000);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 7
		dum.set(39.8750);
		rsi.next();
		rsi.last();
		Assert.assertEquals(76.6667, rsi.get(), ROUNDING_DELTA);
		// row 8
		dum.set(40.1875);
		rsi.next();
		rsi.last();
		Assert.assertEquals(78.8679, rsi.get(), ROUNDING_DELTA);
		// row 9
		dum.set(41.2500);
		rsi.next();
		rsi.last();
		Assert.assertEquals(84.9158, rsi.get(), ROUNDING_DELTA);
		// row 10
		dum.set(41.1250);
		rsi.next();
		rsi.last();
		Assert.assertEquals(81.4863, rsi.get(), ROUNDING_DELTA);
		// row 11
		dum.set(41.6250);
		rsi.next();
		rsi.last();
		Assert.assertEquals(84.5968, rsi.get(), ROUNDING_DELTA);
		// row 12
		dum.set(41.2500);
		rsi.next();
		rsi.last();
		Assert.assertEquals(73.0851, rsi.get(), ROUNDING_DELTA);
		// row 13
		dum.set(40.1875);
		rsi.next();
		rsi.last();
		Assert.assertEquals(49.3173, rsi.get(), ROUNDING_DELTA);
		// row 14
		dum.set(39.9375);
		rsi.next();
		rsi.last();
		Assert.assertEquals(45.0119, rsi.get(), ROUNDING_DELTA);
		// row 15
		dum.set(39.9375);
		rsi.next();
		rsi.last();
		Assert.assertEquals(45.0119, rsi.get(), ROUNDING_DELTA);
		// row 16
		dum.set(40.5000);
		rsi.next();
		rsi.last();
		Assert.assertEquals(57.9252, rsi.get(), ROUNDING_DELTA);
		// row 17
		dum.set(41.9375);
		rsi.next();
		rsi.last();
		Assert.assertEquals(75.9596, rsi.get(), ROUNDING_DELTA);
		// row 18
		dum.set(42.2500);
		rsi.next();
		rsi.last();
		Assert.assertEquals(78.4676, rsi.get(), ROUNDING_DELTA);
		// row 19
		dum.set(42.2500);
		rsi.next();
		rsi.last();
		Assert.assertEquals(78.4676, rsi.get(), ROUNDING_DELTA);
		// row 20
		dum.set(41.8750);
		rsi.next();
		rsi.last();
		Assert.assertEquals(65.6299, rsi.get(), ROUNDING_DELTA);
		// row 21
		dum.set(41.8750);
		rsi.next();
		rsi.last();
		Assert.assertEquals(65.6299, rsi.get(), ROUNDING_DELTA);
	}
	@Test
	public void testExample_2() {
		// ref - ChartSchool,
		// http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:relative_strength_in
		DummyFn dum = new DummyFn();
		Rsi rsi = new Rsi(dum, 14);
		// row 1
		dum.set(Double.NaN);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 2
		dum.set(44.3389);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 3
		dum.set(44.0902);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 4
		dum.set(44.1497);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 5
		dum.set(43.6124);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 6
		dum.set(44.3278);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 7
		dum.set(44.8264);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 8
		dum.set(45.0955);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 9
		dum.set(45.4245);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 10
		dum.set(45.8433);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 11
		dum.set(46.0826);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 12
		dum.set(45.8931);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 13
		dum.set(46.0328);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 14
		dum.set(45.6140);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 15
		dum.set(46.2820);
		rsi.next();
		rsi.last();
		Assert.assertEquals(Double.NaN, rsi.get(), ROUNDING_DELTA);
		// row 16
		dum.set(46.2820);
		rsi.next();
		rsi.last();
		Assert.assertEquals(70.5328, rsi.get(), ROUNDING_DELTA);
		// row 17
		dum.set(46.0028);
		rsi.next();
		rsi.last();
		Assert.assertEquals(66.3186, rsi.get(), ROUNDING_DELTA);
		// row 18
		dum.set(46.0328);
		rsi.next();
		rsi.last();
		Assert.assertEquals(66.5498, rsi.get(), ROUNDING_DELTA);
		// row 19
		dum.set(46.4116);
		rsi.next();
		rsi.last();
		Assert.assertEquals(69.4063, rsi.get(), ROUNDING_DELTA);
		// row 20
		dum.set(46.2222);
		rsi.next();
		rsi.last();
		Assert.assertEquals(66.3552, rsi.get(), ROUNDING_DELTA);
		// row 21
		dum.set(45.6439);
		rsi.next();
		rsi.last();
		Assert.assertEquals(57.9749, rsi.get(), ROUNDING_DELTA);
		// row 22
		dum.set(46.2122);
		rsi.next();
		rsi.last();
		Assert.assertEquals(62.9296, rsi.get(), ROUNDING_DELTA);
		// row 23
		dum.set(46.2521);
		rsi.next();
		rsi.last();
		Assert.assertEquals(63.2571, rsi.get(), ROUNDING_DELTA);
		// row 24
		dum.set(45.7137);
		rsi.next();
		rsi.last();
		Assert.assertEquals(56.0593, rsi.get(), ROUNDING_DELTA);
		// row 25
		dum.set(46.4515);
		rsi.next();
		rsi.last();
		Assert.assertEquals(62.3771, rsi.get(), ROUNDING_DELTA);
		// row 26
		dum.set(45.7835);
		rsi.next();
		rsi.last();
		Assert.assertEquals(54.7076, rsi.get(), ROUNDING_DELTA);
		// row 27
		dum.set(45.3548);
		rsi.next();
		rsi.last();
		Assert.assertEquals(50.4228, rsi.get(), ROUNDING_DELTA);
		// row 28
		dum.set(44.0288);
		rsi.next();
		rsi.last();
		Assert.assertEquals(39.9898, rsi.get(), ROUNDING_DELTA);
		// row 29
		dum.set(44.1783);
		rsi.next();
		rsi.last();
		Assert.assertEquals(41.4605, rsi.get(), ROUNDING_DELTA);
		// row 30
		dum.set(44.2181);
		rsi.next();
		rsi.last();
		Assert.assertEquals(41.8689, rsi.get(), ROUNDING_DELTA);
		// row 31
		dum.set(44.5672);
		rsi.next();
		rsi.last();
		Assert.assertEquals(45.4632, rsi.get(), ROUNDING_DELTA);
		// row 32
		dum.set(43.4205);
		rsi.next();
		rsi.last();
		Assert.assertEquals(37.3040, rsi.get(), ROUNDING_DELTA);
		// row 33
		dum.set(42.6628);
		rsi.next();
		rsi.last();
		Assert.assertEquals(33.0795, rsi.get(), ROUNDING_DELTA);
		// row 34
		dum.set(43.1314);
		rsi.next();
		rsi.last();
		Assert.assertEquals(37.7730, rsi.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIndicatorStuff() {
		IndicatorAssert.checkIndicator(Rsi.class);
	}
}