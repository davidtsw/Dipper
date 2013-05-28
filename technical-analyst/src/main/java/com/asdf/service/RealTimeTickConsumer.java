package com.asdf.service;

public interface RealTimeTickConsumer {
	public void onRealTimeTickUpdated(int seq, long time, double bid, double ask, double volume);
}