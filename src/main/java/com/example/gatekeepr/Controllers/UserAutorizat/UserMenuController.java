package com.example.gatekeepr.Controllers.UserAutorizat;

import com.example.gatekeepr.Models.Model;
import com.example.gatekeepr.Views.UserMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {
    public Button prezentaDepartament_btn;
    public Button prezenta_btn;
    public Button log_btn;
    public Button adugaviz_btn;
    public Button logout_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    addListeners();
    }

    private void addListeners() {
        prezenta_btn.setOnAction(actionEvent -> onPrezenta());
        prezentaDepartament_btn.setOnAction(actionEvent -> onDepartament());
        log_btn.setOnAction(actionEvent -> onAccessLog());

        //logout
        logout_btn.setOnAction(actionEvent -> onLogout());
    }

    private  void onDepartament() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOptions.DEPARTAMENT);
    }

    private void onPrezenta() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOptions.PREZENTA);
    }

    private void onAccessLog() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOptions.ACCESSLOG);
    }

    private void onLogout() {
        //get stage
        Stage stage=(Stage) prezentaDepartament_btn.getScene().getWindow();
        //Close the client window
        Model.getInstance().getViewFactory().closeStage(stage);
        //Show login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        //Set User Login success flag to false
        //Model.getInstance().setClientLoginSuccessFlag(false);
    }

}
