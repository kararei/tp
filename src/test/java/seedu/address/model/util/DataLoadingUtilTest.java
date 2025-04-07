package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTripBook;
import seedu.address.model.TripBook;
import seedu.address.model.UserPrefs;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonTripBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.TripBookStorage;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.testutil.TypicalTrips;

public class DataLoadingUtilTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "DataLoadingUtilTest");

    @TempDir
    public Path testFolder;

    private Storage storage;

    @BeforeEach
    public void setUp() {
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("addressbook.json"));
        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("preferences.json"));
        TripBookStorage tripBookStorage = new JsonTripBookStorage(getTempFilePath("tripbook.json"));
        storage = new StorageManager(addressBookStorage, userPrefsStorage, tripBookStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void loadAddressBook_nullStorage_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DataLoadingUtil.loadAddressBook(null));
    }

    @Test
    public void loadTripBook_nullStorage_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DataLoadingUtil.loadTripBook(null));
    }

    @Test
    public void loadAddressBook_missingFile_returnsSampleAddressBook() throws DataLoadingException {
        ReadOnlyAddressBook loaded = DataLoadingUtil.loadAddressBook(storage);
        assertEquals(SampleDataUtil.getSampleAddressBook(), loaded);
    }

    @Test
    public void loadTripBook_missingFile_returnsEmptyTripBook() throws DataLoadingException {
        ReadOnlyTripBook loaded = DataLoadingUtil.loadTripBook(storage);
        assertEquals(SampleDataUtil.getSampleTripBook(), loaded);
    }

    @Test
    public void loadAddressBook_invalidFile_returnsEmptyAddressBook() throws DataLoadingException {
        // Create an invalid JSON file
        try {
            storage.saveAddressBook(new AddressBook(), getTempFilePath("addressbook.json"));
            // Corrupt the file by writing invalid JSON
            storage.saveUserPrefs(new UserPrefs());
        } catch (IOException e) {
            throw new AssertionError("Failed to create invalid file", e);
        }

        ReadOnlyAddressBook loaded = DataLoadingUtil.loadAddressBook(storage);
        assertEquals(new AddressBook(), loaded);
    }

    @Test
    public void loadTripBook_invalidFile_returnsEmptyTripBook() throws DataLoadingException {
        // Create an invalid JSON file
        try {
            storage.saveTripBook(new TripBook(), getTempFilePath("tripbook.json"));
            // Corrupt the file by writing invalid JSON
            Files.write(getTempFilePath("tripbook.json"), "invalid json content".getBytes());
        } catch (IOException e) {
            throw new AssertionError("Failed to create invalid file", e);
        }

        ReadOnlyTripBook loaded = DataLoadingUtil.loadTripBook(storage);
        assertEquals(new TripBook(), loaded);
    }

    @Test
    public void loadAddressBook_validFile_returnsLoadedAddressBook() throws DataLoadingException, IOException {
        ReadOnlyAddressBook expected = SampleDataUtil.getSampleAddressBook();
        storage.saveAddressBook(new AddressBook(expected), getTempFilePath("addressbook.json"));

        ReadOnlyAddressBook loaded = DataLoadingUtil.loadAddressBook(storage);
        assertEquals(expected, loaded);
    }

    @Test
    public void loadTripBook_validFile_returnsLoadedTripBook() throws DataLoadingException, IOException {
        TripBook expected = TypicalTrips.getTypicalTripBook();
        storage.saveTripBook(expected, getTempFilePath("tripbook.json"));

        ReadOnlyTripBook loaded = DataLoadingUtil.loadTripBook(storage);
        assertEquals(expected, loaded);
    }

    @Test
    public void loadAddressBook_emptyFile_returnsEmptyAddressBook() throws DataLoadingException, IOException {
        // Create an empty file
        Files.write(getTempFilePath("addressbook.json"), "{}".getBytes());

        ReadOnlyAddressBook loaded = DataLoadingUtil.loadAddressBook(storage);
        assertEquals(new AddressBook(), loaded);
    }

    @Test
    public void loadTripBook_emptyFile_returnsEmptyTripBook() throws DataLoadingException, IOException {
        // Create an empty file
        storage.saveTripBook(new TripBook(), getTempFilePath("tripbook.json"));
        // Clear the file
        storage.saveTripBook(new TripBook(), getTempFilePath("tripbook.json"));

        ReadOnlyTripBook loaded = DataLoadingUtil.loadTripBook(storage);
        assertEquals(new TripBook(), loaded);
    }

    @Test
    public void loadAddressBook_corruptedFile_returnsEmptyAddressBook() throws DataLoadingException, IOException {
        // Create a corrupted file with invalid JSON
        try {
            storage.saveAddressBook(new AddressBook(), getTempFilePath("addressbook.json"));
            // Write invalid JSON directly to the file
            Files.write(getTempFilePath("addressbook.json"), "invalid json content".getBytes());
        } catch (IOException e) {
            throw new AssertionError("Failed to create corrupted file", e);
        }

        ReadOnlyAddressBook loaded = DataLoadingUtil.loadAddressBook(storage);
        assertEquals(new AddressBook(), loaded);
    }

    @Test
    public void loadTripBook_corruptedFile_returnsEmptyTripBook() throws DataLoadingException, IOException {
        // Create a corrupted file with invalid JSON
        try {
            storage.saveTripBook(new TripBook(), getTempFilePath("tripbook.json"));
            // Write invalid JSON directly to the file
            Files.write(getTempFilePath("tripbook.json"), "invalid json content".getBytes());
        } catch (IOException e) {
            throw new AssertionError("Failed to create corrupted file", e);
        }

        ReadOnlyTripBook loaded = DataLoadingUtil.loadTripBook(storage);
        assertEquals(new TripBook(), loaded);
    }

    @Test
    public void loadAddressBook_malformedJson_returnsEmptyAddressBook() throws DataLoadingException, IOException {
        // Create a file with malformed JSON
        try {
            storage.saveAddressBook(new AddressBook(), getTempFilePath("addressbook.json"));
            // Write malformed JSON
            Files.write(getTempFilePath("addressbook.json"), "{ \"addressbook\": { \"persons\": [ { } } }".getBytes());
        } catch (IOException e) {
            throw new AssertionError("Failed to create malformed JSON file", e);
        }

        ReadOnlyAddressBook loaded = DataLoadingUtil.loadAddressBook(storage);
        assertEquals(new AddressBook(), loaded);
    }

    @Test
    public void loadTripBook_malformedJson_returnsEmptyTripBook() throws DataLoadingException, IOException {
        // Create a file with malformed JSON
        try {
            storage.saveTripBook(new TripBook(), getTempFilePath("tripbook.json"));
            // Write malformed JSON
            Files.write(getTempFilePath("tripbook.json"), "{ \"tripbook\": { \"trips\": [ { } } }".getBytes());
        } catch (IOException e) {
            throw new AssertionError("Failed to create malformed JSON file", e);
        }

        ReadOnlyTripBook loaded = DataLoadingUtil.loadTripBook(storage);
        assertEquals(new TripBook(), loaded);
    }
}
