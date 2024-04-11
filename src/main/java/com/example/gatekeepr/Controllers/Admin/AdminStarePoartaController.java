package com.example.gatekeepr.Controllers.Admin;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminStarePoartaController implements Initializable {

    public Text numeutil_lbl;
    public Label date_lbl;
    public Label numepietonal_lbl;
    public Label idpietonal_lbl;
    public ImageView pozapietonal_img;
    public Button intrarepietonal_btn;
    public Button iesirepietonal_btn;
    public Label validarepietonal_lbl;
    public Label numeauto_lbl;
    public Label nrmasina_lbl;
    public Label idauto_lbl;
    public Label program_lbl;
    public FontAwesomeIconView iconauto;
    public ImageView pozaauto_img;
    public FontAwesomeIconView iconStarePoarta;
    public Label starepoarta_lbl;
    public Button actiunepoarta_btn;
    public TextField numeangajat_txt;
    public Button cauta_btn;
    public ImageView pozacautare_img;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void intrarenonvalida_btn(ActionEvent actionEvent) {
    }

    public void iesirenonvalida_btn(ActionEvent actionEvent) {
    }
}
