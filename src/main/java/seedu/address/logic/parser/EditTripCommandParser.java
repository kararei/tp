package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTripCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Name;
import seedu.address.model.trip.Note;

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
            String message = pe.getMessage();
            if (message.equals(MESSAGE_INVALID_TRIP_DISPLAYED_INDEX)
                    || message.equals(MESSAGE_INVALID_INDEX)) {
                throw pe;
            }
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
        parseCustomerNamesForEdit(argMultimap.getAllValues(PREFIX_CUSTOMER_NAME))
                .ifPresent(editTripDescriptor::setCustomerNames);

        // Handle note field
        if (!argMultimap.getAllValues(PREFIX_NOTE).isEmpty()) {
            // If note prefix is present, create a note with whatever value is there (could be empty)
            String noteValue = argMultimap.getValue(PREFIX_NOTE).orElse("");
            editTripDescriptor.setNote(new Note(noteValue));
        }

        if (!editTripDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTripCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTripCommand(index, editTripDescriptor);
    }

    /**
     * Parses {@code Collection<String> customerNames} into a {@code Set<Name>} if {@code customerNames} is non-empty.
     * If {@code customerNames} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Name>} containing zero names.
     */
    private Optional<Set<Name>> parseCustomerNamesForEdit(Collection<String> customerNames) throws ParseException {
        assert customerNames != null;

        if (customerNames.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> nameSet = customerNames.size() == 1 && customerNames.contains("")
                ? Collections.emptySet() : customerNames;
        Set<Name> customerNameSet = new HashSet<>();
        for (String customerName : nameSet) {
            customerNameSet.add(ParserUtil.parseName(customerName));
        }
        return Optional.of(customerNameSet);
    }
}
