package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTripBook;

/**
 * A class to access TripBook data stored as a json file on the hard disk.
 */
public class JsonTripBookStorage implements TripBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTripBookStorage.class);

    private Path filePath;

    public JsonTripBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTripBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTripBook> readTripBook() throws DataLoadingException {
        return readTripBook(filePath);
    }

    /**
     * Similar to {@link #readTripBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyTripBook> readTripBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableTripBook> jsonTripBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTripBook.class);
        if (!jsonTripBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTripBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveTripBook(ReadOnlyTripBook tripBook) throws IOException {
        saveTripBook(tripBook, filePath);
    }

    /**
     * Similar to {@link #saveTripBook(ReadOnlyTripBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTripBook(ReadOnlyTripBook tripBook, Path filePath) throws IOException {
        requireNonNull(tripBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTripBook(tripBook), filePath);
    }
}
