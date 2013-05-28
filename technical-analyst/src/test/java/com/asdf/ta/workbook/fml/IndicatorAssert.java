package com.asdf.ta.workbook.fml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import com.asdf.common.meta.NumericInfo;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.FunctionTest;
import com.asdf.ta.function.annotation.IndicatorFn;
import com.asdf.ta.workbook.ColumnT;
import com.asdf.ta.workbook.WorkSheet;
import com.asdf.ta.workbook.impl.DataSheet;

public class IndicatorAssert implements FunctionTest {
	static private Set<String> FN_NAMES = new HashSet<String>();
	static private String DATA_DIR = "indicator_dataset/";
	//
	static private DataSet XAU_SET1 = new DataSet("xau_set1", 1000,
			Arrays.asList(ColumnT.CLOSE, ColumnT.LOW, ColumnT.HIGH, ColumnT.OPEN),
			new int[][] { { 0, 1, 2, 3 }, { 0, 2 }, { 0, 1 }, { 0 } });
	static private DataSet XAU_SET2 = new DataSet("xau_set2", 1000,
			Arrays.asList(ColumnT.CLOSE, ColumnT.LOW, ColumnT.HIGH, ColumnT.OPEN),
			new int[][] { { 0, 1, 2, 3 }, { 0, 2 }, { 0, 1 }, { 0 } });
	//
	static public void checkIndicator(Class<? extends Function> clazz) {
		checkFunctionSignature(clazz);
		testDataSet_xau1(clazz);
		testDataSet_xau2(clazz);
	}
	/* -------------------------------------------------------------------------------- */
	static public void checkFunctionSignature(Class<? extends Function> clazz) {

		// try create builder of the indicator
		IndicatorBuilder fBuilder = null;
		try {
			fBuilder = new IndicatorBuilder(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("fail to create the indicator builder");
		}

		// check factory methods
		int mCount = 0;
		String fnName = null;
		Constructor<?> fMethod = null;
		for (Constructor<?> method : clazz.getConstructors()) {
			IndicatorFn fa = method.getAnnotation(IndicatorFn.class);
			if (method.getAnnotation(IndicatorFn.class) != null) {
				mCount++;
				fnName = fa.name();
				fMethod = method;
			}
		}
		Assert.assertEquals("#factory method", 1, mCount);
		Assert.assertNotNull("function name", fnName);
		Assert.assertFalse("duplicated function names",
				FN_NAMES.contains(fnName));
		FN_NAMES.add(fnName);
		System.out.println("#" + FN_NAMES.size() + "-" + fnName);

		// check method's parameters
		int[] mParamNum = fBuilder.getParamNum();
		Assert.assertEquals("#method's parameter",
				mParamNum[0] + mParamNum[1], fMethod.getParameterTypes().length);
		int i = 0;
		for (; i < mParamNum[0]; i++) {
			Assert.assertTrue("The paramter should be Function: " + fMethod.getParameterTypes()[i],
					Function.class.isAssignableFrom(fMethod.getParameterTypes()[i]));
		}
		for (; i < mParamNum[0] + mParamNum[1]; i++) {
			Assert.assertTrue("The paramter should be int: " + fMethod.getParameterTypes()[i],
					int.class.isAssignableFrom(fMethod.getParameterTypes()[i]));
		}

		// check parameter's relationship
		// TODO
	}
	/* -------------------------------------------------------------------------------- */
	static public void testDataSet_xau1(Class<? extends Function> clazz) {
		textDataSet(XAU_SET1, clazz);
	}
	static public void testDataSet_xau2(Class<? extends Function> clazz) {
		textDataSet(XAU_SET2, clazz);
	}
	//
	static private void textDataSet(DataSet ds, Class<? extends Function> fnClazz) {
		System.out.println("data-set:¡@" + ds.name);
		ds.sheet.clear();
		//
		IndicatorBuilder builder = new IndicatorBuilder(fnClazz);
		File[] rsFiles = findResultSetFiles(ds.file.getParentFile(), ds.name, builder.getName());

		for (File rsFile : rsFiles) {
			System.out.println("  testcase:¡@" + rsFile.getName());
			// 0. build the iut
			List<String> rsColList = new ArrayList<String>();
			double[][] rsExpect = loadDataFromFile(rsFile, ds.size, rsColList);
			extractParametersFromFileName(rsFile.getName(), builder.getParameterList());
			WorkSheet rs = builder.build(ds.sheet, builder.getParameterList());
			// 1. pumping data
			int dsColSize = ds.colList.size();
			double[] dsRow = new double[dsColSize];
			for (int i = 0; i < ds.size; i++) {
				ds.sheet.nextRow();
				for (int j = 0; j < ds.updSeq.length; j++) {
					for (int col : ds.updSeq[j]) {
						dsRow[col] = ds.data[i][j];
					}
					ds.sheet.save(dsRow);
					ds.sheet.onUpdate();
				}
				for (int j = 0; j < rsColList.size(); j++) {
					Assert.assertEquals("1stRound[" + i + "][" + j + "]" + rs.getEndRowNum(),
							rsExpect[i][j],
							rs.getColumn(rsColList.get(j)).getValue(ds.sheet.getEndRowNum()), ROUNDING_DELTA);
				}
			}
			// 2. reset
			ds.sheet.onReload();
			for (int i = 0; i < ds.size; i++) {
				for (int j = 0; j < rsColList.size(); j++) {
					Assert.assertEquals("2ndRound[" + i + "][" + j + "]",
							rsExpect[i][j],
							rs.getColumn(rsColList.get(j)).getValue(rs.getStartRowNum() + i), ROUNDING_DELTA);
				}
			}
			// x. cleanup
			rs.delete();
			ds.sheet.clear();
		}
	}
	static private File findInputDataSetFile(String datasetName) {
		URL myTestURL = IndicatorAssert.class.getClassLoader().getResource(DATA_DIR + datasetName + ".csv");
		try {
			File df = new File(myTestURL.toURI());
			return df;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			Assert.fail("Testing data set is NOT found: " + datasetName);
		}
		return null;
	}
	static private File[] findResultSetFiles(File dir, String dsName, String fnName) {
		final String prefix = dsName + "_" + fnName + "_";
		File[] fList = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String fname) {
				return fname.startsWith(prefix);
			}
		});
		return fList;
	}
	static private void extractParametersFromFileName(String fname, List<NumericInfo> pList) {
		String[] pStr = fname.substring(fname.lastIndexOf("_") + 1, fname.indexOf(".")).split("\\^");
		for (int i = 0; i < pStr.length; i++) {
			int p = Integer.parseInt(pStr[i]);
			pList.get(i).setValue(p);
		}
	}
	static private double[][] loadDataFromFile(File dataFile, int row, List<String> colList) {
		double[][] data = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile));
			// 1. get the column names
			String str = br.readLine();
			String[] colStr = str.split(",");
			int col = colStr.length;
			for (int i = 0; i < col; i++) {
				colList.add(colStr[i]);
			}
			data = new double[row][col];
			// 2. load the data
			int lineNo = 0;
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
			Assert.fail("fail to load testing data [" + dataFile.getName() + "] - " + e.getMessage());
		}
		return data;
	}
	static private class DataSet {
		final private String name;
		final private int size;
		final private List<String> colList;
		final private int[][] updSeq;
		final private File file;
		final private double[][] data;
		final private DataSheet sheet;
		private DataSet(String name, int size, List<String> colList, int[][] updSeq) {
			this.name = name;
			this.size = size;
			this.colList = colList;
			this.updSeq = updSeq;
			file = findInputDataSetFile(name);
			data = loadDataFromFile(file, size, new ArrayList<String>());
			sheet = new DataSheet(name, "", colList);
		}
	}
}