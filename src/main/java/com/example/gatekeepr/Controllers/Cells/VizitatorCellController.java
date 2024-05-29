package com.example.gatekeepr.Controllers.Cells;

import com.example.gatekeepr.Models.Vizitator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class VizitatorCellController implements Initializable {
    @FXML
    public Label nume_lbl;
    @FXML
    public Label CNP_lbl;
    @FXML
    public Button plecare_btn;
    @FXML
    public Label data_ora_intrare;
    @FXML
    public Label data_ora_iesire;
    @FXML
    public Label motivVizita_lbl;
    @FXML
    public Label gardian_lbl;

    private final Vizitator vizitator;

    public VizitatorCellController(Vizitator vizitator) {
        this.vizitator = vizitator;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nume_lbl.textProperty().bind(vizitator.numeProperty());
        CNP_lbl.textProperty().bind(vizitator.cnpProperty());
        data_ora_intrare.textProperty().bind(vizitator.dataOraIntrareProperty().asString());
        data_ora_iesire.textProperty().bind(vizitator.dataOraIesireProperty().asString());
        motivVizita_lbl.textProperty().bind(vizitator.scopVizitaProperty());
        gardian_lbl.textProperty().bind(vizitator.portarProperty());
    }
}