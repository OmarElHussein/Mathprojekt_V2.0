package com.wipd.schulprojekt_mathe.prozesseClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wipd.schulprojekt_mathe.R;

public class ProzesseRechnerActivity extends AppCompatActivity {

    private EditText editTextKapital, editTextZinssatz, editTextLaufzeit;

    private TextView textViewJahr, textViewKapital, textViewZinsbetrag, textViewJahresendbetrag;
    private TextView spalte1Titel, spalte2Titel, spalte3Titel, spalte4Titel;
    private TextView textVieweingabeEins, textVieweingabeZwei, textVieweingabeDrei;

    private double kapital, zinssatz;
    private int laufzeit;

    private TableLayout tableLayoutProzesse;

    private ArrayAdapter<String> adapter;

    private Spinner spinnerProzesse;
    private String[] spinnerValues = {"Sparvertrag", "Zellwachstum"};

    private boolean isCollapsed = false;


    /**
     * Die Darstellung oder Kreatierung und die Verbindung zwischen
     * die Klasse und die dazu gehörige Layout Datei
     * @param savedInstanceState kann werte speichern und sie zu andere Activities bringen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prozesse_rechner);

        viewsDeclaration();
        spinnerSettingValues();

        activateSpinner();

    }

    /**
     * Die Deklaration von alle ein und ausgabe Felder in ProzessRechnerActivity
     */
    private void viewsDeclaration() {
        editTextKapital = findViewById(R.id.editTextKapital);
        editTextZinssatz = findViewById(R.id.editTextZinssatz);
        editTextLaufzeit = findViewById(R.id.editTextLaufzeit);

        textViewJahr = findViewById(R.id.textViewJahr);
        textViewKapital = findViewById(R.id.textViewKapital);
        textViewZinsbetrag = findViewById(R.id.textViewZinsbetrag);
        textViewJahresendbetrag = findViewById(R.id.textViewJahresendbetrag);
        tableLayoutProzesse = findViewById(R.id.tableProzesse);

        spalte1Titel = findViewById(R.id.spalte1Titel);
        spalte2Titel = findViewById(R.id.spalte2Titel);
        spalte3Titel = findViewById(R.id.spalte3Titel);
        spalte4Titel = findViewById(R.id.spalte4Titel);

        spinnerProzesse = findViewById(R.id.spinnerProzesse);

        textVieweingabeEins = findViewById(R.id.textViewEingabeEins);
        textVieweingabeZwei = findViewById(R.id.textViewEingabe2);
        textVieweingabeDrei = findViewById(R.id.textViewEingabeDrei);

    }

    /**
     * Setzt alle Felder in der Tabelle auf null
     */
    private void resetTextViews() {
        textViewJahr.setText("");
        textViewKapital.setText("");
        textViewZinsbetrag.setText("");
        textViewJahresendbetrag.setText("");
    }

    /**
     * Das fokusieren weg zu bringen
     */
    private void clearFocus() {
        editTextKapital.clearFocus();
        editTextLaufzeit.clearFocus();
        editTextZinssatz.clearFocus();
    }

