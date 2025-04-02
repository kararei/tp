package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PARIS_2025;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TOKYO_2026;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_81;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIP_NAME_TOKYO_2026;
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
import seedu.address.logic.commands.EditTripCommand.EditTripDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.trip.Trip;
import seedu.address.testutil.EditTripDescriptorBuilder;
import seedu.address.testutil.TripBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTripCommand.
 */
public class EditTripCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTripBook(), new UserPrefs());

    @Test public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Trip editedTrip = new TripBuilder().build();
        EditTripDescriptor descriptor = new EditTripDescriptorBuilder(editedTrip).build();
        EditTripCommand editCommand = new EditTripCommand(INDEX_FIRST_TRIP, descriptor);

        String expectedMessage = String.format(EditTripCommand.MESSAGE_EDIT_TRIP_SUCCESS, Messages.format(editedTrip));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTripBook(), new UserPrefs());
        expectedModel.setTrip(model.getFilteredTripList().get(0), editedTrip);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTrip = Index.fromOneBased(model.getFilteredTripList().size());
        Trip lastTrip = model.getFilteredTripList().get(indexLastTrip.getZeroBased());

        TripBuilder tripInList = new TripBuilder(lastTrip);
        Trip editedTrip =
                tripInList.withName(VALID_TRIP_NAME_TOKYO_2026).withAccommodation(VALID_ACCOMMODATION_HOTEL_81)
                        .withNote("Window seat preferred").build();

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withName(VALID_TRIP_NAME_TOKYO_2026)
                .withAccommodation(VALID_ACCOMMODATION_HOTEL_81).withNote("Window seat preferred").build();
        EditTripCommand editCommand = new EditTripCommand(indexLastTrip, descriptor);

        String expectedMessage = String.format(EditTripCommand.MESSAGE_EDIT_TRIP_SUCCESS, Messages.format(editedTrip));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTripBook(), new UserPrefs());
        expectedModel.setTrip(lastTrip, editedTrip);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditTripCommand editCommand = new EditTripCommand(INDEX_FIRST_TRIP, new EditTripDescriptor());
        Trip editedTrip = model.getFilteredTripList().get(INDEX_FIRST_TRIP.getZeroBased());

        String expectedMessage = String.format(EditTripCommand.MESSAGE_EDIT_TRIP_SUCCESS, Messages.format(editedTrip));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTripBook(), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test public void execute_filteredList_success() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);

        Trip tripInFilteredList = model.getFilteredTripList().get(INDEX_FIRST_TRIP.getZeroBased());
        Trip editedTrip = new TripBuilder(tripInFilteredList).withName(VALID_TRIP_NAME_TOKYO_2026).build();
        EditTripCommand editCommand = new EditTripCommand(INDEX_FIRST_TRIP,
                new EditTripDescriptorBuilder().withName(VALID_TRIP_NAME_TOKYO_2026).build());

        String expectedMessage = String.format(EditTripCommand.MESSAGE_EDIT_TRIP_SUCCESS, Messages.format(editedTrip));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTripBook(), new UserPrefs());
        expectedModel.setTrip(model.getFilteredTripList().get(0), editedTrip);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test public void execute_duplicateTripUnfilteredList_failure() {
        Trip firstTrip = model.getFilteredTripList().get(INDEX_FIRST_TRIP.getZeroBased());
        EditTripDescriptor descriptor = new EditTripDescriptorBuilder(firstTrip).build();
        EditTripCommand editCommand = new EditTripCommand(INDEX_SECOND_TRIP, descriptor);

        assertCommandFailure(editCommand, model, EditTripCommand.MESSAGE_DUPLICATE_TRIP);
    }

    @Test public void execute_duplicateTripFilteredList_failure() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);

        // edit trip in filtered list into a duplicate in address book
        Trip tripInList = model.getTripBook().getTripList().get(INDEX_SECOND_TRIP.getZeroBased());
        EditTripCommand editCommand =
                new EditTripCommand(INDEX_FIRST_TRIP, new EditTripDescriptorBuilder(tripInList).build());

        assertCommandFailure(editCommand, model, EditTripCommand.MESSAGE_DUPLICATE_TRIP);
    }

    @Test public void execute_invalidTripIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTripList().size() + 1);
        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withName(VALID_TRIP_NAME_TOKYO_2026).build();
        EditTripCommand editCommand = new EditTripCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of trip book
     */
    @Test public void execute_invalidTripIndexFilteredList_failure() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);
        Index outOfBoundIndex = INDEX_SECOND_TRIP;
        // ensures that outOfBoundIndex is still in bounds of trip book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTripBook().getTripList().size());

        EditTripCommand editCommand = new EditTripCommand(outOfBoundIndex,
                new EditTripDescriptorBuilder().withName(VALID_TRIP_NAME_TOKYO_2026).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
    }

    @Test public void execute_emptyNoteUnfilteredList_success() {
        Index indexLastTrip = Index.fromOneBased(model.getFilteredTripList().size());
        Trip lastTrip = model.getFilteredTripList().get(indexLastTrip.getZeroBased());

        TripBuilder tripInList = new TripBuilder(lastTrip);
        Trip editedTrip = tripInList.withNote("").build();

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withNote("").build();
        EditTripCommand editCommand = new EditTripCommand(indexLastTrip, descriptor);

        String expectedMessage = String.format(EditTripCommand.MESSAGE_EDIT_TRIP_SUCCESS, Messages.format(editedTrip));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTripBook(), new UserPrefs());
        expectedModel.setTrip(lastTrip, editedTrip);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test public void equals() {
        final EditTripCommand standardCommand = new EditTripCommand(INDEX_FIRST_TRIP, DESC_PARIS_2025);

        // same values -> returns true
        EditTripDescriptor copyDescriptor = new EditTripDescriptor(DESC_PARIS_2025);
        EditTripCommand commandWithSameValues = new EditTripCommand(INDEX_FIRST_TRIP, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTripCommand(INDEX_SECOND_TRIP, DESC_PARIS_2025)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTripCommand(INDEX_FIRST_TRIP, DESC_TOKYO_2026)));
    }

    @Test public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditTripDescriptor editTripDescriptor = new EditTripDescriptor();
        EditTripCommand editCommand = new EditTripCommand(index, editTripDescriptor);
        String expected = EditTripCommand.class.getCanonicalName() + "{index=" + index + ", editTripDescriptor="
                + editTripDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }
}
