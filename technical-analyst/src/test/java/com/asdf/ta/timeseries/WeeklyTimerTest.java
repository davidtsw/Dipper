package com.asdf.ta.timeseries;

import junit.framework.Assert;
import org.junit.Test;
import com.asdf.ta.workbook.TimeScale;

public class WeeklyTimerTest {
	private long MONDAY_NIGHT = 1300032000000l;
	private long ONE_WEEK = 604800000l;
	private long week_1 = MONDAY_NIGHT;
	private long week_2 = week_1 + ONE_WEEK;
	@Test
	public void testCurrIntervalStartTime() {
		WeeklyTimer iut = new WeeklyTimer();
		Assert.assertEquals(week_1, iut.currIntervalStartTime(week_1));
		Assert.assertEquals(week_1, iut.currIntervalStartTime(week_1 + 1));
		Assert.assertEquals(week_1, iut.currIntervalStartTime(week_2 - 1));
		Assert.assertEquals(week_2, iut.currIntervalStartTime(week_2));
		// TODO test Month-End
	}
	@Test
	public void testNextIntervalStartTime() {
		WeeklyTimer iut = new WeeklyTimer();
		long week_1 = MONDAY_NIGHT;
		long week_2 = week_1 + ONE_WEEK;
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(week_1));
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(week_1 + 1));
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(week_2 - 1));
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(week_2));
	}
	@Test
	public void testGetScale() {
		WeeklyTimer iut = new WeeklyTimer();
		Assert.assertEquals(TimeScale.WEEK_1, iut.getScale());
	}
}