    /**
     * Hier ist der Teil wo alles zum funktionieren bringt, wenn man auf dem Button clickt wird
     * die Methode gerufen und alles was da drinne steht bearbeitet bzw. berechnet
     * @param view ist wichtig um das Zeigen zu ermöglichen, ohne View cann unser App einfach crashen.
     */
    public void prozessBerechnung(View view) {

        if (editTextKapital.length() > 0 && editTextZinssatz.length() > 0 && editTextLaufzeit.length() > 0) {

            kapital = Double.parseDouble(editTextKapital.getText().toString());
            zinssatz = Double.parseDouble(editTextZinssatz.getText().toString());
            laufzeit = Integer.parseInt(editTextLaufzeit.getText().toString());


            if (spinnerProzesse.getSelectedItem().equals(spinnerValues[1])) {
                berechneZellwachstum();
            }

            else if (spinnerProzesse.getSelectedItem().equals(spinnerValues[0])) {
                berechneSparvertrag();
            }

        } else {
            Toast.makeText(this, "Bitte Felder ausfüllen", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hier wird festgelegt welche auswahl automatisch getroffen werden sollte
     * von die Liste um fehler zu vermeiden
     */
    private void activateSpinner() {
        Intent intent = getIntent();
        String methodeAuswahl = intent.getStringExtra(ProzessePageActivity.buttonInhalt);

        if (methodeAuswahl.equalsIgnoreCase("Sparvertrag")) {
            spinnerProzesse.setSelection(0);
        } else if (methodeAuswahl.equalsIgnoreCase("Zellwachstum")) {
            spinnerProzesse.setSelection(1);
        }
    }

    /**
     * Hier wird die berechnung von Sparvertrag direkt in die Tabelle eingetragen
     * @param i ist der Counter für das Jahr
     * @param zb ist der Zinssatzbetrag
     * @param jb ist der jahresendbetrag
     */
    private void setTextViewsInTable(int i, double zb, double jb) {
        textViewJahr.setText(textViewJahr.getText().toString() + i + "\n");
        textViewKapital.setText(textViewKapital.getText().toString() + kapital + "\n");
        textViewZinsbetrag.setText(textViewZinsbetrag.getText().toString() + zb + "\n");
        textViewJahresendbetrag.setText(textViewJahresendbetrag.getText().toString() + jb + "\n");
    }

    /**
     * Hier wird der Spinner deklariert und ein Adapter für ihn erstellt
     * Ein Listener wird auch erstellt um es zu ermöglichen etwas aus der Liste
     * auszuwählen
     */
    private void spinnerSettingValues() {

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spinnerValues);
        spinnerProzesse.setAdapter(adapter);

        spinnerProzesse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switchingItemsConditions();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Hier wird überprüft ob es ein Sparvertag ist oder ein Zellwachstum um die Seite
     * anzupassen zu können
     */
    private void switchingItemsConditions() {
        if (spinnerProzesse.getSelectedItem().toString().equals(spinnerValues[0])) {
            setSparvertragTable();
        } else if (spinnerProzesse.getSelectedItem().toString().equals(spinnerValues[1])) {
            setZellwachstumTable();
        }
    }

    /**
     * Hier werden die Texte für die Zellwachstum Seite angepasst (Tabelle und Text)
     */
    private void setZellwachstumTable() {
        isCollapsed = true;
        spalte2Titel.setText("Zellmenge");
        spalte1Titel.setText("Tag");
        textVieweingabeEins.setText("Bitte die Aktuelle Zellmenge eingeben: ");
        textVieweingabeZwei.setText("Bitte den Wachtumsfaktor eingeben: ");
        textVieweingabeDrei.setText("Bitte die Laufzeit der Hochrechnung eingeben: ");
        spalte3Titel.setText("");
        spalte4Titel.setText("");
        tableLayoutProzesse.setColumnCollapsed(2, isCollapsed);
        tableLayoutProzesse.setColumnCollapsed(3, isCollapsed);
    }

    /**
     * Hier werden die Texte für die Sparvertrag Seite angepasst (Tabelle und Text)
     */
    private void setSparvertragTable() {
        isCollapsed = false;
        spalte1Titel.setText("Jahr");
        spalte2Titel.setText("Kapital");
        spalte3Titel.setText("Zinsbetrag");
        spalte4Titel.setText("Jahresendbetrag");
        textVieweingabeEins.setText("Bitte Kapital eingeben: ");
        textVieweingabeZwei.setText("Bitte den Zinssatz eingeben: ");
        textVieweingabeDrei.setText("Bitte die Laufzeit in Jahren eingeben: ");
        tableLayoutProzesse.setColumnCollapsed(2, isCollapsed);
        tableLayoutProzesse.setColumnCollapsed(3, isCollapsed);
    }

    /**
     * Die berechnung von Sparvertäge Prozessen
     */
    private void berechneSparvertrag() {

        clearFocus();
        resetTextViews();
        setSparvertragTable();
        tableLayoutProzesse.setVisibility(View.VISIBLE);

        for (int i = 1; i <= laufzeit; i++) {
            double zb = (zinssatz * kapital) / 100;
            zb = Math.round(zb * 100.) / 100.;
            double jb = zb + kapital;
            jb = Math.round(jb * 100.) / 100.;

            kapital = jb;
            kapital = Math.round(kapital * 100.) / 100.;

            setTextViewsInTable(i, zb, jb);
        }

    }

    /**
     * Die berechnung von Zellwachstum Prozessen
     */
    private void berechneZellwachstum() {

        clearFocus();
        resetTextViews();
        setZellwachstumTable();
        tableLayoutProzesse.setVisibility(View.VISIBLE);


        for (int i = 0; i <= laufzeit; i++) {
            double fx = kapital * Math.pow(zinssatz, i); //kapital == zellmenge, zinssatz == wachtumsfaktor

            textViewJahr.setText(textViewJahr.getText().toString() + i + "\n");
            textViewKapital.setText(textViewKapital.getText().toString() + fx + "\n");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}