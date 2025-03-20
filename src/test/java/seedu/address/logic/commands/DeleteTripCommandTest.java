package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTripAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TRIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TRIP;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTrips.getTypicalTripBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.trip.Trip;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTripCommand}.
 */
public class DeleteTripCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTripBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Trip tripToDelete = model.getFilteredTripList().get(INDEX_FIRST_TRIP.getZeroBased());
        DeleteTripCommand deleteCommand = new DeleteTripCommand(INDEX_FIRST_TRIP);

        String expectedMessage = String.format(DeleteTripCommand.MESSAGE_DELETE_TRIP_SUCCESS,
                Messages.format(tripToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getTripBook(), new UserPrefs());
        expectedModel.deleteTrip(tripToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTripList().size() + 1);
        DeleteTripCommand deleteCommand = new DeleteTripCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);

        Trip tripToDelete = model.getFilteredTripList().get(INDEX_FIRST_TRIP.getZeroBased());
        DeleteTripCommand deleteCommand = new DeleteTripCommand(INDEX_FIRST_TRIP);

        String expectedMessage = String.format(DeleteTripCommand.MESSAGE_DELETE_TRIP_SUCCESS,
                Messages.format(tripToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTripBook(), new UserPrefs());
        expectedModel.deleteTrip(tripToDelete);
        showNoTrip(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);

        Index outOfBoundIndex = INDEX_SECOND_TRIP;
        // ensures that outOfBoundIndex is still in bounds of trip book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTripBook().getTripList().size());

        DeleteTripCommand deleteCommand = new DeleteTripCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTripCommand deleteFirstCommand = new DeleteTripCommand(INDEX_FIRST_TRIP);
        DeleteTripCommand deleteSecondCommand = new DeleteTripCommand(INDEX_SECOND_TRIP);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTripCommand deleteFirstCommandCopy = new DeleteTripCommand(INDEX_FIRST_TRIP);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different trip -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteTripCommand deleteCommand = new DeleteTripCommand(targetIndex);
        String expected = DeleteTripCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no trip.
     */
    private void showNoTrip(Model model) {
        model.updateFilteredTripList(p -> false);

        assertTrue(model.getFilteredTripList().isEmpty());
    }
}
