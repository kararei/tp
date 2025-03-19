package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.contact.Name;
import seedu.address.model.trip.Accommodation;
import seedu.address.model.trip.Itinerary;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.TripDate;
import seedu.address.model.trip.TripName;

/**
 * Jackson-friendly version of {@link Trip}.
 */
class JsonAdaptedTrip {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Trip's %s field is missing!";

    private final String name;
    private final String accommodation;
    private final String itinerary;
    private final String date;
    private final List<String> customerNames = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTrip} with the given trip details.
     */
    @JsonCreator
    public JsonAdaptedTrip(@JsonProperty("name") String name, @JsonProperty("accommodation") String accommodation,
            @JsonProperty("itinerary") String itinerary, @JsonProperty("date") String date,
            @JsonProperty("customerNames") List<String> customerNames) {
        this.name = name;
        this.accommodation = accommodation;
        this.itinerary = itinerary;
        this.date = date;
        if (customerNames != null) {
            this.customerNames.addAll(customerNames);
        }
    }

    /**
     * Converts a given {@code Trip} into this class for Jackson use.
     */
    public JsonAdaptedTrip(Trip source) {
        name = source.getName().toString();
        accommodation = source.getAccommodation().toString();
        itinerary = source.getItinerary().toString();
        date = source.getDate().toString();
        customerNames.addAll(source.getCustomerNames().stream()
                .map(customer -> customer.fullName)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted trip object into the model's {@code Trip} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted trip.
     */
    public Trip toModelType() throws IllegalValueException {
        final Set<Name> modelCustomerNames = new HashSet<>();
        for (String customerName : customerNames) {
            try {
                modelCustomerNames.add(ParserUtil.parseName(customerName));
            } catch (IllegalValueException e) {
                throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
            }
        }

        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TripName.class.getSimpleName()));
        }

        if (accommodation == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Accommodation.class.getSimpleName()));
        }

        if (itinerary == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Itinerary.class.getSimpleName()));
        }

        if (date == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TripDate.class.getSimpleName()));
        }

        try {
            final TripName modelName = ParserUtil.parseTripName(name);
            final Accommodation modelAccommodation = ParserUtil.parseAccommodation(accommodation);
            final Itinerary modelItinerary = ParserUtil.parseItinerary(itinerary);
            final TripDate modelDate = ParserUtil.parseTripDate(date);

            return new Trip(modelName, modelAccommodation, modelItinerary, modelDate, modelCustomerNames);
        } catch (IllegalValueException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}
