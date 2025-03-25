package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.trip.Trip;

/**
 * Panel containing the list of trips.
 */
public class TripListPanel extends UiPart<Region> {
    private static final String FXML = "TripListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TripListPanel.class);

    @FXML
    private ListView<Trip> tripListView;

    /**
     * Creates a {@code TripListPanel} with the given {@code ObservableList}.
     */
    public TripListPanel(ObservableList<Trip> tripList) {
        super(FXML);
        tripListView.setItems(tripList);
        tripListView.setCellFactory(listView -> new TripListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Trip} using a {@code TripCard}.
     */
    class TripListViewCell extends ListCell<Trip> {
        @Override
        protected void updateItem(Trip trip, boolean empty) {
            super.updateItem(trip, empty);

            if (empty || trip == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TripCard(trip, getIndex() + 1).getRoot());
            }
        }
    }

}
