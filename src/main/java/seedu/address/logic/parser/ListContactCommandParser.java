package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListContactCommand object
 */
public class ListContactCommandParser implements Parser<ListContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListContactCommand
     * and returns a ListContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListContactCommand parse(String args) throws ParseException {
        assert args != null : "Input args should not be null";
        try {
            String tag = ParserUtil.parseTagName(args);
            return new ListContactCommand(tag);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListContactCommand.MESSAGE_USAGE), pe);
        }
    }

}
