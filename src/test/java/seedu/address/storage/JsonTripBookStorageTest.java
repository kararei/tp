package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTrips.PARIS;
import static seedu.address.testutil.TypicalTrips.TOKYO;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyTripBook;
import seedu.address.model.TripBook;

public class JsonTripBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            JsonTripBookStorageTest.class.getSimpleName());
    private static final Path TYPICAL_TRIPS_FILE = TEST_DATA_FOLDER.resolve("typicalTrips.json");
    private static final Path INVALID_TRIP_FILE = TEST_DATA_FOLDER.resolve("invalidTrip.json");
    private static final Path NONEXISTENT_FILE = TEST_DATA_FOLDER.resolve("nonexistent.json");

    @TempDir
    public Path testFolder;

    private JsonTripBookStorage jsonTripBookStorage;

    private static Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? Paths.get("src", "test", "data", JsonTripBookStorageTest.class.getSimpleName(),
                        prefsFileInTestDataFolder)
                : null;
    }

    @BeforeEach
    public void setUp() {
        jsonTripBookStorage = new JsonTripBookStorage(getTempFilePath("ab"));
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void readTripBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTripBook(null));
    }

    private java.util.Optional<ReadOnlyTripBook> readTripBook(String filePath) throws DataLoadingException {
        return jsonTripBookStorage.readTripBook(addToTestDataPathIfNotNull(filePath));
    }

    @Test
    public void read_missingFile_emptyResult() throws DataLoadingException {
        assertFalse(readTripBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readTripBook("notJsonFormat.json"));
    }

    @Test
    public void readTripBook_invalidTripFile_throwsDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readTripBook("invalidTrip.json"));
    }

    @Test
    public void readTripBook_invalidAndValidTripFile_throwsDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readTripBook("invalidAndValidTrip.json"));
    }

    @Test
    public void readAndSaveTripBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTripBook.json");
        TripBook original = getTypicalTripBook();
        JsonTripBookStorage jsonTripBookStorage = new JsonTripBookStorage(filePath);

        // Save in new file and read back
        jsonTripBookStorage.saveTripBook(original, filePath);
        ReadOnlyTripBook readBack = jsonTripBookStorage.readTripBook(filePath).get();
        assertEquals(original, new TripBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTrip(TOKYO);
        original.removeTrip(PARIS);
        jsonTripBookStorage.saveTripBook(original, filePath);
        readBack = jsonTripBookStorage.readTripBook(filePath).get();
        assertEquals(original, new TripBook(readBack));

        // Save and read without specifying file path
        original.addTrip(PARIS);
        jsonTripBookStorage.saveTripBook(original); // file path not specified
        readBack = jsonTripBookStorage.readTripBook().get(); // file path not specified
        assertEquals(original, new TripBook(readBack));
    }

    @Test
    public void saveTripBook_nullTripBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTripBook(null, "SomeFile.json"));
    }

    @Test
    public void saveTripBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTripBook(new TripBook(), null));
    }

    private void saveTripBook(ReadOnlyTripBook tripBook, String filePath) {
        try {
            jsonTripBookStorage.saveTripBook(tripBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTripBook_allInOrder_success() throws Exception {
        TripBook original = getTypicalTripBook();
        Path filePath = testFolder.resolve("TempTripBook.json");
        JsonTripBookStorage jsonTripBookStorage = new JsonTripBookStorage(filePath);

        // Try writing when the file doesn't exist
        jsonTripBookStorage.saveTripBook(original, filePath);
        ReadOnlyTripBook readBack = jsonTripBookStorage.readTripBook(filePath).get();
        assertEquals(original, new TripBook(readBack));

        // Try reading when there is an existing file with the same data
        jsonTripBookStorage.saveTripBook(readBack, filePath);
        ReadOnlyTripBook readBackAgain = jsonTripBookStorage.readTripBook(filePath).get();
        assertEquals(original, new TripBook(readBackAgain));
    }

    private TripBook getTypicalTripBook() {
        TripBook ab = new TripBook();
        ab.addTrip(PARIS);
        return ab;
    }
}
