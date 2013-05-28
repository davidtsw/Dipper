package com.asdf.ta.workbook;

public interface ColumnT {
	public String getName();
	public long getValue(long row);
	//
	public final String TIME = "time";
	public final String BID = "bid";
	public final String ASK = "ask";
	//
	public final String OPEN = "open";
	public final String HIGH = "high";
	public final String LOW = "low";
	public final String CLOSE = "close";
	public final String VOLUME = "volume";
}