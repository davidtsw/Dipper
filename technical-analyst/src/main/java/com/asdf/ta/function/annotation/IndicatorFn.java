package com.asdf.ta.function.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.CONSTRUCTOR)
public @interface IndicatorFn {
	/**
	 * Returns the indicator name
	 * @return the indicator name
	 */
	public String name();
//	/**
//	 * Returns the names of required columns. Empty string means accepting anything.
//	 * @return the names of required columns
//	 */
//	public String[] req() default { "" };
//	/**
//	 * Returns the names of output columns.
//	 * @return the names of output columns
//	 */
//	public String[] out() default { "value" };
}
