package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTripBook;
import seedu.address.model.TripBook;
import seedu.address.model.trip.Trip;

/**
 * An Immutable TripBook that is serializable to JSON format.
 */
@JsonRootName(value = "tripbook")
class JsonSerializableTripBook {

    public static final String MESSAGE_DUPLICATE_TRIP = "Trips list contains duplicate trip(s).";

    private final List<JsonAdaptedTrip> trips = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTripBook} with the given trips.
     */
    @JsonCreator
    public JsonSerializableTripBook(@JsonProperty("trips") List<JsonAdaptedTrip> trips) {
        this.trips.addAll(trips);
    }

    /**
     * Converts a given {@code ReadOnlyTripBook} into this class for Json use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTripBook}.
     */
    public JsonSerializableTripBook(ReadOnlyTripBook source) {
        trips.addAll(source.getTripList().stream().map(JsonAdaptedTrip::new).collect(Collectors.toList()));
    }

    /**
     * Converts this trip book into the model's {@code TripBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TripBook toModelType() throws IllegalValueException {
        TripBook tripBook = new TripBook();
        for (JsonAdaptedTrip jsonAdaptedTrip : trips) {
            Trip trip = jsonAdaptedTrip.toModelType();
            if (tripBook.hasTrip(trip)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRIP);
            }
            tripBook.addTrip(trip);
        }
        return tripBook;
    }
}
