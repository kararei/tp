package seedu.address.model.trip;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TripNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TripName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TripName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> TripName.isValidName(null));

        // invalid name
        assertFalse(TripName.isValidName("")); // empty string
        assertFalse(TripName.isValidName(" ")); // spaces only
        assertFalse(TripName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(TripName.isValidName("europe*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TripName.isValidName("summer vacation")); // alphabets only
        assertTrue(TripName.isValidName("12345")); // numbers only
        assertTrue(TripName.isValidName("europe trip 2025")); // alphanumeric characters
        assertTrue(TripName.isValidName("Europe Tour")); // with capital letters
        assertTrue(TripName.isValidName("Grand ABC 123123123 Holiday Trip 2025")); // long names
    }

    @Test
    public void equals() {
        TripName tripName = new TripName("Europe Trip");

        // same values -> returns true
        assertTrue(tripName.equals(new TripName("Europe Trip")));

        // same object -> returns true
        assertTrue(tripName.equals(tripName));

        // null -> returns false
        assertFalse(tripName.equals(null));

        // different types -> returns false
        assertFalse(tripName.equals(5.0f));

        // different values -> returns false
        assertFalse(tripName.equals(new TripName("Asia Tour")));
    }
}
