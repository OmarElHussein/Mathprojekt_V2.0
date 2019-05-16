package com.wipd.schulprojekt_mathe.statistikClasses;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wipd.schulprojekt_mathe.R;

import java.util.Arrays;
import java.util.Objects;

@SuppressLint("ALL")
public class StatistikRechnerActivity extends AppCompatActivity {

    private EditText editTextInputSize, editTextInputs;

    private double[] numbers;
    private int arraySize, i;
    private String statistik_button_dateien, ausgabe;
    private String[] spinnerValues = {"Maximum", "Minimum", "Spannweite", "Arithmetisches Mittel",
            "Geometrisches Mittel", "Median", "Modalwert", "Varianz", "Standardabweichung", "Alles zusammen"};
    private boolean clearBtnActive = false;
    private boolean isFelledFilled = false;
    private boolean spinnerIsOn = true;

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

        setSpinner_layout_Values();

    }

    private void komponente_Initzialisieren() {
        editTextInputSize = findViewById(R.id.editTextInputSize);
        editTextInputs = findViewById(R.id.editTextInputs);

        btnCheckInputs = findViewById(R.id.imageButtonCheckInputs);
        btnCheckInputSize = findViewById(R.id.imageButtonCheckInputSize);

        textViewInfoCounter = findViewById(R.id.textViewInfoCounter);
        textViewStatistikErgebnis = findViewById(R.id.textViewStatistikErgebnis);

        spinnerStatistik = findViewById(R.id.spinnerStatistik);

        statistik_button_dateien = StatistikPageActivity.extra_statistik_dateien;
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
            isFelledFilled = true;
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
            isFelledFilled = false;
            Toast.makeText(this, "Geben Sie die Größe ein", Toast.LENGTH_SHORT).show();
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
            buttonSuchen_methodeAusfuehren(spinnerStatistik.getSelectedItem().toString());
            textViewStatistikErgebnis.setVisibility(View.VISIBLE);

            editTextInputs.setEnabled(false);
            btnCheckInputSize.setEnabled(false);
        }
    }

    /**
     * Löscht alle Felder und setzt alles wieder von Anfang ein
     */
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

    private double maximumNummerSuchen() {
        Arrays.sort(numbers);
        double zahl = numbers[arraySize - 1];
        textViewStatistikErgebnis.setText("Die größte Zahl ist: " + zahl);
        return zahl;
    }

    private double minimumNummerSuchen() {
        Arrays.sort(numbers);
        double zahl = numbers[0];
        textViewStatistikErgebnis.setText("Die kleinste Zahl ist: " + zahl);
        return zahl;
    }

    private double spannweiteBerechnen() {
        double range;
        if (minimumNummerSuchen() < 0) {
            range = maximumNummerSuchen() + minimumNummerSuchen();
        } else {
            range = maximumNummerSuchen() - minimumNummerSuchen();
        }
        textViewStatistikErgebnis.setText("Die Spannweite ist: " + range);
        return range;
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

    private double geometrischesMittelBerechnen() {
        double gMittel = 1;

        for (double number : numbers) {
            gMittel *= number;
        }

        gMittel = Math.pow(gMittel, (double) 1 / numbers.length);
        textViewStatistikErgebnis.setText("Das Geometrische Mittel ist: " + gMittel);
        return gMittel;
    }

    private double medianBerechnen() {
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
        return median;
    }

    private double modusBerechnen() {
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
        return mod;
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

    private double standardabweichungBerechnen() {
        double standardabweichung;
        standardabweichung = Math.sqrt(varianzBerechnen());
        textViewStatistikErgebnis.setText("Die Standardabweichung ist: " + standardabweichung);
        return standardabweichung;
    }

    private void alleszusammenBerechnen() {
        String ausgabe = "Der Maximum ist: " + maximumNummerSuchen() +
                "\nDer Minimum ist: " + minimumNummerSuchen() +
                "\nDie Spannweite ist: " + spannweiteBerechnen() +
                "\nDas Arithmetische Mittel ist: " + arithmetischesMittelBerechnen() +
                "\nDas Geometrische Mittel ist: " + geometrischesMittelBerechnen() +
                "\nDer Median ist: " + medianBerechnen() +
                "\nDer Modus ist: " + modusBerechnen() +
                "\nDie Varianz ist: " + varianzBerechnen() +
                "\nDie Standardabweichung ist: " + standardabweichungBerechnen();
        textViewStatistikErgebnis.setText(ausgabe);
    }

    /**
     * Diese methode überprüft welcher Button gecklickt wurde um
     * die rechnungen anzupassen
     */
    private void buttonSuchen_methodeAusfuehren(String itemCase) {

        setSpinnerItem();
        setTitle();
        spinnerIsOn = false;

        if (isFelledFilled) {
            switch (itemCase) {
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
    }

    /**
     * Erstellt den Titel je nachdem welches Spinner item man gewählt hat
     */
    private void setTitle() {
        String title = spinnerStatistik.getSelectedItem().toString();
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    private void setSpinnerItem() {
        int selection = 0;
        if (spinnerIsOn) {
            for (int j = 0; j < spinnerValues.length; j++) {
                if (statistik_button_dateien.equalsIgnoreCase(spinnerValues[j])) {
                    selection = j;
                    break;
                }
            }
            spinnerStatistik.setSelection(selection);
        }
    }

    /**
     * hier wird der Spinner erstellt und initialisiert
     */
    private void setSpinner_layout_Values() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spinnerValues);
        spinnerStatistik.setAdapter(adapter);

        spinnerStatistik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                buttonSuchen_methodeAusfuehren(spinnerStatistik.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}