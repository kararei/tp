package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTrips.getTypicalTripBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.trip.Trip;

import java.util.List;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTripCommand.
 */

public class ListTripCommandTest {

    private Model model;
    private Model expectedModel;

    /**
     * Sets up the test environment before each test.
     * Initializes the model with typical data for trips and address book.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTripBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getTripBook(), new UserPrefs());
    }

    /**
     * Tests if executing ListTripCommand on an unfiltered trip list
     * results in the same list being created.
     */
    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String MESSAGE_EXPECTED = generateExpectedMessage(expectedModel);
        assertCommandSuccess(new ListTripCommand(), model, MESSAGE_EXPECTED, expectedModel);
    }

    /**
     * Tests if executing ListTripCommand when there are no trips in the list
     * correctly gives an empty list.
     */
    @Test
    public void execute_noTrips_showsEmptyList() {
        model = new ModelManager(getTypicalAddressBook(), new seedu.address.model.TripBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getTripBook(), new UserPrefs());

        assertCommandSuccess(new ListTripCommand(), model, ListTripCommand.MESSAGE_EMPTY, expectedModel);
    }

    private String generateExpectedMessage(Model model) {
        List<Trip> tripList = model.getFilteredTripList();

        if (tripList.isEmpty()) {
            return ListTripCommand.MESSAGE_EMPTY;
        }

        StringBuilder MESSAGE_OUTPUT = new StringBuilder("Listed all trips:\n");
        int counter = 0;
        for (Trip trip : tripList) {
            MESSAGE_OUTPUT.append(++counter).append(". ").append(trip.toListString()).append("\n");
        }
        return MESSAGE_OUTPUT.toString().trim();
    }

}


