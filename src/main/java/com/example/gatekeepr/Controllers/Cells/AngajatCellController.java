package com.example.gatekeepr.Controllers.Cells;

import com.example.gatekeepr.Models.Angajat;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AngajatCellController implements Initializable {
    public Label nume_lbl;
    public Label Id_lbl;
    public Label nrMasina_lbl;
    public Label CNP_lbl;
    public Button concediaza_btn;
    public Label departament_lbl;
    public Label intervalOrar_lbl;

    private final Angajat angajat;

    public AngajatCellController(Angajat angajat) {this.angajat=angajat;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
