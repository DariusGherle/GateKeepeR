package com.example.gatekeepr.Controllers;

import com.example.gatekeepr.Models.Model;
import com.example.gatekeepr.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_adress_fld;
    public PasswordField password_fld;
    public Button login_btn;
    public Label error_lbl;
    public static String guestPortarName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the onLogin method to be called when the login button is pressed
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.PORTAR, AccountType.ADMIN, AccountType.USERAUTORIZAT));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(actionEvent -> onLogin());
    }


    private void onLogin() {
        // Close the current stage (login window)
        Stage stage = (Stage) login_btn.getScene().getWindow();

        if(Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.PORTAR) {
            //evaluate gatekeeper login credentials
            Model.getInstance().evaluatePortarCred(payee_adress_fld.getText(), password_fld.getText());
            if(Model.getInstance().getPortarLoginSuccessFlag()) {
                guestPortarName=payee_adress_fld.getText();
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
                guestPortarName=payee_adress_fld.getText();
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
                guestPortarName=payee_adress_fld.getText();

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

    //Modificare window in functie de utilizator
    private void setAcc_selector() {
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
        //Set label accordingly
        if(acc_selector.getValue()==AccountType.ADMIN) {
            payee_address_lbl.setText("Username:");
        } else if(acc_selector.getValue()==AccountType.PORTAR){
            payee_address_lbl.setText("Portar:");
        } else {
            payee_address_lbl.setText("Sef Departament:");
        }
    }
}
