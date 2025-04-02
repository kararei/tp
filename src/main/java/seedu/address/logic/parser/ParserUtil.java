package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.trip.Accommodation;
import seedu.address.model.trip.Itinerary;
import seedu.address.model.trip.Note;
import seedu.address.model.trip.TripDate;
import seedu.address.model.trip.TripName;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    private static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String tag}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static String parseTagName(String tag) throws ParseException {
        if (tag.equals("")) {
            return tag;
        }
        String trimmedTagName = tag.trim().toLowerCase();
        if (!Tag.isValidTagName(trimmedTagName)) {
            throw new ParseException(Tag.TAGNAME_SPECIFICATION);
        }
        return trimmedTagName;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String name} into a {@code TripName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TripName parseTripName(String tripName) throws ParseException {
        requireNonNull(tripName);
        String trimmedTripName = tripName.trim();
        if (!TripName.isValidName(trimmedTripName)) {
            throw new ParseException(TripName.MESSAGE_CONSTRAINTS);
        }
        return new TripName(trimmedTripName);
    }

    /**
     * Parses a {@code String itinerary} into a {@code Itinerary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code itinerary} is invalid.
     */
    public static Itinerary parseItinerary(String itinerary) throws ParseException {
        requireNonNull(itinerary);
        String trimmedItinerary = itinerary.trim();
        if (!Itinerary.isValidItinerary(trimmedItinerary)) {
            throw new ParseException(Itinerary.MESSAGE_CONSTRAINTS);
        }
        return new Itinerary(trimmedItinerary);
    }

    /**
     * Parses a {@code String accommodation} into a {@code Accommodation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code accommodation} is invalid.
     */
    public static Accommodation parseAccommodation(String accommodation) throws ParseException {
        requireNonNull(accommodation);
        String trimmedAccommodation = accommodation.trim();
        if (!Accommodation.isValidAccommodation(trimmedAccommodation)) {
            throw new ParseException(Accommodation.MESSAGE_CONSTRAINTS);
        }
        return new Accommodation(trimmedAccommodation);
    }

    /**
     * Parses a {@code String tripDate} into a {@code TripDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tripDate} is invalid.
     */
    public static TripDate parseTripDate(String tripDate) throws ParseException {
        requireNonNull(tripDate);
        String trimmedTripDate = tripDate.trim();
        if (!TripDate.isValidTripDate(trimmedTripDate)) {
            throw new ParseException(TripDate.MESSAGE_CONSTRAINTS);
        }
        return new TripDate(trimmedTripDate);
    }

    /**
     * Parses a {@code String note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        return new Note(note);
    }
}
