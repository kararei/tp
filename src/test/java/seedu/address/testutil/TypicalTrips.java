package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_81;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_EAT_BAGUETTES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIP_DATE_2025;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIP_NAME_PARIS_2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TripBook;
import seedu.address.model.trip.Trip;

/**
 * A utility class containing a list of {@code Trip} objects to be used in tests.
 */
public class TypicalTrips {

    public static final Trip PARIS = new TripBuilder().withName("Paris Adventure")
            .withAccommodation("Hotel de Paris")
            .withItinerary("Visit Eiffel Tower, Louvre Museum")
            .withDate("15/6/2024")
            .withCustomerNames("Alice Pauline", "Bob Chen").build();

    public static final Trip TOKYO = new TripBuilder().withName("Tokyo Explorer")
            .withAccommodation("Shinjuku Hotel")
            .withItinerary("Visit Tokyo Tower, Shibuya Crossing")
            .withDate("20/7/2024")
            .withCustomerNames("Charlie Brown", "David Lee").build();

    public static final Trip SINGAPORE = new TripBuilder().withName("Singapore Tour")
            .withAccommodation("Marina Bay Sands")
            .withItinerary("Gardens by the Bay, Sentosa Island")
            .withDate("10/8/2024")
            .withCustomerNames("Eve Tan").build();

    public static final Trip BALI = new TripBuilder().withName("Bali Getaway")
            .withAccommodation("Ubud Resort")
            .withItinerary("Rice Terraces, Tanah Lot Temple")
            .withDate("5/9/2024")
            .withCustomerNames("Frank Wong", "Grace Liu").build();

    public static final Trip SEOUL = new TripBuilder().withName("Seoul Discovery")
            .withAccommodation("Gangnam Hotel")
            .withItinerary("Namsan Tower, Myeongdong Shopping")
            .withDate("1/10/2024")
            .withCustomerNames("Henry Park").build();

    // Manually added
    public static final Trip LONDON = new TripBuilder().withName("London Explorer")
            .withAccommodation("Westminster Hotel")
            .withItinerary("Big Ben, London Eye")
            .withDate("15/11/2024")
            .withCustomerNames("Ian Smith").build();

    public static final Trip NEW_YORK = new TripBuilder().withName("NYC Adventure")
            .withAccommodation("Manhattan Hotel")
            .withItinerary("Times Square, Central Park")
            .withDate("20/12/2024")
            .withCustomerNames("Jane Wilson").build();

    // Manually added - Trip's details found in {@code CommandTestUtil}
    public static final Trip PARIS_2025 = new TripBuilder().withName(VALID_TRIP_NAME_PARIS_2025)
            .withAccommodation(VALID_ACCOMMODATION_HOTEL_81)
            .withItinerary(VALID_ITINERARY_EAT_BAGUETTES)
            .withDate(VALID_TRIP_DATE_2025)
            .withCustomerNames(VALID_NAME_AMY, VALID_NAME_BOB).build();

    public static final String KEYWORD_MATCHING_PARIS = "Paris"; // A keyword that matches PARIS

    private TypicalTrips() {} // prevents instantiation

    /**
     * Returns a {@code TripBook} with all the typical trips.
     */
    public static TripBook getTypicalTripBook() {
        TripBook tb = new TripBook();
        for (Trip trip : getTypicalTrips()) {
            tb.addTrip(trip);
        }
        return tb;
    }

    public static List<Trip> getTypicalTrips() {
        return new ArrayList<>(Arrays.asList(PARIS, TOKYO, SINGAPORE, BALI, SEOUL));
    }
}
