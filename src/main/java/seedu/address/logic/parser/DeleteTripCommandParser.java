package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTripCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTripCommand object
 */
public class DeleteTripCommandParser implements Parser<DeleteTripCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTripCommand
     * and returns a DeleteTripCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTripCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTripCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTripCommand.MESSAGE_USAGE), pe);
        }
    }

}