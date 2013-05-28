package com.asdf.common.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.asdf.common.util.OrderedMap;

public class PropertySupport {
	/* category-name-item mapping */
	private final Map<Class<? extends PropertyInfo>, OrderedMap<String, PropertyInfo>> props;

	/**
	 * Creates a property support
	 */
	public PropertySupport() {
		props = new HashMap<Class<? extends PropertyInfo>, OrderedMap<String, PropertyInfo>>();
	}
	/**
	 * Adds a property
	 * @param name the name of the item
	 * @param prop the property to set
	 */
	public void addProperty(String name, PropertyInfo prop) {
		prop.setName(name);
		OrderedMap<String, PropertyInfo> cMap = props.get(prop.getClass());
		if (cMap == null) {
			cMap = new OrderedMap<String, PropertyInfo>();
			props.put(prop.getClass(), cMap);
		}
		cMap.put(name, prop);
	}
	/**
	 * Returns a property of the specified category and name
	 * @param <T> property class implies the category
	 * @param category the category of property
	 * @param name the name of item
	 * @return property of the item, or null
	 */
	public <T> T getProperty(Class<T> category, String name) {
		OrderedMap<String, PropertyInfo> cMap = props.get(category);
		if (cMap == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		T t = (T) cMap.getByKey(name);
		return t;
	}
	/**
	 * Returns list of properties of the specified category
	 * @param <T> property class implies the category
	 * @param category the category of property
	 * @return list of items in the category, or empty list
	 */
	public <T> List<T> getProperties(Class<T> category) {
		OrderedMap<String, PropertyInfo> cMap = props.get(category);
		if (cMap == null) {
			return new ArrayList<T>();
		}
		@SuppressWarnings("unchecked")
		List<T> ts = (ArrayList<T>) cMap.getList();
		return ts;
	}
	
	public void reset() {
		for (OrderedMap<String, PropertyInfo> cMap : props.values()) {
			for (PropertyInfo info : cMap) {
				info.reset();
			}
		}
	}
}