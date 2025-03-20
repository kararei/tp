package seedu.address.model.trip;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.contact.Name;

/**
 * Represents a Trip in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Trip {
    private final TripName name;
    private final Accommodation accommodation;
    private final Itinerary itinerary;
    private final TripDate date;
    private final Set<Name> customerNames = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Trip(TripName name, Accommodation accommodation, Itinerary itinerary, TripDate date,
                Set<Name> customerNames) {
        requireAllNonNull(name, accommodation, itinerary, date, customerNames);
        this.name = name;
        this.accommodation = accommodation;
        this.itinerary = itinerary;
        this.date = date;
        this.customerNames.addAll(customerNames);
    }

    public TripName getName() {
        return name;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public TripDate getDate() {
        return date;
    }

    public Set<Name> getCustomerNames() {
        return Collections.unmodifiableSet(customerNames);
    }

    /**
     * Returns true if both trips have the same name.
     * This defines a weaker notion of equality between two trips.
     */
    public boolean isSameTrip(Trip otherTrip) {
        if (otherTrip == this) {
            return true;
        }

        return otherTrip != null
                && otherTrip.getName().equals(getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Trip trip = (Trip) o;
        return Objects.equals(name, trip.name) && Objects.equals(accommodation, trip.accommodation)
                && Objects.equals(itinerary, trip.itinerary)
                && Objects.equals(date, trip.date)
                && Objects.equals(customerNames, trip.customerNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, accommodation, itinerary, date, customerNames);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("accommodation", accommodation)
                .add("itinerary", itinerary)
                .add("date", date)
                .add("customerNames", customerNames)
                .toString();
    }

    public String toListString() {
        return String.format("Name = '%s', "
                + "Accommodation = '%s', "
                + "Itinerary = '%s', "
                + "Date = '%s', "
                + "Customer Names = '%s'",
                name, accommodation, itinerary, date, customerNames);
    }




}
