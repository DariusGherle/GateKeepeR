package com.example.gatekeepr.Controllers.Portar;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.Random;
import static com.example.gatekeepr.Controllers.LoginController.guestPortarName;

class DatabaseHelper {
    private static final String CONNECTION_URL = "jdbc:sqlserver://database-IP.database.windows.net:1433;"
            + "database=database-IP;"
            + "encrypt=true;"
            + "trustServerCertificate=false;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;";
    private static final String USER = "database-IP@database-IP";
    private static final String PASSWORD = "Aloha123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
    }

    public static int executeUpdate(String query, Object... params) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();
        }
    }
    public static ResultSet executeQuery(String query, Object... params) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeQuery();
    }
}

public class AdaugaVizController implements Initializable {
    public TextField numeViz_fld;
    public TextField prenumeViz_fld;
    public TextField cnpViz_fld;
    public TextArea motivViz_fld;
    public Button adaugaViz_btn;
    public Button modificaViz_btn;
    public Button incheieViz_btn;
    public TextField cautaNume_fld;
    public Button cautaViz_btn;
    public ListView<String> listaViz_listview;

    private static final String _guestPortarName = guestPortarName; // Replace with actual portar name

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adaugaViz_btn.setOnAction(event -> addGuest());
        cautaViz_btn.setOnAction(event -> searchGuest());
    }

    private void addGuest() {
        Random random = new Random();
        // Store the input values in variables
        int id_vizite= random.nextInt();
        String nume = numeViz_fld.getText();
        String prenume = prenumeViz_fld.getText();
        String cnp = cnpViz_fld.getText();
        String motivVizita = motivViz_fld.getText();
        LocalDateTime dataOraIntrare = LocalDateTime.now();
        LocalDateTime dataOraIesire = dataOraIntrare.plusHours(4);
        // Insert the values into the database
        String query = "INSERT INTO guests (id_vizite, nume, prenume, cnp, scop_vizita, portar, data_ora_intrare, data_ora_iesire) VALUES (?,?,?, ?, ?, ?, ?, ?)";
        try {
            int rowsAffected = DatabaseHelper.executeUpdate(query, id_vizite, nume, prenume, cnp, motivVizita, guestPortarName, dataOraIntrare, dataOraIesire);
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Guest added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while adding the guest.");
        }
    }

    private void searchGuest() {
        String name = cautaNume_fld.getText();
        String query = "SELECT * FROM guests WHERE nume = ?";
        try {
            ResultSet resultSet = DatabaseHelper.executeQuery(query, name);
            listaViz_listview.getItems().clear();
            while (resultSet.next()) {
                String guestInfo = "ID: " + resultSet.getInt("id_vizite") +
                        ", Name: " + resultSet.getString("nume") +
                        ", Surname: " + resultSet.getString("prenume") +
                        ", CNP: " + resultSet.getString("cnp") +
                        ", Purpose: " + resultSet.getString("scop_vizita") +
                        ", Guard: " + resultSet.getString("portar") +
                        ", Entry Time: " + resultSet.getDate("data_ora_intrare")+
                        ", Exit Time: " + resultSet.getDate("data_ora_iesire");
                listaViz_listview.getItems().add(guestInfo);
            }
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
