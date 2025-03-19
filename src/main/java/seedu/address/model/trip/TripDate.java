package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Trip's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTripDate(String)}
 */
public class TripDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Trip date should be in the format of d/M/yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");

    public final LocalDate date;

    /**
     * Constructs a {@code TripDate}.
     *
     * @param date A valid date.
     */
    public TripDate(String date) {
        requireNonNull(date);
        checkArgument(isValidTripDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date.trim(), DATE_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid trip date.
     */
    public static boolean isValidTripDate(String test) {
        if (test == null || test.trim().isEmpty()) {
            return false;
        }

        try {
            LocalDate.parse(test.trim(), DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return date.format(DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TripDate)) {
            return false;
        }

        TripDate otherTripDate = (TripDate) other;
        return date.equals(otherTripDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
