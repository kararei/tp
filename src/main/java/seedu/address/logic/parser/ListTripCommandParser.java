package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.ListTripCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.trip.TripDate;

/**
 * Parses input arguments and creates a new ListContactCommand object
 */
public class ListTripCommandParser implements Parser<ListTripCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListContactCommand
     * and returns a ListContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTripCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            return new ListTripCommand();
        }
        try {

            DateTimeFormatter formatter = TripDate.DATE_FORMATTER;
            LocalDate filterDate = LocalDate.parse(trimmedArgs, formatter);
            return new ListTripCommand(filterDate);
        } catch (DateTimeParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListTripCommand.MESSAGE_USAGE));
        }
    }

}
