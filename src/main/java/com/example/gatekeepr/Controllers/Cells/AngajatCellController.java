package com.example.gatekeepr.Controllers.Cells;

import com.example.gatekeepr.Models.Angajat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AngajatCellController implements Initializable {
    @FXML
    public Label nume_lbl;
    @FXML
    public Label Id_lbl;
    @FXML
    public Label nrMasina_lbl;
    @FXML
    public Label CNP_lbl;
    @FXML
    public Button concediaza_btn;
    @FXML
    public Label departament_lbl;
    @FXML
    public Label intervalOrar_lbl;

    private final Angajat angajat;

    public AngajatCellController(Angajat angajat) {
        this.angajat = angajat;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nume_lbl.textProperty().bind(angajat.numeProperty());
        Id_lbl.textProperty().bind(angajat.marcaProperty());
        nrMasina_lbl.textProperty().bind(angajat.numar_autoProperty());
        CNP_lbl.textProperty().bind(angajat.CNPProperty());
        departament_lbl.setText(String.valueOf(angajat.getDepartament()));
        intervalOrar_lbl.setText(String.valueOf(angajat.getIntervale_orare_acces()));

        concediaza_btn.setOnAction(event -> {
            // Add code to handle the dismissal of the employee
        });
    }
}