package com.asdf.ta.workbook;

public interface Column {
	public String getName();
	public double getValue(long row);
	//
	public final String ANY = "";
}