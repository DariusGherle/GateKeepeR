package com.example.gatekeepr.Controllers.Admin;

import com.example.gatekeepr.Controllers.Cells.AngajatCellController;
import com.example.gatekeepr.Database.EmployeesDB;
import com.example.gatekeepr.Models.Angajat;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;

public class ListaAngajatiController implements Initializable {
    @FXML
    public ListView<Angajat> angajati_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Angajat> angajatiList = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = EmployeesDB.selectAll();
            while (resultSet.next()) {
                Angajat angajat = new Angajat(
                        new SimpleStringProperty(resultSet.getString("marca")),
                        new SimpleStringProperty(resultSet.getString("nume")),
                        new SimpleStringProperty(resultSet.getString("prenume")),
                        new SimpleStringProperty(resultSet.getString("cnp")),
                        new SimpleStringProperty(resultSet.getString("divizie")),  // Assuming divizie is the department
                        resultSet.getString("poza"),
                        resultSet.getString("cod_securitate_bluetooth"),
                        resultSet.getString("cod_securitate_dispozitiv"),
                        resultSet.getBoolean("acces_poarta"),
                        resultSet.getBoolean("acces_bariera"),
                        new SimpleStringProperty(resultSet.getString("numar_auto")),
                        resultSet.getBoolean("este_sef"),
                        resultSet.getInt("divizie"),
                        resultSet.getInt("intervale_orare_access")
                );
                angajatiList.add(angajat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        angajati_listview.setItems(angajatiList);
        angajati_listview.setCellFactory(new Callback<ListView<Angajat>, ListCell<Angajat>>() {
            @Override
            public ListCell<Angajat> call(ListView<Angajat> param) {
                return new ListCell<Angajat>() {
                    @Override
                    protected void updateItem(Angajat item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Cells/AngajatCell.fxml"));
                                // No need to set the controller here
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