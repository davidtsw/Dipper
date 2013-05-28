package com.asdf.common.meta;

/**
 * This class represents arbitrary properties information
 */
public class PropertyInfo {
	private String name;
	private boolean readOnly;
	private boolean hidden;

	/**
	 * Returns the name of this properties
	 * @return the name of this properties
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of this properties
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns if this property is read-only or not
	 * @return if this property is read-only or not
	 */
	public boolean isReadOnly() {
		return readOnly;
	}
	/**
	 * Sets if this property is read-only or not
	 * @param readOnly the read-only flag to set
	 * @return this property
	 */
	public PropertyInfo setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		return this;
	}
	/**
	 * Returns if this property is hidden or not
	 * @return if this property is hidden or not
	 */
	public boolean isHidden() {
		return hidden;
	}
	/**
	 * Sets if this property is hidden or not
	 * @param hidden the hidden flag to set
	 * @return this property
	 */
	public PropertyInfo setHidden(boolean hidden) {
		this.hidden = hidden;
		return this;
	}
	/**
	 * Resets the info to default value
	 */
	public void reset() {
		// do nothing
	}
}