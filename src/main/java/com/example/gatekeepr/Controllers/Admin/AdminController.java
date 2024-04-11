package com.example.gatekeepr.Controllers.Admin;

import com.example.gatekeepr.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case STAREPOARTA -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminStarePoartaView());
                case ACCESLOG -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAccessLogView());
                case ANGAJATNOU -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAngajatNouView());
                case ADAUGAVIZ -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdaugaVizView()); // Fixed the line here
                case ANGAJATI -> admin_parent.setCenter(Model.getInstance().getViewFactory().getListaAngajatiView());
                case PREZENTAA -> admin_parent.setCenter(Model.getInstance().getViewFactory().getPrezentaAdminView());
            }
        });
    }

}
