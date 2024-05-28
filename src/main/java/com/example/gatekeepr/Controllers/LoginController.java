package com.example.gatekeepr.Controllers;

import com.example.gatekeepr.Models.Model;
import com.example.gatekeepr.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_adress_fld;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the onLogin method to be called when the login button is pressed
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN, AccountType.USERAUTORIZAT));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(actionEvent -> onLogin());
    }


    private void onLogin() {
        // Close the current stage (login window)
        Stage stage = (Stage) login_btn.getScene().getWindow();

        if(Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.CLIENT) {
            //evaluate gatekeeper login credentials
            Model.getInstance().evaluatePortarCred(payee_adress_fld.getText(), password_fld.getText());
            if(Model.getInstance().getPortarLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showPortarWindow();
                //close the login stage
                Model.getInstance().getViewFactory().closeStage(stage);

            } else {
                payee_adress_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("Utilizator sau Parola gresite!");
            }
        } else if (Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.ADMIN) {
            Model.getInstance().evaluateAdminCred(payee_adress_fld.getText(), password_fld.getText());
            if(Model.getInstance().getAdminLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                //close login
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                payee_adress_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("Utilizator sau Parola gresite!");
            }
        } else {
            Model.getInstance().evaluateUtilCred(payee_adress_fld.getText(), password_fld.getText());
            if(Model.getInstance().getUserAutorizatLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showUserWindow();
                //close login
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                payee_adress_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("Utilizator sau Parola gresite!");
            }
        }

    }
}
