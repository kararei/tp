package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTrips.BALI;
import static seedu.address.testutil.TypicalTrips.getTypicalTripBook;

import java.time.LocalDate;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.TripDate;
import seedu.address.testutil.TripBuilder;

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
    public void execute_listAllTrips_success() {
        expectedModel.updateFilteredTripList(trip -> true);
        assertCommandSuccess(new ListTripCommand(), model,
                "All trips are listed.", expectedModel);
    }

    @Test
    public void execute_filterByDate_success() {
        LocalDate testDate = BALI.getDate().date;
        Predicate<Trip> predicate = trip -> trip.getDate().date.equals(testDate);

        expectedModel.updateFilteredTripList(predicate);
        String expectedOutput = "Listed trips on " + testDate.format(TripDate.DATE_FORMATTER);
        assertCommandSuccess(new ListTripCommand(testDate), model,
                expectedOutput, expectedModel);
    }

    @Test
    public void execute_filterByNonExistentDate_showsNoTripsMessage() {
        LocalDate nonExistentDate = LocalDate.of(2070, 8, 12);
        Predicate<Trip> predicate = trip -> trip.getDate().date.equals(nonExistentDate);
        expectedModel.updateFilteredTripList(predicate);

        String expectedOutput = "No trips found. Use the addTrip command to create a new trip.";
        assertCommandSuccess(new ListTripCommand(nonExistentDate), model,
                expectedOutput, expectedModel);
    }

    @Test
    public void execute_filterByFutureDateWithTrip_successfullyListsTrip() {
        Trip t = new TripBuilder().withDate("12/8/2070").build();
        expectedModel.addTrip(t);

        LocalDate nonExistentDate = LocalDate.of(2070, 8, 12);
        Predicate<Trip> predicate = trip -> trip.getDate().date.equals(nonExistentDate);
        expectedModel.updateFilteredTripList(predicate);

        String expectedOutput = "Listed trips on 12/8/2070";
        assertCommandSuccess(new ListTripCommand(nonExistentDate), expectedModel,
                expectedOutput, expectedModel);
    }
}
