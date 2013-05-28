package com.asdf.service;

public interface HistoricalBarConsumer {
	public void onHistoricalBarReady(long[] time, double[] open, double[] high, double[] low, double[] close);
	public void onHistoricalBarFail();
}