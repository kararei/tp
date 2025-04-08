package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.TripBook;

/**
 * Clears the address book and trip book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book and Trip book has been cleared!";
    public static final String MESSAGE_CONFIRMATION = "Are you sure you want to clear all contacts and trips?";
    private final boolean isConfirmed;

    public ClearCommand() {
        this.isConfirmed = false;
    }

    public ClearCommand(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (!isConfirmed) {
            return new CommandResult("", true, MESSAGE_CONFIRMATION);
        }

        model.setAddressBook(new AddressBook());
        model.setTripBook(new TripBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
