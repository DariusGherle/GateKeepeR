package com.example.gatekeepr.Controllers.Admin;

import com.example.gatekeepr.Models.Prezenta;
import com.example.gatekeepr.Views.PrezentaCellFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PrezentaAController implements Initializable {
    @FXML
    public TextField Nume_fld;
    @FXML
    public TextField ID_fld;
    @FXML
    public CheckBox AccesAuto_box;
    @FXML
    public TextField NrMasina_fld;
    @FXML
    public CheckBox Program_box;
    @FXML
    public ChoiceBox<Division> Departament_choiceBox;
    @FXML
    public TextField NormaOre_fld;
    @FXML
    public CheckBox laLucru_box;
    @FXML
    public ChoiceBox prezentaCurenta_choiceBox;
    @FXML
    public Button aplicaFiltre_btn;
    @FXML
    public Label error_lbl;
    @FXML
    public ListView<Prezenta> prezenta_listview;
    @FXML
    public Label procentajPrezenta_lbl;
    @FXML
    public ChoiceBox procDep_choiceBox;
    @FXML
    public Button actualizeaza_btn;

    private static final String CONNECTION_URL = "jdbc:sqlserver://database-IP.database.windows.net:1433;"
            + "database=database-IP;"
            + "encrypt=true;"
            + "trustServerCertificate=false;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;";
    private static final String USER = "database-IP@database-IP";
    private static final String PASSWORD = "Aloha123";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate the ChoiceBox with division options
        Departament_choiceBox.setItems(FXCollections.observableArrayList(Division.values()));

        // Add listener to the ChoiceBox
        Departament_choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            applyFilters();
        });

        // Set the action for the apply filters button
        aplicaFiltre_btn.setOnAction(event -> applyFilters());

        // Configure ListView to use PrezentaCell
        prezenta_listview.setCellFactory(new PrezentaCellFactory());

        // Load all employees when the page is initialized
        loadAllEmployees();
    }

    private void loadAllEmployees() {
        String query = "SELECT * FROM employees";
        ObservableList<Prezenta> data = FXCollections.observableArrayList();
        BigDecimal totalPrezente = BigDecimal.ZERO;
        int count = 0;

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String marca = resultSet.getString("marca");
                int departament = resultSet.getInt("divizie");
                String procentajPrezenta = resultSet.getString("prezente");
                String actualDepartament = getDepartamentName(departament);

                Prezenta prezenta = new Prezenta(
                        new SimpleStringProperty(nume),
                        new SimpleStringProperty(prenume),
                        new SimpleStringProperty(marca),
                        new SimpleStringProperty(actualDepartament),
                        new SimpleStringProperty(procentajPrezenta)
                );

                data.add(prezenta);
                totalPrezente = totalPrezente.add(new BigDecimal(procentajPrezenta));
                count++;
            }

            prezenta_listview.setItems(data);

            // Calculate the average percentage and update the label
            if (count > 0) {
                BigDecimal averagePrezente = totalPrezente.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);
                procentajPrezenta_lbl.setText(averagePrezente + "%");
            } else {
                procentajPrezenta_lbl.setText("0%");
            }

        } catch (Exception e) {
            e.printStackTrace();
            error_lbl.setText("An error occurred while loading employees.");
        }
    }

    private void applyFilters() {
        String query = buildQuery();
        ObservableList<Prezenta> data = FXCollections.observableArrayList();
        BigDecimal totalPrezente = BigDecimal.ZERO;
        int count = 0;

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String marca = resultSet.getString("marca");
                int departament = resultSet.getInt("divizie");
                String procentajPrezenta = resultSet.getString("prezente");
                String actualDepartament = getDepartamentName(departament);

                Prezenta prezenta = new Prezenta(
                        new SimpleStringProperty(nume),
                        new SimpleStringProperty(prenume),
                        new SimpleStringProperty(marca),
                        new SimpleStringProperty(actualDepartament),
                        new SimpleStringProperty(procentajPrezenta)
                );

                data.add(prezenta);
                totalPrezente = totalPrezente.add(new BigDecimal(procentajPrezenta));
                count++;
            }

            prezenta_listview.setItems(data);

            // Calculate the average percentage and update the label
            if (count > 0) {
                BigDecimal averagePrezente = totalPrezente.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);
                procentajPrezenta_lbl.setText(averagePrezente + "%");
            } else {
                procentajPrezenta_lbl.setText("0%");
            }

        } catch (Exception e) {
            e.printStackTrace();
            error_lbl.setText("An error occurred while applying filters.");
        }
    }

    private String buildQuery() {
        StringBuilder query = new StringBuilder("SELECT * FROM employees");

        boolean hasCondition = false;

        if (!Nume_fld.getText().isEmpty()) {
            query.append(" WHERE nume LIKE '%").append(Nume_fld.getText()).append("%'");
            hasCondition = true;
        }
        if (!ID_fld.getText().isEmpty()) {
            if (hasCondition) {
                query.append(" AND");
            } else {
                query.append(" WHERE");
                hasCondition = true;
            }
            query.append(" marca = ").append(ID_fld.getText());
        }
        if (Departament_choiceBox.getValue() != null) {
            if (hasCondition) {
                query.append(" AND");
            } else {
                query.append(" WHERE");
                hasCondition = true;
            }
            query.append(" divizie = ").append(Departament_choiceBox.getValue().ordinal() + 1);
        }
        if (laLucru_box.isSelected()) {
            if (hasCondition) {
                query.append(" AND");
            } else {
                query.append(" WHERE");
            }
            query.append(" prezente > 0");
        }

        return query.toString();
    }

    private String getDepartamentName(int departament) {
        switch (departament) {
            case 1:
                return "URGENTE";
            case 2:
                return "GENERAL";
            case 3:
                return "ONCOLOGIE";
            default:
                return "ALTELE";
        }
    }
}