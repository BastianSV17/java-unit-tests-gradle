package com.orangeandbronze.enlistment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RoomTest {
	@Test
	public void test_hashcode_equal_room_objects() {
		Room room1 = new Room("Room1", 10);
		Room room2 = new Room("Room1", 10);
		assertEquals(room1.hashCode(), room2.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_blank_roomname() {
		new Room("", 10);
	}

	@Test
	public void test_isfull_false_less() {
		Room room = new Room("Room1", 10);
		assertFalse(room.isFull(5));
	}

	@Test
	public void test_hashCode_not_equal_room_objects() {
		Room room1 = new Room("Room1", 10);
		Room room2 = new Room("Room2", 20);
		assertNotEquals(room1.hashCode(), room2.hashCode());
	}

	@Test
	public void test_isFull_returns_true_when_no_of_students_equal_capacity() {
		Room room = new Room("Room1", 10);
		assertTrue(room.isFull(10));
	}
}
