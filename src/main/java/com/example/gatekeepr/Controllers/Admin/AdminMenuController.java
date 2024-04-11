package com.example.gatekeepr.Controllers.Admin;

import com.example.gatekeepr.Models.Model;
import com.example.gatekeepr.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button creeazaAngajat_btn;
    public Button angajati_btn;
    public Button prezenta_btn;
    public Button logout_btn;
    public Button starePoarta_btn;
    public Button adaugaViz_btn;
    public Button accesLog_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        starePoarta_btn.setOnAction(actionEvent -> onStarePoarta());
        creeazaAngajat_btn.setOnAction(actionEvent -> onAngajatNou());
        prezenta_btn.setOnAction(actionEvent -> onPrezenta());
        accesLog_btn.setOnAction(actionEvent -> onAccessLog());
        adaugaViz_btn.setOnAction(actionEvent -> onAdaugaViz());
        prezenta_btn.setOnAction(actionEvent -> onPrezenta());
        angajati_btn.setOnAction(actionEvent -> onAngajati());
        logout_btn.setOnAction(actionEvent -> onLogout());
    }

    private void onStarePoarta() {
        System.out.println("Attempting to load StarePoartaView...");
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.STAREPOARTA);
    }

    private void onAccessLog() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.ACCESLOG);
    }

    private void onAngajatNou() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.ANGAJATNOU);
    }

    private void onAdaugaViz() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.ADAUGAVIZ);
    }

    private void onAngajati() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.ANGAJATI);
    }

    private void onPrezenta() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.PREZENTAA);
    }

    private void onLogout() {
        //get stage
        Stage stage=(Stage) starePoarta_btn.getScene().getWindow();
        //Close the client window
        Model.getInstance().getViewFactory().closeStage(stage);
        //Show login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        //Set Client Login success flag to false
        //Model.getInstance().setClientLoginSuccessFlag(false);
    }
}
