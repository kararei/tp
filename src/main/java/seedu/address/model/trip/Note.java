package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Trip's note in the address book.
 * Guarantees: immutable
 */
public class Note {

    public final String note;

    /**
     * Constructs a {@code Note}.
     *
     * @param note A note.
     */
    public Note(String note) {
        requireNonNull(note);
        if (!note.trim().isEmpty()) {
            this.note = note.trim();
        } else {
            this.note = "";
        }
    }

    @Override
    public String toString() {
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
        return note.hashCode();
    }
}
