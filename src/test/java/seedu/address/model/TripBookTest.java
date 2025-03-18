package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTrips.PARIS;
import static seedu.address.testutil.TypicalTrips.TOKYO;
import static seedu.address.testutil.TypicalTrips.getTypicalTripBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.exceptions.DuplicateTripException;
import seedu.address.model.trip.exceptions.TripNotFoundException;
import seedu.address.testutil.TripBuilder;

public class TripBookTest {
    private final TripBook tripBook = new TripBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tripBook.getTripList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tripBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTripBook_replacesData() {
        TripBook newData = getTypicalTripBook();
        tripBook.resetData(newData);
        assertEquals(newData, tripBook);
    }

    @Test
    public void resetData_withDuplicateTrips_throwsDuplicateTripException() {
        List<Trip> newTrips = Arrays.asList(PARIS, PARIS);
        TripBookStub newData = new TripBookStub(newTrips);
        assertThrows(DuplicateTripException.class, () -> tripBook.resetData(newData));
    }

    @Test
    public void hasTrip_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tripBook.hasTrip(null));
    }

    @Test
    public void hasTrip_tripNotInTripBook_returnsFalse() {
        assertFalse(tripBook.hasTrip(PARIS));
    }

    @Test
    public void hasTrip_tripInTripBook_returnsTrue() {
        tripBook.addTrip(PARIS);
        assertTrue(tripBook.hasTrip(PARIS));
    }

    @Test
    public void hasTrip_tripWithSameIdentityFieldsInTripBook_returnsTrue() {
        tripBook.addTrip(PARIS);
        Trip editedParis = new TripBuilder(PARIS).withAccommodation("Different Hotel")
                .withItinerary("Different Itinerary").build();
        assertTrue(tripBook.hasTrip(editedParis));
    }

    @Test
    public void getTrips_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tripBook.getTripList().remove(0));
    }

    @Test
    public void removeTrip_tripIsRemoved() {
        tripBook.addTrip(PARIS);
        tripBook.removeTrip(PARIS);
        TripBook expectedTripBook = new TripBook();
        assertEquals(expectedTripBook, tripBook);
    }

    @Test
    public void setTrip_targetTripNotInList_throwsTripNotFoundException() {
        assertThrows(TripNotFoundException.class, () -> tripBook.setTrip(PARIS, PARIS));
    }

    @Test
    public void setTrip_editedTripIsSameTrip_success() {
        tripBook.addTrip(PARIS);
        tripBook.setTrip(PARIS, PARIS);
        TripBook expectedTripBook = new TripBook();
        expectedTripBook.addTrip(PARIS);
        assertEquals(expectedTripBook, tripBook);
    }

    @Test
    public void setTrip_editedTripHasDifferentIdentity_success() {
        tripBook.addTrip(PARIS);
        tripBook.setTrip(PARIS, TOKYO);
        TripBook expectedTripBook = new TripBook();
        expectedTripBook.addTrip(TOKYO);
        assertEquals(expectedTripBook, tripBook);
    }

    /**
     * A stub ReadOnlyTripBook whose trips list can violate interface constraints.
     */
    private static class TripBookStub implements ReadOnlyTripBook {
        private final ObservableList<Trip> trips = FXCollections.observableArrayList();

        TripBookStub(Collection<Trip> trips) {
            this.trips.setAll(trips);
        }

        @Override
        public ObservableList<Trip> getTripList() {
            return trips;
        }
    }
}
