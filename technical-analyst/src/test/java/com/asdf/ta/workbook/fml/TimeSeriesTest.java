package com.asdf.ta.workbook.fml;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import com.asdf.service.PriceService;
import com.asdf.ta.function.TimeSeriesFn;
import com.asdf.ta.timeseries.Timer;
import com.asdf.ta.workbook.ColumnT;
import com.asdf.ta.workbook.TimeScale;
import com.asdf.ta.workbook.WorkSheetT;
import com.asdf.ta.workbook.impl.TimeSheet;

public class TimeSeriesTest {
	private long MID_NIGHT = 1300032000000l;
	private long ONE_MINUTE = 60000l;
	private long ONE_DAY = 86400000l;
	@Test
	public void testRealTimeTickUpdate() {
		TimeSheet tick = new TimeSheet("tick", "dummy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		Timer m1 = TimeScale.MINUTE_1.getTimer();

		PriceService ps = mock(PriceService.class);
		TimeSheet own = mock(TimeSheet.class);
		TimeSeriesFn fn = mock(TimeSeriesFn.class);
		TimeSeries ts = new TimeSeries(m1, own, fn, ps, tick, tick.getTemporalColumn(), tick.getColumn());
		tick.addListener(ts);
		when(own.getName()).thenReturn("xxx");

		// first tick
		saveTick(tick, MID_NIGHT + 11, 1.234, 1.236, 1);
		verify(own).onReload();
		verify(ps).RequestHistoricalBarData(eq(ts), eq("xxx"), eq(TimeScale.MINUTE_1));
		// tick update while loading
		reset(ps);
		saveTick(tick, MID_NIGHT + 12, 2.234, 2.236, 2);

		verify(ps, never()).RequestHistoricalBarData((TimeSeries) any(), (String) any(), (TimeScale) any());
		// until historical data is back
		ts.onHistoricalBarReady(new long[] {}, new double[] {}, new double[] {}, new double[] {}, new double[] {});
		reset(own);
		reset(fn);
		when(fn.getAllTime()).thenReturn(new long[] { 13 });
		when(fn.getAll()).thenReturn(new double[] { 1d, 2d, 3d, 4d });
		saveTick(tick, MID_NIGHT + 13, 3.234, 3.236, 3);
		//
		verify(fn).last(MID_NIGHT + 13, 3.234);
		verify(own).save(new long[] { 13 }, new double[] { 1d, 2d, 3d, 4d });
		verify(own).onUpdate();
		// tick update again
		reset(own);
		reset(fn);
		when(fn.getAllTime()).thenReturn(new long[] { 14 });
		when(fn.getAll()).thenReturn(new double[] { 5d, 6d, 7d, 8d });
		saveTick(tick, MID_NIGHT + 14, 4.234, 4.236, 4);
		//
		verify(fn).last(MID_NIGHT + 14, 4.234);
		verify(own).save(new long[] { 14 }, new double[] { 5d, 6d, 7d, 8d });
		verify(own).onUpdate();
		// next time interval
		reset(own);
		reset(fn);
		when(fn.getAllTime()).thenReturn(new long[] { 15 });
		when(fn.getAll()).thenReturn(new double[] { 9d, 10d, 11d, 12d });
		saveTick(tick, MID_NIGHT + 60015, 5.234, 5.236, 5);
		//
		verify(fn).next(MID_NIGHT + 60000);
		verify(fn).last(MID_NIGHT + 60015, 5.234);
		verify(own).nextRow();
		verify(own).save(new long[] { 15 }, new double[] { 9d, 10d, 11d, 12d });
		verify(own).onUpdate();
	}
	private void saveTick(TimeSheet tick, long time, double bid, double ask, double vol) {
		tick.nextRow();
		tick.save(new long[] { time }, new double[] { bid, ask, vol });
		tick.onUpdate();
	}
	private void assertLastRow(WorkSheetT ts, String[] cols, long[] times, double[] vals) {
		assertLastRow(ts, 0, cols, times, vals);
	}
	private void assertLastRow(WorkSheetT ts, int offset, String[] cols, long[] times, double[] vals) {
		for (int i = 0; i < cols.length; i++) {
			assertLastRow(ts, cols[i], times[i], vals[i], offset);
		}
	}
	private void assertLastRow(WorkSheetT ts, String col, long time, double val, int offset) {
		Assert.assertEquals("col: " + col, time, ts.getTemporalColumn(col).getValue(ts.getEndRowNum() + offset));
		Assert.assertEquals("col: " + col, val, ts.getColumn(col).getValue(ts.getEndRowNum() + offset), 0.0000000001);
	}
	@Test
	public void testInterDay() {
		TimeSheet tick = new TimeSheet("tick", "dummy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		Timer m1 = TimeScale.MINUTE_1.getTimer();
		PriceService ps = mock(PriceService.class);
		WorkSheetT ts = TimeSeries.getInstance("yyy", ps, m1, tick);

		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				((TimeSeries) args[0]).onHistoricalBarReady(
						new long[] {}, new double[] {}, new double[] {}, new double[] {}, new double[] {});
				// Object mock = invocation.getMock();
				return "called with arguments: " + args;
			}
		}).when(ps).RequestHistoricalBarData((TimeSeries) any(), (String) any(), (TimeScale) any());
		Assert.assertEquals("yyy", ts.getName());

