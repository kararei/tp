package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TRIP;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTripCommand;

/**
 * Unit tests for {@code DeleteTripCommandParser}.
 */
public class DeleteTripCommandParserTest {
    private final DeleteTripCommandParser parser = new DeleteTripCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTripCommand() {
        assertParseSuccess(parser, "1", new DeleteTripCommand(INDEX_FIRST_TRIP));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Non-integer input
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTripCommand.MESSAGE_USAGE));

        // Zero or negative index
        assertParseFailure(parser, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTripCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTripCommand.MESSAGE_USAGE));
    }
}
