package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTrips.PARIS;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Name;
import seedu.address.model.trip.Accommodation;
import seedu.address.model.trip.Itinerary;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.TripDate;
import seedu.address.model.trip.TripName;

public class JsonAdaptedTripTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ACCOMMODATION = " ";
    private static final String INVALID_ITINERARY = " ";
    private static final String INVALID_DATE = "32/13/2025";
    private static final String INVALID_CUSTOMER_NAME = "R@chel";

    private static final String VALID_NAME = PARIS.getName().toString();
    private static final String VALID_ACCOMMODATION = PARIS.getAccommodation().toString();
    private static final String VALID_ITINERARY = PARIS.getItinerary().toString();
    private static final String VALID_DATE = PARIS.getDate().toString();
    private static final List<String> VALID_CUSTOMER_NAMES = PARIS.getCustomerNames().stream()
            .map(Name::toString)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTripDetails_returnsTrip() throws Exception {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_NAME, VALID_ACCOMMODATION, VALID_ITINERARY,
                VALID_DATE, VALID_CUSTOMER_NAMES);
        Trip expectedTrip = PARIS;
        assertEquals(expectedTrip, trip.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(INVALID_NAME, VALID_ACCOMMODATION, VALID_ITINERARY,
                VALID_DATE, VALID_CUSTOMER_NAMES);
        String expectedMessage = TripName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(null, VALID_ACCOMMODATION, VALID_ITINERARY,
                VALID_DATE, VALID_CUSTOMER_NAMES);
        String expectedMessage = String.format(JsonAdaptedTrip.MISSING_FIELD_MESSAGE_FORMAT,
                TripName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_invalidAccommodation_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_NAME, INVALID_ACCOMMODATION, VALID_ITINERARY,
                VALID_DATE, VALID_CUSTOMER_NAMES);
        String expectedMessage = Accommodation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullAccommodation_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_NAME, null, VALID_ITINERARY,
                VALID_DATE, VALID_CUSTOMER_NAMES);
        String expectedMessage = String.format(JsonAdaptedTrip.MISSING_FIELD_MESSAGE_FORMAT,
                Accommodation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_invalidItinerary_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_NAME, VALID_ACCOMMODATION, INVALID_ITINERARY,
                VALID_DATE, VALID_CUSTOMER_NAMES);
        String expectedMessage = Itinerary.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullItinerary_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_NAME, VALID_ACCOMMODATION, null,
                VALID_DATE, VALID_CUSTOMER_NAMES);
        String expectedMessage = String.format(JsonAdaptedTrip.MISSING_FIELD_MESSAGE_FORMAT,
                Itinerary.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_NAME, VALID_ACCOMMODATION, VALID_ITINERARY,
                INVALID_DATE, VALID_CUSTOMER_NAMES);
        String expectedMessage = TripDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_NAME, VALID_ACCOMMODATION, VALID_ITINERARY,
                null, VALID_CUSTOMER_NAMES);
        String expectedMessage = String.format(JsonAdaptedTrip.MISSING_FIELD_MESSAGE_FORMAT,
                TripDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_invalidCustomerName_throwsIllegalValueException() {
        List<String> invalidCustomerNames = List.of(INVALID_CUSTOMER_NAME);
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_NAME, VALID_ACCOMMODATION, VALID_ITINERARY,
                VALID_DATE, invalidCustomerNames);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }
}
