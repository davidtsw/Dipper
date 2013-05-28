package com.asdf.ta.workbook.fml;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import com.asdf.service.PriceService;
import com.asdf.ta.workbook.ColumnT;
import com.asdf.ta.workbook.impl.TimeSheet;

public class TickSeriesTest {
	@Test
	public void testRealTimeTickUpdate() {
		PriceService ps = mock(PriceService.class);
		TimeSheet own = new TimeSheet("xxx", "yyy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		TimeSheet tmp = new TimeSheet("xxx", "yyy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		TickSeries ts = new TickSeries(ps, own, tmp);
		// first tick
		ts.onRealTimeTickUpdated(1, 1300000000011l, 1.234, 1.236, 1);
		verify(ps).RequestHistoricalTickData(ts, "xxx");
		Assert.assertTrue(own.isEmpty());
		Assert.assertEquals(1, tmp.getEndRowNum() - tmp.getStartRowNum() + 1);
		Assert.assertEquals(1300000000011l, tmp.getTemporalColumn().getValue(tmp.getEndRowNum()));
		Assert.assertEquals(1.234, tmp.getColumn(ColumnT.BID).getValue(tmp.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(1.236, tmp.getColumn(ColumnT.ASK).getValue(tmp.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(1, tmp.getColumn(ColumnT.VOLUME).getValue(tmp.getEndRowNum()), 0.0000000001);
		// second tick
		ts.onRealTimeTickUpdated(2, 1300000000012l, 2.234, 2.236, 2);
		Assert.assertTrue(own.isEmpty());
		Assert.assertEquals(2, tmp.getEndRowNum() - tmp.getStartRowNum() + 1);
		Assert.assertEquals(1300000000012l, tmp.getTemporalColumn().getValue(tmp.getEndRowNum()));
		Assert.assertEquals(2.234, tmp.getColumn(ColumnT.BID).getValue(tmp.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(2.236, tmp.getColumn(ColumnT.ASK).getValue(tmp.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(2, tmp.getColumn(ColumnT.VOLUME).getValue(tmp.getEndRowNum()), 0.0000000001);
		// historical data ready
		ts.onHistoricalTickReady(new long[] {}, new double[] {}, new double[] {}, new double[] {});
		Assert.assertEquals(2, own.getEndRowNum() - own.getStartRowNum() + 1);
		Assert.assertEquals(1300000000011l, own.getTemporalColumn().getValue(own.getEndRowNum() - 1));
		Assert.assertEquals(1.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1300000000012l, own.getTemporalColumn().getValue(own.getEndRowNum()));
		Assert.assertEquals(2.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(2.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(2, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum()), 0.0000000001);
		// real time tick again
		ts.onRealTimeTickUpdated(3, 1300000000013l, 3.234, 3.236, 3);
		Assert.assertEquals(1300000000013l, own.getTemporalColumn().getValue(own.getEndRowNum()));
		Assert.assertEquals(3.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(3.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(3, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum()), 0.0000000001);
	}
	@Test
	public void testNoHistoricalTicks() {
		PriceService ps = mock(PriceService.class);
		TimeSheet own = new TimeSheet("xxx", "yyy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		TimeSheet tmp = new TimeSheet("xxx", "yyy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		TickSeries ts = new TickSeries(ps, own, tmp);
		// own /// hist //= buff
		ts.onRealTimeTickUpdated(1, 1300000000011l, 1.234, 1.236, 1);
		ts.onRealTimeTickUpdated(2, 1300000000012l, 2.234, 2.236, 2);
		verify(ps).RequestHistoricalTickData(ts, "xxx");
		ts.onHistoricalTickReady(new long[] {}, new double[] {}, new double[] {}, new double[] {});
		Assert.assertEquals(2, own.getEndRowNum() - own.getStartRowNum() + 1);
		Assert.assertEquals(1300000000011l, own.getTemporalColumn().getValue(own.getEndRowNum() - 1));
		Assert.assertEquals(1.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1300000000012l, own.getTemporalColumn().getValue(own.getEndRowNum()));
		Assert.assertEquals(2.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(2.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(2, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum()), 0.0000000001);
		// own =// hist //= buff
		ts.onRealTimeTickUpdated(4, 1300000000014l, 4.234, 4.236, 4);
		ts.onRealTimeTickUpdated(5, 1300000000015l, 5.234, 5.236, 5);
		ts.onHistoricalTickReady(new long[] {}, new double[] {}, new double[] {}, new double[] {});
		Assert.assertEquals(2, own.getEndRowNum() - own.getStartRowNum() + 1);
		Assert.assertEquals(1300000000014l, own.getTemporalColumn().getValue(own.getEndRowNum() - 1));
		Assert.assertEquals(4.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(4.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(4, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1300000000015l, own.getTemporalColumn().getValue(own.getEndRowNum()));
		Assert.assertEquals(5.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(5.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(5, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum()), 0.0000000001);
	}
	@Test
	public void testDiscontHistoricalTicks() {
		PriceService ps = mock(PriceService.class);
		TimeSheet own = new TimeSheet("xxx", "yyy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		TimeSheet tmp = new TimeSheet("xxx", "yyy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		TickSeries ts = new TickSeries(ps, own, tmp);
		// own /// hist =/= buff
		ts.onRealTimeTickUpdated(1, 1300000000011l, 1.234, 1.236, 1);
		ts.onRealTimeTickUpdated(2, 1300000000012l, 2.234, 2.236, 2);
		ts.onHistoricalTickReady(new long[] { 1300000000009l, 1300000000010l },
				new double[] { 0.123, 0.234 },
				new double[] { 0.126, 0.236 },
				new double[] { 0.1, 0.2 });
		Assert.assertEquals(2, own.getEndRowNum() - own.getStartRowNum() + 1);
		Assert.assertEquals(1300000000011l, own.getTemporalColumn().getValue(own.getEndRowNum() - 1));
		Assert.assertEquals(1.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1300000000012l, own.getTemporalColumn().getValue(own.getEndRowNum()));
		Assert.assertEquals(2.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(2.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(2, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum()), 0.0000000001);
		// own =/= hist =/= buff
		ts.onRealTimeTickUpdated(4, 1300000000024l, 4.234, 4.236, 4);
		ts.onRealTimeTickUpdated(5, 1300000000025l, 5.234, 5.236, 5);
		ts.onHistoricalTickReady(new long[] { 1300000000013l, 1300000000023l },
				new double[] { 0.323, 0.434 },
				new double[] { 0.326, 0.436 },
				new double[] { 0.3, 0.4 });
		Assert.assertEquals(2, own.getEndRowNum() - own.getStartRowNum() + 1);
		Assert.assertEquals(1300000000024l, own.getTemporalColumn().getValue(own.getEndRowNum() - 1));
		Assert.assertEquals(4.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(4.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(4, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1300000000025l, own.getTemporalColumn().getValue(own.getEndRowNum()));
		Assert.assertEquals(5.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(5.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(5, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum()), 0.0000000001);
		// own =/= hist === buff
		ts.onRealTimeTickUpdated(7, 1300000000037l, 7.234, 7.236, 7);
		ts.onRealTimeTickUpdated(8, 1300000000038l, 8.234, 8.236, 8);
		ts.onHistoricalTickReady(new long[] { 1300000000026l, 1300000000037l },
				new double[] { 6.234, 7.234 },
				new double[] { 6.236, 7.236 },
				new double[] { 6, 7 });
		Assert.assertEquals(3, own.getEndRowNum() - own.getStartRowNum() + 1);
		Assert.assertEquals(1300000000026l, own.getTemporalColumn().getValue(own.getEndRowNum() - 2));
		Assert.assertEquals(6.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 2), 0.0000000001);
		Assert.assertEquals(6.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 2), 0.0000000001);
		Assert.assertEquals(6, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 2), 0.0000000001);
		Assert.assertEquals(1300000000037l, own.getTemporalColumn().getValue(own.getEndRowNum() - 1));
		Assert.assertEquals(7.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(7.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(7, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1300000000038l, own.getTemporalColumn().getValue(own.getEndRowNum()));
		Assert.assertEquals(8.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(8.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(8, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum()), 0.0000000001);
	}
	@Test
	public void testContHistoricalTicks() {
		PriceService ps = mock(PriceService.class);
		TimeSheet own = new TimeSheet("xxx", "yyy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		TimeSheet tmp = new TimeSheet("xxx", "yyy",
				Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME), Arrays.asList(ColumnT.TIME));
		TickSeries ts = new TickSeries(ps, own, tmp);
		// own /// hist === buff
		ts.onRealTimeTickUpdated(1, 1300000000011l, 1.234, 1.236, 1);
		ts.onRealTimeTickUpdated(2, 1300000000012l, 2.234, 2.236, 2);
		ts.onHistoricalTickReady(new long[] { 1300000000010l, 1300000000011l },
				new double[] { 0.234, 1.234 },
				new double[] { 0.236, 1.236 },
				new double[] { 0, 1 });
		Assert.assertEquals(3, own.getEndRowNum() - own.getStartRowNum() + 1);
		Assert.assertEquals(1300000000010l, own.getTemporalColumn().getValue(own.getEndRowNum() - 2));
		Assert.assertEquals(0.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 2), 0.0000000001);
		Assert.assertEquals(0.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 2), 0.0000000001);
		Assert.assertEquals(0, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 2), 0.0000000001);
		Assert.assertEquals(1300000000011l, own.getTemporalColumn().getValue(own.getEndRowNum() - 1));
		Assert.assertEquals(1.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1300000000012l, own.getTemporalColumn().getValue(own.getEndRowNum()));
		Assert.assertEquals(2.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(2.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(2, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum()), 0.0000000001);
		// own === hist === buff
		ts.onRealTimeTickUpdated(4, 1300000000014l, 4.234, 4.236, 4);
		ts.onRealTimeTickUpdated(5, 1300000000015l, 5.234, 5.236, 5);
		ts.onHistoricalTickReady(new long[] { 1300000000012l, 1300000000013l, 1300000000014l },
				new double[] { 2.234, 3.234, 4.234 },
				new double[] { 2.236, 3.236, 4.236 },
				new double[] { 2, 3, 4 });
		Assert.assertEquals(6, own.getEndRowNum() - own.getStartRowNum() + 1);
		Assert.assertEquals(1300000000010l, own.getTemporalColumn().getValue(own.getEndRowNum() - 5));
		Assert.assertEquals(0.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 5), 0.0000000001);
		Assert.assertEquals(0.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 5), 0.0000000001);
		Assert.assertEquals(0, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 5), 0.0000000001);
		Assert.assertEquals(1300000000011l, own.getTemporalColumn().getValue(own.getEndRowNum() - 4));
		Assert.assertEquals(1.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 4), 0.0000000001);
		Assert.assertEquals(1.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 4), 0.0000000001);
		Assert.assertEquals(1, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 4), 0.0000000001);
		Assert.assertEquals(1300000000012l, own.getTemporalColumn().getValue(own.getEndRowNum() - 3));
		Assert.assertEquals(2.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 3), 0.0000000001);
		Assert.assertEquals(2.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 3), 0.0000000001);
		Assert.assertEquals(2, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 3), 0.0000000001);
		Assert.assertEquals(1300000000013l, own.getTemporalColumn().getValue(own.getEndRowNum() - 2));
		Assert.assertEquals(3.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 2), 0.0000000001);
		Assert.assertEquals(3.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 2), 0.0000000001);
		Assert.assertEquals(3, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 2), 0.0000000001);
		Assert.assertEquals(1300000000014l, own.getTemporalColumn().getValue(own.getEndRowNum() - 1));
		Assert.assertEquals(4.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(4.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(4, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum() - 1), 0.0000000001);
		Assert.assertEquals(1300000000015l, own.getTemporalColumn().getValue(own.getEndRowNum()));
		Assert.assertEquals(5.234, own.getColumn(ColumnT.BID).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(5.236, own.getColumn(ColumnT.ASK).getValue(own.getEndRowNum()), 0.0000000001);
		Assert.assertEquals(5, own.getColumn(ColumnT.VOLUME).getValue(own.getEndRowNum()), 0.0000000001);
	}
}
