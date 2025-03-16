package seedu.address.model.trip;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ItineraryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Itinerary(null));
    }

    @Test
    public void constructor_invalidItinerary_throwsIllegalArgumentException() {
        String invalidItinerary = "   "; // only whitespace
        assertThrows(IllegalArgumentException.class, () -> new Itinerary(invalidItinerary));
    }

    @Test
    public void isValidItinerary() {
        // null itinerary
        assertFalse(Itinerary.isValidItinerary(null));

        // invalid itinerary
        assertFalse(Itinerary.isValidItinerary("")); // empty string
        assertFalse(Itinerary.isValidItinerary("    ")); // spaces only

        // valid itinerary
        assertTrue(Itinerary.isValidItinerary("Day 1: City tour")); // simple itinerary
        assertTrue(Itinerary.isValidItinerary("Visit museums and parks")); // activity description
        assertTrue(Itinerary.isValidItinerary("9:00 - Check out from hotel, 10:30 - Flight to Paris")); // with times
        assertTrue(Itinerary.isValidItinerary("Day 1-3: Rome\nDay 4-6: Florence\nDay 7-10: Venice")); // with newlines
        assertTrue(Itinerary.isValidItinerary("Morning: Eiffel Tower, Afternoon: Louvre Museum")); // with colons
        assertTrue(Itinerary.isValidItinerary("Visit Café de Flore & Notre-Dame")); // with special characters
        assertTrue(Itinerary.isValidItinerary(
                "1. Breakfast at hotel 2. City tour 3. Shopping at local market")); // numbered list
    }

    @Test
    public void equals() {
        Itinerary itinerary = new Itinerary("Day 1: City tour");

        // same values -> returns true
        assertTrue(itinerary.equals(new Itinerary("Day 1: City tour")));

        // same object -> returns true
        assertTrue(itinerary.equals(itinerary));

        // null -> returns false
        assertFalse(itinerary.equals(null));

        // different types -> returns false
        assertFalse(itinerary.equals(5.0f));

        // different values -> returns false
        assertFalse(itinerary.equals(new Itinerary("Day 2: Beach day")));
    }
}
