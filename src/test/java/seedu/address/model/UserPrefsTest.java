package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        UserPrefs copy = new UserPrefs();
        assertTrue(userPrefs.equals(copy));

        // same object -> returns true
        assertTrue(userPrefs.equals(userPrefs));

        // null -> returns false
        assertFalse(userPrefs.equals(null));

        // different type -> returns false
        assertFalse(userPrefs.equals(5));

        // different addressBookFilePath -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("different/path"));
        assertFalse(userPrefs.equals(differentUserPrefs));

        // different guiSettings -> returns false
        differentUserPrefs = new UserPrefs();
        GuiSettings guiSettings = new GuiSettings(10, 20, 30, 40);
        differentUserPrefs.setGuiSettings(guiSettings);
        assertFalse(userPrefs.equals(differentUserPrefs));
    }

    @Test
    public void hashcode() {
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns same hashcode
        UserPrefs copy = new UserPrefs();
        assertEquals(userPrefs.hashCode(), copy.hashCode());

        // different values -> returns different hashcode
        copy.setAddressBookFilePath(Paths.get("different/path"));
        assertFalse(userPrefs.hashCode() == copy.hashCode());
    }
}
