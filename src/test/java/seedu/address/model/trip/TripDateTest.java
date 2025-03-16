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
        // Empty string
        final String invalidTripDate = "";
        assertThrows(IllegalArgumentException.class, () -> new TripDate(invalidTripDate));

        // Invalid format
        final String invalidTripDate2 = "01-01-2023";
        assertThrows(IllegalArgumentException.class, () -> new TripDate(invalidTripDate2));

        // Invalid date
        final String invalidTripDate3 = "32/1/2023";
        assertThrows(IllegalArgumentException.class, () -> new TripDate(invalidTripDate3));
    }

    @Test
    public void constructor_validTripDate_success() {
        // Test with various valid formats
        TripDate tripDate1 = new TripDate("1/1/2023");
        assertEquals(LocalDate.of(2023, 1, 1), tripDate1.date);

        TripDate tripDate2 = new TripDate("15/6/2024");
        assertEquals(LocalDate.of(2024, 6, 15), tripDate2.date);

        // Test with leading/trailing whitespace
        TripDate tripDate3 = new TripDate("  25/12/2025  ");
        assertEquals(LocalDate.of(2025, 12, 25), tripDate3.date);
    }

    @Test
    public void isValidTripDate() {
        // null trip date
        assertFalse(TripDate.isValidTripDate(null));

        // invalid trip dates
        assertFalse(TripDate.isValidTripDate("")); // empty string
        assertFalse(TripDate.isValidTripDate("    ")); // spaces only
        assertFalse(TripDate.isValidTripDate("15-05-2023")); // wrong format with hyphens
        assertFalse(TripDate.isValidTripDate("2023/05/15")); // wrong format (yyyy/MM/dd)
        assertFalse(TripDate.isValidTripDate("15.05.2023")); // wrong format with dots
        assertFalse(TripDate.isValidTripDate("32/1/2023")); // invalid day
        assertFalse(TripDate.isValidTripDate("15/13/2023")); // invalid month
        assertFalse(TripDate.isValidTripDate("ABC")); // non-numeric

        // valid trip dates
        assertTrue(TripDate.isValidTripDate("1/1/2023")); // minimal format
        assertTrue(TripDate.isValidTripDate("31/12/2023")); // end of year
        assertTrue(TripDate.isValidTripDate("29/2/2024")); // leap year
        assertTrue(TripDate.isValidTripDate("15/6/2025")); // future date
        assertTrue(TripDate.isValidTripDate("  25/12/2023  ")); // with whitespace
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
    }

    @Test
    public void toString_returnsDateString() {
        TripDate tripDate = new TripDate("15/6/2023");
        assertEquals(LocalDate.of(2023, 6, 15).toString(), tripDate.toString());
    }
}
