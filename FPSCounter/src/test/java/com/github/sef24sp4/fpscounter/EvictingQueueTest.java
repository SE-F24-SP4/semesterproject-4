package com.github.sef24sp4.fpscounter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class EvictingQueueTest {
	private EvictingQueue<Integer> queue;

	@BeforeEach
	void setUp() {
		this.queue = new EvictingQueue<>(5);
	}

	@Test
	void getOldest() {
		assertThrows(NoSuchElementException.class, () -> this.queue.getOldest());
		this.queue.add(1);
		assertEquals(1, this.queue.getOldest());
		this.queue.add(2);
		this.queue.add(3);
		this.queue.add(4);
		this.queue.add(5);
		assertEquals(1, this.queue.getOldest());
		this.queue.add(6);
		assertEquals(2, this.queue.getOldest());
		this.queue.add(7);
		assertEquals(3, this.queue.getOldest());
		this.queue.add(8);
		assertEquals(4, this.queue.getOldest());
		this.queue.add(9);
		assertEquals(5, this.queue.getOldest());
		this.queue.add(10);
		assertEquals(6, this.queue.getOldest());
	}

	@Test
	void getNewest() {
		assertThrows(NoSuchElementException.class, () -> this.queue.getNewest());
		final int[] testElements = {2, 3, 34, 2, -1, 4, 12, 32, 124, 32, 98, 878, 77, 923, 712, 234, 9};

		for (final int testElement : testElements) {
			this.queue.add(testElement);
			assertEquals(testElement, this.queue.getNewest());
		}
	}

	@Test
	void clear() {
		assertTrue(this.queue.isEmpty());
		this.queue.add(1);
		assertFalse(this.queue.isEmpty());
		this.queue.clear();
		assertTrue(this.queue.isEmpty());
	}

	@Test
	void size() {
		assertEquals(0, this.queue.size());
		this.queue.add(1);
		assertEquals(1, this.queue.size());
		this.queue.add(2);
		assertEquals(2, this.queue.size());
		this.queue.add(3);
		assertEquals(3, this.queue.size());
		this.queue.add(4);
		assertEquals(4, this.queue.size());
		this.queue.add(5);
		assertEquals(5, this.queue.size());

		this.queue.add(6);
		assertEquals(5, this.queue.size());

		this.queue.add(6);
		this.queue.add(6);
		this.queue.add(6);
		this.queue.add(6);
		assertEquals(5, this.queue.size());
	}
}