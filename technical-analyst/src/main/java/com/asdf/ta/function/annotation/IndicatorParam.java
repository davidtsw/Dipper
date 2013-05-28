package com.asdf.ta.function.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
public @interface IndicatorParam {
	/**
	 * Returns the name of the parameter
	 * @return the name of the parameter
	 */
	public String name();
	/**
	 * Returns the default value of this parameter
	 * @return the default value of this parameter
	 */
	public int init();
	/**
	 * Returns the (inclusive) upper bound of argument values
	 * @return the (inclusive) upper bound of argument values
	 */
	public int max() default 500;
	/**
	 * Returns the (inclusive) lower bound of arguments values
	 * @return the (inclusive) lower bound of arguments values
	 */
	public int min() default 1;
	/**
	 * Returns the decimal place of arguments
	 * @return the decimal place of arguments
	 */
	public int dp() default 0;
}