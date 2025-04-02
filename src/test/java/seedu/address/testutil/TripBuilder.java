package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.contact.Name;
import seedu.address.model.trip.Accommodation;
import seedu.address.model.trip.Itinerary;
import seedu.address.model.trip.Note;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.TripDate;
import seedu.address.model.trip.TripName;

/**
 * A utility class to help with building Trip objects.
 */
public class TripBuilder {

    public static final String DEFAULT_NAME = "Paris 2025";
    public static final String DEFAULT_ACCOMMODATION = "Hotel 81";
    public static final String DEFAULT_ITINERARY = "Eat baguettes";
    public static final String DEFAULT_DATE = "01/01/2205";
    public static final String DEFAULT_NOTE = "";

    private TripName name;
    private Accommodation accommodation;
    private Itinerary itinerary;
    private TripDate date;
    private Set<Name> customerNames;
    private Note note;

    /**
     * Creates a {@code TripBuilder} with the default details.
     */
    public TripBuilder() {
        name = new TripName(DEFAULT_NAME);
        accommodation = new Accommodation(DEFAULT_ACCOMMODATION);
        itinerary = new Itinerary(DEFAULT_ITINERARY);
        date = new TripDate(DEFAULT_DATE);
        customerNames = new HashSet<>();
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the TripBuilder with the data of {@code tripToCopy}.
     */
    public TripBuilder(Trip tripToCopy) {
        name = tripToCopy.getName();
        accommodation = tripToCopy.getAccommodation();
        itinerary = tripToCopy.getItinerary();
        date = tripToCopy.getDate();
        customerNames = new HashSet<>(tripToCopy.getCustomerNames());
        note = tripToCopy.getNote();
    }

    /**
     * Sets the {@code Name} of the {@code Trip} that we are building.
     */
    public TripBuilder withName(String name) {
        this.name = new TripName(name);
        return this;
    }

    /**
     * Sets the {@code Accommodation} of the {@code Trip} that we are building.
     */
    public TripBuilder withAccommodation(String accommodation) {
        this.accommodation = new Accommodation(accommodation);
        return this;
    }

    /**
     * Sets the {@code Itinerary} of the {@code Trip} that we are building.
     */
    public TripBuilder withItinerary(String itinerary) {
        this.itinerary = new Itinerary(itinerary);
        return this;
    }

    /**
     * Sets the {@code TripDate} of the {@code Trip} that we are building.
     */
    public TripBuilder withDate(String date) {
        this.date = new TripDate(date);
        return this;
    }

    /**
     * Sets the customer names of the {@code Trip} that we are building.
     */
    public TripBuilder withCustomerNames(String... customerNames) {
        Set<Name> customerNameSet = new HashSet<>();
        for (String customerName : customerNames) {
            customerNameSet.add(new Name(customerName));
        }
        this.customerNames = customerNameSet;
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Trip} that we are building.
     */
    public TripBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    public Trip build() {
        return new Trip(name, accommodation, itinerary, date, customerNames, note);
    }
}
