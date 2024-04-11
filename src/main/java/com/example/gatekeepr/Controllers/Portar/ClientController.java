package com.example.gatekeepr.Controllers.Client;

import com.example.gatekeepr.Models.Model;
import com.example.gatekeepr.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public BorderPane client_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case ClientMenuOptions.ACCESSLOG -> client_parent.setCenter(Model.getInstance().getViewFactory().getAccessLogView());
                case ClientMenuOptions.ADAUGAVIZ -> client_parent.setCenter(Model.getInstance().getViewFactory().getAdaugaVizView());
                case ClientMenuOptions.PROFIL ->client_parent.setCenter(Model.getInstance().getViewFactory().getProfilView());
                case ClientMenuOptions.PREZENTA ->client_parent.setCenter(Model.getInstance().getViewFactory().getPrezentaView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getStarePoartaView());
            }
        });
    }
}
