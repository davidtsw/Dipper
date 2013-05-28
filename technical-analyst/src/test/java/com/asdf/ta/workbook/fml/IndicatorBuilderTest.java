package com.asdf.ta.workbook.fml;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.asdf.common.meta.NumericInfo;
import com.asdf.ta.function.DummyFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.annotation.IndicatorFn;
import com.asdf.ta.function.annotation.IndicatorInput;
import com.asdf.ta.function.annotation.IndicatorParam;
import com.asdf.ta.function.annotation.IndicatorRelation;
import com.asdf.ta.workbook.WorkSheet;
import com.asdf.ta.workbook.impl.DataSheet;

public class IndicatorBuilderTest {

	@Test
	public void testExtractParameterInfo() {
		class Dum extends DummyFn {
			@IndicatorFn(name = "Dum")
			public Dum(
					@IndicatorInput Function in1,
					@IndicatorInput(req = "c2") Function in2,
					@IndicatorParam(name = "p1", init = 11) int param1,
					@IndicatorParam(name = "p2", init = 12, min = 2, max = 22) int param2,
					@IndicatorParam(name = "p3", init = 13, min = 3, max = 23, dp = 2) @IndicatorRelation(gt = "p2") int param3) {
			}
		}
		IndicatorBuilder iut = new IndicatorBuilder(Dum.class);
		Assert.assertEquals("Dum", iut.getName());
		Assert.assertArrayEquals(new int[] { 2, 3 }, iut.getParamNum());
		//
		List<NumericInfo> pInfo = iut.getParameterList();
		Assert.assertEquals(3, pInfo.size());
		//
		Assert.assertEquals(IndicatorBuilder.PREFIX + "p1", pInfo.get(0).getName());
		Assert.assertEquals(11, pInfo.get(0).getValue());
		Assert.assertEquals(1, pInfo.get(0).getMin());
		Assert.assertEquals(500, pInfo.get(0).getMax());
		Assert.assertEquals(0, pInfo.get(0).getDp());
		//
		Assert.assertEquals(IndicatorBuilder.PREFIX + "p2", pInfo.get(1).getName());
		Assert.assertEquals(12, pInfo.get(1).getValue());
		Assert.assertEquals(2, pInfo.get(1).getMin());
		Assert.assertEquals(22, pInfo.get(1).getMax());
		Assert.assertEquals(0, pInfo.get(1).getDp());
		//
		Assert.assertEquals(IndicatorBuilder.PREFIX + "p3", pInfo.get(2).getName());
		Assert.assertEquals(13, pInfo.get(2).getValue());
		Assert.assertEquals(3, pInfo.get(2).getMin());
		Assert.assertEquals(23, pInfo.get(2).getMax());
		Assert.assertEquals(2, pInfo.get(2).getDp());
		Assert.assertEquals(pInfo.get(1), pInfo.get(2).getGt());

	}
	@Test
	public void testMissingAnnotation() {
		class Dum extends DummyFn {
			public Dum() {
			}
		}
		try {
			new IndicatorBuilder(Dum.class);
			Assert.fail("should fail with IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// success
			// e.printStackTrace();
		}
	}
	@Test
	public void testParamRelationship() {
		class Dum1 extends DummyFn {
			@IndicatorFn(name = "Dum1")
			public Dum1(
					@IndicatorRelation(gt = "p2") int param3) {
			}
		}
		try {
			new IndicatorBuilder(Dum1.class);
			Assert.fail("should fail with IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// success
			// e.printStackTrace();
		}
		class Dum2 extends DummyFn {
			@IndicatorFn(name = "Dum2")
			public Dum2(
					@IndicatorParam(name = "p1", init = 11) @IndicatorRelation(gt = "p2") int param3) {
			}
		}
		try {
			new IndicatorBuilder(Dum2.class);
			Assert.fail("should fail with IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// success
			// e.printStackTrace();
		}
	}
	static class Dum extends DummyFn {
		static Dum fn;
		int p1, p2, p3;
		Function f1, f2, f3;
		@IndicatorFn(name = "Dum")
		public Dum(
				@IndicatorInput Function in1,
				@IndicatorInput(req = "c2") Function in2,
				@IndicatorInput(req = "c3") Function in3,
				@IndicatorParam(name = "p1", init = 1) int param1,
				@IndicatorParam(name = "p2", init = 2) int param2,
				@IndicatorParam(name = "p3", init = 3) int param3) {
			f1 = in1;
			f2 = in2;
			f3 = in3;
			p1 = param1;
			p2 = param2;
			p3 = param3;
			fn = this;
		}
	}

	@Test
	public void testBuild() {
		DataSheet src = new DataSheet("src", "", Arrays.asList("c1", "c2"));
		IndicatorBuilder iut = new IndicatorBuilder(Dum.class);
		List<NumericInfo> pInfo = iut.getParameterList();
		// let things as default
		iut.build(src, pInfo);
		Assert.assertEquals(1, Dum.fn.p1);
		Assert.assertEquals(2, Dum.fn.p2);
		Assert.assertEquals(3, Dum.fn.p3);
		// change parameter values
		pInfo.get(0).setValue(4);
		pInfo.get(1).setValue(5);
		pInfo.get(2).setValue(6);
		iut.build(src, pInfo);
		Assert.assertEquals(4, Dum.fn.p1);
		Assert.assertEquals(5, Dum.fn.p2);
		Assert.assertEquals(6, Dum.fn.p3);
	}
	static class DumDefCol extends DummyFn {
		Function f1;
		@IndicatorFn(name = "Dum")
		public DumDefCol(
				@IndicatorInput Function in) {
			f1 = in;
		}
		@Override
		public void last() {
			set(f1.get());
		}
	}
	@Test
	public void testUsingDefaultColumn() {
		DataSheet src = new DataSheet("src", "", Arrays.asList("c1", "c2"));
		src.nextRow();
		src.save(new double[] { 1.234, 2.234 });
		// default column
		IndicatorBuilder iut = new IndicatorBuilder(DumDefCol.class);
		WorkSheet ws = iut.build(src, iut.getParameterList());
		src.onUpdate();
		Assert.assertEquals(1.234, ws.getColumn().getValue(ws.getEndRowNum()), 0.0000000001);

		// match column

		// column not found

	}
	static class DumNonExistCol extends DummyFn {
		Function f1;
		@IndicatorFn(name = "Dum")
		public DumNonExistCol(
				@IndicatorInput(req = "c3") Function in) {
			f1 = in;
		}
		@Override
		public void last() {
			set(f1.get());
		}
	}
	@Test
	public void testUsingNonExistColumn() {
		DataSheet src = new DataSheet("src", "", Arrays.asList("c1", "c2"));
		src.nextRow();
		src.save(new double[] { 1.234, 2.234 });
		// default column
		IndicatorBuilder iut = new IndicatorBuilder(DumNonExistCol.class);
		WorkSheet ws = iut.build(src, iut.getParameterList());
		src.onUpdate();
		Assert.assertEquals(1.234, ws.getColumn().getValue(ws.getEndRowNum()), 0.0000000001);

		// match column

		// column not found

	}
	static class DumExistCol extends DummyFn {
		Function f1;
		@IndicatorFn(name = "Dum")
		public DumExistCol(
				@IndicatorInput(req = "c2") Function in) {
			f1 = in;
		}
		@Override
		public void last() {
			set(f1.get());
		}
	}
	@Test
	public void testUsingExistColumn() {
		DataSheet src = new DataSheet("src", "", Arrays.asList("c1", "c2"));
		src.nextRow();
		src.save(new double[] { 1.234, 2.234 });
		// default column
		IndicatorBuilder iut = new IndicatorBuilder(DumExistCol.class);
		WorkSheet ws = iut.build(src, iut.getParameterList());
		src.onUpdate();
		Assert.assertEquals(2.234, ws.getColumn().getValue(ws.getEndRowNum()), 0.0000000001);

		// match column

		// column not found

	}
}