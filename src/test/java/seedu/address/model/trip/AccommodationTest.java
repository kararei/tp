package seedu.address.model.trip;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AccommodationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Accommodation(null));
    }

    @Test
    public void constructor_invalidAccommodation_throwsIllegalArgumentException() {
        String invalidAccommodation = "   "; // only whitespace
        assertThrows(IllegalArgumentException.class, () -> new Accommodation(invalidAccommodation));
    }

    @Test
    public void isValidAccommodation() {
        // null accommodation
        assertFalse(Accommodation.isValidAccommodation(null));

        // invalid accommodation
        assertFalse(Accommodation.isValidAccommodation("")); // empty string
        assertFalse(Accommodation.isValidAccommodation("    ")); // spaces only

        // valid accommodation
        assertTrue(Accommodation.isValidAccommodation("Hilton Hotel")); // alphabets only
        assertTrue(Accommodation.isValidAccommodation("123")); // numbers only
        assertTrue(Accommodation.isValidAccommodation("Airbnb Apartment 42")); // alphanumeric characters
        assertTrue(Accommodation.isValidAccommodation("The Grand Budapest Hotel")); // with capital letters
        assertTrue(Accommodation.isValidAccommodation(
                "Holiday Inn Express & Suites Downtown")); // with special characters
        assertTrue(Accommodation.isValidAccommodation("Le Méridien")); // with non-english characters
        assertTrue(Accommodation.isValidAccommodation(
                "Four Seasons Resort Maui at Wailea, 3900 Wailea Alanui Dr")); // long name with address
    }

    @Test
    public void equals() {
        Accommodation accommodation = new Accommodation("Hilton Hotel");

        // same values -> returns true
        assertTrue(accommodation.equals(new Accommodation("Hilton Hotel")));

        // same object -> returns true
        assertTrue(accommodation.equals(accommodation));

        // null -> returns false
        assertFalse(accommodation.equals(null));

        // different types -> returns false
        assertFalse(accommodation.equals(5.0f));

        // different values -> returns false
        assertFalse(accommodation.equals(new Accommodation("Marriott Hotel")));
    }
}
