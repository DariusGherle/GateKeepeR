package com.example.gatekeepr.Controllers.Cells;

import com.example.gatekeepr.Models.Vizitator;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class VizitatorCellController implements Initializable {
    public Label access_date_lbl;
    public Label nume_lbl;
    public Label carePoarta_lbl;
    public Label access_time_lbl;
    public Label nrMasina_lbl;
    public Label CNP_lbl;
    public Button plecare_btn;

    public final Vizitator vizitator;

    public VizitatorCellController(Vizitator vizitator) {this.vizitator=vizitator;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
