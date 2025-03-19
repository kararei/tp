package seedu.address.model.util;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTripBook;
import seedu.address.model.TripBook;
import seedu.address.storage.Storage;

/**
 * Utility class for loading data from storage.
 */
public class DataLoadingUtil {
    private static final Logger logger = LogsCenter.getLogger(DataLoadingUtil.class);

    /**
     * Loads the address book from storage.
     * If the file doesn't exist or there's an error, returns a sample address book.
     */
    public static ReadOnlyAddressBook loadAddressBook(Storage storage) {
        logger.info("Using data file : " + storage.getAddressBookFilePath());

        Optional<ReadOnlyAddressBook> addressBookOptional;
        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getAddressBookFilePath()
                        + " populated with a sample AddressBook.");
            }
            return addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getAddressBookFilePath() + " could not be loaded."
                    + " Will be starting with an empty AddressBook.");
            return new AddressBook();
        }
    }

    /**
     * Loads the trip book from storage.
     * If the file doesn't exist or there's an error, returns an empty trip book.
     */
    public static ReadOnlyTripBook loadTripBook(Storage storage) {
        Optional<ReadOnlyTripBook> tripBookOptional;
        try {
            tripBookOptional = storage.readTripBook();
            if (!tripBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getTripBookFilePath()
                        + " for TripBook.");
            }
            return tripBookOptional.orElseGet(TripBook::new);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getTripBookFilePath() + " could not be loaded."
                    + " Will be starting with an empty TripBook.");
            return new TripBook();
        }
    }
}
