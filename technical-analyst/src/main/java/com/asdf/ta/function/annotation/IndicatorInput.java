package com.asdf.ta.function.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.asdf.ta.workbook.Column;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
public @interface IndicatorInput {
	/**
	 * Returns the names of required columns. Empty string means accepting anything.
	 * @return the names of required columns
	 */
	public String req() default Column.ANY;
}
