package com.asdf.ta.workbook.fml;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Test;
import com.asdf.ta.function.Function;
import com.asdf.ta.workbook.WorkSheet;
import com.asdf.ta.workbook.impl.DataSheet;

public class IndicatorTest {
	@Test
	public void testOnUpdate() {
		DataSheet ds = mock(DataSheet.class);
		Function fn = mock(Function.class);
		WorkSheet src = mock(WorkSheet.class);
		InputCol in = mock(InputCol.class);
		Indicator iut = new Indicator(ds, fn, src, in);

		// first time
		when(src.getStartRowNum()).thenReturn(101L);
		when(src.getEndRowNum()).thenReturn(110L);
		iut.onUpdate();
		verify(ds).clear(101L);
		verify(fn, times(10)).next();
		verify(fn, times(10)).last();
		verify(ds, times(10)).save(any(double[].class));
		verify(ds).onUpdate();

		// next row
		reset(fn);
		reset(ds);
		when(src.getEndRowNum()).thenReturn(111L);
		when(fn.getAll()).thenReturn(new double[] { 1.231, 1.232 });
		iut.onUpdate();
		verify(fn).next();
		verify(fn, times(2)).last();
		verify(ds, times(2)).save(new double[] { 1.231, 1.232 });
		verify(ds).onUpdate();

		// update row
		reset(fn);
		reset(ds);
		when(src.getEndRowNum()).thenReturn(111L);
		when(fn.getAll()).thenReturn(new double[] { 2.231, 2.232 });
		iut.onUpdate();
		verify(fn).last();
		verify(ds).save(new double[] { 2.231, 2.232 });
		verify(ds).onUpdate();

		// reload
		reset(fn);
		reset(ds);
		iut.onReload();
		iut.onUpdate();
		verify(ds).clear(101L);
		verify(fn, times(11)).next();
		verify(fn, times(11)).last();
		verify(ds, times(11)).save(any(double[].class));
		verify(ds).onUpdate();
	}
	@Test
	public void testOnDelete() {
		DataSheet ds = mock(DataSheet.class);
		Function fn = mock(Function.class);
		WorkSheet src = mock(WorkSheet.class);
		InputCol in = mock(InputCol.class);
		Indicator iut = new Indicator(ds, fn, src, in);

		iut.onDelete();
		verify(src).dropListener(iut);
	}
}