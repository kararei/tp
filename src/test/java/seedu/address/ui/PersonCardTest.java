package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;
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
    public void display_noteAndTags() {
        Contact contact = new PersonBuilder()
                .withNote("Likes the sea")
                .withTags("customer", "service")
                .build();
        TestFxmlObject testObject = new TestFxmlObject(contact.getNote().toString());
        assertEquals("Likes the sea", testObject.getText());

        Tag customerTag = contact.getTags().stream()
                .filter(tag -> tag.tagName.equals("customer"))
                .findFirst()
                .get();
        TestFxmlObject customerTagObject = new TestFxmlObject(customerTag.getStyleClass());
        assertEquals("customer-tag", customerTagObject.getText());

        Tag serviceTag = contact.getTags().stream()
                .filter(tag -> tag.tagName.equals("service"))
                .findFirst()
                .get();
        TestFxmlObject serviceTagObject = new TestFxmlObject(serviceTag.getStyleClass());
        assertEquals("service-tag", serviceTagObject.getText());
    }

    @Test
    public void testEquals() {
        Contact contact = new PersonBuilder()
                .withNote("Allergic to peanuts")
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
