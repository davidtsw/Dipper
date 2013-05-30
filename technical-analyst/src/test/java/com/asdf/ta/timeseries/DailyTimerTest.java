package com.asdf.ta.timeseries;

import junit.framework.Assert;
import org.junit.Test;
import com.asdf.ta.workbook.TimeScale;

public class DailyTimerTest {
	private long MONDAY_NIGHT = 1300032000000l;
	private long ONE_DAY = 86400000l;
	private long day_1 = MONDAY_NIGHT;
	private long day_2 = day_1 + ONE_DAY;
	@Test
	public void testCurrIntervalStartTime() {
		DailyTimer iut = new DailyTimer();
		Assert.assertEquals(day_1, iut.currIntervalStartTime(day_1));
		Assert.assertEquals(day_1, iut.currIntervalStartTime(day_1 + 1));
		Assert.assertEquals(day_1, iut.currIntervalStartTime(day_2 - 1));
		Assert.assertEquals(day_2, iut.currIntervalStartTime(day_2));
	}
	@Test
	public void testNextIntervalStartTime() {
		DailyTimer iut = new DailyTimer();
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(day_1));
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(day_1 + 1));
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(day_2 - 1));
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(day_2));
	}
	@Test
	public void testGetScale() {
		DailyTimer iut = new DailyTimer();
		Assert.assertEquals(TimeScale.DAY_1, iut.getScale());
	}
}