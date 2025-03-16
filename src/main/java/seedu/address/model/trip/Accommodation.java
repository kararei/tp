package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Trip's accommodation in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAccommodation(String)}
 */
public class Accommodation {

    public static final String MESSAGE_CONSTRAINTS =
            "Accommodation should not be blank";

    public final String accommodation;

    /**
     * Constructs a {@code Accommodation}.
     *
     * @param accommodation A valid accommodation.
     */
    public Accommodation(String accommodation) {
        requireNonNull(accommodation);
        checkArgument(isValidAccommodation(accommodation), MESSAGE_CONSTRAINTS);
        this.accommodation = accommodation;
    }

    /**
     * Returns true if a given string is a valid accommodation.
     */
    public static boolean isValidAccommodation(String test) {
        return test != null && !test.trim().isEmpty();
    }


    @Override
    public String toString() {
        return accommodation;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Accommodation)) {
            return false;
        }

        Accommodation otherAccommodation = (Accommodation) other;
        return accommodation.equals(otherAccommodation.accommodation);
    }

    @Override
    public int hashCode() {
        return accommodation.hashCode();
    }
}
