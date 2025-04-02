package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Available commands:\n"
            + "- help: Shows program usage instructions\n"
            + "- addContact: Adds a new contact\n"
            + "- addTrip: Adds a new trip\n"
            + "- deleteContact: Removes a contact at a specified index\n"
            + "- deleteTrip: Removes a trip at a specified index\n"
            + "- edit: Edits a contact at a specified index\n"
            + "- listContact: Lists all contacts [can specify tag type]\n"
            + "- listTrip: Lists all trips \n"
            + "- exit: Exits the program";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
