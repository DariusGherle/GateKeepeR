package com.example.gatekeepr.Controllers.Cells;

import com.example.gatekeepr.Models.Access;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AccessCellController implements Initializable {
    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label access_date_lbl;
    public Label nume_lbl;
    public Label carePoarta_lbl;
    public Label status_lbl;
    public FontAwesomeIconView Clock_icon;
    public Button message_btn;
    public Label access_time_lbl;
    public Label nrMasina_lbl;

    private  final Access access;

    public AccessCellController(Access access){
        this.access = access;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Leagă proprietățile accesului la etichetele din interfață
        nume_lbl.textProperty().bind(access.numeProperty());
        access_date_lbl.textProperty().bind(access.datePropery());
        access_time_lbl.textProperty().bind(access.oraProperty());
        carePoarta_lbl.textProperty().bind(access.carePoartaProperty());
        nrMasina_lbl.textProperty().bind(access.nrMasinaSauIdProperty());

        // Statusul bazat pe tipul accesului (Entry/Exit)
        if (access.carePoartaProperty().get().equals("Entry")) {
            status_lbl.setText("Intrare");
            in_icon.setVisible(true);
            out_icon.setVisible(false);
        } else {
            status_lbl.setText("Iesire");
            in_icon.setVisible(false);
            out_icon.setVisible(true);
        }
    }
}
