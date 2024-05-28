package com.example.gatekeepr.Controllers.Admin;

import com.example.gatekeepr.Models.Angajat;
import com.example.gatekeepr.Views.Departamente;
import com.example.gatekeepr.Views.IntervaleOrare;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Boolean.TRUE;

public class AngajatNouController implements Initializable {
    public TextField aNume_fld;
    public TextField aID_fld;
    public TextField aParola_fld;
    public CheckBox aAccesAuto_box;
    public TextField aNrMasina_fld;
    public CheckBox aProgram_box;
    public ChoiceBox<IntervaleOrare> aProgramPoarta_choiceBox;
    public ChoiceBox<Departamente> aDepartament_choiceBox;
    public TextField aNormaOre_fld;
    public Button aCreeazaAngajat_btn;
    public Label error_lbl;
    public TextField pNume_fld;
    public TextField pID_fld;
    public TextField pParola_fld;
    public CheckBox pAccesAuto_box;
    public TextField pNrMasina_fld;
    public CheckBox pTura_box;
    public ChoiceBox<IntervaleOrare> pTura_choiceBox;
    public CheckBox pNorma_box;
    public TextField pNormaOre_fld;
    public Button create_client_btn1;
    public Label error_lbl1;
    public TextField aNume_fld1;
    public Text sIdAngajat_lbl;
    public Text sDepartament_lbl;
    public CheckBox sSefDepartament_box;
    public CheckBox sAdaugaAccesAuto_box;
    public TextField sNrMasina_fld;
    public CheckBox sSchimbaProgramPoarta_box;
    public ChoiceBox<IntervaleOrare> sProgramLucu_choiceBox;
    public Button pCreeazaPortar_btn;
    public Button modificaAngajat_btn;
    public Button cautaAngajat_btn;
    public CheckBox sSuspendaAcces_box;

    private List<Angajat> listaAngajati;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the list of employees
        listaAngajati = new ArrayList<>();

        // Set items for choice boxes
        aProgramPoarta_choiceBox.setItems(FXCollections.observableArrayList(IntervaleOrare.INC00SF08, IntervaleOrare.INC08SF16, IntervaleOrare.INC16SF24));
        aDepartament_choiceBox.setItems(FXCollections.observableArrayList(Departamente.URGENTE, Departamente.GENERAL));
        pTura_choiceBox.setItems(FXCollections.observableArrayList(IntervaleOrare.INC00SF12, IntervaleOrare.INC12SF24));
        sProgramLucu_choiceBox.setItems(FXCollections.observableArrayList(IntervaleOrare.INC00SF08, IntervaleOrare.INC08SF16, IntervaleOrare.INC16SF24));

        // Add action for create employee button
        aCreeazaAngajat_btn.setOnAction(event -> createAngajat());

