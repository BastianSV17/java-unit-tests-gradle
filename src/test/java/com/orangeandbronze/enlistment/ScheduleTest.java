package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.Days.MTH;
import static com.orangeandbronze.enlistment.Days.TF;
import static com.orangeandbronze.enlistment.Days.WS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Test;

public class ScheduleTest {

	static final Schedule DEFAULT_SCHEDULE = new Schedule(MTH, new Period(830, 1000));
	static final Room DEFAULT_ROOM = new Room("A1", 2);
	static final Subject CCPROG1 = new Subject("CCPROG1", 3, false);
	static final Subject MTH101A = new Subject("MTH101A", 3, false);
	static final Subject CCPROG2 = new Subject("CCPROG2", 3, new HashSet<Subject>() {{add(CCPROG1);}}, false);
	static final Subject CSARCH2 = new Subject("CSARCH2", 3, false);
	static final Subject LBYARCH = new Subject("LBYARCH", 1, true);
	static final Subject STSWENG = new Subject("STSWENG", 3, false);
	static final Subject LBYPROG = new Subject("LBYPROG", 3, true);
	static final Subject CCICOMP = new Subject("CCICOMP", 3, true);
	static final DegreeProgram DEFAULT_PROGRAM = new DegreeProgram("CCS", new ArrayList<Subject>() {{add(CCPROG1); add(MTH101A); add(CCPROG2); add(CSARCH2); add(LBYARCH); add(STSWENG); add(LBYPROG); add(CCICOMP);}});

	@Test
	public void test_schedule_creation_with_valid_days_and_period() {
		Days days = Days.MTH;
		Period period = new Period(830, 930);
		Schedule schedule = new Schedule(days, period);

		assertEquals(days, schedule.getDays());
		assertEquals(period, schedule.getPeriod());
	}

	@Test
	public void test_schedule_overlap() {
		Days days1 = Days.MTH;
		Period period1 = new Period(830, 930);
		Schedule schedule1 = new Schedule(days1, period1);

		Days days2 = Days.TF;
		Period period2 = new Period(930, 1030);
		Schedule schedule2 = new Schedule(days2, period2);

		schedule1.checkOverlaps(schedule2);
	}

	@Test(expected = NullPointerException.class)
	public void test_period_overlap_null() {
		Period period = new Period(830, 930);

		Period other = null;

		period.checkOverlaps(other);
	}

	@Test
	public void test_different_days_or_period() {
		Period period1 = new Period(830, 930);
		Period period2 = new Period(830, 930);
		Schedule schedule1 = new Schedule(MTH, period1);
		Schedule schedule2 = new Schedule(TF, period2);
		assertFalse(schedule1.equals(schedule2));
	}

	@Test
	public void test_compare_with_null() {
		Period period1 = new Period(830, 930);
		Schedule schedule = new Schedule(MTH, period1);
		assertFalse(schedule.equals(null));
	}

	@Test
	public void test_same_object() {
		Period period1 = new Period(830, 930);
		Schedule schedule = new Schedule(WS, period1);
		assertTrue(schedule.equals(schedule));
	}
}
