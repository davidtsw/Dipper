package com.asdf.ta.workbook.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import junit.framework.Assert;
import org.junit.Test;
import com.asdf.ta.workbook.Column;
import com.asdf.ta.workbook.Formula;
import com.asdf.ta.workbook.WorkSheetListener;
import com.asdf.ta.workbook.WorkSheet.Status;

public class DataSheetTest {
	@Test
	public void testCreateDelete() {
		Formula fml = mock(Formula.class);
		WorkSheetListener wsl = mock(WorkSheetListener.class);
		DataSheet iut = new DataSheet("xxx", "yyy", Arrays.asList("c1", "c2"));
		//
		Assert.assertEquals("xxx", iut.getName());
		Assert.assertEquals("yyy", iut.getDesc());
		// data sheet should be able to delete even no formula is binded
		Assert.assertEquals(Status.READY, iut.getStatus());
		iut.delete();
		Assert.assertEquals(Status.DELETED, iut.getStatus());
		// after binding with formula & listeners
		iut.setFml(fml);
		iut.addListener(wsl);
		iut.delete();
		verify(fml).onDelete();
		verify(wsl).onDelete();
	}
	@Test
	public void testGetColumn() {
		DataSheet iut = new DataSheet("xxx", "yyy", Arrays.asList("c1", "c2"));
		//
		Assert.assertEquals("xxx", iut.getName());
		Assert.assertEquals("yyy", iut.getDesc());
		Assert.assertEquals(iut.getColumn("c1"), iut.getColumn());
		Assert.assertEquals(iut.getColumn("c1"), iut.getColumn("cXXX"));
		//
		Assert.assertEquals("c1", iut.getColumn("c1").getName());
		Assert.assertEquals("c2", iut.getColumn("c2").getName());
	}
	@Test
	public void testAddDiscardData() {
		DataSheet iut = new DataSheet("xxx", "yyy", Arrays.asList("c1", "c2"));
		Column c1 = iut.getColumn("c1");
		Column c2 = iut.getColumn("c2");
		// empty
		Assert.assertTrue(iut.isEmpty());
		Assert.assertEquals(0, iut.getSize());
		Assert.assertTrue(iut.getStartRowNum() > iut.getEndRowNum());
		iut.clear();
		Assert.assertTrue(iut.isEmpty());
		Assert.assertEquals(0, iut.getSize());
		Assert.assertTrue(iut.getStartRowNum() > iut.getEndRowNum());
		// add data - row 1
		iut.nextRow();
		Assert.assertFalse(iut.isEmpty());
		Assert.assertEquals(1, iut.getSize());
		Assert.assertEquals(iut.getStartRowNum(), iut.getEndRowNum());
		long end = iut.getEndRowNum();
		iut.save(new double[] { 1.234, 2.345 });
		Assert.assertEquals(1.234, c1.getValue(end));
		Assert.assertEquals(1.234, iut.getLast("c1"));
		Assert.assertEquals(2.345, c2.getValue(end));
		Assert.assertEquals(2.345, iut.getLast("c2"));
		//
		iut.save(new double[] { 2.234, 3.345 });
		Assert.assertEquals(2.234, c1.getValue(end));
		Assert.assertEquals(2.234, iut.getLast("c1"));
		Assert.assertEquals(3.345, c2.getValue(end));
		Assert.assertEquals(3.345, iut.getLast("c2"));
		// data row 2
		iut.nextRow();
		Assert.assertFalse(iut.isEmpty());
		Assert.assertEquals(2, iut.getSize());
		Assert.assertEquals(iut.getStartRowNum() + 1, iut.getEndRowNum());
		end = iut.getEndRowNum();
		//
		iut.save(new double[] { 3.234, 4.345 });
		Assert.assertEquals(2.234, c1.getValue(end - 1));
		Assert.assertEquals(3.234, c1.getValue(end));
		Assert.assertEquals(3.234, iut.getLast("c1"));
		Assert.assertEquals(3.345, c2.getValue(end - 1));
		Assert.assertEquals(4.345, c2.getValue(end));
		Assert.assertEquals(4.345, iut.getLast("c2"));
		// discard row 1
		iut.discard(end);
		Assert.assertFalse(iut.isEmpty());
		Assert.assertEquals(1, iut.getSize());
		Assert.assertEquals(end, iut.getStartRowNum());
		Assert.assertEquals(end, iut.getEndRowNum());
		Assert.assertEquals(3.234, c1.getValue(end));
		Assert.assertEquals(3.234, iut.getLast("c1"));
		Assert.assertEquals(4.345, c2.getValue(end));
		Assert.assertEquals(4.345, iut.getLast("c2"));
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
	@Test
	public void testCapacity() {
		int oCap = DataSheet._WorkSheetCapacity;
		DataSheet.setDefaultCapacity(2);
		DataSheet iut = new DataSheet("xxx", "yyy", Arrays.asList("c1", "c2"));
		Column c1 = iut.getColumn("c1");
		Column c2 = iut.getColumn("c2");
		//
		iut.nextRow();
		iut.save(new double[] { 1.234, 2.345 });
		iut.nextRow();
		iut.save(new double[] { 2.234, 3.345 });
		iut.nextRow();
		iut.save(new double[] { 3.234, 4.345 });
		//
		Assert.assertEquals(2, iut.getSize());
		Assert.assertEquals(iut.getStartRowNum() + 1, iut.getEndRowNum());
		long end = iut.getEndRowNum();
		Assert.assertEquals(2.234, c1.getValue(end - 1));
		Assert.assertEquals(3.234, c1.getValue(end));
		Assert.assertEquals(3.234, iut.getLast("c1"));
		Assert.assertEquals(3.345, c2.getValue(end - 1));
		Assert.assertEquals(4.345, c2.getValue(end));
		Assert.assertEquals(4.345, iut.getLast("c2"));
		// discard
		iut.discard(end);
		Assert.assertFalse(iut.isEmpty());
		Assert.assertEquals(1, iut.getSize());
		Assert.assertEquals(end, iut.getStartRowNum());
		Assert.assertEquals(end, iut.getEndRowNum());
		// discard
		end += 10;
		iut.discard(end);
		Assert.assertTrue(iut.isEmpty());
		Assert.assertEquals(0, iut.getSize());
		Assert.assertEquals(end, iut.getStartRowNum());
		// cleanup
		DataSheet._WorkSheetCapacity = oCap;
	}
}