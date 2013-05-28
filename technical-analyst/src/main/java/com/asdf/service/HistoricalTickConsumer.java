package com.asdf.service;

public interface HistoricalTickConsumer {
	public void onHistoricalTickReady(long[] time, double[] bids, double[] asks, double[] vols);
}