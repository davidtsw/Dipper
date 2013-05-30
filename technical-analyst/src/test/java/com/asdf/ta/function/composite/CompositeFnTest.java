package com.asdf.ta.function.composite;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.junit.Assert;
import org.junit.Test;
import com.asdf.ta.function.DummyFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.basic.Ema;
import com.asdf.ta.function.composite.Macd;

public class CompositeFnTest {
	static private String DATA_DIR = "indicator_dataset/";
	static double MARGIN = 0.00005001; // 4 decimal places

	@Test
	public void testXauDataSet1() {
		String dataSetName = "xau_set1";
		int dataSize = 1000;
		int colNum = 4;
		double[][] testData = loadData(dataSetName, dataSize, colNum);
		DummyFn o = new DummyFn();
		DummyFn h = new DummyFn();
		DummyFn l = new DummyFn();
		DummyFn c = new DummyFn();
		DummyFn[][] fnList = { { o, h, l, c }, { h, c }, { l, c }, { c } };

		Function iut = new Macd(c, 12, 26, 9);
		double[][] expResult = loadData(dataSetName + "_macd_12^26^9", dataSize, 3);

		for (int i = 0; i < dataSize; i++) {
			iut.next();
			for (int j = 0; j < colNum; j++) {
				for (DummyFn f : fnList[j]) {
					f.set(testData[i][j]);
				}
				iut.last();
			}
			Assert.assertArrayEquals("row[" + i + "]", expResult[i], iut.getAll(), MARGIN);
		}
	}
	public void testXauDataSet2() {
		String dataSetName = "xau_set1";
		int dataSize = 1000;
		int colNum = 4;
		double[][] testData = loadData(dataSetName, dataSize, colNum);
		DummyFn o = new DummyFn();
		DummyFn h = new DummyFn();
		DummyFn l = new DummyFn();
		DummyFn c = new DummyFn();
		DummyFn[][] fnList = { { o, h, l, c }, { h, c }, { l, c }, { c } };

		Function iut = new Ema(c, 14);
		double[][] expResult = loadData(dataSetName + "_ema_14", dataSize, 1);

		for (int i = 0; i < dataSize; i++) {
			iut.next();
			for (int j = 0; j < colNum; j++) {
				for (DummyFn f : fnList[j]) {
					f.set(testData[i][j]);
				}
				iut.last();
			}
			Assert.assertArrayEquals("row[" + i + "]", expResult[i], iut.getAll(), MARGIN);
		}
	}
	private double[][] loadData(String dataFileName, int row, int col) {
		double[][] data = new double[row][col];
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader()
					.getResourceAsStream(DATA_DIR + dataFileName + ".csv")));
			int lineNo = 0;
			String str = br.readLine(); // skip the first row = header
			while ((str = br.readLine()) != null) {
				String[] dataStr = str.split(",");
				for (int i = 0; i < dataStr.length; i++) {
					data[lineNo][i] = Double.parseDouble(dataStr[i]);
				}
				lineNo++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("fail to load testing data [" + dataFileName + "] - " + e.getMessage());
		}
		return data;
	}
}