        // Add action for search employee button
        cautaAngajat_btn.setOnAction(event -> cautaAngajat());
    }

    public void emptyFields() {
        // Empty all text fields
        aNume_fld.setText("");
        aID_fld.setText("");
        aParola_fld.setText("");
        aNrMasina_fld.setText("");
        aNormaOre_fld.setText("");
        pNume_fld.setText("");
        pID_fld.setText("");
        pParola_fld.setText("");
        pNrMasina_fld.setText("");
        pNormaOre_fld.setText("");
        aNume_fld1.setText("");
        sNrMasina_fld.setText("");

        // Reset all checkboxes
        aAccesAuto_box.setSelected(false);
        aProgram_box.setSelected(false);
        pAccesAuto_box.setSelected(false);
        pTura_box.setSelected(false);
        pNorma_box.setSelected(false);
        sSefDepartament_box.setSelected(false);
        sAdaugaAccesAuto_box.setSelected(false);
        sSchimbaProgramPoarta_box.setSelected(false);

        // Reset all choice boxes
        aProgramPoarta_choiceBox.getSelectionModel().clearSelection();
        aDepartament_choiceBox.getSelectionModel().clearSelection();
        pTura_choiceBox.getSelectionModel().clearSelection();
        sProgramLucu_choiceBox.getSelectionModel().clearSelection();

        // Clear error labels
        error_lbl.setText("");
        error_lbl1.setText("");

        // Optionally, reset texts
        sIdAngajat_lbl.setText("");
        sDepartament_lbl.setText("");
    }

    private void createAngajat() {
        try {
            // Verifică dacă toate câmpurile necesare sunt completate
            if (aID_fld.getText().isEmpty() || aNume_fld.getText().isEmpty() || aParola_fld.getText().isEmpty() || aNormaOre_fld.getText().isEmpty() || aProgramPoarta_choiceBox.getValue() == null) {
                error_lbl.setText("Toate câmpurile marcate sunt obligatorii.");
                return;
            }

            // Colectarea datelor din câmpurile de input
            String marca = aID_fld.getText();
            String nume = aNume_fld.getText();
            String prenume = aNume_fld1.getText();
            String cnp = aParola_fld.getText();
            String poarta = aProgramPoarta_choiceBox.getValue().toString();
            String poza = ""; // Poza poate fi selectată dintr-un fișier sau altă sursă, dar aici o lăsăm goală
            String codSecuritateBluetooth = ""; // Asumăm că este gol pentru acum
            String codSecuritateDispozitiv = ""; // Asumăm că este gol pentru acum
            boolean accesPoarta = TRUE;
            boolean accesBariera = aAccesAuto_box.isSelected(); // Folosim checkbox-ul pAccesAuto_box pentru acces la barieră
            String numarAuto = aNrMasina_fld.getText();
            boolean esteSef = sSefDepartament_box.isSelected();
            Departamente departament = aDepartament_choiceBox.getValue();
            int intervaleOrareAcces = Integer.parseInt(aNormaOre_fld.getText());

            // Crearea unui nou angajat
            Angajat angajat = new Angajat(
                    new SimpleStringProperty(marca),
                    new SimpleStringProperty(nume),
                    new SimpleStringProperty(prenume),
                    new SimpleStringProperty(cnp),
                    new SimpleStringProperty(poarta),
                    poza,
                    codSecuritateBluetooth,
                    codSecuritateDispozitiv,
                    accesPoarta,
                    accesBariera,
                    new SimpleStringProperty(numarAuto),
                    esteSef,
                    departament.ordinal(),
                    intervaleOrareAcces
            );

            // Adăugarea angajatului în lista de angajați
            listaAngajati.add(angajat);
            System.out.println("Angajat adaugat cu succes!");
            // Resetarea câmpurilor la starea inițială
            emptyFields();
            error_lbl.setText("Angajat creat cu succes!");
        } catch (Exception e) {
            error_lbl.setText("Eroare la crearea angajatului. Verificați datele introduse.");
            e.printStackTrace();
        }
    }

    private void cautaAngajat() {
        try {
            // Verifică dacă numele pentru căutare este introdus
            if (aNume_fld1.getText().isEmpty()) {
                error_lbl1.setText("Introduceți numele angajatului pentru căutare.");
                return;
            }

            // Căutarea angajatului în listă după nume
            String numeCautat = aNume_fld1.getText();
            Angajat angajatGasit = null;

            for (Angajat angajat : listaAngajati) {
                if (angajat.getNume().equals(numeCautat)) {
                    angajatGasit = angajat;
                    break;
                }
            }

            if (angajatGasit == null) {
                error_lbl1.setText("Angajatul nu a fost găsit.");
                return;
            }

            // Afișarea detaliilor angajatului găsit
            sIdAngajat_lbl.setText(angajatGasit.getMarca());
            sDepartament_lbl.setText(angajatGasit.getDepartament() == 0 ? "URGENTE" : "GENERAL");
            sSefDepartament_box.setSelected(angajatGasit.isEste_sef());
            sAdaugaAccesAuto_box.setSelected(angajatGasit.isAcces_poarta());
            sNrMasina_fld.setText(angajatGasit.getNumar_auto());

            error_lbl1.setText("Angajat găsit.");
        } catch (Exception e) {
            error_lbl1.setText("Eroare la căutarea angajatului.");
            e.printStackTrace();
        }
    }
}
