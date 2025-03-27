package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Represents a Trip's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTripDate(String)}
 */
public class TripDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Trip date should be in the format of d/M/yyyy and must be a valid date";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final Logger logger = LogsCenter.getLogger(TripDate.class);

    public final LocalDate date;

    /**
     * Constructs a {@code TripDate}.
     *
     * @param date A valid date.
     */
    public TripDate(String date) {
        requireNonNull(date);
        checkArgument(isValidTripDate(date), MESSAGE_CONSTRAINTS);

        logger.fine("Creating new TripDate with input: " + date);

        // Assert that preconditions are met
        assert !date.trim().isEmpty() : "Date string cannot be empty";
        assert isValidTripDate(date) : "Date must be valid before parsing";

        try {
            this.date = LocalDate.parse(date.trim(), DATE_FORMATTER);
            logger.fine("Successfully parsed date: " + this.date);
        } catch (DateTimeParseException e) {
            logger.warning("Failed to parse date: " + date);
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }

        // Post-construction validation
        assert this.date.getYear() >= 1000 && this.date.getYear() <= 9999 : "Year must be between 1000-9999";
    }

    /**
     * Returns true if a given string is a valid trip date.
     */
    public static boolean isValidTripDate(String test) {
        if (test == null || test.trim().isEmpty()) {
            logger.fine("Invalid date: null or empty string");
            return false;
        }

        try {
            LocalDate parsedDate = LocalDate.parse(test.trim(), DATE_FORMATTER);
            // Additional validation for year range
            int year = parsedDate.getYear();
            if (year < 1000 || year > 9999) {
                logger.fine("Invalid year in date: " + test);
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            logger.fine("Failed to parse date string: " + test);
            return false;
        }
    }

    @Override
    public String toString() {
        String formatted = date.format(DATE_FORMATTER);
        logger.finest("Formatting date to string: " + formatted);
        return formatted;
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
        boolean isEqual = date.equals(otherTripDate.date);
        logger.finest("Comparing TripDates: " + this + " and " + otherTripDate + " -> " + isEqual);
        return isEqual;
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
