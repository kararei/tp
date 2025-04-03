package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents a Contact's note in the address book.
 * Guarantees: immutable.
 */
public class Note {
    private final String note;

    /**
     * Constructs a {@code Note}.
     *
     * @param note A valid note string.
     */
    public Note(String note) {
        requireNonNull(note);
        if (!note.trim().isEmpty()) {
            this.note = note.trim();
        } else {
            this.note = "";
        }
    }

    /**
     * Returns the note value.
     */
    public String getNote() {
        return note;
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
        return note.equals(otherNote.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note);
    }

    @Override
    public String toString() {
        return note;
    }

    public boolean isEmpty() {
        return note.isEmpty();
    }
}
