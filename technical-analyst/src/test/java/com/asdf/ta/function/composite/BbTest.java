package com.asdf.ta.function.composite;

import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.DummyFn;
import com.asdf.ta.function.FunctionTest;
import com.asdf.ta.function.composite.Bb;
import com.asdf.ta.workbook.fml.IndicatorAssert;

public class BbTest implements FunctionTest {
	@Test
	public void testExample_1() {
		// ref - Technical Analysis From A to Z, Page XX
		DummyFn dum = new DummyFn();
		Bb bb = new Bb(dum, 5, 20);
		// row 1
		dum.set(Double.NaN);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 2
		dum.set(31.8750);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 3
		dum.set(32.1250);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 4
		dum.set(32.3125);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 5
		dum.set(32.1250);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 6
		dum.set(31.8750);
		bb.next();
		bb.last();
		Assert.assertEquals(32.3979, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(31.7271, bb.get(2), ROUNDING_DELTA);
		// row 7
		dum.set(32.3125);
		bb.next();
		bb.last();
		Assert.assertEquals(32.4721, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(31.8279, bb.get(2), ROUNDING_DELTA);
		// row 8
		dum.set(32.2500);
		bb.next();
		bb.last();
		Assert.assertEquals(32.5048, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(31.8452, bb.get(2), ROUNDING_DELTA);
		// row 9
		dum.set(32.4375);
		bb.next();
		bb.last();
		Assert.assertEquals(32.5824, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(31.8176, bb.get(2), ROUNDING_DELTA);
		// row 10
		dum.set(32.8125);
		bb.next();
		bb.last();
		Assert.assertEquals(32.9427, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(31.7323, bb.get(2), ROUNDING_DELTA);
		// row 11
		dum.set(32.3750);
		bb.next();
		bb.last();
		Assert.assertEquals(32.8328, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.0422, bb.get(2), ROUNDING_DELTA);
		// row 12
		dum.set(32.5000);
		bb.next();
		bb.last();
		Assert.assertEquals(32.8508, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.0992, bb.get(2), ROUNDING_DELTA);
		// row 13
		dum.set(32.4375);
		bb.next();
		bb.last();
		Assert.assertEquals(32.8227, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.2023, bb.get(2), ROUNDING_DELTA);
		// row 14
		dum.set(32.7500);
		bb.next();
		bb.last();
		Assert.assertEquals(32.9232, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.2268, bb.get(2), ROUNDING_DELTA);
		// row 15
		dum.set(33.1875);
		bb.next();
		bb.last();
		Assert.assertEquals(33.2448, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.0552, bb.get(2), ROUNDING_DELTA);
		// row 16
		dum.set(33.0625);
		bb.next();
		bb.last();
		Assert.assertEquals(33.3823, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.1927, bb.get(2), ROUNDING_DELTA);
		// row 17
		dum.set(33.0625);
		bb.next();
		bb.last();
		Assert.assertEquals(33.4454, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.3546, bb.get(2), ROUNDING_DELTA);
		// row 18
		dum.set(33.1250);
		bb.next();
		bb.last();
		Assert.assertEquals(33.3396, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.7354, bb.get(2), ROUNDING_DELTA);
		// row 19
		dum.set(33.0625);
		bb.next();
		bb.last();
		Assert.assertEquals(33.2000, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(33.0000, bb.get(2), ROUNDING_DELTA);
		// row 20
		dum.set(32.8125);
		bb.next();
		bb.last();
		Assert.assertEquals(33.2429, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.8071, bb.get(2), ROUNDING_DELTA);
		// row 21
		dum.set(32.8750);
		bb.next();
		bb.last();
		Assert.assertEquals(33.2299, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.7451, bb.get(2), ROUNDING_DELTA);
		// row 22
		dum.set(33.2500);
		bb.next();
		bb.last();
		Assert.assertEquals(33.3471, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.7029, bb.get(2), ROUNDING_DELTA);
		// row 23
		dum.set(33.1250);
		bb.next();
		bb.last();
		Assert.assertEquals(33.3471, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(32.7029, bb.get(2), ROUNDING_DELTA);
	}
	@Test
	public void testExample_2() {
		// ref - ChartSchool,
		// http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:bollinger_bands
		DummyFn dum = new DummyFn();
		Bb bb = new Bb(dum, 20, 20);
		// row 1
		dum.set(Double.NaN);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 2
		dum.set(86.1557);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 3
		dum.set(89.0867);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 4
		dum.set(88.7829);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 5
		dum.set(90.3228);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 6
		dum.set(89.0671);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 7
		dum.set(91.1453);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 8
		dum.set(89.4397);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 9
		dum.set(89.1750);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 10
		dum.set(86.9302);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 11
		dum.set(87.6752);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 12
		dum.set(86.9596);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 13
		dum.set(89.4299);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 14
		dum.set(89.3221);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 15
		dum.set(88.7241);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 16
		dum.set(87.4497);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 17
		dum.set(87.2634);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 18
		dum.set(89.4985);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 19
		dum.set(87.9006);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 20
		dum.set(89.1260);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// row 21
		dum.set(90.7043);
		bb.next();
		bb.last();
		Assert.assertEquals(88.7079, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(91.2919, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(86.1240, bb.get(2), ROUNDING_DELTA);
		// row 22
		dum.set(92.9001);
		bb.next();
		bb.last();
		Assert.assertEquals(89.0452, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(91.9493, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(86.1411, bb.get(2), ROUNDING_DELTA);
		// row 23
		dum.set(92.9784);
		bb.next();
		bb.last();
		Assert.assertEquals(89.2397, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(92.6126, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(85.8669, bb.get(2), ROUNDING_DELTA);
		// row 24
		dum.set(91.8021);
		bb.next();
		bb.last();
		Assert.assertEquals(89.3907, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(92.9342, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(85.8472, bb.get(2), ROUNDING_DELTA);
		// row 25
		dum.set(92.6647);
		bb.next();
		bb.last();
		Assert.assertEquals(89.5078, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(93.3119, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(85.7037, bb.get(2), ROUNDING_DELTA);
		// row 26
		dum.set(92.6843);
		bb.next();
		bb.last();
		Assert.assertEquals(89.6887, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(93.7284, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(85.6489, bb.get(2), ROUNDING_DELTA);
		// row 27
		dum.set(92.3021);
		bb.next();
		bb.last();
		Assert.assertEquals(89.7465, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(93.8996, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(85.5934, bb.get(2), ROUNDING_DELTA);
		// row 28
		dum.set(92.7725);
		bb.next();
		bb.last();
		Assert.assertEquals(89.9131, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.2663, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(85.5600, bb.get(2), ROUNDING_DELTA);
		// row 29
		dum.set(92.5373);
		bb.next();
		bb.last();
		Assert.assertEquals(90.0813, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.5651, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(85.5974, bb.get(2), ROUNDING_DELTA);
		// row 30
		dum.set(92.9490);
		bb.next();
		bb.last();
		Assert.assertEquals(90.3822, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.7869, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(85.9775, bb.get(2), ROUNDING_DELTA);
		// row 31
		dum.set(93.2039);
		bb.next();
		bb.last();
		Assert.assertEquals(90.6586, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(95.0430, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(86.2743, bb.get(2), ROUNDING_DELTA);
		// row 32
		dum.set(91.0669);
		bb.next();
		bb.last();
		Assert.assertEquals(90.8640, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.9076, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(86.8204, bb.get(2), ROUNDING_DELTA);
		// row 33
		dum.set(89.8318);
		bb.next();
		bb.last();
		Assert.assertEquals(90.8841, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.9029, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(86.8653, bb.get(2), ROUNDING_DELTA);
		// row 34
		dum.set(89.7435);
		bb.next();
		bb.last();
		Assert.assertEquals(90.9052, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.8953, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(86.9150, bb.get(2), ROUNDING_DELTA);
		// row 35
		dum.set(90.3994);
		bb.next();
		bb.last();
		Assert.assertEquals(90.9889, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.8610, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(87.1168, bb.get(2), ROUNDING_DELTA);
		// row 36
		dum.set(90.7387);
		bb.next();
		bb.last();
		Assert.assertEquals(91.1534, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.6736, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(87.6331, bb.get(2), ROUNDING_DELTA);
		// row 37
		dum.set(88.0177);
		bb.next();
		bb.last();
		Assert.assertEquals(91.1911, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.5566, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(87.8256, bb.get(2), ROUNDING_DELTA);
		// row 38
		dum.set(88.0867);
		bb.next();
		bb.last();
		Assert.assertEquals(91.1205, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.6788, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(87.5622, bb.get(2), ROUNDING_DELTA);
		// row 39
		dum.set(88.8439);
		bb.next();
		bb.last();
		Assert.assertEquals(91.1677, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.5758, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(87.7595, bb.get(2), ROUNDING_DELTA);
		// row 40
		dum.set(90.7781);
		bb.next();
		bb.last();
		Assert.assertEquals(91.2503, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.5343, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(87.9663, bb.get(2), ROUNDING_DELTA);
		// row 41
		dum.set(90.5416);
		bb.next();
		bb.last();
		Assert.assertEquals(91.2421, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.5323, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(87.9520, bb.get(2), ROUNDING_DELTA);
		// row 42
		dum.set(91.3894);
		bb.next();
		bb.last();
		Assert.assertEquals(91.1666, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.3693, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(87.9639, bb.get(2), ROUNDING_DELTA);
		// row 43
		dum.set(90.6500);
		bb.next();
		bb.last();
		Assert.assertEquals(91.0502, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(94.1485, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(87.9519, bb.get(2), ROUNDING_DELTA);
	}
	@Test
	public void testIncrementalUpdate() {
		DummyFn dum = new DummyFn();
		Bb bb = new Bb(dum, 3, 10);
		// a1 - before full-period, w/ change value
		dum.set(1713.3800);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		dum.set(1713.5800);
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// a2 - before full-period, w/o change value
		dum.set(1713.8000);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		dum.set(1713.8000);
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// a3 - after full-period, w/ change value
		dum.set(1713.2500);
		bb.next();
		bb.last();
		Assert.assertEquals(1713.5433, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(1713.7694, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(1713.3173, bb.get(2), ROUNDING_DELTA);
		dum.set(1713.0500);
		bb.last();
		Assert.assertEquals(1713.4767, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(1713.7915, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(1713.1619, bb.get(2), ROUNDING_DELTA);
		// a4 - after full-period, w/o change value
		dum.set(1712.0500);
		bb.next();
		bb.last();
		Assert.assertEquals(1712.9667, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(1713.6835, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(1712.2498, bb.get(2), ROUNDING_DELTA);
		dum.set(1712.0500);
		bb.last();
		Assert.assertEquals(1712.9667, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(1713.6835, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(1712.2498, bb.get(2), ROUNDING_DELTA);
		// a5 - successive new rows
		dum.set(1708.3600);
		bb.next();
		bb.last();
		Assert.assertEquals(1711.1533, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(1713.1703, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(1709.1364, bb.get(2), ROUNDING_DELTA);
		// a6
		dum.set(1709.4200);
		bb.next();
		bb.last();
		Assert.assertEquals(1709.9433, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(1711.4946, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(1708.3921, bb.get(2), ROUNDING_DELTA);
		// reset
		bb.reset();
		// b1
		dum.set(1707.7600);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// b2
		dum.set(1704.5400);
		bb.next();
		bb.last();
		Assert.assertEquals(Double.NaN, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, bb.get(2), ROUNDING_DELTA);
		// b3
		dum.set(1703.9600);
		bb.next();
		bb.last();
		Assert.assertEquals(1705.4200, bb.get(), ROUNDING_DELTA);
		Assert.assertEquals(1707.0915, bb.get(1), ROUNDING_DELTA);
		Assert.assertEquals(1703.7485, bb.get(2), ROUNDING_DELTA);
	}
	@Test
	public void testIndicatorStuff() {
		IndicatorAssert.checkIndicator(Bb.class);
	}
}