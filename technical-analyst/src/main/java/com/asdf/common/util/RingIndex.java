package com.asdf.common.util;

public class RingIndex {
	final private String name;
	final private int modMask;
	final private int size;

	public RingIndex(String name, int capacity) {
		assert capacity > 0 : "#capacity<=0 (" + capacity + ")";
		this.name = name;
		size = 1 << (32 - Integer.numberOfLeadingZeros(capacity));
		modMask = size - 1;
	}
	public String getName() {
		return name;
	}
	public int size() {
		return size;
	}
	public int loc(long seq) {
		return (int) seq & modMask;
	}
}
