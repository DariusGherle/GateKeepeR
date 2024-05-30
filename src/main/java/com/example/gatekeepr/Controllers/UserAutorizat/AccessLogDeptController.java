package com.example.gatekeepr.Controllers.UserAutorizat;

import com.example.gatekeepr.Controllers.Cells.AccessCellController;
import com.example.gatekeepr.Models.Access;
import com.example.gatekeepr.Database.RaportsGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccessLogDeptController implements Initializable {
    @FXML
    public ListView<Access> access_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Obține toate înregistrările access_logs pentru departament ca o listă de obiecte Access
        List<Access> accessLogs = RaportsGenerator.getAllDivisionsAttendanceReports();

        // Convertește lista la un ObservableList
        ObservableList<Access> logsList = FXCollections.observableArrayList(accessLogs);

        // Configurează ListView pentru a folosi AccessCell pentru fiecare element
        access_listview.setItems(logsList);
        access_listview.setCellFactory(new Callback<ListView<Access>, ListCell<Access>>() {
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
}