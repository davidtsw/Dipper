package com.asdf.ta.function;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class BasicFnTest {

	@Test
	public void testNoDependence() {
		IUT iut = new IUT("", new Function[] {}, new Function[] {});
		// 1. when both inputs have values
		iut.next();
		// 1.1.
		iut.last();
		Assert.assertArrayEquals(iut.getCalled(), new String[] { IUT.NEXT, IUT.LAST });
		// 1.2.
		iut.last();
		Assert.assertArrayEquals(iut.getCalled(), new String[] { IUT.LAST });
	}
	@Test
	public void testDependence() {
		Function in1 = mock(Function.class);
		Function in2 = mock(Function.class);
		IUT iut = new IUT("", new Function[] { in1, in2 }, new Function[] {});

		// 1. when both inputs have values
		when(in1.get()).thenReturn(1.0);
		when(in2.get()).thenReturn(2.0);
		iut.next();
		// 1.1.
		iut.last();
		Assert.assertArrayEquals(iut.getCalled(), new String[] { IUT.NEXT, IUT.LAST });
		// 1.2.
		iut.last();
		Assert.assertArrayEquals(iut.getCalled(), new String[] { IUT.LAST });

		// 2. when one of the inputs has no value
		when(in1.get()).thenReturn(1.0);
		when(in2.get()).thenReturn(Double.NaN);
		iut.next();
		// 2.1.
		iut.last();
		Assert.assertArrayEquals(iut.getCalled(), new String[] {});
		// 2.2.
		iut.last();
		Assert.assertArrayEquals(iut.getCalled(), new String[] {});
		// 2.3. even Next again
		iut.next();
		iut.next();
		iut.last();
		Assert.assertArrayEquals(iut.getCalled(), new String[] {});

		// 3. when all inputs have values again
		when(in2.get()).thenReturn(2.0);
		// 3.1.
		iut.last();
		Assert.assertArrayEquals(iut.getCalled(), new String[] { IUT.NEXT, IUT.LAST });
		// 3.2.
		iut.last();
		Assert.assertArrayEquals(iut.getCalled(), new String[] { IUT.LAST });
	}
	@Test
	public void testComponents() {
		Function in1 = mock(Function.class);
		Function in2 = mock(Function.class);
		IUT iut = new IUT("", new Function[] {}, new Function[] { in1, in2 });
		// 1. on Next
		iut.next();
		verify(in1).next();
		verify(in2).next();
		// 2. on Last
		iut.last();
		verify(in1).last();
		verify(in2).last();
		// 3. on Reset
		iut.reset();
		verify(in1).reset();
		verify(in2).reset();
	}

	static class IUT extends BasicFn {
		static public String LAST = "doLast", NEXT = "doNext", RESET = "doReset";
		public IUT(String name, Function[] inputs, Function[] comp) {
			super(name, inputs);
			dependOn(inputs);
			consist(comp);
		}
		private List<String> calledMehotds = new ArrayList<String>(10);
		@Override
		protected void doLast() {
			calledMehotds.add(LAST);
		}
		@Override
		protected void doNext() {
			calledMehotds.add(NEXT);
		}
		@Override
		protected void doReset() {
			calledMehotds.add(RESET);
		}
		String[] getCalled() {
			String[] hist = calledMehotds.toArray(new String[0]);
			calledMehotds.clear();
			return hist;
		}
	}
}