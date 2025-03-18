package seedu.address.model.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTrips.PARIS;
import static seedu.address.testutil.TypicalTrips.TOKYO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.trip.exceptions.DuplicateTripException;
import seedu.address.model.trip.exceptions.TripNotFoundException;
import seedu.address.testutil.TripBuilder;

public class UniqueTripListTest {
    private final UniqueTripList uniqueTripList = new UniqueTripList();

    @Test
    public void contains_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.contains(null));
    }

    @Test
    public void contains_tripNotInList_returnsFalse() {
        assertFalse(uniqueTripList.contains(PARIS));
    }

    @Test
    public void contains_tripInList_returnsTrue() {
        uniqueTripList.add(PARIS);
        assertTrue(uniqueTripList.contains(PARIS));
    }

    @Test
    public void contains_tripWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTripList.add(PARIS);
        Trip editedParis = new TripBuilder(PARIS).withAccommodation("Different Hotel")
                .withItinerary("Different Itinerary").build();
        assertTrue(uniqueTripList.contains(editedParis));
    }

    @Test
    public void add_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.add(null));
    }

    @Test
    public void add_duplicateTrip_throwsDuplicateTripException() {
        uniqueTripList.add(PARIS);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.add(PARIS));
    }

    @Test
    public void setTrip_nullTargetTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrip(null, PARIS));
    }

    @Test
    public void setTrip_nullEditedTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrip(PARIS, null));
    }

    @Test
    public void setTrip_targetTripNotInList_throwsTripNotFoundException() {
        assertThrows(TripNotFoundException.class, () -> uniqueTripList.setTrip(PARIS, PARIS));
    }

    @Test
    public void setTrip_editedTripIsSameTrip_success() {
        uniqueTripList.add(PARIS);
        uniqueTripList.setTrip(PARIS, PARIS);
        UniqueTripList expectedList = new UniqueTripList();
        expectedList.add(PARIS);
        assertEquals(expectedList, uniqueTripList);
    }

    @Test
    public void setTrip_editedTripHasSameIdentity_success() {
        uniqueTripList.add(PARIS);
        Trip editedParis = new TripBuilder(PARIS).withAccommodation("Different Hotel")
                .withItinerary("Different Itinerary").build();
        uniqueTripList.setTrip(PARIS, editedParis);
        UniqueTripList expectedList = new UniqueTripList();
        expectedList.add(editedParis);
        assertEquals(expectedList, uniqueTripList);
    }

    @Test
    public void setTrip_editedTripHasDifferentIdentity_success() {
        uniqueTripList.add(PARIS);
        uniqueTripList.setTrip(PARIS, TOKYO);
        UniqueTripList expectedList = new UniqueTripList();
        expectedList.add(TOKYO);
        assertEquals(expectedList, uniqueTripList);
    }

    @Test
    public void setTrip_editedTripHasNonUniqueIdentity_throwsDuplicateTripException() {
        uniqueTripList.add(PARIS);
        uniqueTripList.add(TOKYO);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.setTrip(PARIS, TOKYO));
    }

    @Test
    public void remove_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.remove(null));
    }

    @Test
    public void remove_tripDoesNotExist_throwsTripNotFoundException() {
        assertThrows(TripNotFoundException.class, () -> uniqueTripList.remove(PARIS));
    }

    @Test
    public void remove_existingTrip_removesTrip() {
        uniqueTripList.add(PARIS);
        uniqueTripList.remove(PARIS);
        UniqueTripList expectedList = new UniqueTripList();
        assertEquals(expectedList, uniqueTripList);
    }

    @Test
    public void setTrips_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTripList.setTrips((List<Trip>) null));
    }

    @Test
    public void setTrips_list_replacesOwnListWithProvidedList() {
        uniqueTripList.add(PARIS);
        List<Trip> tripList = Collections.singletonList(TOKYO);
        uniqueTripList.setTrips(tripList);
        UniqueTripList expectedList = new UniqueTripList();
        expectedList.add(TOKYO);
        assertEquals(expectedList, uniqueTripList);
    }

    @Test
    public void setTrips_listWithDuplicateTrips_throwsDuplicateTripException() {
        List<Trip> listWithDuplicateTrips = Arrays.asList(PARIS, PARIS);
        assertThrows(DuplicateTripException.class, () -> uniqueTripList.setTrips(listWithDuplicateTrips));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueTripList.asUnmodifiableObservableList().remove(0));
    }
}
