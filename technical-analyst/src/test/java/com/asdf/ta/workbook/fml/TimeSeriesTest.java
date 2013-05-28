package com.asdf.ta.workbook.fml;

import static org.mockito.Mockito.mock;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import com.asdf.service.PriceService;
import com.asdf.ta.function.TimeSeriesFn;
import com.asdf.ta.timeseries.Timer;
import com.asdf.ta.workbook.Column;
import com.asdf.ta.workbook.ColumnT;
import com.asdf.ta.workbook.TimeScale;
import com.asdf.ta.workbook.WorkSheetT;
import com.asdf.ta.workbook.impl.TimeSheet;

public class TimeSeriesTest {
	@Test
	public void testRealTimeTickUpdate() {
		TimeSheet tick = new TimeSheet("tick", "dummy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		PriceService ps = mock(PriceService.class);
		Timer m1 = TimeScale.MINUTE_1.getTimer();
		TimeSheet own = mock(TimeSheet.class);
		TimeSeriesFn fn = mock(TimeSeriesFn.class);
		ColumnT tc = mock(ColumnT.class);
		Column dc = mock(Column.class);
		TimeSeries ts = new TimeSeries(m1, own, fn, ps, tick, tc, dc);

		// first tick
		tick.nextRow();
		tick.save(new long[] { 1300000000011l }, new double[] { 1.234, 1.236, 1 });
		tick.onUpdate();

		// verify(ps).RequestHistoricalBarData((TimeSeries) any(), "xxx", TimeScale.MINUTE_1);
		// Assert.assertEquals(1, ts.getEndRowNum() - ts.getStartRowNum() + 1);
		// Assert.assertEquals(1300000000011l, ts.getTemporalColumn().getValue(ts.getEndRowNum()));
		// Assert.assertEquals(1.234, ts.getColumn(ColumnT.OPEN).getValue(ts.getEndRowNum()),
		// 0.0000000001);
		// Assert.assertEquals(1.234, ts.getColumn(ColumnT.HIGH).getValue(ts.getEndRowNum()),
		// 0.0000000001);
		// Assert.assertEquals(1.234, ts.getColumn(ColumnT.LOW).getValue(ts.getEndRowNum()),
		// 0.0000000001);
		// Assert.assertEquals(1.234, ts.getColumn(ColumnT.CLOSE).getValue(ts.getEndRowNum()), 0.0000000001);
	}
}