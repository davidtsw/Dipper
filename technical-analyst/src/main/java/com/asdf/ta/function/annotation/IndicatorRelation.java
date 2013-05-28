package com.asdf.ta.function.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
public @interface IndicatorRelation {
	/**
	 * Returns the another parameter than which this one should be greater
	 * @return the another parameter than which this one should be greater
	 */
	public String gt() default "";
}