package com.asdf.common.util;

/**
 * Fix(-size) queue is one that the oldest (head) element will be dropped when adding new element
 * into a full queue.<br>
 * Caution: NOT thread-safe!
 */
final public class FixQueue {
	final private int capacity;
	final private double[] val;
	private int head;
	private int tail;
	private boolean isFull;

	/**
	 * Creates a fix(-size) queue
	 * @param capacity max number of element the queue will hold
	 * @throws IllegalArgumentException - if the specified initial capacity is non-positive
	 */
	public FixQueue(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		}
		this.capacity = capacity;
		double[] e = new double[capacity];
		val = e;
		head = 0;
		tail = -1;
		isFull = false;
	}
	/**
	 * Adds the specified element at the end of the queue
	 * @param e the element to add
	 * @return the dropped (the head) element if the queue is full
	 */
	public double add(double e) {
		double old = 0;
		tail = inc(tail);
		if (isFull) {
			old = val[head];
			head = inc(head);
		} else {
			if (inc(tail) == head) {
				isFull = true;
			}
		}
		val[tail] = e;
		return old;
	}
	/**
	 * Updates the last element in the queue to the specified one
	 * @param e the element to replace the last element
	 * @return the original last element
	 */
	public double update(double e) {
		// FIXME fail if the array is empty (i.e. tail=-1)
		double old = val[tail];
		val[tail] = e;
		return old;
	}
	/**
	 * Retrieves and removes the first element of the queue
	 * @return the first element
	 */
	public double poll() {
		double result = 0;
		if (!isEmpty()) {
			result = val[head];
			val[head] = 0;
			head = inc(head);
			isFull = false;
		}
		return result;
	}
	/**
	 * Returns the element at the specified position in the queue
	 * @param index index of the element to return
	 * @return the element at the specified position in the queue
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 */
	public double get(int index) {
		if (index < 0 || index >= getSize()) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + getSize());
		}
		int pos = index + head;
		pos %= capacity;
		return val[pos];
	}
	/**
	 * Removes all of the elements from the queue
	 */
	public void clear() {
		for (int i = 0; i < capacity; i++) {
			val[i] = 0;
		}
		head = 0;
		tail = -1;
		isFull = false;
	}
	/**
	 * Returns if the queue is full
	 * @return if the queue is full
	 */
	public boolean isFull() {
		return isFull;
	}
	/**
	 * Returns the capacity of the queue
	 * @return the capacity of the queue
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * Returns the number of elements in the queue
	 * @return the number of elements in the queue
	 */
	public int getSize() {
		if (isFull) {
			return capacity;
		} else {
			int len = tail - head + 1;
			return len < 0 ? len + capacity : len;
		}
	}
	private boolean isEmpty() {
		return (!isFull && inc(tail) == head);
	}
	private final int inc(int i) {
		return (++i >= capacity) ? 0 : i;
	}
}