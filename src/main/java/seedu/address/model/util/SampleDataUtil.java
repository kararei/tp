package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTripBook;
import seedu.address.model.TripBook;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Note;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.trip.Accommodation;
import seedu.address.model.trip.Itinerary;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.TripDate;
import seedu.address.model.trip.TripName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSamplePersons() {
        return new Contact[] {
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("customer")),
            new Contact(new Name("XYZ Hotel"), new Phone("63451234"), new Email("xyz_hotel@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens"),
                getTagSet("service"), new Note("Fully booked during the holidays")),
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet(), new Note("Allergic to peanuts")),
            new Contact(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("customer")),
            new Contact(new Name("Irfan's Cake Shop"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #01-35"),
                getTagSet("customer", "service")),
            new Contact(new Name("Gardens by the Bay"), new Phone("64206848"),
                new Email("feedback@gardensbythebay.com.sg"), new Address("18 Marina Gardens Drive"),
                getTagSet("service"), new Note("Opens 9am - 9pm daily"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Contact sampleContact : getSamplePersons()) {
            sampleAb.addPerson(sampleContact);
        }
        return sampleAb;
    }

    public static ReadOnlyTripBook getSampleTripBook() {
        TripBook sampleTb = new TripBook();

        TripName name = new TripName("Paris 2025");
        Accommodation accommodation = new Accommodation("Hotel 81");
        Itinerary itinerary = new Itinerary("Eat baguettes");
        TripDate date = new TripDate("12/06/2025");
        Set<Name> customerNames = new HashSet<>();
        customerNames.add(new Name("John Doe"));
        customerNames.add(new Name("Jane Doe"));

        Trip trip = new Trip(name, accommodation, itinerary, date, customerNames,
                new seedu.address.model.trip.Note("Customer prefers window seat"));
        sampleTb.addTrip(trip);

        return sampleTb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
