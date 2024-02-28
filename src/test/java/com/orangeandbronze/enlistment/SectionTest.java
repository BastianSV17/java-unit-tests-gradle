package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.Days.TF;
import static com.orangeandbronze.enlistment.Days.WS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SectionTest {

	@Test
	public void test_create_section_with_valid_parameters() {
		Section sec5 = new Section("E", new Schedule(WS,  new Period(1600,1630)), new Room("A5", 2), new Subject("LBYPROG", 3, true));
		assertNotNull(sec5);
	}

	@Test
	public void test_add_student_to_section_with_available_capacity() {
		Section sec3 = new Section("C", new Schedule(WS,  new Period(830, 900)), new Room("A3", 2), new Subject("LBYARCH", 1, true));

		int initialNumStudents = sec3.getNumStudents();

		sec3.addStudent();

		assertEquals(initialNumStudents + 1, sec3.getNumStudents());
	}

	@Test
	public void test_remove_student_from_section() {
		Section sec2 = new Section("B", new Schedule(TF,  new Period(1600, 1630)), new Room("A2", 2), new Subject("CSARCH2", 3, false));

		sec2.addStudent();
		int initialNumStudents = sec2.getNumStudents();

		sec2.removeStudent();

		assertEquals(initialNumStudents - 1, sec2.getNumStudents());
	}
}
