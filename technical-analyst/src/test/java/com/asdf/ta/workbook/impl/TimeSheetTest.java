package com.asdf.ta.workbook.impl;

import static org.mockito.Mockito.*;
import java.util.Arrays;
import junit.framework.Assert;
import org.junit.Test;
import com.asdf.ta.workbook.Column;
import com.asdf.ta.workbook.ColumnT;
import com.asdf.ta.workbook.Formula;
import com.asdf.ta.workbook.WorkSheetListener;
import com.asdf.ta.workbook.WorkSheet.Status;

public class TimeSheetTest {
	@Test
	public void testCreateDelete() {
		TimeSheet iut = new TimeSheet("xxx", "yyy", Arrays.asList("c1", "c2"), Arrays.asList("t1", "t2"));
		//
		Assert.assertEquals("xxx", iut.getName());
		Assert.assertEquals("yyy", iut.getDesc());

		// data sheet should be able to delete even no formula is binded
		Assert.assertEquals(Status.READY, iut.getStatus());
		iut.delete();
		Assert.assertEquals(Status.DELETED, iut.getStatus());

		// after binding with formula & listeners
		Formula fml = mock(Formula.class);
		iut.setFml(fml);
		WorkSheetListener wsl = mock(WorkSheetListener.class);
		iut.addListener(wsl);
		iut.delete();
		//
		verify(fml).onDelete();
		verify(wsl).onDelete();
	}
	@Test
	public void testGetColumn() {
		TimeSheet iut = new TimeSheet("xxx", "yyy", Arrays.asList("c1", "c2"), Arrays.asList("t1", "t2"));
		// default columns
		Assert.assertEquals("xxx", iut.getName());
		Assert.assertEquals("yyy", iut.getDesc());
		Assert.assertEquals(iut.getColumn("c1"), iut.getColumn());
		Assert.assertEquals(iut.getColumn("c1"), iut.getColumn("cXXX"));
		Assert.assertEquals(iut.getTemporalColumn("t1"), iut.getTemporalColumn());
		Assert.assertEquals(iut.getTemporalColumn("t1"), iut.getTemporalColumn("tXXX"));
		//
		Assert.assertEquals("c1", iut.getColumn("c1").getName());
		Assert.assertEquals("c2", iut.getColumn("c2").getName());
		Assert.assertEquals("t1", iut.getTemporalColumn("t1").getName());
		Assert.assertEquals("t2", iut.getTemporalColumn("t2").getName());
	}
	@Test
	public void testAddDiscardData() {
		TimeSheet iut = new TimeSheet("xxx", "yyy", Arrays.asList("c1", "c2"), Arrays.asList("t1", "t2"));
		Column c1 = iut.getColumn("c1");
		Column c2 = iut.getColumn("c2");
		ColumnT t1 = iut.getTemporalColumn("t1");
		ColumnT t2 = iut.getTemporalColumn("t2");
		// empty
		Assert.assertEquals(0, iut.getSize());
		// add row-1
		iut.nextRow();
		Assert.assertEquals(1, iut.getSize());
		long end = iut.getEndRowNum();
		iut.save(new long[] { 1234l, 2345l },
				new double[] { 1.234, 2.345 });
		Assert.assertEquals(1.234, c1.getValue(end));
		Assert.assertEquals(1.234, iut.getLast("c1"));
		Assert.assertEquals(2.345, c2.getValue(end));
		Assert.assertEquals(2.345, iut.getLast("c2"));
		//
		Assert.assertEquals(1234l, t1.getValue(end));
		Assert.assertEquals(1234l, iut.getTemporalLast("t1"));
		Assert.assertEquals(2345l, t2.getValue(end));
		Assert.assertEquals(2345l, iut.getTemporalLast("t2"));
		// update row-1
		iut.save(new long[] { 2234l, 3345l },
				new double[] { 2.234, 3.345 });
		Assert.assertEquals(2.234, c1.getValue(end));
		Assert.assertEquals(2.234, iut.getLast("c1"));
		Assert.assertEquals(3.345, c2.getValue(end));
		Assert.assertEquals(3.345, iut.getLast("c2"));
		//
		Assert.assertEquals(2234l, t1.getValue(end));
		Assert.assertEquals(2234l, iut.getTemporalLast("t1"));
		Assert.assertEquals(3345l, t2.getValue(end));
		Assert.assertEquals(3345l, iut.getTemporalLast("t2"));

		// add row-2
		iut.nextRow();
		Assert.assertEquals(2, iut.getSize());
		end = iut.getEndRowNum();
		//
		iut.save(new long[] { 3234l, 4345l },
				new double[] { 3.234, 4.345 });
		Assert.assertEquals(2.234, c1.getValue(end - 1));
		Assert.assertEquals(3.234, c1.getValue(end));
		Assert.assertEquals(3.234, iut.getLast("c1"));
		Assert.assertEquals(3.345, c2.getValue(end - 1));
		Assert.assertEquals(4.345, c2.getValue(end));
		Assert.assertEquals(4.345, iut.getLast("c2"));
		//
		Assert.assertEquals(2234l, t1.getValue(end - 1));
		Assert.assertEquals(3234l, t1.getValue(end));
		Assert.assertEquals(3234l, iut.getTemporalLast("t1"));
		Assert.assertEquals(3345l, t2.getValue(end - 1));
		Assert.assertEquals(4345l, t2.getValue(end));
		Assert.assertEquals(4345l, iut.getTemporalLast("t2"));

		// discard row 1
		iut.discard(end);
		Assert.assertEquals(1, iut.getSize());
		Assert.assertEquals(3.234, c1.getValue(end));
		Assert.assertEquals(3.234, iut.getLast("c1"));
		Assert.assertEquals(4.345, c2.getValue(end));
		Assert.assertEquals(4.345, iut.getLast("c2"));
		//
		Assert.assertEquals(3234l, t1.getValue(end));
		Assert.assertEquals(3234l, iut.getTemporalLast("t1"));
		Assert.assertEquals(4345l, t2.getValue(end));
		Assert.assertEquals(4345l, iut.getTemporalLast("t2"));

		// clear
		iut.clear();
		Assert.assertTrue(iut.isEmpty());
		Assert.assertEquals(0, iut.getSize());
		Assert.assertTrue(iut.getStartRowNum() > end);
		//
		iut.clear(11);
		Assert.assertTrue(iut.isEmpty());
		Assert.assertEquals(0, iut.getSize());
		Assert.assertEquals(11, iut.getStartRowNum());
	}

}