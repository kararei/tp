package seedu.address.model.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TripDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TripDate(null));
    }

    @Test
    public void constructor_invalidTripDate_throwsIllegalArgumentException() {
        // Test empty string
        assertThrows(IllegalArgumentException.class, () -> new TripDate(""));

        // Test various invalid formats
        assertThrows(IllegalArgumentException.class, () -> new TripDate("01-01-2023"));
        assertThrows(IllegalArgumentException.class, () -> new TripDate("32/1/2023"));
        assertThrows(IllegalArgumentException.class, () -> new TripDate("15/13/2023"));
        assertThrows(IllegalArgumentException.class, () -> new TripDate("29/2/2023")); // Not a leap year
        assertThrows(IllegalArgumentException.class, () -> new TripDate("ABC"));
        assertThrows(IllegalArgumentException.class, () -> new TripDate("2023/05/15"));
        assertThrows(IllegalArgumentException.class, () -> new TripDate("15.05.2023"));
    }

    @Test
    public void constructor_validTripDate_success() {
        // Test with various valid formats
        new TripDate("1/1/2023"); // Minimal format
        new TripDate("31/12/2023"); // End of year
        new TripDate("29/2/2024"); // Leap year
        new TripDate("15/6/2025"); // Future date
        new TripDate("  25/12/2025  "); // With whitespace

        // Test with single digit day/month
        TripDate tripDate = new TripDate("5/8/2023");
        assertEquals(LocalDate.of(2023, 8, 5), tripDate.date);
    }

    @Test
    public void isValidTripDate() {
        // null trip date
        assertFalse(TripDate.isValidTripDate(null));

        // invalid trip dates
        assertFalse(TripDate.isValidTripDate("")); // empty string
        assertFalse(TripDate.isValidTripDate("    ")); // spaces only
        assertFalse(TripDate.isValidTripDate("15-05-2023")); // wrong format
        assertFalse(TripDate.isValidTripDate("2023/05/15")); // wrong format
        assertFalse(TripDate.isValidTripDate("15.05.2023")); // wrong format
        assertFalse(TripDate.isValidTripDate("32/1/2023")); // invalid day
        assertFalse(TripDate.isValidTripDate("15/13/2023")); // invalid month
        assertFalse(TripDate.isValidTripDate("29/2/2023")); // not leap year
        assertFalse(TripDate.isValidTripDate("ABC")); // non-numeric
        assertFalse(TripDate.isValidTripDate("1/1/10000")); // year too large
        assertFalse(TripDate.isValidTripDate("0/1/2023")); // day zero
        assertFalse(TripDate.isValidTripDate("1/0/2023")); // month zero
        assertFalse(TripDate.isValidTripDate("1/1/2101")); // >2100
        assertFalse(TripDate.isValidTripDate("1/1/1949")); // <1950

        // valid trip dates
        assertTrue(TripDate.isValidTripDate("1/1/2100")); // minimal format
        assertTrue(TripDate.isValidTripDate("1/1/1950")); // minimal format
        assertTrue(TripDate.isValidTripDate("1/1/2023")); // minimal format
        assertTrue(TripDate.isValidTripDate("31/12/2023")); // end of year
        assertTrue(TripDate.isValidTripDate("29/2/2024")); // leap year
        assertTrue(TripDate.isValidTripDate("15/6/2025")); // future date
        assertTrue(TripDate.isValidTripDate("  25/12/2023  ")); // with whitespace
        assertTrue(TripDate.isValidTripDate("5/8/2023")); // single digit day/month
    }

    @Test
    public void equals() {
        TripDate tripDate = new TripDate("15/6/2023");

        // same values -> returns true
        assertTrue(tripDate.equals(new TripDate("15/6/2023")));

        // same object -> returns true
        assertTrue(tripDate.equals(tripDate));

        // null -> returns false
        assertFalse(tripDate.equals(null));

        // different types -> returns false
        assertFalse(tripDate.equals(5.0f));

        // different values -> returns false
        assertFalse(tripDate.equals(new TripDate("16/6/2023"))); // different day
        assertFalse(tripDate.equals(new TripDate("15/7/2023"))); // different month
        assertFalse(tripDate.equals(new TripDate("15/6/2024"))); // different year
        assertFalse(tripDate.equals(new TripDate("1/1/2023"))); // completely different
    }

    @Test
    public void toString_returnsDateString() {
        TripDate tripDate = new TripDate("15/6/2023");
        assertEquals("15/6/2023", tripDate.toString());

        // Test with single digit day/month
        TripDate tripDate2 = new TripDate("5/8/2023");
        assertEquals("5/8/2023", tripDate2.toString());
    }

    @Test
    public void hashCode_consistency() {
        TripDate tripDate1 = new TripDate("15/6/2023");
        TripDate tripDate2 = new TripDate("15/6/2023");
        assertEquals(tripDate1.hashCode(), tripDate2.hashCode());
    }
}
