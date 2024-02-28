package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.Days.MTH;
import static com.orangeandbronze.enlistment.Days.WS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.orangeandbronze.enlistment.exceptions.RoomConflictException;
import org.junit.Test;

public class PeriodTest {

    @Test
    public void check_valid_periods(){
        //Given 4 valid periods
        //When they are initialized
        //No exception is thrown
        assertDoesNotThrow(()->{
            Period period1 = new Period(830,900);
            Period period2 = new Period(900, 1200);
            Period period3 = new Period(1430, 1630);
            Period period4 = new Period(900, 1030);
        });
    }

    @Test
    public void check_invalid_periods(){
        assertThrows(IllegalArgumentException.class, ()->{
            //Given 4 periods that are invalid: invalid start time, invalid end time, valid increment, invalid period
            Period period1 = new Period(815, 1030);
            Period period2 = new Period(1000, 1930);
            Period period3 = new Period (1230, 1250);
            Period period4 = new Period (1230, 1215);
        });
    }

    @Test
    public void test_overlap_different_num_students_no_exception() {
        Period period1 = new Period(830, 900);
        Period period2 = new Period(900, 930);
        Section sec1 = new Section("A", new Schedule(MTH, new Period(830, 1200)), new Room("A1", 2), new Subject("CCPROG1", 3));
        Section sec2 = new Section("B", new Schedule (MTH, new Period(1030, 1300)), new Room("A2", 2), new Subject("CCICOMP", 3));
        sec2.addStudent();

        try {
            period1.checkRoomOverlaps(period2, sec1);
            period1.checkRoomOverlaps(period2, sec2);
        } catch (RoomConflictException e) {
            fail("Unexpected RoomConflictException");
        }
    }

    @Test
    public void test_no_overlap_no_exception() {
        Period period1 = new Period(830, 900);
        Period period2 = new Period(930, 1000);
        Section sec1 = new Section("A", new Schedule(MTH, new Period(830, 1200)), new Room("A1", 2), new Subject("CCPROG1", 3));

        try {
            period1.checkRoomOverlaps(period2, sec1);
        } catch (RoomConflictException e) {
            fail("Unexpected RoomConflictException");
        }
    }

    @Test
    public void test_overlap_different_rooms_no_exception() {
        Period period1 = new Period(830, 900);
        Period period2 = new Period(900, 930);
        Section sec4 = new Section("D", new Schedule(MTH, new Period(1430, 1500)), new Room("A4", 2), new Subject("STSWENG", 3, false));
        Section sec5 = new Section("E", new Schedule(WS,  new Period(1600,1630)), new Room("A5", 2), new Subject("LBYPROG", 3, true));

        try {
            period1.checkRoomOverlaps(period2, sec4);
            period1.checkRoomOverlaps(period2, sec5);
        } catch (RoomConflictException e) {
            fail("Unexpected RoomConflictException");
        }
    }
}
