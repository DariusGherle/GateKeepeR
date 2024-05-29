package com.example.gatekeepr.Views;

import com.example.gatekeepr.Controllers.Cells.PrezentaCellController;
import com.example.gatekeepr.Models.Prezenta;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class PrezentaCellFactory implements Callback<ListView<Prezenta>, ListCell<Prezenta>> {
    @Override
    public ListCell<Prezenta> call(ListView<Prezenta> param) {
        return new ListCell<Prezenta>() {
            @Override
            protected void updateItem(Prezenta item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Cells/PrezentaCell.fxml"));
                        PrezentaCellController controller = new PrezentaCellController(item);
                        loader.setController(controller);
                        setGraphic(loader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}