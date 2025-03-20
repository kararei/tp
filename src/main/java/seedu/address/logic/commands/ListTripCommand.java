package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRIPS;

import seedu.address.model.Model;
import seedu.address.model.trip.Trip;

import java.util.List;

/**
 * Lists all trips in the address book to the user.
 */
public class ListTripCommand extends Command {

    public static final String COMMAND_WORD = "listTrip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all the trips\n"
            + "Parameters: [TAGNAME] (optional)\n"
            + "Examples: "
            + COMMAND_WORD;

    public static final String MESSAGE_EMPTY = "No trips found.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTripList(PREDICATE_SHOW_ALL_TRIPS);
        List<Trip> tripList = model.getFilteredTripList();
        if (tripList.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY);
        }
        StringBuilder MESSAGE_SUCCESS = new StringBuilder("Listed all trips:\n");
        int counter = 0;
        for (Trip trip : tripList) {
            MESSAGE_SUCCESS.append(++counter).append(". ").append(trip.toListString()).append("\n");
        }
        return new CommandResult(MESSAGE_SUCCESS.toString().trim());
    }
}

