package com.example.gatekeepr.Controllers.Portar;

import com.example.gatekeepr.Database.DatabaseHelper;
import com.example.gatekeepr.Models.Vizitator;
import com.example.gatekeepr.Views.VizitatorCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.Random;

import static com.example.gatekeepr.Controllers.LoginController.guestPortarName;

public class AdaugaVizController implements Initializable {
    @FXML
    public TextField numeViz_fld;
    @FXML
    public TextField prenumeViz_fld;
    @FXML
    public TextField cnpViz_fld;
    @FXML
    public TextArea motivViz_fld;
    @FXML
    public Button adaugaViz_btn;
    @FXML
    public Button modificaViz_btn;
    @FXML
    public Button incheieViz_btn;
    @FXML
    public TextField cautaNume_fld;
    @FXML
    public Button cautaViz_btn;
    @FXML
    public ListView<Vizitator> listaViz_listview;

    private static final String _guestPortarName = guestPortarName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adaugaViz_btn.setOnAction(event -> addGuest());
        cautaViz_btn.setOnAction(event -> searchGuest());

        // Configure ListView to use VizitatorCell
        listaViz_listview.setCellFactory(new VizitatorCellFactory());

        // Load all guests initially
        loadAllGuests();
    }

    private void loadAllGuests() {
        String query = "SELECT * FROM guests";
        ObservableList<Vizitator> data = FXCollections.observableArrayList();

        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Vizitator vizitator = new Vizitator(
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("cnp"),
                        resultSet.getString("scop_vizita"),
                        resultSet.getString("portar"),
                        resultSet.getDate("data_ora_intrare"),
                        resultSet.getDate("data_ora_iesire")
                );
                data.add(vizitator);
            }

            listaViz_listview.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading guests.");
        }
    }

    private void addGuest() {
        Random random = new Random();
        int id_vizite = random.nextInt();
        String nume = numeViz_fld.getText();
        String prenume = prenumeViz_fld.getText();
        String cnp = cnpViz_fld.getText();
        String motivVizita = motivViz_fld.getText();
        LocalDateTime dataOraIntrare = LocalDateTime.now();
        LocalDateTime dataOraIesire = dataOraIntrare.plusHours(4);

        String query = "INSERT INTO guests (id_vizite, nume, prenume, cnp, scop_vizita, portar, data_ora_intrare, data_ora_iesire) VALUES (?,?,?,?,?,?,?,?)";
        try {
            int rowsAffected = DatabaseHelper.executeUpdate(query, id_vizite, nume, prenume, cnp, motivVizita, guestPortarName, dataOraIntrare, dataOraIesire);
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Guest added successfully.");
                loadAllGuests(); // Reload guests to update the ListView
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while adding the guest.");
        }
    }

    private void searchGuest() {
        String name = cautaNume_fld.getText();
        String query = "SELECT * FROM guests WHERE nume = ?";
        ObservableList<Vizitator> data = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = DatabaseHelper.executeQuery(query, name);
            while (resultSet.next()) {
                Vizitator vizitator = new Vizitator(
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("cnp"),
                        resultSet.getString("scop_vizita"),
                        resultSet.getString("portar"),
                        resultSet.getDate("data_ora_intrare"),
                        resultSet.getDate("data_ora_iesire")
                );
                data.add(vizitator);
            }

            listaViz_listview.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while searching for guests.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}