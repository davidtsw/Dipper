import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import com.asdf.ta.function.basic.EmaTest;
import com.asdf.ta.function.basic.SdTest;
import com.asdf.ta.function.basic.SmaTest;
import com.asdf.ta.function.basic.WsTest;
import com.asdf.ta.function.composite.BbTest;
import com.asdf.ta.function.composite.MacdTest;
import com.asdf.ta.function.composite.RsiTest;
import com.asdf.ta.function.composite.SsTest;
import com.asdf.ta.function.op.binary.OpDivideTest;
import com.asdf.ta.function.op.binary.OpGtTest;
import com.asdf.ta.function.op.binary.OpLtTest;
import com.asdf.ta.function.op.binary.OpMinusTest;
import com.asdf.ta.function.op.binary.OpMultiplyTest;
import com.asdf.ta.function.op.binary.OpPlusTest;
import com.asdf.ta.function.op.ternary.OpIfElseTest;
import com.asdf.ta.workbook.fml.IndicatorBuilderTest;
import com.asdf.ta.workbook.fml.IndicatorTest;
import com.asdf.ta.workbook.fml.TickSeriesTest;
import com.asdf.ta.workbook.fml.TimeSeriesTest;

/*
 * checking test coverage only
 */
public class CoverageTest {
	// public static void main(String[] args) {
	// new CoverageTest().test();
	// }
	@Test
	public void test() {
		Result rs = JUnitCore.runClasses(new Class[] {
				/* OpFn */
				OpPlusTest.class,
				OpMinusTest.class,
				OpMultiplyTest.class,
				OpDivideTest.class,
				OpGtTest.class,
				OpLtTest.class,
				OpIfElseTest.class,
				/* BasicFn */
				SmaTest.class,
				EmaTest.class,
				SdTest.class,
				WsTest.class,
				/* CompositeFn */
				BbTest.class,
				MacdTest.class,
				SsTest.class,
				RsiTest.class,
				/* Fml */
				IndicatorTest.class,
				IndicatorBuilderTest.class,
				TickSeriesTest.class,
				TimeSeriesTest.class,
		});
		// check testing result
		for (Failure f : rs.getFailures()) {
			System.out.println(f);
		}
		Assert.assertEquals(0, rs.getFailureCount());
	}
}