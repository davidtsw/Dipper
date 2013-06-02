package com.asdf.ta.workbook.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import junit.framework.Assert;
import org.junit.Test;
import com.asdf.ta.workbook.WorkSheet.Status;
import com.asdf.ta.workbook.WorkSheetListener;

public class ObservableSheetTest {
	@Test
	public void testAddDropListener() {
		WorkSheetListener m1 = mock(WorkSheetListener.class);
		WorkSheetListener m2 = mock(WorkSheetListener.class);
		ObservableSheet iut = new ObservableSheet();
		// add listeners
		iut.addListener(m1);
		iut.addListener(m2);
		//
		iut.onUpdate();
		verify(m1).onUpdate();
		verify(m2).onUpdate();
		// drop listeners
		iut.dropListener(m1);
		reset(m1, m2);
		//
		iut.onUpdate();
		verify(m1, times(0)).onUpdate();
		verify(m2).onUpdate();
	}
	@Test
	public void testOnReload() {
		WorkSheetListener m1 = mock(WorkSheetListener.class);
		ObservableSheet iut = new ObservableSheet();
		iut.addListener(m1);
		//
		iut.onReload();
		verify(m1).onReload();
		Assert.assertEquals(Status.LOADING, iut.getStatus());
	}
	@Test
	public void testOnUpdate() {
		WorkSheetListener m1 = mock(WorkSheetListener.class);
		ObservableSheet iut = new ObservableSheet();
		iut.addListener(m1);
		//
		iut.onUpdate();
		verify(m1).onUpdate();
		Assert.assertEquals(Status.READY, iut.getStatus());
	}
	@Test
	public void testOnDelete() {
		WorkSheetListener m1 = mock(WorkSheetListener.class);
		ObservableSheet iut = new ObservableSheet();
		iut.addListener(m1);
		//
		iut.onDelete();
		verify(m1).onDelete();
		Assert.assertEquals(Status.DELETED, iut.getStatus());
	}
	// TODO test concurrent situation
}