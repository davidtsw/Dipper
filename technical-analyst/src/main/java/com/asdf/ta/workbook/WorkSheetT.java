package com.asdf.ta.workbook;

public interface WorkSheetT extends WorkSheet {
	public ColumnT getTemporalColumn();
	public ColumnT getTemporalColumn(String colName);
}