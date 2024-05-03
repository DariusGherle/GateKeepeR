package com.example.gatekeepr.Views;

import com.example.gatekeepr.Controllers.Cells.AngajatCellController;
import com.example.gatekeepr.Models.Angajat;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class AngajatCellFactory extends ListCell<Angajat> {
    @Override
    protected void updateItem(Angajat angajat, boolean empty) {
        super.updateItem(angajat, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Cells/AngajatCell.fxml"));
            AngajatCellController controller=new AngajatCellController(angajat);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
