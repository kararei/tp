package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Trip's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTripDate(String)}
 */
public class TripDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Trip date should be in the format of d/M/yyyy and must be a valid date";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public final LocalDate date;

    /**
     * Constructs a {@code TripDate}.
     *
     * @param date A valid date.
     */
    public TripDate(String date) {
        requireNonNull(date);
        checkArgument(isValidTripDate(date), MESSAGE_CONSTRAINTS);

        // Assert that preconditions are met
        assert !date.trim().isEmpty() : "Date string cannot be empty";
        assert isValidTripDate(date) : "Date must be valid before parsing";

        this.date = LocalDate.parse(date.trim(), DATE_FORMATTER);

        // Post-construction validation
        assert this.date.getYear() >= 1000 && this.date.getYear() <= 9999 : "Year must be between 1000-9999";
    }

    /**
     * Returns true if a given string is a valid trip date.
     */
    public static boolean isValidTripDate(String test) {
        if (test == null || test.trim().isEmpty()) {
            return false;
        }

        try {
            LocalDate parsedDate = LocalDate.parse(test.trim(), DATE_FORMATTER);
            // Additional validation for year range
            int year = parsedDate.getYear();
            if (year < 1000 || year > 9999) {
                return false;
            }
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
