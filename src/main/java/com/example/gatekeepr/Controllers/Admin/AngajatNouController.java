package com.example.gatekeepr.Controllers.Admin;

import com.example.gatekeepr.Models.Angajat;
import com.example.gatekeepr.Views.Departamente;
import com.example.gatekeepr.Views.IntervaleOrare;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Boolean.TRUE;

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
}

public class AngajatNouController implements Initializable {
    public TextField aNume_fld;
    public TextField aID_fld;
    public TextField aParola_fld;
    public CheckBox aAccesAuto_box;
    public TextField aNrMasina_fld;
    public CheckBox aProgram_box;
    public ChoiceBox<IntervaleOrare> aProgramPoarta_choiceBox;
    public ChoiceBox<Departamente> aDepartament_choiceBox;
    public TextField aNormaOre_fld;
    public Button aCreeazaAngajat_btn;
    public Label error_lbl;
    public TextField pNume_fld;
    public TextField pID_fld;
    public TextField pParola_fld;
    public CheckBox pAccesAuto_box;
    public TextField pNrMasina_fld;
    public CheckBox pTura_box;
    public ChoiceBox<IntervaleOrare> pTura_choiceBox;
    public CheckBox pNorma_box;
    public TextField pNormaOre_fld;
    public Button create_client_btn1;
    public Label error_lbl1;
    public TextField aNume_fld1;
    public Text sIdAngajat_lbl;
    public Text sDepartament_lbl;
    public CheckBox sSefDepartament_box;
    public CheckBox sAdaugaAccesAuto_box;
    public TextField sNrMasina_fld;
    public CheckBox sSchimbaProgramPoarta_box;
    public ChoiceBox<IntervaleOrare> sProgramLucu_choiceBox;
    public Button pCreeazaPortar_btn;
    public Button modificaAngajat_btn;
    public Button cautaAngajat_btn;
    public CheckBox sSuspendaAcces_box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set items for choice boxes
        aProgramPoarta_choiceBox.setItems(FXCollections.observableArrayList(IntervaleOrare.INC00SF08, IntervaleOrare.INC08SF16, IntervaleOrare.INC16SF24));
        aDepartament_choiceBox.setItems(FXCollections.observableArrayList(Departamente.URGENTE, Departamente.GENERAL));
        pTura_choiceBox.setItems(FXCollections.observableArrayList(IntervaleOrare.INC00SF12, IntervaleOrare.INC12SF24));
        sProgramLucu_choiceBox.setItems(FXCollections.observableArrayList(IntervaleOrare.INC00SF08, IntervaleOrare.INC08SF16, IntervaleOrare.INC16SF24));

        // Add action for create employee button
        aCreeazaAngajat_btn.setOnAction(event -> createAngajat());

        // Add action for search employee button
        cautaAngajat_btn.setOnAction(event -> cautaAngajat());
    }

    public void emptyFields() {
        // Empty all text fields
        aNume_fld.setText("");
        aID_fld.setText("");
        aParola_fld.setText("");
        aNrMasina_fld.setText("");
        aNormaOre_fld.setText("");
        pNume_fld.setText("");
        pID_fld.setText("");
        pParola_fld.setText("");
        pNrMasina_fld.setText("");
        pNormaOre_fld.setText("");
        aNume_fld1.setText("");
        sNrMasina_fld.setText("");

        // Reset all checkboxes
        aAccesAuto_box.setSelected(false);
        aProgram_box.setSelected(false);
        pAccesAuto_box.setSelected(false);
        pTura_box.setSelected(false);
        pNorma_box.setSelected(false);
        sSefDepartament_box.setSelected(false);
        sAdaugaAccesAuto_box.setSelected(false);
        sSchimbaProgramPoarta_box.setSelected(false);

        // Reset all choice boxes
        aProgramPoarta_choiceBox.getSelectionModel().clearSelection();
        aDepartament_choiceBox.getSelectionModel().clearSelection();
        pTura_choiceBox.getSelectionModel().clearSelection();
        sProgramLucu_choiceBox.getSelectionModel().clearSelection();

        // Clear error labels
        error_lbl.setText("");
        error_lbl1.setText("");

        // Optionally, reset texts
        sIdAngajat_lbl.setText("");
        sDepartament_lbl.setText("");
    }

    private void createAngajat() {
        try {
            // Verify that all required fields are filled
            if (aID_fld.getText().isEmpty() || aNume_fld.getText().isEmpty() || aParola_fld.getText().isEmpty() || aNormaOre_fld.getText().isEmpty() || aDepartament_choiceBox.getValue() == null) {
                error_lbl.setText("Toate câmpurile marcate sunt obligatorii.");
                return;
            }

            // Collect data from input fields
            int marca = Integer.parseInt(aID_fld.getText());
            String nume = aNume_fld.getText();
            String prenume = aNume_fld1.getText();
            String cnp = aParola_fld.getText();
            String poza = ""; // Poza poate fi selectată dintr-un fișier sau altă sursă, dar aici o lăsăm goală
            String codSecuritateBluetooth = ""; // Assumed to be empty for now
            String codSecuritateDispozitiv = ""; // Assumed to be empty for now
            boolean accesPoarta = TRUE;
            boolean accesBariera = aAccesAuto_box.isSelected();
            String numarAuto = aNrMasina_fld.getText();
            boolean esteSef = sSefDepartament_box.isSelected();
            int divizie = aDepartament_choiceBox.getValue().ordinal();
            int intervaleOrareAcces = Integer.parseInt(aNormaOre_fld.getText());

            // Insert the employee into the database
            String query = "INSERT INTO employees (marca, divizie, nume, prenume, cnp, poza, cod_securitate_bluetooth, cod_securitate_dispozitiv, intervale_orare_access, acces_poarta, acces_bariera, numar_auto, este_sef) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int rowsAffected = DatabaseHelper.executeUpdate(query, marca, divizie, nume, prenume, cnp, poza, codSecuritateBluetooth, codSecuritateDispozitiv, intervaleOrareAcces, accesPoarta ? 1 : 0, accesBariera ? 1 : 0, numarAuto, esteSef ? 1 : 0);

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Angajat adăugat cu succes.");
                emptyFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "A apărut o eroare la adăugarea angajatului.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void cautaAngajat() {
        // Implement the search functionality
    }
}
