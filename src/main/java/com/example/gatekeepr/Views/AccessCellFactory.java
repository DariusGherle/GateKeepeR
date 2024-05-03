package com.example.gatekeepr.Views;

import com.example.gatekeepr.Controllers.Cells.AccessCellController;
import com.example.gatekeepr.Models.Access;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class AccessCellFactory extends ListCell<Access> {
    @Override
    protected void updateItem(Access access, boolean empty) {
        super.updateItem(access, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Cells/AccessCell.fxml"));
            AccessCellController controller=new AccessCellController(access);
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
