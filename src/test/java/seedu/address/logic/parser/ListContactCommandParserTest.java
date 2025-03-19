package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListContactCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeleteContactCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteContactCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ListContactCommandParserTest {

    private static final String INVALID_TAGNAME = "friend";
    private static final String VALID_TAGNAME_1 = "service";
    private static final String VALID_TAGNAME_2 = "";
    private ListContactCommandParser parser = new ListContactCommandParser();


    @Test
    public void parse_validTag_returnsListContactCommand() {
        assertParseSuccess(parser, "service", new ListContactCommand(VALID_TAGNAME_1));
    }

    @Test
    public void parse_validTag2_returnsListContactCommand() {
        assertParseSuccess(parser, "", new ListContactCommand(VALID_TAGNAME_2));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_TAGNAME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListContactCommand.MESSAGE_USAGE));
    }
}
