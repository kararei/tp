package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.trip.Trip;

/**
 * An UI component that displays information of a {@code Trip}.
 */
public class TripCard extends UiPart<Region> {

    private static final String FXML = "TripListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Trip trip;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label accommodation;
    @FXML
    private Label itinerary;
    @FXML
    private Label date;
    @FXML
    private Label customerNames;
    @FXML
    private Label notes;

    /**
     * Creates a {@code TripCard} with the given {@code Trip} and index to display.
     */
    public TripCard(Trip trip, int displayedIndex) {
        super(FXML);
        this.trip = trip;
        id.setText(displayedIndex + ". ");
        name.setText(trip.getName().name);
        date.setText("(" + trip.getDate().toString() + ")");
        itinerary.setText("Itinerary: " + trip.getItinerary().itinerary);
        accommodation.setText("Accomodation: " + trip.getAccommodation().accommodation);
        notes.setText("Notes: " + trip.getNote().note);

        String customerNamesStr = trip.getCustomerNames().stream()
                .sorted(Comparator.comparing(customerName -> customerName.fullName.toLowerCase()))
                .map(customerName -> customerName.fullName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
        customerNames.setText("Customers: " + customerNamesStr);
    }
}
