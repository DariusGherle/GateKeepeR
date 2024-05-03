package com.example.gatekeepr.Views;

import com.example.gatekeepr.Controllers.Admin.AdminController;
import com.example.gatekeepr.Controllers.Portar.ClientController;
import com.example.gatekeepr.Controllers.UserAutorizat.UserController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private AccountType loginAccountType;
    // Portar View
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane starePoartaView;
    private AnchorPane accessLogView;
    private AnchorPane adaugaVizView;
    private AnchorPane profilView;

    private AnchorPane prezentaView;

    //Admin Views
    private final  ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane prezentaAdminView;
    private AnchorPane angajatNouView;
    private AnchorPane adminStarePoartaView;
    private AnchorPane listaAngajatiView;

    //User-Sef Departament Views
    private final ObjectProperty<UserMenuOptions> userSelectedMenuItem;
    private AnchorPane departamentView;
    private AnchorPane prezDeptView;
    private AnchorPane accessLogDeptView;


    public ViewFactory() {
        this.loginAccountType=AccountType.CLIENT;
        this.clientSelectedMenuItem=new SimpleObjectProperty<>(ClientMenuOptions.STAREPOARTA);
        this.adminSelectedMenuItem=new SimpleObjectProperty<>(AdminMenuOptions.STAREPOARTA);
        this.userSelectedMenuItem=new SimpleObjectProperty<>(UserMenuOptions.DEPARTAMENT);
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    //
    //Client-Portar Views
    //

    public AnchorPane getStarePoartaView() {
        if(starePoartaView == null) {
            try {
                starePoartaView=new FXMLLoader(getClass().getResource("/Fxml/Client/StarePoarta.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return starePoartaView;
    }

    public AnchorPane getAccessLogView() {
        if(accessLogView==null) {
            try {
                accessLogView = new FXMLLoader(getClass().getResource("/Fxml/Client/AccessLog.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accessLogView;
    }

    public AnchorPane getProfilView() {
        if(profilView==null) {
            try {
                profilView=new FXMLLoader(getClass().getResource("/Fxml/Client/Profil.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profilView;
    }

    public AnchorPane getAdaugaVizView() {
        if(adaugaVizView==null) {
            try {
                adaugaVizView=new FXMLLoader(getClass().getResource("/Fxml/Client/AdaugaViz.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adaugaVizView;
    }

    public AnchorPane getPrezentaView() {
        if(prezentaView==null) {
            try {
                prezentaView=new FXMLLoader(getClass().getResource("/Fxml/Client/Prezenta.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prezentaView;
    }

    public void showLoginWindow() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    public void showPortarWindow() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController=new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    //Admin
    //
    //
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public void showAdminWindow() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller=new AdminController();
        loader.setController(controller);
        createStage(loader);
    }

    public AnchorPane getAdminStarePoartaView() {
        if(adminStarePoartaView==null) {
            try {
                adminStarePoartaView=new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminStarePoarta.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminStarePoartaView;
    }

    public AnchorPane getPrezentaAdminView() {
        if(prezentaAdminView == null) {
            try {
                prezentaAdminView=new FXMLLoader(getClass().getResource("/Fxml/Admin/PrezentaA.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prezentaAdminView;
    }

    public AnchorPane getAngajatNouView() {
        if(angajatNouView==null) {
            try {
                angajatNouView=new FXMLLoader(getClass().getResource("/FXML/Admin/AngajatNou.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return angajatNouView;
    }

    public AnchorPane getListaAngajatiView() {
        if(listaAngajatiView ==null) {
            try {
                listaAngajatiView =new FXMLLoader(getClass().getResource("/FXML/Admin/ListaAngajati.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaAngajatiView;
    }

    //
    //   USER
    //

    public void showUserWindow() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/UserAutorizat/User.fxml"));
        UserController controller=new UserController();
        loader.setController(controller);
        createStage(loader);
    }

    public ObjectProperty<UserMenuOptions> getUserSelectedMenuItem() {
        return userSelectedMenuItem;
    }

    public AnchorPane getDepartamentView() {
        if(departamentView==null) {
            try {
                departamentView=new FXMLLoader(getClass().getResource("/FXML/UserAutorizat/Departament.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return departamentView;
    }

    public AnchorPane getPrezDeptView() {
        if(prezDeptView==null) {
            try {
                prezDeptView=new FXMLLoader(getClass().getResource("/FXML/UserAutorizat/PrezDept.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prezDeptView;
    }

    public AnchorPane getAccessLogDeptView() {
        if(accessLogDeptView==null) {
            try{
                accessLogDeptView=new FXMLLoader(getClass().getResource("/FXML/UserAutorizat/AccessLogDept.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accessLogDeptView;
    }




    //
    //Stage
    //


    private void createStage(FXMLLoader loader) {
        Scene scene=null;
        try {
            scene=new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage=new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icons8-google-plus-32.png"))));
        //Resizable
        stage.setResizable(false);
        stage.setTitle("GateKeepR");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
