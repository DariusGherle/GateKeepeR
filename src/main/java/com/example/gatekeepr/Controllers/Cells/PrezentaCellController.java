package com.example.gatekeepr.Controllers.Cells;

import com.example.gatekeepr.Models.Prezenta;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PrezentaCellController implements Initializable {
    public Label Nume_lbl;
    public Label Prenume_lbl;
    public Label ID_lbl;
    public Label nrMasina_lbl;
    public Label date_lbl;
    public Label oraIntrare_lbl;
    public Button plecare_btn;
    public Label Intarziat_lbl;

    public final Prezenta prezenta;

    public PrezentaCellController(Prezenta prezenta) {this.prezenta=prezenta;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
