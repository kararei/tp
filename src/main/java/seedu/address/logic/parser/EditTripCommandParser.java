package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTripCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Name;

/**
 * Parses input arguments and creates a new EditTripCommand object
 */
public class EditTripCommandParser implements Parser<EditTripCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTripCommand
     * and returns an EditTripCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTripCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ACCOMMODATION, PREFIX_ITINERARY,
                        PREFIX_DATE, PREFIX_CUSTOMER_NAME, PREFIX_NOTE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTripCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ACCOMMODATION,
                PREFIX_ITINERARY, PREFIX_DATE, PREFIX_NOTE);
        EditTripCommand.EditTripDescriptor editTripDescriptor = new EditTripCommand.EditTripDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editTripDescriptor.setName(ParserUtil.parseTripName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ACCOMMODATION).isPresent()) {
            editTripDescriptor.setAccommodation(ParserUtil.parseAccommodation(
                    argMultimap.getValue(PREFIX_ACCOMMODATION).get()));
        }
        if (argMultimap.getValue(PREFIX_ITINERARY).isPresent()) {
            editTripDescriptor.setItinerary(ParserUtil.parseItinerary(argMultimap.getValue(PREFIX_ITINERARY).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editTripDescriptor.setDate(ParserUtil.parseTripDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_CUSTOMER_NAME).isPresent()) {
            List<String> customerNames = argMultimap.getAllValues(PREFIX_CUSTOMER_NAME);
            Set<Name> customerNameSet = new HashSet<>();
            for (String customerName : customerNames) {
                customerNameSet.add(ParserUtil.parseName(customerName));
            }
            editTripDescriptor.setCustomerNames(customerNameSet);
        }

        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            editTripDescriptor.setNote(ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get()));
        }

        if (!editTripDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTripCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTripCommand(index, editTripDescriptor);
    }
}
