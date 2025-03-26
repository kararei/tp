package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.contact.Contact;
import seedu.address.model.trip.Trip;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        logger.info("LogicManager initialized with model and storage");
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveTripBook(model.getTripBook());
            logger.info("Command executed successfully: " + command.getClass().getSimpleName());
        } catch (AccessDeniedException e) {
            logger.warning("Command execution failed: " + e.getMessage());
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            logger.warning("Command execution failed: " + ioe.getMessage());
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        logger.fine("Retrieving address book");
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Contact> getFilteredPersonList() {
        logger.fine("Retrieving filtered person list");
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Trip> getFilteredTripList() {
        logger.fine("Retrieving filtered trip list");
        return model.getFilteredTripList();
    }

    @Override
    public Path getAddressBookFilePath() {
        logger.fine("Retrieving address book file path");
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        logger.fine("Retrieving GUI settings");
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        logger.info("Updating GUI settings");
        model.setGuiSettings(guiSettings);
    }
}
