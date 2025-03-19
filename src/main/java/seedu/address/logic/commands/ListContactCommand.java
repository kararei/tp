package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SERVICE;

import seedu.address.model.Model;

/**
 * Lists all contacts in the address book to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "listContact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
    + ": Lists all the contacts or contacts that have a specific tag.\n"
    + "Parameters: TAGNAME (optional)\n"
    + "Examples: " + COMMAND_WORD + " OR " + COMMAND_WORD + " customer" + " OR " + COMMAND_WORD + " service";

    public static final String MESSAGE_SUCCESS = "Listed all %scontacts";

    private final String tagName;

    public ListContactCommand(String tagname) {
        this.tagName = tagname;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (tagName.equals("customer")) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_CUSTOMER);
        } else if (tagName.equals("service")) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_SERVICE);
        } else {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, (tagName.equals("") ? "" :  tagName + " ")));

    }
}
