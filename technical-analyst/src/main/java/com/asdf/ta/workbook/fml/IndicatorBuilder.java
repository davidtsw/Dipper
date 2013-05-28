package com.asdf.ta.workbook.fml;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.asdf.common.meta.NumericInfo;
import com.asdf.ta.function.BasicFn;
import com.asdf.ta.function.Function;
import com.asdf.ta.function.annotation.IndicatorFn;
import com.asdf.ta.function.annotation.IndicatorInput;
import com.asdf.ta.function.annotation.IndicatorParam;
import com.asdf.ta.function.annotation.IndicatorRelation;
import com.asdf.ta.workbook.Column;
import com.asdf.ta.workbook.WorkSheet;
import com.asdf.ta.workbook.impl.DataSheet;

public class IndicatorBuilder {
	static public final String PREFIX = "indicator.input.";
	private String fnName;
	final private Constructor<?> factoryMethod;
	final private List<String> fnInputList;
	final private List<NumericInfo> fnParamList;

	public IndicatorBuilder(Class<? extends Function> fnClazz) {
		fnInputList = new ArrayList<String>();
		fnParamList = new ArrayList<NumericInfo>();
		factoryMethod = tryExtractMetaData(fnClazz);
	}
	private Constructor<?> tryExtractMetaData(Class<?> clazz) {
		// TODO add more validation
		for (Constructor<?> method : clazz.getConstructors()) {
			IndicatorFn fn = method.getAnnotation(IndicatorFn.class);
			if (fn != null) {
				// 1. name
				fnName = fn.name();
				// 2. required inputs
				Annotation[][] pAnnotations = method.getParameterAnnotations();
				for (Annotation[] annotations : pAnnotations) {
					for (Annotation annotation : annotations) {
						if (annotation instanceof IndicatorInput) {
							IndicatorInput fnInput = (IndicatorInput) annotation;
							fnInputList.add(fnInput.req());
						}
					}
				}
				// 3. required params
				Map<String, NumericInfo> pMap = new HashMap<String, NumericInfo>();
				for (Annotation[] annotations : pAnnotations) {
					NumericInfo pInfo = null;
					IndicatorRelation iRel = null;
					for (Annotation annotation : annotations) {
						if (annotation instanceof IndicatorParam) {
							IndicatorParam fnParam = (IndicatorParam) annotation;
							pInfo = new NumericInfo();
							pInfo.setName(PREFIX + fnParam.name());
							pInfo.setValue(fnParam.init());
							pInfo.setMax(fnParam.max());
							pInfo.setMin(fnParam.min());
							pInfo.setDp(fnParam.dp());
							fnParamList.add(pInfo);
							pMap.put(fnParam.name(), pInfo);
						} else if (annotation instanceof IndicatorRelation) {
							iRel = (IndicatorRelation) annotation;
						}
					}
					// handle parameter's relationship
					if (iRel != null) {
						if (pInfo == null) {
							throw new IllegalArgumentException("@relationship apply on parameters only");
						}
						if (pMap.get(iRel.gt()) == null) {
							throw new IllegalArgumentException("parameter relates on non-existing parameter");
						}
						pInfo.setGt(pMap.get(iRel.gt()));
					}
				}
				return method;
			}
		}
		throw new IllegalArgumentException("factory methods is not found in " + clazz);
	}
	int[] getParamNum() {
		return new int[] { fnInputList.size(), fnParamList.size() };
	}
	public String getName() {
		return fnName;
	}
	public List<NumericInfo> getParameterList() {
		return fnParamList;
	}
	public WorkSheet build(WorkSheet src, List<NumericInfo> paramList) {
		InputCol in = selectColumnsFromSrc(src);
		Function fn = createFnInstance(in, paramList);
		DataSheet ws = new DataSheet(fnName, "", fn.getOutputs());
		Indicator fml = new Indicator(ws, fn, src, in);
		ws.setFml(fml);
		fml.onUpdate();
		src.addListener(fml);
		return ws;
	}
	private InputCol selectColumnsFromSrc(WorkSheet src) {
		List<CFn> colFnList = new ArrayList<CFn>(fnInputList.size());
		for (String colName : fnInputList) {
			colFnList.add(new CFn(src.getColumn(colName)));
		}
		InputCol in = new InputCol(colFnList);
		return in;
	}
	private Function createFnInstance(InputCol in, List<NumericInfo> paramList) {
		int constructorParamNum = in.size() + paramList.size();
		final Object[] constructorParam = new Object[constructorParamNum];
		try {
			int i = 0;
			for (; i < in.size(); i++) {
				constructorParam[i] = in.get(i);
			}
			StringBuilder paramStr = new StringBuilder();
			for (NumericInfo p : paramList) {
				constructorParam[i] = p.getValue();
				paramStr.append('-').append(p.getValue());
				i++;
			}
			Function fn = (Function) factoryMethod.newInstance(constructorParam);
			return fn;
		} catch (Exception e) {
			// logger.error("Fail to create indicator: " + builder.getName(), e);
			// throw e;
			e.printStackTrace();
		}
		return null;
	}
}

class InputCol {
	final private List<CFn> cols;
	InputCol(List<CFn> cols) {
		this.cols = cols;
	}
	void update(long pos) {
		for (CFn c : cols) {
			c.readPos(pos);
		}
	}
	int size() {
		return cols.size();
	}
	CFn get(int i) {
		return cols.get(i);
	}
}

class CFn extends BasicFn {
	final private Column src;
	CFn(Column src) {
		super("CF");
		this.src = src;
	}
	void readPos(long pos) {
		saveResult(src.getValue(pos));
	}
}