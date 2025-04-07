package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.ACCOMMODATION_DESC_HOTEL_81;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ACCOMMODATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ITINERARY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRIP_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRIP_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ITINERARY_DESC_EAT_BAGUETTES;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_CUSTOMER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_CUSTOMER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_DATE_DESC_2025;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_NAME_DESC_PARIS_2025;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_NOTE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_81;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_EAT_BAGUETTES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIP_DATE_2025;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIP_NAME_PARIS_2025;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TRIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TRIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TRIP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditTripCommand;
import seedu.address.logic.commands.EditTripCommand.EditTripDescriptor;
import seedu.address.model.trip.Accommodation;
import seedu.address.model.trip.Itinerary;
import seedu.address.model.trip.TripDate;
import seedu.address.model.trip.TripName;
import seedu.address.testutil.EditTripDescriptorBuilder;

public class EditTripCommandParserTest {

    private static final String CUSTOMER_NAME_EMPTY = " " + PREFIX_CUSTOMER_NAME;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTripCommand.MESSAGE_USAGE);

    private final EditTripCommandParser parser = new EditTripCommandParser();

    @Test public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TRIP_NAME_PARIS_2025, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTripCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TRIP_NAME_DESC_PARIS_2025, MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + TRIP_NAME_DESC_PARIS_2025, MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TRIP_NAME_DESC, TripName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_ACCOMMODATION_DESC, Accommodation.MESSAGE_CONSTRAINTS); // invalid
        // accommodation
        assertParseFailure(parser, "1" + INVALID_ITINERARY_DESC, Itinerary.MESSAGE_CONSTRAINTS); // invalid itinerary
        assertParseFailure(parser, "1" + INVALID_TRIP_DATE_DESC, TripDate.MESSAGE_CONSTRAINTS); // invalid date

        // invalid accommodation followed by valid itinerary
        assertParseFailure(parser, "1" + INVALID_ACCOMMODATION_DESC + ITINERARY_DESC_EAT_BAGUETTES,
                Accommodation.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TRIP_NAME_DESC + INVALID_ITINERARY_DESC + VALID_ACCOMMODATION_HOTEL_81
                + VALID_TRIP_DATE_2025, TripName.MESSAGE_CONSTRAINTS);
    }

    @Test public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TRIP;
        String userInput = targetIndex.getOneBased() + ACCOMMODATION_DESC_HOTEL_81 + TRIP_CUSTOMER_DESC_AMY
                + ITINERARY_DESC_EAT_BAGUETTES + TRIP_DATE_DESC_2025 + TRIP_NAME_DESC_PARIS_2025
                + TRIP_CUSTOMER_DESC_BOB + TRIP_NOTE_DESC;

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withName(VALID_TRIP_NAME_PARIS_2025)
                .withAccommodation(VALID_ACCOMMODATION_HOTEL_81).withItinerary(VALID_ITINERARY_EAT_BAGUETTES)
                .withDate(VALID_TRIP_DATE_2025).withCustomerNames("Amy Bee", "Bob Choo")
                .withNote("Customer prefers window seat").build();
        EditTripCommand expectedCommand = new EditTripCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TRIP;
        String userInput = targetIndex.getOneBased() + ACCOMMODATION_DESC_HOTEL_81 + ITINERARY_DESC_EAT_BAGUETTES;

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withAccommodation(VALID_ACCOMMODATION_HOTEL_81)
                .withItinerary(VALID_ITINERARY_EAT_BAGUETTES).build();
        EditTripCommand expectedCommand = new EditTripCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_TRIP;
        String userInput = targetIndex.getOneBased() + TRIP_NAME_DESC_PARIS_2025;
        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withName(VALID_TRIP_NAME_PARIS_2025).build();
        EditTripCommand expectedCommand = new EditTripCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // accommodation
        userInput = targetIndex.getOneBased() + ACCOMMODATION_DESC_HOTEL_81;
        descriptor = new EditTripDescriptorBuilder().withAccommodation(VALID_ACCOMMODATION_HOTEL_81).build();
        expectedCommand = new EditTripCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // itinerary
        userInput = targetIndex.getOneBased() + ITINERARY_DESC_EAT_BAGUETTES;
        descriptor = new EditTripDescriptorBuilder().withItinerary(VALID_ITINERARY_EAT_BAGUETTES).build();
        expectedCommand = new EditTripCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + TRIP_DATE_DESC_2025;
        descriptor = new EditTripDescriptorBuilder().withDate(VALID_TRIP_DATE_2025).build();
        expectedCommand = new EditTripCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // customer names
        userInput = targetIndex.getOneBased() + TRIP_CUSTOMER_DESC_AMY;
        descriptor = new EditTripDescriptorBuilder().withCustomerNames("Amy Bee").build();
        expectedCommand = new EditTripCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // note
        userInput = targetIndex.getOneBased() + TRIP_NOTE_DESC;
        descriptor = new EditTripDescriptorBuilder().withNote("Customer prefers window seat").build();
        expectedCommand = new EditTripCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test public void parse_multipleRepeatedFields_failure() {
        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_TRIP;
        String userInput = targetIndex.getOneBased() + INVALID_ACCOMMODATION_DESC + ACCOMMODATION_DESC_HOTEL_81;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ACCOMMODATION));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + ACCOMMODATION_DESC_HOTEL_81 + INVALID_ACCOMMODATION_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ACCOMMODATION));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + ACCOMMODATION_DESC_HOTEL_81 + TRIP_DATE_DESC_2025
                + ITINERARY_DESC_EAT_BAGUETTES + TRIP_NAME_DESC_PARIS_2025 + ACCOMMODATION_DESC_HOTEL_81
                + TRIP_DATE_DESC_2025 + ITINERARY_DESC_EAT_BAGUETTES;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ACCOMMODATION, PREFIX_DATE, PREFIX_ITINERARY));

        // multiple invalid values
        userInput =
                targetIndex.getOneBased() + INVALID_ACCOMMODATION_DESC + INVALID_TRIP_DATE_DESC + INVALID_ITINERARY_DESC
                        + INVALID_ACCOMMODATION_DESC + INVALID_TRIP_DATE_DESC + INVALID_ITINERARY_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ACCOMMODATION, PREFIX_DATE, PREFIX_ITINERARY));
    }

    @Test public void parse_resetCustomerNames_success() {
        Index targetIndex = INDEX_THIRD_TRIP;
        String userInput = targetIndex.getOneBased() + CUSTOMER_NAME_EMPTY;

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withCustomerNames().build();
        EditTripCommand expectedCommand = new EditTripCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyNoteField_success() {
        Index targetIndex = INDEX_THIRD_TRIP;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE;

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withNote("").build();
        EditTripCommand expectedCommand = new EditTripCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
