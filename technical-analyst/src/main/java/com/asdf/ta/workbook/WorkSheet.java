package com.asdf.ta.workbook;

public interface WorkSheet {
	public String getName();
	public String getDesc();
	//
	public long getStartRowNum();
	public long getEndRowNum();
	public long getSize();
	//
	public Column getColumn();
	public Column getColumn(String colName);
	public boolean isEmpty();
	public void delete();
	//
	public enum Status {
		LOADING, READY, DELETED;
	}
	public Status getStatus();
	public void addListener(WorkSheetListener wsl);
	public void dropListener(WorkSheetListener wsl);
}