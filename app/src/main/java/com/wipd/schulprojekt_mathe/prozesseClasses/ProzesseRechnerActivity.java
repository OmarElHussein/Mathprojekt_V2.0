package com.wipd.schulprojekt_mathe.prozesseClasses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import java.util.Objects;

@SuppressLint("ALL")
public class ProzesseRechnerActivity extends AppCompatActivity {

    private EditText editTextKapital, editTextZinssatz, editTextLaufzeit;

    private TextView textViewJahr, textViewKapital, textViewZinsbetrag, textViewJahresendbetrag;
    private TextView spalte1Titel, spalte2Titel, spalte3Titel, spalte4Titel;
    private TextView textVieweingabeEins, textVieweingabeZwei, textVieweingabeDrei;

    private double kapital, zinssatz;
    private int laufzeit;

    private TableLayout tableLayoutProzesse;

    private Spinner spinnerProzesse;
    private String[] spinnerValues = {"Sparvertrag", "Zellwachstum"};

    private boolean isCollapsed = false;

    /**
     * Die Darstellung oder Kreatierung und die Verbindung zwischen
     * die Klasse und die dazu gehörige Layout Datei
     *
     * @param savedInstanceState um speicher zu können
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prozesse_rechner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        viewsInitzialisieren();
        spinnerSettingValues();
        activateSpinner();

    }

    /**
     * Die Deklaration von alle ein und ausgabe Felder in ProzessRechnerActivity
     */
    private void viewsInitzialisieren() {
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
     * löscht den Text von Eingaben Felder
     */
    private void deleteAllData() {
        resetTextViews();
        editTextKapital.setText("");
        editTextLaufzeit.setText("");
        editTextZinssatz.setText("");
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
     *
     * @param view ist wichtig um das Zeigen zu ermöglichen, ohne View cann unser App einfach crashen.
     */
    public void prozessBerechnung(View view) {

        if (editTextKapital.length() > 0 && editTextZinssatz.length() > 0 && editTextLaufzeit.length() > 0) {

            try {
                kapital = Double.parseDouble(editTextKapital.getText().toString());
                zinssatz = Double.parseDouble(editTextZinssatz.getText().toString());
                laufzeit = Integer.parseInt(editTextLaufzeit.getText().toString());

                if (spinnerProzesse.getSelectedItem().equals(spinnerValues[1])) {
                    berechneZellwachstum();
                } else if (spinnerProzesse.getSelectedItem().equals(spinnerValues[0])) {
                    berechneSparvertrag();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Bitte Felder ausfüllen", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "Bitte Felder ausfüllen", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Wenn auf den Button geclickt wird, werden die Texte in den Eingaben Felder gelöscht
     *
     * @param view ist wichtig um das Zeigen der Methode zu ermöglicher in .XML
     */
    public void deleteDataFromPage(View view) {
        deleteAllData();
    }

    /**
     * Hier wird festgelegt welche auswahl automatisch getroffen werden sollte
     * von der Liste um fehler zu vermeiden
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
     *
     * @param i  ist der Counter für das Jahr
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
     * Ein Listener wird auch erstellt um es zu ermöglichen ein Action zu machen
     * wenn etwas ausgewählt ist
     */
    private void spinnerSettingValues() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spinnerValues);
        spinnerProzesse.setAdapter(adapter);

        spinnerProzesse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switchungButtons_Tables();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Hier wird überprüft ob es ein Sparvertag ist oder ein Zellwachstum
     * um danach die Seite anzupassen
     */
    private void switchungButtons_Tables() {
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

    /**
     * die Eigenschafter der zurück Pfeil im Toolbar
     *
     * @param item der Pfeil ist ein item und benötigt den Parameter
     * @return true wenn gecklickt wird
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}