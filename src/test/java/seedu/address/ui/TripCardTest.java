package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.trip.Trip;
import seedu.address.testutil.TripBuilder;

public class TripCardTest {

    @Test
    public void display() {
        Trip trip = new TripBuilder()
                .withName("Paris Adventure")
                .withDate("15/6/2024")
                .withItinerary("Visit Eiffel Tower")
                .withAccommodation("Hotel Paris")
                .withCustomerNames("Alice Tan")
                .withNote("First time in Paris")
                .build();

        TestFxmlObject nameObject = new TestFxmlObject(trip.getName().name);
        assertEquals("Paris Adventure", nameObject.getText());

        TestFxmlObject dateObject = new TestFxmlObject(trip.getDate().toString());
        assertEquals("15/6/2024", dateObject.getText());

        TestFxmlObject itineraryObject = new TestFxmlObject(trip.getItinerary().itinerary);
        assertEquals("Visit Eiffel Tower", itineraryObject.getText());

        TestFxmlObject accommodationObject = new TestFxmlObject(trip.getAccommodation().accommodation);
        assertEquals("Hotel Paris", accommodationObject.getText());

        TestFxmlObject customerObject = new TestFxmlObject(
            trip.getCustomerNames().stream().findFirst().get().fullName);
        assertEquals("Alice Tan", customerObject.getText());

        TestFxmlObject noteObject = new TestFxmlObject(trip.getNote().note);
        assertEquals("First time in Paris", noteObject.getText());
    }

    @Test
    public void testEquals() {
        Trip trip = new TripBuilder()
                .withName("Paris Trip")
                .withItinerary("Visit Eiffel Tower")
                .build();

        TestFxmlObject testObject = new TestFxmlObject(trip.getName().name);
        TestFxmlObject sameTestObject = new TestFxmlObject(trip.getName().name);
        TestFxmlObject differentTestObject = new TestFxmlObject("Different Trip");

        assertTrue(testObject.equals(testObject));
        assertFalse(testObject.equals(null));
        assertFalse(testObject.equals(2));
        assertTrue(testObject.equals(sameTestObject));
        assertFalse(testObject.equals(differentTestObject));
    }
}
