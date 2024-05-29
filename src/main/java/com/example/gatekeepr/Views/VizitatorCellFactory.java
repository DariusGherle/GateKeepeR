package com.example.gatekeepr.Views;

import com.example.gatekeepr.Controllers.Cells.VizitatorCellController;
import com.example.gatekeepr.Models.Vizitator;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class VizitatorCellFactory implements Callback<ListView<Vizitator>, ListCell<Vizitator>> {
    @Override
    public ListCell<Vizitator> call(ListView<Vizitator> param) {
        return new ListCell<Vizitator>() {
            @Override
            protected void updateItem(Vizitator item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Cells/VizitatorCell.fxml"));
                        loader.setController(new VizitatorCellController(item));
                        setGraphic(loader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}