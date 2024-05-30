package com.example.gatekeepr.Controllers.Admin;

import com.example.gatekeepr.Controllers.Cells.AccessCellController;
import com.example.gatekeepr.Models.Access;
import com.example.gatekeepr.Database.RaportsGenerator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class AdminStarePoartaController implements Initializable {
    public Text numeutil_lbl;
    public Label date_lbl;
    public Label numepietonal_lbl;
    public Label idpietonal_lbl;
    public ImageView pozapietonal_img;
    public Button intrarepietonal_btn;
    public Button iesirepietonal_btn;
    public Label validarepietonal_lbl;
    public Label numeauto_lbl;
    public Label nrmasina_lbl;
    public Label idauto_lbl;
    public Label program_lbl;
    public FontAwesomeIconView iconauto;
    public ImageView pozaauto_img;
    public FontAwesomeIconView iconStarePoarta;
    public Label starepoarta_lbl;
    public Button actiunepoarta_btn;
    public TextField numeangajat_txt;
    public Button cauta_btn;
    public ImageView pozacautare_img;
    public ListView<Access> ultimeleactiuni_listview;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create a Timeline to update the date_lbl every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateTime()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.play(); // Start the timeline

        // Fetch the last 5 access logs and populate the ListView
        List<Access> accessLogs = RaportsGenerator.getAllDivisionsAttendanceReports();
        ultimeleactiuni_listview.setItems(FXCollections.observableArrayList(accessLogs.subList(Math.max(accessLogs.size() - 5, 0), accessLogs.size())));
        ultimeleactiuni_listview.setCellFactory(new Callback<ListView<Access>, ListCell<Access>>() {
            @Override
            public ListCell<Access> call(ListView<Access> param) {
                return new ListCell<Access>() {
                    @Override
                    protected void updateItem(Access item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Cells/AccessCell.fxml"));
                                AccessCellController controller = new AccessCellController(item);
                                loader.setController(controller);
                                setGraphic(loader.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
            }
        });
    }

    private void updateDateTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Format the date and time
        String formattedDateTime = currentDateTime.format(formatter);
        // Set the formatted date and time to date_lbl
        date_lbl.setText(formattedDateTime);
    }

    public void iesirenonvalida_btn(ActionEvent actionEvent) {
    }

    public void intrarenonvalida_btn(ActionEvent actionEvent) {
    }
}