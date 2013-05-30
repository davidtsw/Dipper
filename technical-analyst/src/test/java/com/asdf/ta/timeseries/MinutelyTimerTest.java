package com.asdf.ta.timeseries;

import junit.framework.Assert;
import org.junit.Test;
import com.asdf.ta.workbook.TimeScale;

public class MinutelyTimerTest {

	private long MONDAY_NIGHT = 1300032000000l;
	private long ONE_MIN = 60000;
	private long min_1 = MONDAY_NIGHT;
	private long min_2 = min_1 + ONE_MIN;
	private long min_3 = min_2 + ONE_MIN;
	private long min_4 = min_3 + ONE_MIN;
	private long min_5 = min_4 + ONE_MIN;

	@Test
	public void testCurrIntervalStartTime() {
		MinutelyTimer m1 = new MinutelyTimer(TimeScale.MINUTE_1, 1);
		Assert.assertEquals(min_1, m1.currIntervalStartTime(min_1));
		Assert.assertEquals(min_1, m1.currIntervalStartTime(min_1 + 1));
		Assert.assertEquals(min_1, m1.currIntervalStartTime(min_2 - 1));
		Assert.assertEquals(min_2, m1.currIntervalStartTime(min_2));
		//
		MinutelyTimer m2 = new MinutelyTimer(TimeScale.MINUTE_2, 2);
		Assert.assertEquals(min_1, m2.currIntervalStartTime(min_1));
		Assert.assertEquals(min_1, m2.currIntervalStartTime(min_1 + 1));
		Assert.assertEquals(min_1, m2.currIntervalStartTime(min_3 - 1));
		Assert.assertEquals(min_3, m2.currIntervalStartTime(min_3));
	}
	@Test
	public void testNextIntervalStartTime() {
		MinutelyTimer m1 = new MinutelyTimer(TimeScale.MINUTE_1, 1);
		Assert.assertEquals(min_2, m1.nextIntervalStartTime(min_1));
		Assert.assertEquals(min_2, m1.nextIntervalStartTime(min_1 + 1));
		Assert.assertEquals(min_2, m1.nextIntervalStartTime(min_2 - 1));
		Assert.assertEquals(min_3, m1.nextIntervalStartTime(min_2));
		//
		MinutelyTimer m2 = new MinutelyTimer(TimeScale.MINUTE_2, 2);
		Assert.assertEquals(min_3, m2.nextIntervalStartTime(min_1));
		Assert.assertEquals(min_3, m2.nextIntervalStartTime(min_1 + 1));
		Assert.assertEquals(min_3, m2.nextIntervalStartTime(min_3 - 1));
		Assert.assertEquals(min_5, m2.nextIntervalStartTime(min_3));
	}
	@Test
	public void testGetScale() {
		MinutelyTimer m1 = new MinutelyTimer(TimeScale.MINUTE_1, 1);
		Assert.assertEquals(TimeScale.MINUTE_1, m1.getScale());
		//
		MinutelyTimer m2 = new MinutelyTimer(TimeScale.MINUTE_2, 2);
		Assert.assertEquals(TimeScale.MINUTE_2, m2.getScale());
	}
}