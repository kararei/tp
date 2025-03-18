package seedu.address.model.trip.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DuplicateTripExceptionTest {
    @Test
    public void constructor_defaultMessage_messageMatchesExpected() {
        DuplicateTripException exception = new DuplicateTripException();
        assertEquals("Operation would result in duplicate trips", exception.getMessage());
    }
}
