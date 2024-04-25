package com.example.gatekeepr;

import com.example.gatekeepr.Controllers.SchemaInitializer;
import com.example.gatekeepr.Models.Model;
import com.example.gatekeepr.Views.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        SchemaInitializer.initializeDatabase();
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
