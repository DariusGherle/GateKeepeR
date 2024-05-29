package com.example.gatekeepr.Controllers.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public TextField Nume_fld;
    public TextField ID_fld;
    public CheckBox AccesAuto_box;
    public TextField NrMasina_fld;
    public CheckBox Program_box;
    public ChoiceBox<Division> Departament_choiceBox;
    public TextField NormaOre_fld;
    public CheckBox laLucru_box;
    public ChoiceBox prezentaCurenta_choiceBox;
    public Button aplicaFiltre_btn;
    public Label error_lbl;
    public ListView prezenta_listview;
    public Label procentajPrezenta_lbl;
    public ChoiceBox procDep_choiceBox;
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
    }

    private void applyFilters() {
        String query = buildQuery();
        ObservableList<String> data = FXCollections.observableArrayList();
        BigDecimal totalPrezente = BigDecimal.ZERO;
        int count = 0;

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int divizie = resultSet.getInt("divizie");
                String divisionName = Division.getNameByValue(divizie);
                BigDecimal prezente = resultSet.getBigDecimal("prezente");
                String employeeData = resultSet.getInt("marca") + " - " +
                        resultSet.getString("nume") + " " +
                        resultSet.getString("prenume") + ", Division: " +
                        divisionName + ", Prezente: " +
                        prezente + "%";
                data.add(employeeData);

                totalPrezente = totalPrezente.add(prezente);
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
        StringBuilder query = new StringBuilder("SELECT * FROM employees WHERE 1=1");

        if (!Nume_fld.getText().isEmpty()) {
            query.append(" AND nume LIKE '%").append(Nume_fld.getText()).append("%'");
        }
        if (!ID_fld.getText().isEmpty()) {
            query.append(" AND marca = ").append(ID_fld.getText());
        }
        if (Departament_choiceBox.getValue() != null) {
            query.append(" AND divizie = ").append(Departament_choiceBox.getValue().ordinal() + 1);
        }
        if (laLucru_box.isSelected()) {
            query.append(" AND prezente > 0");
        }

        // Add other filters as needed

        return query.toString();
    }
}
