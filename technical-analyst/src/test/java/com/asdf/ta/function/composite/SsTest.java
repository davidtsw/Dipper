package com.asdf.ta.function.composite;

import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.DummyFn;
import com.asdf.ta.function.FunctionTest;
import com.asdf.ta.workbook.fml.IndicatorAssert;

public class SsTest implements FunctionTest {
	@Test
	public void testExample_1() {
		// ref - Technical Analysis From A to Z, Page XX
		DummyFn dH = new DummyFn();
		DummyFn dL = new DummyFn();
		DummyFn dC = new DummyFn();
		Ss ss = new Ss(dC, dH, dL, 5, 3, 3);
		// row 1
		dH.set(Double.NaN);
		dL.set(Double.NaN);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, ss.get(1), ROUNDING_DELTA);
		// row 2
		dH.set(34.3750);
		dL.set(33.5312);
		dC.set(34.3125);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, ss.get(1), ROUNDING_DELTA);
		// row 3
		dH.set(34.7500);
		dL.set(33.9062);
		dC.set(34.1250);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, ss.get(1), ROUNDING_DELTA);
		// row 4
		dH.set(34.2188);
		dL.set(33.6875);
		dC.set(33.7500);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, ss.get(1), ROUNDING_DELTA);
		// row 5
		dH.set(33.8281);
		dL.set(33.2500);
		dC.set(33.6406);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, ss.get(1), ROUNDING_DELTA);
		// row 6
		dH.set(33.4375);
		dL.set(33.0000);
		dC.set(33.0156);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, ss.get(1), ROUNDING_DELTA);
		// row 7
		dH.set(33.4688);
		dL.set(32.9375);
		dC.set(33.0469);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, ss.get(1), ROUNDING_DELTA);
		// row 8
		dH.set(34.3750);
		dL.set(33.2500);
		dC.set(34.2969);
		ss.next();
		ss.last();
		Assert.assertEquals(29.6880, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, ss.get(1), ROUNDING_DELTA);
		// row 9
		dH.set(34.7188);
		dL.set(34.0469);
		dC.set(34.1406);
		ss.next();
		ss.last();
		Assert.assertEquals(53.1056, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(Double.NaN, ss.get(1), ROUNDING_DELTA);
		// row 10
		dH.set(34.6250);
		dL.set(33.9375);
		dC.set(34.5469);
		ss.next();
		ss.last();
		Assert.assertEquals(83.4363, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(55.4100, ss.get(1), ROUNDING_DELTA);
		// row 11
		dH.set(34.9219);
		dL.set(34.0625);
		dC.set(34.3281);
		ss.next();
		ss.last();
		Assert.assertEquals(75.7725, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(70.7715, ss.get(1), ROUNDING_DELTA);
		// row 12
		dH.set(34.9531);
		dL.set(34.4375);
		dC.set(34.8281);
		ss.next();
		ss.last();
		Assert.assertEquals(83.7131, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(80.9740, ss.get(1), ROUNDING_DELTA);
		// row 13
		dH.set(35.0625);
		dL.set(34.5938);
		dC.set(34.8750);
		ss.next();
		ss.last();
		Assert.assertEquals(81.1678, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(80.2178, ss.get(1), ROUNDING_DELTA);
		// row 14
		dH.set(34.7812);
		dL.set(33.7656);
		dC.set(33.7812);
		ss.next();
		ss.last();
		Assert.assertEquals(61.3624, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(75.4144, ss.get(1), ROUNDING_DELTA);
		// row 15
		dH.set(34.3438);
		dL.set(33.2188);
		dC.set(34.2031);
		ss.next();
		ss.last();
		Assert.assertEquals(45.4192, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(62.6498, ss.get(1), ROUNDING_DELTA);
		// row 16
		dH.set(34.5938);
		dL.set(33.9062);
		dC.set(34.4844);
		ss.next();
		ss.last();
		Assert.assertEquals(45.4527, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(50.7448, ss.get(1), ROUNDING_DELTA);
		// row 17
		dH.set(34.3125);
		dL.set(32.6562);
		dC.set(32.6719);
		ss.next();
		ss.last();
		Assert.assertEquals(37.1794, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(42.6838, ss.get(1), ROUNDING_DELTA);
		// row 18
		dH.set(34.2500);
		dL.set(32.7500);
		dC.set(34.0938);
		ss.next();
		ss.last();
		Assert.assertEquals(42.6494, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(41.7605, ss.get(1), ROUNDING_DELTA);
		// row 19
		dH.set(34.1875);
		dL.set(33.1562);
		dC.set(33.2969);
		ss.next();
		ss.last();
		Assert.assertEquals(32.3703, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(37.3997, ss.get(1), ROUNDING_DELTA);
		// row 20
		dH.set(33.7812);
		dL.set(32.8594);
		dC.set(33.0625);
		ss.next();
		ss.last();
		Assert.assertEquals(41.4086, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(38.8094, ss.get(1), ROUNDING_DELTA);
		// row 21
		dH.set(33.8125);
		dL.set(33.0000);
		dC.set(33.7969);
		ss.next();
		ss.last();
		Assert.assertEquals(39.5499, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(37.7762, ss.get(1), ROUNDING_DELTA);
		// row 22
		dH.set(33.9688);
		dL.set(33.2969);
		dC.set(33.3281);
		ss.next();
		ss.last();
		Assert.assertEquals(41.7185, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(40.8923, ss.get(1), ROUNDING_DELTA);
		// row 23
		dH.set(33.8750);
		dL.set(33.2812);
		dC.set(33.8750);
		ss.next();
		ss.last();
		Assert.assertEquals(60.9758, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(47.4147, ss.get(1), ROUNDING_DELTA);
		// row 24
		dH.set(34.0156);
		dL.set(33.0312);
		dC.set(33.1094);
		ss.next();
		ss.last();
		Assert.assertEquals(46.2741, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(49.6562, ss.get(1), ROUNDING_DELTA);
		// row 25
		dH.set(33.5312);
		dL.set(33.0156);
		dC.set(33.1875);
		ss.next();
		ss.last();
		Assert.assertEquals(41.5183, ss.get(), ROUNDING_DELTA);
		Assert.assertEquals(49.5894, ss.get(1), ROUNDING_DELTA);
	}
	@Test
	public void testExample_2() {
		// ref - ChartSchool,
		// http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:stochastic_oscillato
		DummyFn dH = new DummyFn();
		DummyFn dL = new DummyFn();
		DummyFn dC = new DummyFn();
		Ss ss = new Ss(dC, dH, dL, 14, 1, 1);
		// row 1
		dH.set(Double.NaN);
		dL.set(Double.NaN);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 2
		dH.set(127.0090);
		dL.set(125.3574);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 3
		dH.set(127.6159);
		dL.set(126.1633);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 4
		dH.set(126.5911);
		dL.set(124.9296);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 5
		dH.set(127.3472);
		dL.set(126.0937);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 6
		dH.set(128.1730);
		dL.set(126.8199);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 7
		dH.set(128.4317);
		dL.set(126.4817);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 8
		dH.set(127.3671);
		dL.set(126.0340);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 9
		dH.set(126.4220);
		dL.set(124.8301);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 10
		dH.set(126.8995);
		dL.set(126.3921);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 11
		dH.set(126.8498);
		dL.set(125.7156);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 12
		dH.set(125.6460);
		dL.set(124.5615);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 13
		dH.set(125.7156);
		dL.set(124.5715);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 14
		dH.set(127.1582);
		dL.set(125.0689);
		dC.set(Double.NaN);
		ss.next();
		ss.last();
		Assert.assertEquals(Double.NaN, ss.get(), ROUNDING_DELTA);
		// row 15
		dH.set(127.7154);
		dL.set(126.8597);
		dC.set(127.2876);
		ss.next();
		ss.last();
		Assert.assertEquals(70.4382, ss.get(), ROUNDING_DELTA);
		// row 16
		dH.set(127.6855);
		dL.set(126.6309);
		dC.set(127.1781);
		ss.next();
		ss.last();
		Assert.assertEquals(67.6089, ss.get(), ROUNDING_DELTA);
		// row 17
		dH.set(128.2228);
		dL.set(126.8001);
		dC.set(128.0138);
		ss.next();
		ss.last();
		Assert.assertEquals(89.2021, ss.get(), ROUNDING_DELTA);
		// row 18
		dH.set(128.2725);
		dL.set(126.7105);
		dC.set(127.1085);
		ss.next();
		ss.last();
		Assert.assertEquals(65.8106, ss.get(), ROUNDING_DELTA);
		// row 19
		dH.set(128.0934);
		dL.set(126.8001);
		dC.set(127.7253);
		ss.next();
		ss.last();
		Assert.assertEquals(81.7477, ss.get(), ROUNDING_DELTA);
		// row 20
		dH.set(128.2725);
		dL.set(126.1335);
		dC.set(127.0587);
		ss.next();
		ss.last();
		Assert.assertEquals(64.5238, ss.get(), ROUNDING_DELTA);
		// row 21
		dH.set(127.7353);
		dL.set(125.9245);
		dC.set(127.3273);
		ss.next();
		ss.last();
		Assert.assertEquals(74.5298, ss.get(), ROUNDING_DELTA);
		// row 22
		dH.set(128.7700);
		dL.set(126.9891);
		dC.set(128.7103);
		ss.next();
		ss.last();
		Assert.assertEquals(98.5814, ss.get(), ROUNDING_DELTA);
		// row 23
		dH.set(129.2873);
		dL.set(127.8148);
		dC.set(127.8745);
		ss.next();
		ss.last();
		Assert.assertEquals(70.1045, ss.get(), ROUNDING_DELTA);
		// row 24
		dH.set(130.0633);
		dL.set(128.4715);
		dC.set(128.5809);
		ss.next();
		ss.last();
		Assert.assertEquals(73.0561, ss.get(), ROUNDING_DELTA);
		// row 25
		dH.set(129.1182);
		dL.set(128.0641);
		dC.set(128.6008);
		ss.next();
		ss.last();
		Assert.assertEquals(73.4178, ss.get(), ROUNDING_DELTA);
		// row 26
		dH.set(129.2873);
		dL.set(127.6059);
		dC.set(127.9342);
		ss.next();
		ss.last();
		Assert.assertEquals(61.2313, ss.get(), ROUNDING_DELTA);
		// row 27
		dH.set(128.4715);
		dL.set(127.5960);
		dC.set(128.1133);
		ss.next();
		ss.last();
		Assert.assertEquals(60.9563, ss.get(), ROUNDING_DELTA);
		// row 28
		dH.set(128.0934);
		dL.set(126.9990);
		dC.set(127.5960);
		ss.next();
		ss.last();
		Assert.assertEquals(40.3861, ss.get(), ROUNDING_DELTA);
		// row 29
		dH.set(128.6506);
		dL.set(126.8995);
		dC.set(127.5960);
		ss.next();
		ss.last();
		Assert.assertEquals(40.3861, ss.get(), ROUNDING_DELTA);
		// row 30
		dH.set(129.1381);
		dL.set(127.4865);
		dC.set(128.6904);
		ss.next();
		ss.last();
		Assert.assertEquals(66.8285, ss.get(), ROUNDING_DELTA);
		// row 31
		dH.set(128.6406);
		dL.set(127.3970);
		dC.set(128.2725);
		ss.next();
		ss.last();
		Assert.assertEquals(56.7314, ss.get(), ROUNDING_DELTA);
	}
	@Test
	public void testIndicatorStuff() {
		IndicatorAssert.checkIndicator(Ss.class);
	}
}