package seedu.address.model.contact;

import java.util.Objects;

/**
 * Represents a Contact's note in the address book.
 * Guarantees: immutable.
 */
public class Note {
    private final String value;

    /**
     * Constructs a {@code Note}.
     *
     * @param note A valid note string.
     */
    public Note(String note) {
        this.value = note;
    }

    /**
     * Returns the note value.
     */
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return value.equals(otherNote.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }
}
