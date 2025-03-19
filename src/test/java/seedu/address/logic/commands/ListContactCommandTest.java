package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SERVICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTrips.getTypicalTripBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListContactCommand.
 */
public class ListContactCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTripBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getTripBook(), new UserPrefs());
    }

    @Test
    public void execute_listContactIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListContactCommand(""), model,
            String.format(ListContactCommand.MESSAGE_SUCCESS, ""), expectedModel);
    }

    @Test
    public void execute_listContactFilteredByCustomer() {
        String customerTagName = "customer";
        assertCommandSuccess(new ListContactCommand(customerTagName), model,
            String.format(ListContactCommand.MESSAGE_SUCCESS, (customerTagName + " ")), model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_CUSTOMER);
        assertTrue(model.getFilteredPersonList().size() == 2);
    }

    @Test
    public void execute_listContactFilteredByService() {
        String serviceTagName = "service";
        assertCommandSuccess(new ListContactCommand(serviceTagName), model,
            String.format(ListContactCommand.MESSAGE_SUCCESS, (serviceTagName + " ")), model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_SERVICE);
        assertTrue(model.getFilteredPersonList().size() == 2);
    }

}
