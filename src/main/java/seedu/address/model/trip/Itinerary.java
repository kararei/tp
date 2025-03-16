package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Trip's itinerary in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidItinerary(String)}
 */
public class Itinerary {

    public static final String MESSAGE_CONSTRAINTS =
            "Itinerary should not be blank";

    public final String itinerary;

    /**
     * Constructs a {@code Itinerary}.
     *
     * @param itinerary A valid itinerary.
     */
    public Itinerary(String itinerary) {
        requireNonNull(itinerary);
        checkArgument(isValidItinerary(itinerary), MESSAGE_CONSTRAINTS);
        this.itinerary = itinerary;
    }

    /**
     * Returns true if a given string is a valid itinerary.
     */
    public static boolean isValidItinerary(String test) {
        return test != null && !test.trim().isEmpty();
    }


    @Override
    public String toString() {
        return itinerary;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Itinerary)) {
            return false;
        }

        Itinerary otherItinerary = (Itinerary) other;
        return itinerary.equals(otherItinerary.itinerary);
    }

    @Override
    public int hashCode() {
        return itinerary.hashCode();
    }
}
