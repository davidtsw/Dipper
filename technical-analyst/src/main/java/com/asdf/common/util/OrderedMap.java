package com.asdf.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * OrderedMap keeps the order of the values in the map, while the order may change later.
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
final public class OrderedMap<K, V> implements Iterable<V> {
	private ArrayList<K> list = new ArrayList<K>();
	private Map<K, V> map = new HashMap<K, V>();

	/**
	 * Associates the specified value with the specified key in this map
	 * @param key key with which the specified value is to be associated
	 * @param val value to be associated with the specified key
	 * @return the previous value associated with key, or null if there was no mapping for key.
	 */
	public V put(K key, V val) {
		V old = map.put(key, val);
		if (old != null) {
			list.remove(key);
		}
		list.add(key);
		return old;
	}
	/**
	 * Removes the mapping for a key from this map if it is present
	 * @param key key whose mapping is to be removed from the map
	 * @return the previous value associated with key, or null if there was no mapping for key.
	 */
	public V remove(K key) {
		V val = map.remove(key);
		list.remove(key);
		return val;
	}
	/**
	 * Returns the value to which the specified key is mapped, or null.
	 * @param key key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or null
	 */
	public V getByKey(K key) {
		return map.get(key);
	}
	/**
	 * Returns the value at the specified position in this map
	 * @param index index of the element to return
	 * @return the value at the specified position in this list
	 * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >=
	 *             size())
	 */
	public V getByIndex(int index) {
		return map.get(list.get(index));
	}
	/**
	 * Returns the number of key-value mappings in this map.
	 * @return the number of key-value mappings in this map
	 */
	public int size() {
		return list.size();
	}
	/**
	 * Returns true if this map contains a mapping for the specified key.
	 * @param key key whose presence in this map is to be tested
	 * @return if this map contains a mapping for the specified key
	 */
	public boolean contains(K key) {
		return map.containsKey(key);
	}
	/**
	 * Reorders the specified value at the specified position in this map
	 * @param key key whose associated value is to be reordered
	 * @param index index at which the specified value is to be inserted
	 * @return if success to reorder
	 */
	public boolean reOrder(K key, int index) {
		int ki = list.indexOf(key);
		if (ki >= 0 && index >= 0 && index <= size()) {
			list.remove(key);
			if (ki < index) {
				list.add(index - 1, key);
			} else {
				list.add(index, key);
			}
			return true;
		}
		return false;
	}
	/**
	 * Returns the underlying list
	 * @return the underlying list
	 */
	public List<V> getList() {
		List<V> vList = new ArrayList<V>(list.size());
		for (K k : list) {
			vList.add(map.get(k));
		}
		return vList;
	}
	/**
	 * Returns the underlying map
	 * @return the underlying map
	 */
	public Map<K, V> getMap() {
		return Collections.unmodifiableMap(map);
	}
	@Override
	public Iterator<V> iterator() {
		List<V> vList = new ArrayList<V>(list.size());
		for (K k : list) {
			vList.add(map.get(k));
		}
		return vList.iterator();
	}
}