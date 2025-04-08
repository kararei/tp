package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTrips.getTypicalTripBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TripBook;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_requestsConfirmation() {
        Model model = new ModelManager();

        CommandResult expectedResult = new CommandResult(
                "", true, ClearCommand.MESSAGE_CONFIRMATION);
        assertCommandSuccess(new ClearCommand(), model, expectedResult, model);
    }

    @Test
    public void execute_nonEmptyAddressBook_requestsConfirmation() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTripBook(), new UserPrefs());

        CommandResult expectedResult = new CommandResult(
                "", true, ClearCommand.MESSAGE_CONFIRMATION);
        assertCommandSuccess(new ClearCommand(), model, expectedResult, model);
    }

    @Test
    public void executeConfirmed_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(true), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeConfirmed_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTripBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTripBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());
        expectedModel.setTripBook(new TripBook());

        assertCommandSuccess(new ClearCommand(true), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
