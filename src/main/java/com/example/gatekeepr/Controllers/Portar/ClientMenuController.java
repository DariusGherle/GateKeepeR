package com.example.gatekeepr.Controllers.Portar;

import com.example.gatekeepr.Models.Model;
import com.example.gatekeepr.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button starepoarta_btn;
    public Button prezenta_btn;
    public Button log_btn;
    public Button adugaviz_btn;
    public Button profile_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        starepoarta_btn.setOnAction(actionEvent -> onStarePoarta());
        log_btn.setOnAction(actionEvent -> onAccessLog());
        adugaviz_btn.setOnAction(actionEvent -> onAdaugaViz());
        profile_btn.setOnAction(actionEvent -> onProfil());
        prezenta_btn.setOnAction(actionEvent -> onPrezenta());
        logout_btn.setOnAction(actionEvent -> onLogout());
    }

    private void onStarePoarta() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.STAREPOARTA);
    }

    private void onAccessLog() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCESSLOG);
    }

    private void onAdaugaViz() {Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ADAUGAVIZ);}
    private void onProfil() {Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PROFIL);}

    private void onPrezenta() {Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PREZENTA);}

    private void onLogout() {
        //get stage
        Stage stage=(Stage) starepoarta_btn.getScene().getWindow();
        //Close the client window
        Model.getInstance().getViewFactory().closeStage(stage);
        //Show login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        //Set Client Login success flag to false
        Model.getInstance().setPortarLoginSuccessFlag(false);
    }
}
