package com.asdf.ta.workbook.fml;

import java.util.ArrayList;
import java.util.List;
import com.asdf.service.HistoricalBarConsumer;
import com.asdf.service.PriceService;
import com.asdf.ta.function.TimeSeriesFn;
import com.asdf.ta.timeseries.Timer;
import com.asdf.ta.workbook.Column;
import com.asdf.ta.workbook.ColumnT;
import com.asdf.ta.workbook.Formula;
import com.asdf.ta.workbook.WorkSheetListener;
import com.asdf.ta.workbook.WorkSheetT;
import com.asdf.ta.workbook.impl.TimeSheet;

public class TimeSeries implements Formula, WorkSheetListener, HistoricalBarConsumer {
	static public String _TickPrice = "bid";
	final private Timer timer;
	final private TimeSheet own;
	final private TimeSeriesFn fn;
	final private PriceService ps;
	final private WorkSheetT src;
	final private ColumnT timeCol;
	final private Column dataCol;
	private long readPos;
	private long currST, nextST;
	private boolean isSuspend, isLoading;

	TimeSeries(Timer timer, TimeSheet own, TimeSeriesFn fn,
			PriceService ps, WorkSheetT src, ColumnT timeCol, Column dataCol) {
		this.timer = timer;
		this.own = own;
		this.fn = fn;
		this.ps = ps;
		this.src = src;
		this.timeCol = timeCol;
		this.dataCol = dataCol;
		readPos = -1;
	}
	@Override
	public void onDelete() {
		src.dropListener(this);
	}
	@Override
	public void onReload() {
		// ignore it
	}
	@Override
	public void onUpdate() {
		if (!isLoading) {
			boolean isUpdateDiscont = readPos < src.getStartRowNum();
			if (isUpdateDiscont) {
				loadBars();
			} else {
				saveBars();
			}
		} else {
			// ignore it
		}
	}
	private void loadBars() {
		own.onReload();
		isLoading = true;
		readPos = src.getEndRowNum();
		ps.RequestHistoricalBarData(this, own.getName(), timer.getScale());
		// TODO mark retry number?
	}
	private void saveBars() {
		while (readPos <= src.getEndRowNum()) {
			long time = timeCol.getValue(readPos);
			double price = dataCol.getValue(readPos);
			if (Double.isNaN(price)) {
				// day-end
				isSuspend = true;
			} else {
				if (isSuspend) {
					// day-start
					isSuspend = false;
					// check if new interval started
					long newST = timer.currIntervalStartTime(time);
					if (newST > currST) {
						nextST = newST;
						nextBar();
					}
				}
				// next bar & fill in gaps
				if (time > nextST) {
					double lastPrice = fn.get();
					nextBar();
					while (time > nextST) {
						fn.last(0, lastPrice);
						own.save(fn.getAllTime(), fn.getAll());
						nextBar();
					}
				}
				// save bar
				fn.last(time, price);
				own.save(fn.getAllTime(), fn.getAll());
			}
			readPos++;
		}
		own.onUpdate();
	}
	private void nextBar() {
		currST = nextST;
		nextST = timer.nextIntervalStartTime(currST);
		fn.next(currST);
		own.nextRow();
	}

	@Override
	public void onHistoricalBarReady(long[] time, double[] open, double[] high, double[] low, double[] close) {

		long lastST = 0;
		if (time.length > 0
				&& readPos >= src.getStartRowNum()) {
			// 1. merge the existing bars with historical bars
			if (time[0] > currST) {
				own.clear();
				own.nextRow();
			}
			long[] t = new long[1];
			double[] p = new double[4];
			int histCnt = 0;
			for (; histCnt < time.length && time[histCnt] < currST; histCnt++) {
				// skip the past overlap bars
			}
			// update the last existing bars
			t[0] = time[histCnt];
			p[0] = open[histCnt];
			p[1] = high[histCnt];
			p[2] = low[histCnt];
			p[3] = close[histCnt];
			lastST = time[histCnt];
			own.save(t, p);
			histCnt++;
			// fill in following historical bars
			for (; histCnt < time.length; histCnt++) {
				own.nextRow();
				t[0] = time[histCnt];
				p[0] = open[histCnt];
				p[1] = high[histCnt];
				p[2] = low[histCnt];
				p[3] = close[histCnt];
				lastST = time[histCnt];
				own.save(t, p);
			}
			fn.next(t[0]);
			for (int i = 0; i < p.length; i++) {
				fn.last(t[0], p[i]);
			}

			// 2. to fill in buffered ticks
			while (timeCol.getValue(readPos) < lastST) {
				// skip the overlap bars
				readPos++;
			}
		}
		if (lastST == 0) { // own is empty
			own.clear();
			readPos = src.getStartRowNum();
			nextST = timer.currIntervalStartTime(timeCol.getValue(readPos));
			nextBar();
		} else {
			currST = lastST;
			nextST = timer.nextIntervalStartTime(currST);
		}

		// 3. cleanup
		isLoading = false;
		saveBars();
	}
	@Override
	public void onHistoricalBarFail() {
		// TODO Auto-generated method stub
	}

	static public WorkSheetT getInstance(String symbol, PriceService ps, Timer timer, WorkSheetT src) {
		// TODO initialize the fn object with proper
		ColumnT tCol = src.getTemporalColumn();
		Column dCol = src.getColumn(_TickPrice);
		TimeSeriesFn fn = TimeSeriesFn.getInstance();
		List<String> tCols = new ArrayList<String>();
		tCols.add(ColumnT.TIME);
		tCols.addAll(fn.getOutputs());
		TimeSheet ws = new TimeSheet(symbol, "", fn.getOutputs(), tCols);
		//
		TimeSeries fml = new TimeSeries(timer, ws, fn, ps, src, tCol, dCol);
		ws.setFml(fml);
		src.addListener(fml);
		return ws;
	}
}
