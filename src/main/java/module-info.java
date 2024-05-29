module com.example.gatekeepr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.gatekeepr.Controllers.Admin to javafx.fxml;
    exports com.example.gatekeepr;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires spring.jdbc;
    requires spring.context;

    opens com.example.gatekeepr to javafx.fxml;
    exports com.example.gatekeepr.Controllers;
    exports com.example.gatekeepr.Controllers.Admin;
    exports com.example.gatekeepr.Controllers.Portar;
    exports com.example.gatekeepr.Controllers.UserAutorizat;
    exports com.example.gatekeepr.Models;
    exports com.example.gatekeepr.Views;
    exports com.example.gatekeepr.Controllers.Cells;
}