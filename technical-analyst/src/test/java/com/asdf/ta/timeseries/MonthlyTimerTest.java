package com.asdf.ta.timeseries;

import java.util.Calendar;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import com.asdf.ta.workbook.TimeScale;

public class MonthlyTimerTest {
	private long MONTH_START = 1301587200000l; // 2011-Apr-01 (Fri) 00:00:00
	private long month_1, month_2;
	@Before
	public void before() {
		month_1 = MONTH_START;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(month_1);
		cal.add(Calendar.MONDAY, 1);
		month_2 = cal.getTimeInMillis();
	}
	@Test
	public void testCurrIntervalStartTime() {
		MonthlyTimer iut = new MonthlyTimer();
		Assert.assertEquals(month_1, iut.currIntervalStartTime(month_1));
		Assert.assertEquals(month_1, iut.currIntervalStartTime(month_1 + 1));
		Assert.assertEquals(month_1, iut.currIntervalStartTime(month_2 - 1));
		Assert.assertEquals(month_2, iut.currIntervalStartTime(month_2));
	}
	@Test
	public void testNextIntervalStartTime() {
		MonthlyTimer iut = new MonthlyTimer();
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(month_1));
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(month_1 + 1));
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(month_2 - 1));
		Assert.assertEquals(Long.MAX_VALUE, iut.nextIntervalStartTime(month_2));
	}
	@Test
	public void testGetScale() {
		MonthlyTimer iut = new MonthlyTimer();
		Assert.assertEquals(TimeScale.MONTH_1, iut.getScale());
	}
}