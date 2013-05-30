package com.asdf.ta.timeseries;

import junit.framework.Assert;
import org.junit.Test;
import com.asdf.ta.workbook.TimeScale;

public class HourlyTimerTest {
	private long MONDAY_NIGHT = 1300032000000l;
	private long ONE_HOUR = 3600000l;
	private long hr_1 = MONDAY_NIGHT;
	private long hr_2 = hr_1 + ONE_HOUR;
	private long hr_3 = hr_2 + ONE_HOUR;
	private long hr_4 = hr_3 + ONE_HOUR;
	private long hr_5 = hr_4 + ONE_HOUR;

	@Test
	public void testCurrIntervalStartTime() {
		HourlyTimer h1 = new HourlyTimer(TimeScale.HOUR_1, 1);
		Assert.assertEquals(hr_1, h1.currIntervalStartTime(hr_1));
		Assert.assertEquals(hr_1, h1.currIntervalStartTime(hr_1 + 1));
		Assert.assertEquals(hr_1, h1.currIntervalStartTime(hr_2 - 1));
		Assert.assertEquals(hr_2, h1.currIntervalStartTime(hr_2));
		//
		HourlyTimer h2 = new HourlyTimer(TimeScale.HOUR_1, 2);
		Assert.assertEquals(hr_1, h2.currIntervalStartTime(hr_1));
		Assert.assertEquals(hr_1, h2.currIntervalStartTime(hr_1 + 1));
		Assert.assertEquals(hr_1, h2.currIntervalStartTime(hr_3 - 1));
		Assert.assertEquals(hr_3, h2.currIntervalStartTime(hr_3));
		// TODO test PM
	}
	@Test
	public void testNextIntervalStartTime() {
		HourlyTimer h1 = new HourlyTimer(TimeScale.HOUR_2, 1);
		Assert.assertEquals(hr_2, h1.nextIntervalStartTime(hr_1));
		Assert.assertEquals(hr_2, h1.nextIntervalStartTime(hr_1 + 1));
		Assert.assertEquals(hr_2, h1.nextIntervalStartTime(hr_2 - 1));
		Assert.assertEquals(hr_3, h1.nextIntervalStartTime(hr_2));
		//
		HourlyTimer h2 = new HourlyTimer(TimeScale.HOUR_2, 2);
		Assert.assertEquals(hr_3, h2.nextIntervalStartTime(hr_1));
		Assert.assertEquals(hr_3, h2.nextIntervalStartTime(hr_1 + 1));
		Assert.assertEquals(hr_3, h2.nextIntervalStartTime(hr_3 - 1));
		Assert.assertEquals(hr_5, h2.nextIntervalStartTime(hr_3));
	}
	@Test
	public void testGetScale() {
		HourlyTimer h1 = new HourlyTimer(TimeScale.HOUR_1, 1);
		Assert.assertEquals(TimeScale.HOUR_1, h1.getScale());
		//
		HourlyTimer h2 = new HourlyTimer(TimeScale.HOUR_2, 2);
		Assert.assertEquals(TimeScale.HOUR_2, h2.getScale());
	}
}