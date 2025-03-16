package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.trip.Trip;

/**
 * Adds a trip to the address book.
 */
public class AddTripCommand extends Command {

    public static final String COMMAND_WORD = "addTrip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a trip to the trip book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ACCOMMODATION + "ACCOMMODATION "
            + PREFIX_ITINERARY + "ITINERARY\n"
            + PREFIX_DATE + "DATE "
            + PREFIX_CUSTOMER_NAME + "CUSTOMER_NAME (>= 1) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Paris 2025 "
            + PREFIX_ACCOMMODATION + "Hotel Sunshine "
            + PREFIX_ITINERARY + "Visit Eiffel Tower; Eat baguette. "
            + PREFIX_DATE + "01/1/2025 "
            + PREFIX_CUSTOMER_NAME + "Jane Doe "
            + PREFIX_CUSTOMER_NAME + "John Doe ";

    public static final String MESSAGE_SUCCESS = "New trip added: %1$s";
    public static final String MESSAGE_DUPLICATE_TRIP = "This trip already exists in the trip book";

    private final Trip toAdd;

    /**
     * Creates an AddTripCommand to add the specified {@code Trip}
     */
    public AddTripCommand(Trip trip) {
        requireNonNull(trip);
        toAdd = trip;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTrip(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRIP);
        }

        model.addTrip(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTripCommand)) {
            return false;
        }

        AddTripCommand otherAddCommand = (AddTripCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
