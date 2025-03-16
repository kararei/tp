package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.ACCOMMODATION_DESC_HOTEL_81;
import static seedu.address.logic.commands.CommandTestUtil.ITINERARY_DESC_EAT_BAGUETTES;
import static seedu.address.logic.commands.CommandTestUtil.PARIS_2025_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_CUSTOMER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_CUSTOMER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_DATE_DESC_2025;
import static seedu.address.logic.commands.CommandTestUtil.TRIP_NAME_DESC_PARIS_2025;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTripCommand;
import seedu.address.model.trip.Trip;
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
}
