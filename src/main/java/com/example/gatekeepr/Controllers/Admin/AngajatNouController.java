package com.example.gatekeepr.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AngajatNouController implements Initializable {
    public TextField aNume_fld;
    public TextField aID_fld;
    public TextField aParola_fld;
    public CheckBox aAccesAuto_box;
    public TextField aNrMasina_fld;
    public CheckBox aProgram_box;
    public ChoiceBox aProgramPoarta_choiceBox;
    public ChoiceBox aDepartament_choiceBox;
    public TextField aNormaOre_fld;
    public Button aCreeazaAngajat_btn;
    public Label error_lbl;
    public TextField pNume_fld;
    public TextField pID_fld;
    public TextField pParola_fld;
    public CheckBox pAccesAuto_box;
    public TextField pNrMasina_fld;
    public CheckBox pTura_box;
    public ChoiceBox pTura_choiceBox;
    public CheckBox pNorma_box;
    public TextField pNormaOre_fld;
    public Button create_client_btn1;
    public Label error_lbl1;
    public TextField aNume_fld1;
    public Button aCreeazaAngajat_btn1;
    public Text sIdAngajat_lbl;
    public Text sDepartament_lbl;
    public CheckBox sSefDepartament_box;
    public CheckBox sAdaugaAccesAuto_box;
    public TextField sNrMasina_fld;
    public CheckBox sSchimbaProgramPoarta_box;
    public ChoiceBox sProgramLucu_choiceBox;
    public CheckBox sNorma_box;
    public TextField sNormaOre_fld;
    public Button pCreeazaPortar_btn;
    public Button modificaAngajat_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
