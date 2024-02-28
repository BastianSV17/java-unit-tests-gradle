package com.orangeandbronze.enlistment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import org.junit.Test;

public class DegreeProgramTest {

	@Test
	public void test_same_programName_and_subjects_are_equal() {
		Collection<Subject> subjects1 = new HashSet<>();
		subjects1.add(new Subject("CCICOMP", 3, true));
		subjects1.add(new Subject("LBYPROG", 3, true));
		DegreeProgram degreeProgram1 = new DegreeProgram("Computer Science", subjects1);

		Collection<Subject> subjects2 = new HashSet<>();
		subjects2.add(new Subject("CCICOMP", 3, true));
		subjects2.add(new Subject("LBYPROG", 3, true));
		DegreeProgram degreeProgram2 = new DegreeProgram("Computer Science", subjects2);

		assertEquals(degreeProgram1, degreeProgram2);
	}

	@Test
	public void test_getSubjectPrerequisites() {
		Collection<Subject> prerequisites = new HashSet<>();
		prerequisites.add(new Subject("CCICOMP", 3, true));
		Subject subject = new Subject("LBYPROG", 3, prerequisites,true);
		assertTrue(subject.getPrerequisites().contains(new Subject("CCICOMP", 3)));
	}

	@Test(expected = NullPointerException.class)
	public void test_createDegreeProgramWithNullSubjects() {
		new DegreeProgram("Computer Science", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_createSubjectWithBlankSubjectID() {
		new Subject("", 4);
	}
}
