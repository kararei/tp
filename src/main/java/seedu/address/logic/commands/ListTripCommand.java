package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRIPS;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.TripDate;

/**
 * Lists all trips in the address book to the user.
 */
public class ListTripCommand extends Command {

    public static final String COMMAND_WORD = "listTrip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all the trips\n"
            + "Parameters: [DATE in d-M-yyyy] (optional)\n"
            + "Examples: "
            + COMMAND_WORD + "\n"
            + COMMAND_WORD + "1/1/2025";

    private final LocalDate date;

    public ListTripCommand() {
        this.date = null;
    }
    public ListTripCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Predicate<Trip> predicate;
        if (date == null) {
            predicate = PREDICATE_SHOW_ALL_TRIPS;
        } else {
            predicate = trip -> trip.getDate().date.equals(date);
        }

        model.updateFilteredTripList(predicate);

        String message = (date == null)
                ? "All trips are listed."
                : "Listed trips on " + date.format(TripDate.DATE_FORMATTER);

        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListTripCommand)) {
            return false;
        }

        ListTripCommand otherListTripCommand = (ListTripCommand) other;
        return (date == null && otherListTripCommand.date == null)
                || (date != null && date.equals(otherListTripCommand.date));
    }
}

