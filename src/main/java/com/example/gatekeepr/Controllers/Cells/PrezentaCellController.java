package com.example.gatekeepr.Controllers.Cells;

import com.example.gatekeepr.Models.Prezenta;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PrezentaCellController implements Initializable {
    @FXML
    public Label Nume_lbl;
    @FXML
    public Label Prenume_lbl;
    @FXML
    public Label ID_lbl;
    @FXML
    public Label Intarziat_lbl;
    @FXML
    public Label Depatament_lbl;
    @FXML
    public Label procentajPrezenta_lbl;

    private final Prezenta prezenta;

    public PrezentaCellController(Prezenta prezenta) {
        this.prezenta = prezenta;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Nume_lbl.textProperty().bind(prezenta.numeProperty());
        Prenume_lbl.textProperty().bind(prezenta.prenumeProperty());
        ID_lbl.textProperty().bind(prezenta.marcaProperty());
        Depatament_lbl.textProperty().bind(prezenta.departamentProperty());
        procentajPrezenta_lbl.textProperty().bind(prezenta.procentajPrezentaProperty());
    }
}