		// first tick - open
		long minT_1 = MID_NIGHT + ONE_MINUTE;
		long tickT_1 = minT_1 + 11;
		saveTick(tick, tickT_1, 1.334, 1.336, 1);
		//
		Assert.assertEquals(1, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.OPEN, ColumnT.HIGH, ColumnT.LOW, ColumnT.CLOSE },
				new long[] { minT_1, tickT_1, tickT_1, tickT_1, tickT_1 },
				new double[] { 1.334, 1.334, 1.334, 1.334, 1.334 });
		// high
		long tickT_2 = minT_1 + 22;
		saveTick(tick, tickT_2, 1.434, 1.436, 2);
		//
		Assert.assertEquals(1, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.OPEN, ColumnT.HIGH, ColumnT.LOW, ColumnT.CLOSE },
				new long[] { minT_1, tickT_1, tickT_2, tickT_1, tickT_2 },
				new double[] { 1.434, 1.334, 1.434, 1.334, 1.434 });
		// low
		long tickT_3 = minT_1 + 33;
		saveTick(tick, tickT_3, 1.034, 1.036, 3);
		//
		Assert.assertEquals(1, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.OPEN, ColumnT.HIGH, ColumnT.LOW, ColumnT.CLOSE },
				new long[] { minT_1, tickT_1, tickT_2, tickT_3, tickT_3 },
				new double[] { 1.034, 1.334, 1.434, 1.034, 1.034 });
		// close
		long tickT_4 = minT_1 + 44;
		saveTick(tick, tickT_4, 1.234, 1.236, 4);
		//
		Assert.assertEquals(1, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.OPEN, ColumnT.HIGH, ColumnT.LOW, ColumnT.CLOSE },
				new long[] { minT_1, tickT_1, tickT_2, tickT_3, tickT_4 },
				new double[] { 1.234, 1.334, 1.434, 1.034, 1.234 });

		// next interval
		long minT_2 = minT_1 + ONE_MINUTE;
		long tickT_5 = minT_2 + 11;
		saveTick(tick, tickT_5, 2.234, 2.236, 1);
		//
		Assert.assertEquals(2, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.OPEN, ColumnT.HIGH, ColumnT.LOW, ColumnT.CLOSE },
				new long[] { minT_2, tickT_5, tickT_5, tickT_5, tickT_5 },
				new double[] { 2.234, 2.234, 2.234, 2.234, 2.234 });

		// filling gaps
		long minT_3 = minT_2 + ONE_MINUTE;
		long minT_4 = minT_3 + ONE_MINUTE;
		long minT_5 = minT_4 + ONE_MINUTE;
		long tickT_6 = minT_5 + 11;
		saveTick(tick, tickT_6, 5.234, 5.236, 1);
		//
		Assert.assertEquals(5, ts.getSize());
		assertLastRow(ts, -2,
				new String[] { "xxx", ColumnT.OPEN, ColumnT.HIGH, ColumnT.LOW, ColumnT.CLOSE },
				new long[] { minT_3, 0, 0, 0, 0 },
				new double[] { 2.234, 2.234, 2.234, 2.234, 2.234 });
		assertLastRow(ts, -1,
				new String[] { "xxx", ColumnT.OPEN, ColumnT.HIGH, ColumnT.LOW, ColumnT.CLOSE },
				new long[] { minT_4, 0, 0, 0, 0 },
				new double[] { 2.234, 2.234, 2.234, 2.234, 2.234 });
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.OPEN, ColumnT.HIGH, ColumnT.LOW, ColumnT.CLOSE },
				new long[] { minT_5, tickT_6, tickT_6, tickT_6, tickT_6 },
				new double[] { 5.234, 5.234, 5.234, 5.234, 5.234 });
	}
	@Test
	public void testOverDay() {
		TimeSheet tick = new TimeSheet("tick", "dummy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		Timer d1 = TimeScale.DAY_1.getTimer();
		PriceService ps = mock(PriceService.class);
		WorkSheetT ts = TimeSeries.getInstance("zzz", ps, d1, tick);
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				((TimeSeries) args[0]).onHistoricalBarReady(
						new long[] {}, new double[] {}, new double[] {}, new double[] {}, new double[] {});
				// Object mock = invocation.getMock();
				return "called with arguments: " + args;
			}
		}).when(ps).RequestHistoricalBarData((TimeSeries) any(), (String) any(), (TimeScale) any());
		Assert.assertEquals("zzz", ts.getName());

		// first tick
		long dayT_1 = MID_NIGHT;
		long tickT_1 = dayT_1 + 11;
		saveTick(tick, tickT_1, 1.234, 1.236, 1);
		//
		Assert.assertEquals(dayT_1, d1.currIntervalStartTime(tickT_1));
		Assert.assertEquals(1, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.CLOSE },
				new long[] { dayT_1, tickT_1 },
				new double[] { 1.234, 1.234 });

		// next day, without (business day end)
		long dayT_2 = dayT_1 + ONE_DAY;
		long tickT_2 = dayT_2 + 22;
		saveTick(tick, tickT_2, 2.234, 2.236, 2);
		//
		Assert.assertEquals(dayT_2, d1.currIntervalStartTime(tickT_2));
		Assert.assertEquals(1, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.CLOSE },
				new long[] { dayT_1, tickT_2 },
				new double[] { 2.234, 2.234 });

		// next interval, after day end, same day start again
		long tickT_3 = dayT_2 + 33;
		saveTick(tick, tickT_2, Double.NaN, Double.NaN, Double.NaN);
		saveTick(tick, tickT_3, 3.234, 3.236, 3);
		//
		Assert.assertEquals(dayT_2, d1.currIntervalStartTime(tickT_2));
		Assert.assertEquals(2, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.CLOSE },
				new long[] { dayT_2, tickT_3 },
				new double[] { 3.234, 3.234 });

		// next interval, after day end, same day start again
		long dayT_3 = dayT_2 + ONE_DAY;
		long dayT_4 = dayT_3 + ONE_DAY;
		long dayT_5 = dayT_4 + ONE_DAY;
		long tickT_4 = dayT_5 + 44;
		long tickT_5 = dayT_5 + 55;
		saveTick(tick, tickT_4, 4.234, 4.236, 4);
		saveTick(tick, tickT_4, Double.NaN, Double.NaN, Double.NaN);
		saveTick(tick, tickT_5, 5.234, 5.236, 5);
		//
		Assert.assertEquals(3, ts.getSize());
		assertLastRow(ts, -1,
				new String[] { "xxx", ColumnT.CLOSE },
				new long[] { dayT_2, tickT_4 },
				new double[] { 4.234, 4.234 });
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.CLOSE },
				new long[] { dayT_5, tickT_5 },
				new double[] { 5.234, 5.234 });

	}
	@Test
	public void testOverWeek() {
		TimeSheet tick = new TimeSheet("tick", "dummy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		Timer w1 = TimeScale.WEEK_1.getTimer();
		PriceService ps = mock(PriceService.class);
		WorkSheetT ts = TimeSeries.getInstance("zzz", ps, w1, tick);
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				((TimeSeries) args[0]).onHistoricalBarReady(
						new long[] {}, new double[] {}, new double[] {}, new double[] {}, new double[] {});
				// Object mock = invocation.getMock();
				return "called with arguments: " + args;
			}
		}).when(ps).RequestHistoricalBarData((TimeSeries) any(), (String) any(), (TimeScale) any());
		Assert.assertEquals("zzz", ts.getName());

		// first tick
		long dayT_1 = MID_NIGHT;
		long tickT_1 = dayT_1 + 11;
		saveTick(tick, tickT_1, 1.234, 1.236, 1);
		//
		Assert.assertEquals(dayT_1, w1.currIntervalStartTime(tickT_1));
		Assert.assertEquals(1, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.CLOSE },
				new long[] { dayT_1, tickT_1 },
				new double[] { 1.234, 1.234 });

		// day end/start, but within the same week
		long dayT_2 = dayT_1 + ONE_DAY;
		long tickT_2 = dayT_2 + 22;
		long tickT_3 = dayT_2 + 33;
		saveTick(tick, tickT_2, 2.234, 2.236, 2);
		saveTick(tick, tickT_2, Double.NaN, Double.NaN, Double.NaN);
		saveTick(tick, tickT_3, 3.234, 3.236, 3);
		//
		Assert.assertEquals(dayT_1, w1.currIntervalStartTime(tickT_2));
		Assert.assertEquals(1, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.CLOSE },
				new long[] { dayT_1, tickT_3 },
				new double[] { 3.234, 3.234 });

		// next interval, but w/o day-end
		long dayT_15 = dayT_1 + 2 * 7 * ONE_DAY;
		long tickT_4 = dayT_15 + 44;
		saveTick(tick, tickT_4, 4.234, 4.236, 4);
		//
		Assert.assertEquals(1, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.CLOSE },
				new long[] { dayT_1, tickT_4 },
				new double[] { 4.234, 4.234 });

		// next interval, after day end, same day start again
		long tickT_5 = dayT_15 + 55;
		saveTick(tick, tickT_4, Double.NaN, Double.NaN, Double.NaN);
		saveTick(tick, tickT_5, 5.234, 5.236, 5);
		//
		Assert.assertEquals(2, ts.getSize());
		assertLastRow(ts,
				new String[] { "xxx", ColumnT.CLOSE },
				new long[] { dayT_15, tickT_5 },
				new double[] { 5.234, 5.234 });

	}
	// TODO test cases with onHistBar mix with dayEndStart
}