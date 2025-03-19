package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyTripBook;

/**
 * Represents a storage for {@link seedu.address.model.TripBook}.
 */
public interface TripBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTripBookFilePath();

    /**
     * Returns TripBook data as a {@link ReadOnlyTripBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyTripBook> readTripBook() throws DataLoadingException;

    /**
     * @see #getTripBookFilePath()
     */
    Optional<ReadOnlyTripBook> readTripBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyTripBook} to the storage.
     * @param tripBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTripBook(ReadOnlyTripBook tripBook) throws IOException;

    /**
     * @see #saveTripBook(ReadOnlyTripBook)
     */
    void saveTripBook(ReadOnlyTripBook tripBook, Path filePath) throws IOException;
}
