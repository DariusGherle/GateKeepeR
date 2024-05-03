package com.example.gatekeepr.Views;

import com.example.gatekeepr.Controllers.Cells.PrezentaCellController;
import com.example.gatekeepr.Models.Prezenta;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class PrezentaCellFactory extends ListCell<Prezenta> {
    @Override
    protected void updateItem(Prezenta prezenta, boolean empty) {
        super.updateItem(prezenta, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Cells/PrezentaCell.fxml"));
            PrezentaCellController controller=new PrezentaCellController(prezenta);
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
