package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Shared prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_NOTE = new Prefix("nts/");

    /* Customer prefix definitions */
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Trip prefix definitions */
    public static final Prefix PREFIX_ACCOMMODATION = new Prefix("acc/");
    public static final Prefix PREFIX_ITINERARY = new Prefix("i/");
    public static final Prefix PREFIX_CUSTOMER_NAME = new Prefix("c/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
}
