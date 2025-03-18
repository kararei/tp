package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.trip.Trip;

/**
 * Unmodifiable view of a trip book
 */
public interface ReadOnlyTripBook {

    /**
     * Returns an unmodifiable view of the trips list.
     * This list will not contain any duplicate trips.
     */
    ObservableList<Trip> getTripList();
}
