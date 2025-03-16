package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.ACCOMMODATION_DESC_HOTEL_81;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ACCOMMODATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ITINERARY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRIP_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRIP_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ITINERARY_DESC_EAT_BAGUETTES;
import static seedu.address.logic.commands.CommandTestUtil.PARIS_2025_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_CUSTOMER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_CUSTOMER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_DATE_DESC_2025;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_NAME_DESC_PARIS_2025;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_81;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_EAT_BAGUETTES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIP_DATE_2025;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIP_NAME_PARIS_2025;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddTripCommand;
import seedu.address.model.trip.Accommodation;
import seedu.address.model.trip.Itinerary;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.TripDate;
import seedu.address.model.trip.TripName;
import seedu.address.testutil.TripBuilder;

public class AddTripCommandParserTest {
    private AddTripCommandParser parser = new AddTripCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Trip expectedTrip = new TripBuilder(PARIS_2025_TRIP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + TRIP_NAME_DESC_PARIS_2025
                + ACCOMMODATION_DESC_HOTEL_81
                + ITINERARY_DESC_EAT_BAGUETTES
                + TRIP_DATE_DESC_2025
                + TRIP_CUSTOMER_DESC_AMY
                + TRIP_CUSTOMER_DESC_BOB,
                new AddTripCommand(expectedTrip));
    }

    @Test
    public void parse_repeatedNonCustomerName_failure() {
        String validExpectedTripString = TRIP_NAME_DESC_PARIS_2025
                + ACCOMMODATION_DESC_HOTEL_81
                + ITINERARY_DESC_EAT_BAGUETTES
                + TRIP_DATE_DESC_2025
                + TRIP_CUSTOMER_DESC_AMY
                + TRIP_CUSTOMER_DESC_BOB;

        // multiple names
        assertParseFailure(parser, TRIP_NAME_DESC_PARIS_2025 + validExpectedTripString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple accommodations
        assertParseFailure(parser, ACCOMMODATION_DESC_HOTEL_81 + validExpectedTripString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ACCOMMODATION));

        // multiple itineraries
        assertParseFailure(parser, ITINERARY_DESC_EAT_BAGUETTES + validExpectedTripString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ITINERARY));

        // multiple dates
        assertParseFailure(parser, TRIP_DATE_DESC_2025 + validExpectedTripString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedTripString + TRIP_NAME_DESC_PARIS_2025 + ACCOMMODATION_DESC_HOTEL_81
                        + ITINERARY_DESC_EAT_BAGUETTES + TRIP_DATE_DESC_2025
                        + validExpectedTripString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ACCOMMODATION,
                        PREFIX_ITINERARY, PREFIX_DATE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_TRIP_NAME_DESC + validExpectedTripString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid accommodation
        assertParseFailure(parser, INVALID_ACCOMMODATION_DESC + validExpectedTripString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ACCOMMODATION));

        // invalid itinerary
        assertParseFailure(parser, INVALID_ITINERARY_DESC + validExpectedTripString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ITINERARY));

        // invalid date
        assertParseFailure(parser, INVALID_TRIP_DATE_DESC + validExpectedTripString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedTripString + INVALID_TRIP_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid accommodation
        assertParseFailure(parser, validExpectedTripString + INVALID_ACCOMMODATION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ACCOMMODATION));

        // invalid itinerary
        assertParseFailure(parser, validExpectedTripString + INVALID_ITINERARY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ITINERARY));

        // invalid date
        assertParseFailure(parser, validExpectedTripString + INVALID_TRIP_DATE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddTripCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TRIP_NAME_PARIS_2025
                        + ACCOMMODATION_DESC_HOTEL_81
                        + ITINERARY_DESC_EAT_BAGUETTES
                        + TRIP_DATE_DESC_2025
                        + TRIP_CUSTOMER_DESC_AMY,
                expectedMessage);

        // missing accommodation prefix (if compulsory - remove if optional)
        assertParseFailure(parser, TRIP_NAME_DESC_PARIS_2025
                        + VALID_ACCOMMODATION_HOTEL_81
                        + ITINERARY_DESC_EAT_BAGUETTES
                        + TRIP_DATE_DESC_2025
                        + TRIP_CUSTOMER_DESC_AMY,
                expectedMessage);

        // missing itinerary prefix
        assertParseFailure(parser, TRIP_NAME_DESC_PARIS_2025
                        + ACCOMMODATION_DESC_HOTEL_81
                        + VALID_ITINERARY_EAT_BAGUETTES
                        + TRIP_DATE_DESC_2025
                        + TRIP_CUSTOMER_DESC_AMY,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, TRIP_NAME_DESC_PARIS_2025
                        + ACCOMMODATION_DESC_HOTEL_81
                        + ITINERARY_DESC_EAT_BAGUETTES
                        + VALID_TRIP_DATE_2025
                        + TRIP_CUSTOMER_DESC_AMY,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TRIP_NAME_PARIS_2025
                        + VALID_ACCOMMODATION_HOTEL_81
                        + VALID_ITINERARY_EAT_BAGUETTES
                        + VALID_TRIP_DATE_2025,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid trip name
        assertParseFailure(parser, INVALID_TRIP_NAME_DESC
                        + ACCOMMODATION_DESC_HOTEL_81
                        + ITINERARY_DESC_EAT_BAGUETTES
                        + TRIP_DATE_DESC_2025
                        + TRIP_CUSTOMER_DESC_AMY,
                TripName.MESSAGE_CONSTRAINTS);

        // invalid accommodation
        assertParseFailure(parser, TRIP_NAME_DESC_PARIS_2025
                        + INVALID_ACCOMMODATION_DESC
                        + ITINERARY_DESC_EAT_BAGUETTES
                        + TRIP_DATE_DESC_2025
                        + TRIP_CUSTOMER_DESC_AMY,
                Accommodation.MESSAGE_CONSTRAINTS);

        // invalid itinerary
        assertParseFailure(parser, TRIP_NAME_DESC_PARIS_2025
                        + ACCOMMODATION_DESC_HOTEL_81
                        + INVALID_ITINERARY_DESC
                        + TRIP_DATE_DESC_2025
                        + TRIP_CUSTOMER_DESC_AMY,
                Itinerary.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, TRIP_NAME_DESC_PARIS_2025
                        + ACCOMMODATION_DESC_HOTEL_81
                        + ITINERARY_DESC_EAT_BAGUETTES
                        + INVALID_TRIP_DATE_DESC
                        + TRIP_CUSTOMER_DESC_AMY,
                TripDate.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TRIP_NAME_DESC
                        + ACCOMMODATION_DESC_HOTEL_81
                        + ITINERARY_DESC_EAT_BAGUETTES
                        + INVALID_TRIP_DATE_DESC
                        + TRIP_CUSTOMER_DESC_AMY,
                TripName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                        + TRIP_NAME_DESC_PARIS_2025
                        + ACCOMMODATION_DESC_HOTEL_81
                        + ITINERARY_DESC_EAT_BAGUETTES
                        + TRIP_DATE_DESC_2025
                        + TRIP_CUSTOMER_DESC_AMY
                        + TRIP_CUSTOMER_DESC_BOB,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddTripCommand.MESSAGE_USAGE));
    }
}
