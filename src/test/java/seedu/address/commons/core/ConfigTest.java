package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toStringMethod() {
        Config config = new Config();
        String expected = Config.class.getCanonicalName() + "{logLevel=" + config.getLogLevel()
                + ", userPrefsFilePath=" + config.getUserPrefsFilePath() + "}";
        assertEquals(expected, config.toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig)); // same object

        // same values -> returns true
        Config copy = new Config();
        assertTrue(defaultConfig.equals(copy));

        // different types -> returns false
        assertFalse(defaultConfig.equals(100));

        // null -> returns false
        assertFalse(defaultConfig.equals(null));

        // different log level -> returns false
        Config differentConfig = new Config();
        differentConfig.setLogLevel(Level.FINE);
        assertFalse(defaultConfig.equals(differentConfig));

        // different user prefs file path -> returns false
        differentConfig = new Config();
        differentConfig.setUserPrefsFilePath(Paths.get("different/path"));
        assertFalse(defaultConfig.equals(differentConfig));
    }

    @Test
    public void hashCodeMethod() {
        Config defaultConfig = new Config();
        Config copy = new Config();

        // same values -> returns same hashcode
        assertEquals(defaultConfig.hashCode(), copy.hashCode());

        // different values -> returns different hashcode
        copy.setLogLevel(Level.FINE);
        assertNotEquals(defaultConfig.hashCode(), copy.hashCode());
    }
}
