package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.UniqueTripList;

/**
 * Wraps all data at the trip-book level
 * Duplicates are not allowed (by .isSameTrip comparison)
 */
public class TripBook implements ReadOnlyTripBook {

    private final UniqueTripList trips;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        trips = new UniqueTripList();
    }

    public TripBook() {}

    /**
     * Creates a TripBook using the Trips in the {@code toBeCopied}
     */
    public TripBook(ReadOnlyTripBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the trip list with {@code trips}.
     * {@code trips} must not contain duplicate trips.
     */
    public void setTrips(List<Trip> trips) {
        this.trips.setTrips(trips);
    }

    /**
     * Resets the existing data of this {@code TripBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTripBook newData) {
        requireNonNull(newData);

        setTrips(newData.getTripList());
    }

    //// trip-level operations

    /**
     * Returns true if a trip with the same identity as {@code trip} exists in the trip book.
     */
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return trips.contains(trip);
    }

    /**
     * Adds a trip to the trip book.
     * The trip must not already exist in the trip book.
     */
    public void addTrip(Trip p) {
        trips.add(p);
    }

    /**
     * Replaces the given trip {@code target} in the list with {@code editedTrip}.
     * {@code target} must exist in the trip book.
     * The trip identity of {@code editedTrip} must not be the same as another existing trip in the trip book.
     */
    public void setTrip(Trip target, Trip editedTrip) {
        requireNonNull(editedTrip);

        trips.setTrip(target, editedTrip);
    }

    /**
     * Removes {@code key} from this {@code TripBook}.
     * {@code key} must exist in the trip book.
     */
    public void removeTrip(Trip key) {
        trips.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("trips", trips)
                .toString();
    }

    @Override
    public ObservableList<Trip> getTripList() {
        return trips.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TripBook)) {
            return false;
        }

        TripBook otherTripBook = (TripBook) other;
        return trips.equals(otherTripBook.trips);
    }

    @Override
    public int hashCode() {
        return trips.hashCode();
    }
}
