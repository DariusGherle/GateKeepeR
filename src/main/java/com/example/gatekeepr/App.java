package com.example.gatekeepr;

import com.example.gatekeepr.Controllers.SchemaInitializer;
import com.example.gatekeepr.DatabaseHelper;
import com.example.gatekeepr.LocalDatabaseHelper;
import com.example.gatekeepr.Models.Model;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        SchemaInitializer.initializeDatabase();
        Model.getInstance().getViewFactory().showLoginWindow();

        // Add a shutdown hook to copy data to the local database on exit
        Platform.runLater(() -> {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    copyDataToLocalDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));
        });
    }

    private void copyDataToLocalDatabase() throws SQLException {
        copyTable("access_logs");
        copyTable("admins");
        copyTable("employees");
        copyTable("gatekeepers");
        copyTable("guests");
    }

    private void copyTable(String tableName) throws SQLException {
        String fetchQuery = "SELECT * FROM " + tableName;
        ResultSet resultSet = DatabaseHelper.executeQuery(fetchQuery);

        while (resultSet.next()) {
            switch (tableName) {
                case "access_logs":
                    String insertAccessLogs = "INSERT INTO access_logs (id, tip, data, marca_angajat, portar, in_afara_orarurului) VALUES (?, ?, ?, ?, ?, ?)";
                    LocalDatabaseHelper.executeUpdate(insertAccessLogs,
                            resultSet.getInt("id"),
                            resultSet.getInt("tip"),
                            resultSet.getTimestamp("data"),
                            resultSet.getInt("marca_angajat"),
                            resultSet.getString("portar"),
                            resultSet.getInt("in_afara_orarurului"));
                    break;
                case "admins":
                    String insertAdmins = "INSERT INTO admins (adresa_utilizator, parola) VALUES (?, ?)";
                    LocalDatabaseHelper.executeUpdate(insertAdmins,
                            resultSet.getString("adresa_utilizator"),
                            resultSet.getString("parola"));
                    break;
                case "employees":
                    String insertEmployees = "INSERT INTO employees (marca, divizie, nume, prenume, cnp, poza, cod_securitate_bluetooth, cod_securitate_dispozitiv, intervale_orare_access, acces_poarta, acces_bariera, numar_auto, este_sef, prezente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    LocalDatabaseHelper.executeUpdate(insertEmployees,
                            resultSet.getInt("marca"),
                            resultSet.getInt("divizie"),
                            resultSet.getString("nume"),
                            resultSet.getString("prenume"),
                            resultSet.getString("cnp"),
                            resultSet.getString("poza"),
                            resultSet.getString("cod_securitate_bluetooth"),
                            resultSet.getString("cod_securitate_dispozitiv"),
                            resultSet.getInt("intervale_orare_access"),
                            resultSet.getInt("acces_poarta"),
                            resultSet.getInt("acces_bariera"),
                            resultSet.getString("numar_auto"),
                            resultSet.getInt("este_sef"),
                            resultSet.getBigDecimal("prezente"));
                    break;
                case "gatekeepers":
                    String insertGatekeepers = "INSERT INTO gatekeepers (adresa_utilizator, parola) VALUES (?, ?)";
                    LocalDatabaseHelper.executeUpdate(insertGatekeepers,
                            resultSet.getString("adresa_utilizator"),
                            resultSet.getString("parola"));
                    break;
                case "guests":
                    String insertGuests = "INSERT INTO guests (id_vizite, nume, prenume, cnp, scop_vizita, portar, data_ora_intrare, data_ora_iesire) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    LocalDatabaseHelper.executeUpdate(insertGuests,
                            resultSet.getInt("id_vizite"),
                            resultSet.getString("nume"),
                            resultSet.getString("prenume"),
                            resultSet.getString("cnp"),
                            resultSet.getString("scop_vizita"),
                            resultSet.getString("portar"),
                            resultSet.getTimestamp("data_ora_intrare"),
                            resultSet.getTimestamp("data_ora_iesire"));
                    break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
