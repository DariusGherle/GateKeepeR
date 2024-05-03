package com.example.gatekeepr.Views;

import com.example.gatekeepr.Controllers.Cells.AccessCellController;
import com.example.gatekeepr.Controllers.Cells.VizitatorCellController;
import com.example.gatekeepr.Models.Access;
import com.example.gatekeepr.Models.Vizitator;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class VizitatorCellFactory extends ListCell<Vizitator> {
    @Override
    protected void updateItem(Vizitator vizitator, boolean empty) {
        super.updateItem(vizitator, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Cells/VizitatorCell.fxml"));
            VizitatorCellController controller=new VizitatorCellController(vizitator);
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
