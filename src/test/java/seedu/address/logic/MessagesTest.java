package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.trip.Trip;
import seedu.address.testutil.TripBuilder;

public class MessagesTest {

    @Test
    public void formatTrip() {
        Trip trip = new TripBuilder()
                .withName("Paris 2025")
                .withAccommodation("Hotel 81")
                .withItinerary("Eat baguettes")
                .withDate("1/1/2100")
                .withCustomerNames("John Doe", "Jane Doe")
                .withNote("Customer prefers window seat")
                .build();

        String expected = "Paris 2025; Accommodation: Hotel 81; Itinerary: Eat baguettes; "
                + "Date: 1/1/2100; Customers: John Doe, Jane Doe; Note: Customer prefers window seat";
        assertEquals(expected, Messages.format(trip));
    }

    @Test
    public void formatTripWithEmptyNote() {
        Trip trip = new TripBuilder()
                .withName("Paris 2025")
                .withAccommodation("Hotel 81")
                .withItinerary("Eat baguettes")
                .withDate("1/1/2100")
                .withCustomerNames("John Doe", "Jane Doe")
                .build();

        String expected = "Paris 2025; Accommodation: Hotel 81; Itinerary: Eat baguettes; "
                + "Date: 1/1/2100; Customers: John Doe, Jane Doe; Note: ";
        assertEquals(expected, Messages.format(trip));
    }

    @Test
    public void formatTripWithSingleCustomer() {
        Trip trip = new TripBuilder()
                .withName("Paris 2025")
                .withAccommodation("Hotel 81")
                .withItinerary("Eat baguettes")
                .withDate("1/1/2100")
                .withCustomerNames("John Doe")
                .withNote("Customer prefers window seat")
                .build();

        String expected = "Paris 2025; Accommodation: Hotel 81; Itinerary: Eat baguettes; "
                + "Date: 1/1/2100; Customers: John Doe; Note: Customer prefers window seat";
        assertEquals(expected, Messages.format(trip));
    }
}
