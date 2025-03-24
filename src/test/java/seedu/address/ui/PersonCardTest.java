package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.Contact;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class PersonCardTest {

    @Test
    public void display() {
        Contact contact = TypicalPersons.ALICE;
        TestFxmlObject testObject = new TestFxmlObject(contact.getName().fullName);
        assertEquals(contact.getName().fullName, testObject.getText());
    }

    @Test
    public void display_notesAndTags() {
        Contact contact = new PersonBuilder()
                .withNotes("Likes the sea")
                .withTags("customer", "service")
                .build();
        TestFxmlObject testObject = new TestFxmlObject(contact.getNotes());
        assertEquals("Likes the sea", testObject.getText());
    }

    @Test
    public void testEquals() {
        Contact contact = new PersonBuilder()
                .withNotes("Allergic to peanuts")
                .withTags("customer")
                .build();

        TestFxmlObject testObject = new TestFxmlObject(contact.getName().fullName);
        TestFxmlObject sameTestObject = new TestFxmlObject(contact.getName().fullName);
        TestFxmlObject differentTestObject = new TestFxmlObject("differentName");

        assertTrue(testObject.equals(testObject));
        assertFalse(testObject.equals(null));
        assertFalse(testObject.equals(2));
        assertTrue(testObject.equals(sameTestObject));
        assertFalse(testObject.equals(differentTestObject));
    }
}
