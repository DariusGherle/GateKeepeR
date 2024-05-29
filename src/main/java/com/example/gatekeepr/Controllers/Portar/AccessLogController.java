package com.example.gatekeepr.Controllers.Portar;

import com.example.gatekeepr.Database.RaportsGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class AccessLogController implements Initializable {
    public ListView<String> access_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Obține toate înregistrările access_logs ca un array de string-uri
        String[] logsArray = RaportsGenerator.getAccessLogsAsStringArray();

        // Convertește array-ul la un ObservableList
        ObservableList<String> logsList = FXCollections.observableArrayList(logsArray);

        // Adaugă lista la ListView
        access_listview.setItems(logsList);
    }
}
