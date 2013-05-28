package com.asdf.ta.workbook.fml;

import java.util.Arrays;
import java.util.List;
import com.asdf.service.HistoricalTickConsumer;
import com.asdf.service.PriceService;
import com.asdf.service.RealTimeTickConsumer;
import com.asdf.ta.workbook.ColumnT;
import com.asdf.ta.workbook.Formula;
import com.asdf.ta.workbook.WorkSheetT;
import com.asdf.ta.workbook.impl.TimeSheet;

public class TickSeries implements Formula, RealTimeTickConsumer, HistoricalTickConsumer {
	final private TimeSheet own, tmp;
	final private long[] timeArr = new long[1];
	final private double[] priceArr = new double[3];
	final private PriceService ps;
	private int lastSeq;
	private TimeSheet ws;
	private boolean isLoading;

	TickSeries(PriceService ps, TimeSheet own, TimeSheet tmp) {
		this.ps = ps;
		this.own = own;
		this.tmp = tmp;
		lastSeq = -2;
	}
	@Override
	public void onDelete() {
		// ignore it
	}
	@Override
	public void onRealTimeTickUpdated(int seq, long time, double bid, double ask, double vol) {
		boolean isTickDiscont = (lastSeq + 1 < seq);
		if (isTickDiscont) {
			loadTick();
		}
		saveTick(seq, time, bid, ask, vol);
	}
	private void loadTick() {
		own.onReload();
		tmp.clear();
		ws = tmp;
		isLoading = true;
		ps.RequestHistoricalTickData(this, own.getName());
	}
	private void saveTick(int seq, long time, double bid, double ask, double vol) {
		lastSeq = seq;
		ws.nextRow();
		timeArr[0] = time;
		priceArr[0] = bid;
		priceArr[1] = ask;
		priceArr[2] = vol;
		ws.save(timeArr, priceArr);
		if (!isLoading) {
			ws.onUpdate();
		}
	}

	@Override
	public void onHistoricalTickReady(long[] time, double[] bids, double[] asks, double[] vols) {
		ws = own;
		int lastBuffSeq = lastSeq;
		// 1. merge existing sheet with historical ticks
		mergeHistoricalTicks(time, bids, asks, vols);
		// 2. fill in buffered ticks
		mergeBufferedTicks(time, lastBuffSeq);
		// 3. cleanup
		isLoading = false;
		own.onUpdate();
	}
	private void mergeHistoricalTicks(long[] time, double[] bids, double[] asks, double[] vols) {
		long lastTickTime = 0;
		boolean hasOverlap = false;
		if (!own.isEmpty()) {
			lastTickTime = own.getTemporalColumn().getValue(own.getEndRowNum());
		}
		int histCnt = 0;
		for (; histCnt < time.length && time[histCnt] <= lastTickTime; histCnt++) {
			// skip the overlapped ticks
			hasOverlap = true;
		}
		if (!hasOverlap) {
			// clear existing sheet if historical ticks don't continuous
			own.clear();
		}
		for (; histCnt < time.length; histCnt++) {
			saveTick(0, time[histCnt], bids[histCnt], asks[histCnt], vols[histCnt]);
		}
	}
	private void mergeBufferedTicks(long[] time, int lastBuffSeq) {
		long lastTickTime = 0;
		boolean hasOverlap = false;
		if (time.length > 0) {
			// historical ticks should be merged into own sheet
			lastTickTime = own.getTemporalColumn().getValue(own.getEndRowNum());
		}
		long buffCnt = tmp.getStartRowNum();
		for (; buffCnt <= tmp.getEndRowNum()
				&& tmp.getTemporalColumn().getValue(buffCnt) <= lastTickTime; buffCnt++) {
			// skip the overlapped ticks
			hasOverlap = true;
		}
		if (!hasOverlap) {
			// clear existing sheet if buffered ticks don't continuous
			own.clear();
		}
		for (; buffCnt <= tmp.getEndRowNum(); buffCnt++) {
			saveTick(lastBuffSeq,
					tmp.getTemporalColumn().getValue(buffCnt),
					tmp.getColumn(ColumnT.BID).getValue(buffCnt),
					tmp.getColumn(ColumnT.ASK).getValue(buffCnt),
					tmp.getColumn(ColumnT.VOLUME).getValue(buffCnt));
		}
	}

	/**
	 * Returns an instanced of temporal work sheet fill with ticks of specified instruments
	 * @param symbol the symbol of the instrument
	 * @param ps price service instance
	 * @return the work sheet of the instrument tick prices
	 */
	static public WorkSheetT getInstance(String symbol, PriceService ps) {
		List<String> dCols = Arrays.asList(ColumnT.BID, ColumnT.ASK, ColumnT.VOLUME);
		List<String> tCols = Arrays.asList(ColumnT.TIME);
		TimeSheet own = new TimeSheet(symbol, "", dCols, tCols);
		TimeSheet tmp = new TimeSheet(symbol, "", dCols, tCols);
		TickSeries fml = new TickSeries(ps, own, tmp);
		own.setFml(fml);
		ps.RegisterRealTimeTick(fml);
		return own;
	}
}