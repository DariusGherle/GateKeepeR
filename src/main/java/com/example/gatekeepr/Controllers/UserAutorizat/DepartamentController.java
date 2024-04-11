package com.example.gatekeepr.Controllers.UserAutorizat;

import com.example.gatekeepr.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartamentController implements Initializable {
    public TextField Nume_fld;
    public TextField ID_fld;
    public CheckBox AccesAuto_box;
    public TextField NrMasina_fld;
    public CheckBox Program_box;
    public ChoiceBox ProgramPoarta_choiceBox;
    public ChoiceBox Departament_choiceBox;
    public TextField NormaOre_fld;
    public CheckBox laLucru_box;
    public ChoiceBox prezentaCurenta_choiceBox;
    public Button aplicaFiltre_btn;
    public Label error_lbl;
    public ListView prezenta_listview;
    public Label procentajPrezenta_lbl;
    public Label numeDepartament_lbl;
    public Button actualizeaza_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
