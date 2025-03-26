package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.model.trip.Trip;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final TripBook tripBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Contact> filteredContacts;
    private final FilteredList<Trip> filteredTrips;

    /**
     * Initializes a ModelManager with the given addressBook, tripBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyTripBook tripBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, tripBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + ", trip book: " + tripBook
                + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.tripBook = new TripBook(tripBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredContacts = new FilteredList<>(this.addressBook.getPersonList());
        filteredTrips = new FilteredList<>(this.tripBook.getTripList());
        logger.info("ModelManager initialized successfully");
    }

    public ModelManager() {
        this(new AddressBook(), new TripBook(), new UserPrefs());
        logger.info("Created new ModelManager with empty data");
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        logger.info("Updating user preferences");
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        logger.fine("Retrieving user preferences");
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        logger.fine("Retrieving GUI settings");
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        logger.info("Updating GUI settings");
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        logger.fine("Retrieving address book file path");
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        logger.info("Updating address book file path to: " + addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getTripBookFilePath() {
        return userPrefs.getTripBookFilePath();
    }

    @Override
    public void setTripBookFilePath(Path tripBookFilePath) {
        requireNonNull(tripBookFilePath);
        userPrefs.setTripBookFilePath(tripBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
        logger.info("Address book data reset");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        logger.fine("Retrieving address book");
        return addressBook;
    }

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        logger.fine("Checking if contact exists: " + contact.getName());
        return addressBook.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        addressBook.removeContact(target);
        logger.info("Deleted contact: " + target.getName());
    }

    @Override
    public void addPerson(Contact contact) {
        addressBook.addPerson(contact);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        logger.info("Added new contact: " + contact.getName());
    }

    @Override
    public void setPerson(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);
        addressBook.setContact(target, editedContact);
        logger.info("Updated contact: " + target.getName() + " to: " + editedContact.getName());
    }

    //=========== TripBook ================================================================================

    @Override
    public void setTripBook(ReadOnlyTripBook newData) {
        tripBook.resetData(newData);
        logger.info("Trip book data reset");
    }

    @Override
    public ReadOnlyTripBook getTripBook() {
        logger.fine("Retrieving trip book");
        return tripBook;
    }

    @Override
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        logger.fine("Checking if trip exists: " + trip.getName());
        return tripBook.hasTrip(trip);
    }

    @Override
    public void deleteTrip(Trip target) {
        tripBook.removeTrip(target);
        logger.info("Deleted trip: " + target.getName());
    }

    @Override
    public void addTrip(Trip trip) {
        tripBook.addTrip(trip);
        updateFilteredTripList(PREDICATE_SHOW_ALL_TRIPS);
        logger.info("Added new trip: " + trip.getName());
    }

    @Override
    public void setTrip(Trip target, Trip editedTrip) {
        requireAllNonNull(target, editedTrip);
        tripBook.setTrip(target, editedTrip);
        logger.info("Updated trip: " + target.getName() + " to: " + editedTrip.getName());
    }

    //=========== Filtered Contact List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredPersonList() {
        logger.fine("Retrieving filtered person list");
        return filteredContacts;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        logger.info("Updating filtered person list with new predicate");
        filteredContacts.setPredicate(predicate);
    }

    //=========== Filtered Trip List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Trip} backed by the internal list of
     * {@code versionedTripBook}
     */
    @Override
    public ObservableList<Trip> getFilteredTripList() {
        logger.fine("Retrieving filtered trip list");
        return filteredTrips;
    }

    @Override
    public void updateFilteredTripList(Predicate<Trip> predicate) {
        requireNonNull(predicate);
        logger.info("Updating filtered trip list with new predicate");
        filteredTrips.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && tripBook.equals(other.tripBook)
                && userPrefs.equals(other.userPrefs)
                && filteredContacts.equals(other.filteredContacts)
                && filteredTrips.equals(other.filteredTrips);
    }

}
