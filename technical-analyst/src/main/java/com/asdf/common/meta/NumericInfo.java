package com.asdf.common.meta;

/**
 * This class represents the numeric information
 */
public class NumericInfo extends PropertyInfo {
	private int defValue, value;
	private int max;
	private int min;
	private int dp;
	private NumericInfo gt;
	private double power = 1;
	private double step = 1;

	/**
	 * Returns the value
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Returns the double value (means the d.p. applied)
	 * @return the double value
	 */
	public double getDoubleValue() {
		return value * step;
	}
	/**
	 * Sets the value
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/**
	 * Sets the default value
	 * @param value the value to set
	 */
	public void setDefaultValue(int value) {
		this.defValue = value;
	}
	/**
	 * Returns the upper limit of allowed arguments
	 * @return the upper limit of allowed arguments
	 */
	public int getMax() {
		return max;
	}
	/**
	 * Sets the upper limit of the argument
	 * @param max the upper limit to set
	 */
	public void setMax(int max) {
		this.max = max;
	}
	/**
	 * Returns the lower limit of allowed arguments
	 * @return the lower limit of allowed arguments
	 */
	public int getMin() {
		return min;
	}
	/**
	 * Sets the lower limit of the argument
	 * @param min the lower limit to set
	 */
	public void setMin(int min) {
		this.min = min;
	}
	/**
	 * Returns the decimal place of the value
	 * @return the decimal place of the value
	 */
	public int getDp() {
		return dp;
	}
	/**
	 * Sets the decimal place of the value
	 * @param dp the decimal place to set
	 */
	public void setDp(int dp) {
		this.dp = dp;
		power = Math.pow(10, dp);
		step = 1 / power;

	}
	@Override
	public String toString() {
		return "NumericInfo " + getName() + ":" + value + " [" + min + "," + max + "]";
	}
	/**
	 * Returns the numeric info than which this one should be greater
	 * @return the numeric info than which this one should be greater
	 */
	public NumericInfo getGt() {
		return gt;
	}
	/**
	 * Set the numeric info than which this one should be greater
	 * @param smallerOne the numeric info than which this one should be greater
	 */
	public void setGt(NumericInfo smallerOne) {
		this.gt = smallerOne;
	}
	@Override
	public void reset() {
		value = defValue;
	}
}