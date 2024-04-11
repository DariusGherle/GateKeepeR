package com.example.gatekeepr.Controllers.UserAutorizat;

import com.example.gatekeepr.Models.Model;
import eu.hansolo.tilesfx.tools.DoubleExponentialSmoothingForLinearSeries;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PropertySheet;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    public BorderPane user_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case DEPARTAMENT -> user_parent.setCenter(Model.getInstance().getViewFactory().getDepartamentView());
                case PREZENTA -> user_parent.setCenter(Model.getInstance().getViewFactory().getPrezDeptView());
                case ACCESSLOG -> user_parent.setCenter(Model.getInstance().getViewFactory().getAccessLogDeptView());
            }
        });
    }
}
