package com.asdf.ta.workbook.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.asdf.ta.workbook.WorkSheet.Status;
import com.asdf.ta.workbook.WorkSheetListener;

public class ObservableSheet {
	final private List<WorkSheetListener> listenerList;
	private Status status = Status.READY;

	ObservableSheet() {
		listenerList = new CopyOnWriteArrayList<WorkSheetListener>();
	}
	public void addListener(WorkSheetListener wsl) {
		listenerList.add(0, wsl);
	}
	public void dropListener(WorkSheetListener wsl) {
		listenerList.remove(wsl);
	}
	public void onReload() {
		status = Status.LOADING;
		for (WorkSheetListener wsl : listenerList) {
			wsl.onReload();
		}
	}
	public void onUpdate() {
		status = Status.READY;
		for (WorkSheetListener wsl : listenerList) {
			wsl.onUpdate();
		}
	}
	public void onDelete() {
		status = Status.DELETED;
		for (WorkSheetListener wsl : listenerList) {
			wsl.onDelete();
		}
	}
	public Status getStatus() {
		return status;
	}
}