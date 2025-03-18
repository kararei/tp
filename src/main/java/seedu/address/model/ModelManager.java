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
    }

    public ModelManager() {
        this(new AddressBook(), new TripBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
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
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return addressBook.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        addressBook.removeContact(target);
    }

    @Override
    public void addPerson(Contact contact) {
        addressBook.addPerson(contact);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        addressBook.setContact(target, editedContact);
    }

    //=========== TripBook ================================================================================

    @Override
    public void setTripBook(ReadOnlyTripBook tripBook) {
        this.tripBook.resetData(tripBook);
    }

    @Override
    public ReadOnlyTripBook getTripBook() {
        return tripBook;
    }

    @Override
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return tripBook.hasTrip(trip);
    }

    @Override
    public void deleteTrip(Trip target) {
        tripBook.removeTrip(target);
    }

    @Override
    public void addTrip(Trip trip) {
        tripBook.addTrip(trip);
        updateFilteredTripList(PREDICATE_SHOW_ALL_TRIPS);
    }

    @Override
    public void setTrip(Trip target, Trip editedTrip) {
        requireAllNonNull(target, editedTrip);

        tripBook.setTrip(target, editedTrip);
    }

    //=========== Filtered Contact List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredPersonList() {
        return filteredContacts;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
    }

    //=========== Filtered Trip List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Trip} backed by the internal list of
     * {@code versionedTripBook}
     */
    @Override
    public ObservableList<Trip> getFilteredTripList() {
        return filteredTrips;
    }

    @Override
    public void updateFilteredTripList(Predicate<Trip> predicate) {
        requireNonNull(predicate);
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
