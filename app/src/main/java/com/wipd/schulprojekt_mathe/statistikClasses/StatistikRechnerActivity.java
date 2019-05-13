package com.wipd.schulprojekt_mathe.statistikClasses;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wipd.schulprojekt_mathe.R;

import java.util.Arrays;

@SuppressLint("ALL")
public class StatistikRechnerActivity extends AppCompatActivity {

    private EditText editTextInputSize, editTextInputs;

    private double[] numbers;
    private int arraySize, i;
    private String statistik_button_dateien, ausgabe;
    private String[] spinnerValues = {"Max", "Min", "Range", "Mittelwert", "GeoMittel", "Median", "Modus", "Varianz", "St.abweichung", "Alles"};
    private boolean clearBtnActive = false;

    private ImageButton btnCheckInputs, btnCheckInputSize;
    private TextView textViewInfoCounter, textViewStatistikErgebnis;
    private Toolbar toolbar;
    private Spinner spinnerStatistik;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik_rechner);

        toolbarEigenschaften();

        komponente_Initzialisieren();

    }

    private void komponente_Initzialisieren() {
        editTextInputSize = findViewById(R.id.editTextInputSize);
        editTextInputs = findViewById(R.id.editTextInputs);

        btnCheckInputs = findViewById(R.id.imageButtonCheckInputs);
        btnCheckInputSize = findViewById(R.id.imageButtonCheckInputSize);

        textViewInfoCounter = findViewById(R.id.textViewInfoCounter);
        textViewStatistikErgebnis = findViewById(R.id.textViewStatistikErgebnis);
    }

    private void toolbarEigenschaften() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(StatistikPageActivity.extra_statistik_dateien);
    }

    /**
     * Diese Methode überprüft wie groß die eingegebene Zahl ist und speichert dies
     * unter eine Array, danach werden andere Felder Sichtbar gemacht
     */
    public void checkInputSize(View view) {

        i = 0;

        if (editTextInputSize.getText().length() > 0 && Integer.parseInt(editTextInputSize.getText().toString()) != 0) {

            clearBtnActive = true;

            arraySize = Integer.parseInt(editTextInputSize.getText().toString());
            numbers = new double[arraySize];
            editTextInputSize.setEnabled(false);
            btnCheckInputs.setEnabled(false);

            textViewInfoCounter.setVisibility(View.VISIBLE);
            editTextInputs.setVisibility(View.VISIBLE);
            btnCheckInputSize.setVisibility(View.VISIBLE);

            textViewInfoCounter.setText("Geben Sie die 1. Zahl ein: (1/" + arraySize + ")");

        } else {
            Toast.makeText(this, "Geben Sie die Größe ein!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hier werden die inputs von dem Benutzer in eine Array gespeichert
     * und eine Ausgabe (Zähler) erstellt
     */
    public void checkInputs(View view) {

        if ((editTextInputs.getText().length() > 0) && !(i > arraySize - 1)) {
            try {
                numbers[i] = Double.parseDouble(editTextInputs.getText().toString());
                i++;
                if (i < arraySize) {
                    ausgabe = "Geben Sie die " + (i + 1) + ". Zahl ein: ";
                }
                textViewInfoCounter.setText(ausgabe + "(" + i + "/" + arraySize + ")");
                editTextInputs.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Bitte Felder ausfüllen", Toast.LENGTH_SHORT).show();
            }

        }

        if (i == arraySize) {
            buttonSuchen_methodeAusfuehren();

            editTextInputs.setEnabled(false);
            btnCheckInputSize.setEnabled(false);
        }
    }

    public void clearAll(View view) {
        if (clearBtnActive) {
            editTextInputSize.setEnabled(true);
            btnCheckInputs.setEnabled(true);

            editTextInputs.setVisibility(View.INVISIBLE);
            btnCheckInputSize.setVisibility(View.INVISIBLE);
            textViewInfoCounter.setVisibility(View.INVISIBLE);

            editTextInputSize.setText("");

            checkInputSize(view);
            checkInputs(view);

            textViewStatistikErgebnis.setVisibility(View.INVISIBLE);
            btnCheckInputSize.setEnabled(true);
            editTextInputs.setEnabled(true);
        }
    }

    /**
     * Diese methode sucht nach die Größte Zahl und gibt dies dann direkt aus
     */
    private double maximumNummerSuchen() {
        Arrays.sort(numbers);
        double zahl = numbers[arraySize - 1];
        textViewStatistikErgebnis.setText("Die größte Zahl ist: " + zahl);
        return zahl;
    }

    /**
     * Diese methode sucht nach die kleinste Zahl und gibt dies dann direkt aus
     */
    private double minimumNummerSuchen() {
        Arrays.sort(numbers);
        double zahl = numbers[0];
        textViewStatistikErgebnis.setText("Die kleinste Zahl ist: " + zahl);
        return zahl;
    }

    private void spannweiteBerechnen() {
        double range = maximumNummerSuchen() - minimumNummerSuchen();
        textViewStatistikErgebnis.setText("Die Spannweite ist: " + range);
    }

    private double arithmetischesMittelBerechnen() {
        double aMittel = 0;
        for (double number : numbers) {
            aMittel += number;
        }
        aMittel /= numbers.length;
        textViewStatistikErgebnis.setText("Das Arithmetische Mittel ist: " + aMittel);
        return aMittel;
    }

    private void geometrischesMittelBerechnen() {
        double gMittel = 1;

        for (double number : numbers) {
            gMittel *= number;
        }

        gMittel = Math.pow(gMittel, (double) 1 / numbers.length);
        textViewStatistikErgebnis.setText("Das Geometrische Mittel ist: " + gMittel);
    }

    private void medianBerechnen() {
        Arrays.sort(numbers);
        double median, median2;
        if (numbers.length % 2 == 0) {
            median = numbers[(numbers.length / 2) - 1];
            median2 = numbers[(numbers.length / 2)];

            textViewStatistikErgebnis.setText("Der Median ist: \nMedian.1: " + median + "\nMedian.2: " + median2);
        } else {
            median = numbers[numbers.length / 2];
            textViewStatistikErgebnis.setText("Der Median ist: " + median);
        }
    }

    private void modusBerechnen() {
        Arrays.sort(numbers);
        int count;
        double mod = 0;
        int modcount = 0;
        for (int j = 0; j < numbers.length; j++) {
            count = 0;
            for (int k = 0; k < numbers.length; k++) {
                if (numbers[j] == numbers[k]) {
                    count++;
                }
            }
            if (modcount < count) {
                mod = numbers[j];
                modcount = count;
            }
        }
        textViewStatistikErgebnis.setText("Der Modus ist " + mod);
    }

    private double varianzBerechnen() {
        double varianz = 0;
        for (double number : numbers) {
            varianz += Math.pow(number - arithmetischesMittelBerechnen(), 2);
        }
        varianz /= numbers.length;
        textViewStatistikErgebnis.setText("Der Varianz ist: " + varianz);
        return varianz;
    }

    private void standardabweichungBerechnen() {
        double standardabweichung;
        standardabweichung = Math.sqrt(varianzBerechnen());
        textViewStatistikErgebnis.setText("Die Standardabweichung ist: " + standardabweichung);
    }

    private void alleszusammenBerechnen() {
    }

    /**
     * Diese methode überprüft welcher Button gecklickt wurde um die Seite
     * und die rechnungen anzupassen
     */
    private void buttonSuchen_methodeAusfuehren() {
        statistik_button_dateien = StatistikPageActivity.extra_statistik_dateien;
        textViewStatistikErgebnis.setVisibility(View.VISIBLE);

        switch (statistik_button_dateien) {
            case "Maximum":
                maximumNummerSuchen();
                break;
            case "Minimum":
                minimumNummerSuchen();
                break;
            case "Spannweite":
                spannweiteBerechnen();
                break;
            case "Arithmetisches Mittel":
                arithmetischesMittelBerechnen();
                break;
            case "Geometrisches Mittel":
                geometrischesMittelBerechnen();
                break;
            case "Median":
                medianBerechnen();
                break;
            case "Varianz":
                varianzBerechnen();
                break;
            case "Standardabweichung":
                standardabweichungBerechnen();
                break;
            case "Modalwert":
                modusBerechnen();
                break;
            case "Alles zusammen":
                alleszusammenBerechnen();
                break;
        }
    }


    private void setSpinner_layout_Values() {
        //TODO
    }

    private void switch_buttons_tables() {
        //TODO
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}