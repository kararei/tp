package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditTripCommand.EditTripDescriptor;
import seedu.address.model.contact.Name;
import seedu.address.model.trip.Accommodation;
import seedu.address.model.trip.Itinerary;
import seedu.address.model.trip.Note;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.TripDate;
import seedu.address.model.trip.TripName;

/**
 * A utility class to help with building EditTripDescriptor objects.
 */
public class EditTripDescriptorBuilder {

    private EditTripDescriptor descriptor;

    public EditTripDescriptorBuilder() {
        descriptor = new EditTripDescriptor();
    }

    public EditTripDescriptorBuilder(EditTripDescriptor descriptor) {
        this.descriptor = new EditTripDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTripDescriptor} with fields containing {@code trip}'s details
     */
    public EditTripDescriptorBuilder(Trip trip) {
        descriptor = new EditTripDescriptor();
        descriptor.setName(trip.getName());
        descriptor.setAccommodation(trip.getAccommodation());
        descriptor.setItinerary(trip.getItinerary());
        descriptor.setDate(trip.getDate());
        descriptor.setCustomerNames(trip.getCustomerNames());
        descriptor.setNote(trip.getNote());
    }

    /**
     * Sets the {@code TripName} of the {@code EditTripDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withName(String name) {
        descriptor.setName(new TripName(name));
        return this;
    }

    /**
     * Sets the {@code Accommodation} of the {@code EditTripDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withAccommodation(String accommodation) {
        descriptor.setAccommodation(new Accommodation(accommodation));
        return this;
    }

    /**
     * Sets the {@code Itinerary} of the {@code EditTripDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withItinerary(String itinerary) {
        descriptor.setItinerary(new Itinerary(itinerary));
        return this;
    }

    /**
     * Sets the {@code TripDate} of the {@code EditTripDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withDate(String date) {
        descriptor.setDate(new TripDate(date));
        return this;
    }

    /**
     * Parses the {@code customerNames} into a {@code Set<Name>} and set it to the {@code EditTripDescriptor}
     * that we are building.
     */
    public EditTripDescriptorBuilder withCustomerNames(String... customerNames) {
        Set<Name> nameSet = Stream.of(customerNames).map(Name::new).collect(Collectors.toSet());
        descriptor.setCustomerNames(nameSet);
        return this;
    }

    /**
     * Sets the {@code customerNames} to empty in the {@code EditTripDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withCustomerNames() {
        descriptor.setCustomerNames(new HashSet<>());
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code EditTripDescriptor} that we are building.
     */
    public EditTripDescriptorBuilder withNote(String note) {
        descriptor.setNote(new Note(note));
        return this;
    }

    public EditTripDescriptor build() {
        return descriptor;
    }
}
