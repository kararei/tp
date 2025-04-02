package seedu.address.model.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void equals() {
        Note note = new Note("Test note");

        // same object -> returns true
        assertTrue(note.equals(note));

        // null -> returns false
        assertFalse(note.equals(null));

        // different types -> returns false
        assertFalse(note.equals(5));

        // different note -> returns false
        assertFalse(note.equals(new Note("Different note")));

        // same note value -> returns true
        assertTrue(note.equals(new Note("Test note")));
    }

    @Test
    public void hashCode_sameNote_sameHashCode() {
        Note note1 = new Note("Test note");
        Note note2 = new Note("Test note");
        assertEquals(note1.hashCode(), note2.hashCode());
    }

    @Test
    public void hashCode_differentNote_differentHashCode() {
        Note note1 = new Note("Test note");
        Note note2 = new Note("Different note");
        assertNotEquals(note1.hashCode(), note2.hashCode());
    }

    @Test
    public void constructor_emptyString_returnsEmptyNote() {
        Note note = new Note("");
        assertEquals("", note.note);
    }

    @Test
    public void constructor_blankString_returnsEmptyNote() {
        Note note = new Note("   ");
        assertEquals("", note.note);
    }
}
