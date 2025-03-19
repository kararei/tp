package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTrips.PARIS;
import static seedu.address.testutil.TypicalTrips.TOKYO;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TripBook;

public class JsonSerializableTripBookTest {
    private static final List<JsonAdaptedTrip> VALID_TRIPS = List.of(
            new JsonAdaptedTrip(PARIS),
            new JsonAdaptedTrip(TOKYO));

    @Test
    public void toModelType_validTripBook_returnsTripBook() throws Exception {
        JsonSerializableTripBook dataFromFile = new JsonSerializableTripBook(VALID_TRIPS);
        TripBook tripBookFromFile = dataFromFile.toModelType();
        TripBook typicalTripBook = new TripBook();
        typicalTripBook.addTrip(PARIS);
        typicalTripBook.addTrip(TOKYO);
        assertEquals(tripBookFromFile, typicalTripBook);
    }

    @Test
    public void toModelType_duplicateTrips_throwsIllegalValueException() {
        JsonSerializableTripBook dataFromFile = new JsonSerializableTripBook(
                List.of(new JsonAdaptedTrip(PARIS), new JsonAdaptedTrip(PARIS)));
        assertThrows(IllegalValueException.class, JsonSerializableTripBook.MESSAGE_DUPLICATE_TRIP,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidTrip_throwsIllegalValueException() {
        JsonAdaptedTrip invalidTrip = new JsonAdaptedTrip("", "", "", "", List.of(), "");
        JsonSerializableTripBook dataFromFile = new JsonSerializableTripBook(List.of(invalidTrip));
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}
