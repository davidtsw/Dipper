package com.asdf.ta.workbook.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ColumnGroup<E> {
	final private Map<String, E> colsMap;
	final private List<E> colsList;
	final private E defCol;
	final private int colNum;

	ColumnGroup(List<String> colNames, int capacity) {
		colsMap = new HashMap<String, E>(colNames.size());
		colsList = new ArrayList<E>(colNames.size());
		for (String colName : colNames) {
			E col = createColumn(colName, capacity);
			colsList.add(col);
			colsMap.put(colName, col);
		}
		// set the first column as default
		defCol = colsMap.get(colNames.get(0));
		colNum = colNames.size();
	}
	public int getColumnNum() {
		return colNum;
	}
	public E getColumn() {
		return defCol;
	}
	public E getColumn(String colName) {
		E col = colsMap.get(colName);
		return (col == null) ? defCol : col;
	}
	public E getColumn(int ind) {
		return colsList.get(ind);
	}
	abstract protected E createColumn(String colName, int capacity);
}