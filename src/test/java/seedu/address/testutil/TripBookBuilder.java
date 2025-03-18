package seedu.address.testutil;

import java.util.List;

import seedu.address.model.TripBook;
import seedu.address.model.trip.Trip;

/**
 * A utility class to help with building TripBook objects.
 */
public class TripBookBuilder {

    private TripBook tripBook;

    public TripBookBuilder() {
        tripBook = new TripBook();
    }

    public TripBookBuilder(TripBook tripBook) {
        this.tripBook = tripBook;
    }

    /**
     * Adds a new {@code Trip} to the {@code TripBook} that we are building.
     */
    public TripBookBuilder withTrip(Trip trip) {
        tripBook.addTrip(trip);
        return this;
    }

    /**
     * Sets the {@code TripBook} we are building to use the {@code trips} argument.
     */
    public TripBookBuilder withTrips(List<Trip> trips) {
        tripBook.setTrips(trips);
        return this;
    }

    public TripBook build() {
        return tripBook;
    }
}
