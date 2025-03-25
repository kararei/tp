package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRIPS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Name;
import seedu.address.model.trip.Accommodation;
import seedu.address.model.trip.Itinerary;
import seedu.address.model.trip.Note;
import seedu.address.model.trip.Trip;
import seedu.address.model.trip.TripDate;
import seedu.address.model.trip.TripName;

/**
 * Edits the details of an existing contact in the address book.
 */
public class EditTripCommand extends Command {

    public static final String COMMAND_WORD = "editTrip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the trip identified "
            + "by the index number used in the displayed contact list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_ACCOMMODATION + "ACCOMMODATION "
            + PREFIX_ITINERARY + "ITINERARY "
            + PREFIX_DATE + "DATE "
            + PREFIX_CUSTOMER_NAME + "CUSTOMER_NAME (>= 1) "
            + "[" + PREFIX_NOTE + "NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Paris 2025 "
            + PREFIX_ACCOMMODATION + "Hotel Sunshine "
            + PREFIX_ITINERARY + "Visit Eiffel Tower; Eat baguette. "
            + PREFIX_DATE + "01/1/2025 "
            + PREFIX_CUSTOMER_NAME + "Jane Doe "
            + PREFIX_CUSTOMER_NAME + "John Doe "
            + PREFIX_NOTE + "Customer prefers window seat";

    public static final String MESSAGE_EDIT_TRIP_SUCCESS = "Edited Trip: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TRIP = "This trip already exists in the address book.";

    private final Index index;
    private final EditTripDescriptor editTripDescriptor;

    /**
     * @param index of the contact in the filtered trip list to edit
     * @param editTripDescriptor details to edit the trip with
     */
    public EditTripCommand(Index index, EditTripDescriptor editTripDescriptor) {
        requireNonNull(index);
        requireNonNull(editTripDescriptor);

        this.index = index;
        this.editTripDescriptor = new EditTripDescriptor(editTripDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Trip> lastShownList = model.getFilteredTripList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
        }

        Trip tripToEdit = lastShownList.get(index.getZeroBased());
        Trip editedTrip = createEditedTrip(tripToEdit, editTripDescriptor);

        if (!tripToEdit.isSameTrip(editedTrip) && model.hasTrip(editedTrip)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRIP);
        }

        model.setTrip(tripToEdit, editedTrip);
        model.updateFilteredTripList(PREDICATE_SHOW_ALL_TRIPS);
        return new CommandResult(String.format(MESSAGE_EDIT_TRIP_SUCCESS, Messages.format(editedTrip)));
    }

    /**
     * Creates and returns a {@code Trip} with the details of {@code tripToEdit}
     * edited with {@code editTripDescriptor}.
     */
    private static Trip createEditedTrip(Trip tripToEdit, EditTripDescriptor editTripDescriptor) {
        assert tripToEdit != null;

        TripName updatedName = editTripDescriptor.getName().orElse(tripToEdit.getName());
        Accommodation updatedAccommodation = editTripDescriptor.getAccommodation()
                .orElse(tripToEdit.getAccommodation());
        Itinerary updatedItinerary = editTripDescriptor.getItinerary().orElse(tripToEdit.getItinerary());
        TripDate updatedDate = editTripDescriptor.getDate().orElse(tripToEdit.getDate());
        Set<Name> updatedCustomerNames = editTripDescriptor.getCustomerNames().orElse(tripToEdit.getCustomerNames());
        Note updatedNote = editTripDescriptor.getNote().orElse(tripToEdit.getNote());

        return new Trip(updatedName, updatedAccommodation,
                updatedItinerary, updatedDate, updatedCustomerNames, updatedNote);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTripCommand)) {
            return false;
        }

        EditTripCommand otherEditCommand = (EditTripCommand) other;
        return index.equals(otherEditCommand.index)
                && editTripDescriptor.equals(otherEditCommand.editTripDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editTripDescriptor", editTripDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the trip with. Each non-empty field value will replace the
     * corresponding field value of the trip.
     */
    public static class EditTripDescriptor {
        private TripName name;
        private Accommodation accommodation;
        private Itinerary itinerary;
        private TripDate date;
        private Set<Name> customerNames;
        private Note note;

        public EditTripDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code customerNames} is used internally.
         */
        public EditTripDescriptor(EditTripDescriptor toCopy) {
            setName(toCopy.name);
            setAccommodation(toCopy.accommodation);
            setItinerary(toCopy.itinerary);
            setDate(toCopy.date);
            setCustomerNames(toCopy.customerNames);
            setNote(toCopy.note);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, accommodation, itinerary, date, customerNames, note);
        }

        public void setName(TripName name) {
            this.name = name;
        }

        public Optional<TripName> getName() {
            return Optional.ofNullable(name);
        }

        public void setAccommodation(Accommodation accommodation) {
            this.accommodation = accommodation;
        }

        public Optional<Accommodation> getAccommodation() {
            return Optional.ofNullable(accommodation);
        }

        public void setItinerary(Itinerary itinerary) {
            this.itinerary = itinerary;
        }

        public Optional<Itinerary> getItinerary() {
            return Optional.ofNullable(itinerary);
        }

        public void setDate(TripDate date) {
            this.date = date;
        }

        public Optional<TripDate> getDate() {
            return Optional.ofNullable(date);
        }

        /**
         * Sets {@code customerNames} to this object's {@code customerNames}.
         * A defensive copy of {@code customerNames} is used internally.
         */
        public void setCustomerNames(Set<Name> customerNames) {
            this.customerNames = (customerNames != null) ? new HashSet<>(customerNames) : null;
        }

        /**
         * Returns an unmodifiable name set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code customerNames} is null.
         */
        public Optional<Set<Name>> getCustomerNames() {
            return (customerNames != null) ? Optional.of(Collections.unmodifiableSet(customerNames)) : Optional.empty();
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTripDescriptor)) {
                return false;
            }

            EditTripDescriptor otherEditTripDescriptor = (EditTripDescriptor) other;
            return Objects.equals(name, otherEditTripDescriptor.name)
                    && Objects.equals(accommodation, otherEditTripDescriptor.accommodation)
                    && Objects.equals(itinerary, otherEditTripDescriptor.itinerary)
                    && Objects.equals(date, otherEditTripDescriptor.date)
                    && Objects.equals(customerNames, otherEditTripDescriptor.customerNames)
                    && Objects.equals(note, otherEditTripDescriptor.note);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("accommodation", accommodation)
                    .add("itinerary", itinerary)
                    .add("date", date)
                    .add("customerNames", customerNames)
                    .add("note", note)
                    .toString();
        }
    }
}
