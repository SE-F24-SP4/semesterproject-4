package com.github.sef24sp4.fpscounter;

import java.util.NoSuchElementException;

/**
 * A queue of {@code maxSize} which evicts oldest elements when full.
 *
 * @param <E> The type to contain.
 */
public class EvictingQueue<E> {
	private static final int EMPTY_INDEX = -1;

	private final E[] array;

	private int oldestElementIndex = EMPTY_INDEX;
	private int newestElementIndex = EMPTY_INDEX;

	public EvictingQueue(final int maxSize) {
		this.array = (E[]) new Object[maxSize];
	}


	public boolean add(final E element) {
		final int nextIndex = this.getNextInsertionIndex();
		this.array[nextIndex] = element;

		this.newestElementIndex = nextIndex;

		if (this.oldestElementIndex == EMPTY_INDEX) {
			this.oldestElementIndex = this.newestElementIndex;
		} else if (this.oldestElementIndex == nextIndex) {
			this.oldestElementIndex = this.getNextInsertionIndex();
		}

		return true;
	}

	protected int getNextInsertionIndex() {
		return (this.newestElementIndex + 1) % this.array.length;
	}

	public E getOldest() {
		if (this.oldestElementIndex == EMPTY_INDEX) throw new NoSuchElementException("Queue is empty");
		return this.array[this.oldestElementIndex];
	}

	public E getNewest() {
		if (this.newestElementIndex == EMPTY_INDEX) throw new NoSuchElementException("Queue is empty");
		return this.array[this.newestElementIndex];
	}

	public void clear() {
		this.oldestElementIndex = EMPTY_INDEX;
		this.newestElementIndex = EMPTY_INDEX;
	}

	public boolean isEmpty() {
		return this.oldestElementIndex == EMPTY_INDEX;
	}

	public int size() {
		if (this.oldestElementIndex == EMPTY_INDEX) return 0;

		final int betweenIndexes = this.newestElementIndex - this.oldestElementIndex + 1;
		if (betweenIndexes > 0) return betweenIndexes;
		return this.array.length - betweenIndexes;
	}
}
