package com.example.gatekeepr.Controllers.Admin;

import com.example.gatekeepr.Database.DatabaseHelper;
import com.example.gatekeepr.Views.Departamente;
import com.example.gatekeepr.Views.IntervaleOrare;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.Boolean.TRUE;

public class AngajatNouController implements Initializable {
    public TextField pNume_fld;
    public TextField pParola_fld;
    public Button pCreeazaPortar_btn;
    public Label error_lbl1;
    @FXML
    private TextField aNume_fld;
    @FXML
    private TextField aID_fld;
    @FXML
    private TextField aParola_fld;
    @FXML
    private CheckBox aAccesAuto_box;
    @FXML
    private TextField aNrMasina_fld;
    @FXML
    private ChoiceBox<IntervaleOrare> aProgramPoarta_choiceBox;
    @FXML
    private ChoiceBox<Departamente> aDepartament_choiceBox;
    @FXML
    private TextField aNormaOre_fld;
    @FXML
    private Button aCreeazaAngajat_btn;
    @FXML
    private Label error_lbl;
    @FXML
    private TextField aNume_fld1;
    @FXML
    private Text sIdAngajat_lbl;
    @FXML
    private Text sDepartament_lbl;
    @FXML
    private CheckBox sSefDepartament_box;
    @FXML
    private CheckBox sAdaugaAccesAuto_box;
    @FXML
    private TextField sNrMasina_fld;
    @FXML
    private ChoiceBox<IntervaleOrare> sProgramLucu_choiceBox;
    @FXML
    private CheckBox sSuspendaAcces_box;
    @FXML
    private Button modificaAngajat_btn;
    @FXML
    private Button cautaAngajat_btn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aProgramPoarta_choiceBox.setItems(FXCollections.observableArrayList(IntervaleOrare.values()));
        aDepartament_choiceBox.setItems(FXCollections.observableArrayList(Departamente.values()));
        sProgramLucu_choiceBox.setItems(FXCollections.observableArrayList(IntervaleOrare.values()));

        aCreeazaAngajat_btn.setOnAction(event -> createAngajat());
        cautaAngajat_btn.setOnAction(event -> cautaAngajat());
        pCreeazaPortar_btn.setOnAction(event -> createPortar());
    }

    private void emptyFields() {
        aNume_fld.setText("");
        aID_fld.setText("");
        aParola_fld.setText("");
        aNrMasina_fld.setText("");
        aNormaOre_fld.setText("");
        aNume_fld1.setText("");
        sNrMasina_fld.setText("");
        aAccesAuto_box.setSelected(false);
        sSefDepartament_box.setSelected(false);
        sAdaugaAccesAuto_box.setSelected(false);
        sSuspendaAcces_box.setSelected(false);
        aProgramPoarta_choiceBox.getSelectionModel().clearSelection();
        aDepartament_choiceBox.getSelectionModel().clearSelection();
        sProgramLucu_choiceBox.getSelectionModel().clearSelection();
        error_lbl.setText("");
        sIdAngajat_lbl.setText("");
        sDepartament_lbl.setText("");
    }

    private void createAngajat() {
        try {
            if (aID_fld.getText().isEmpty() || aNume_fld.getText().isEmpty() || aParola_fld.getText().isEmpty() || aNormaOre_fld.getText().isEmpty() || aDepartament_choiceBox.getValue() == null) {
                error_lbl.setText("Toate câmpurile marcate sunt obligatorii.");
                return;
            }

            int marca = Integer.parseInt(aID_fld.getText());
            String nume = aNume_fld.getText();
            String prenume = aNume_fld1.getText();
            String cnp = aParola_fld.getText();
            String poza = "";
            String codSecuritateBluetooth = "";
            String codSecuritateDispozitiv = "";
            boolean accesPoarta = TRUE;
            boolean accesBariera = aAccesAuto_box.isSelected();
            String numarAuto = aNrMasina_fld.getText();
            boolean esteSef = sSefDepartament_box.isSelected();
            int divizie = aDepartament_choiceBox.getValue().ordinal();
            int intervaleOrareAcces = Integer.parseInt(aNormaOre_fld.getText());

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


    private void createPortar() {
        try {
            // Verify that all required fields are filled
            if (pNume_fld.getText().isEmpty() || pParola_fld.getText().isEmpty()) {
                error_lbl1.setText("Toate câmpurile marcate sunt obligatorii.");
                return;
            }

            // Collect data from input fields
            String nume = pNume_fld.getText();
            String parola = pParola_fld.getText();

            // Insert the portar into the database
            String query = "INSERT INTO gatekeepers (adresa_utilizator, parola) VALUES (?, ?)";
            int rowsAffected = DatabaseHelper.executeUpdate(query, nume, parola);

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Portar adăugat cu succes.");
                emptyFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "A apărut o eroare la adăugarea portarului.");
        }
    }
    private void cautaAngajat() {
        // Implement the search functionality
    }
}
