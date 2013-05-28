package com.asdf.service;

import com.asdf.ta.workbook.TimeScale;

public interface PriceService {

	public void RegisterRealTimeTick(RealTimeTickConsumer con);
	public void RequestHistoricalBarData(HistoricalBarConsumer con, String symbol, TimeScale scale);
	public void RequestHistoricalTickData(HistoricalTickConsumer con, String symbol);
}