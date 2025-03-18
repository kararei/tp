package seedu.address.model.trip.exceptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TripNotFoundExceptionTest {
    @Test
    public void constructor_extendsRuntimeException_isRuntimeException() {
        TripNotFoundException exception = new TripNotFoundException();
        assertTrue(exception instanceof RuntimeException);
    }
}
