package com.wipd.schulprojekt_mathe.statistikClasses;

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

public class StatistikRechnerActivity extends AppCompatActivity {

    private EditText editTextInputSize, editTextInputs;

    private double[] numbers;
    private int arraySize, i;
    private String statistik_button_dateien, ausgabe, endAusgabe;
    private String[] spinnerValues;


    private boolean clearBtnActive = false;
    private boolean isFelledFilled = false;
    private boolean spinnerIsOn = true;

    private ImageButton btnCheckInputs, btnCheckInputSize;
    private TextView textViewInfoCounter, textViewStatistikErgebnis;
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

        spinnerValues = new String[]{getString(R.string.btnMax), getString(R.string.btnMinimum),
                getString(R.string.btnSpannweite), getString(R.string.btnArithmeticMean),
                getString(R.string.btnGeometricMedium), getString(R.string.btnMedian), getString(R.string.btnModal),
                getString(R.string.btnVariance), getString(R.string.btnStandardabweichung), getString(R.string.btnAlles)};
    }

    private void toolbarEigenschaften() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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

            String counterEingabe = getString(R.string.rechner_statistik_counter_eingabe) + " (1/" + arraySize + ")";
            textViewInfoCounter.setText(counterEingabe);

        } else {
            isFelledFilled = false;
            Toast.makeText(this, getString(R.string.message_groesse_eingabe), Toast.LENGTH_SHORT).show();
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
                    ausgabe = getString(R.string.rechner_statistik_text_zahlGeben) + (i + 1) + getString(R.string.rechner_statistik_textZahlNachCounter);
                }
                //TODO...
                String infoCounter = ausgabe + "(" + i + "/" + arraySize + ")";
                textViewInfoCounter.setText(infoCounter);


                editTextInputs.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, getString(R.string.fill_fields_message), Toast.LENGTH_SHORT).show();
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
        endAusgabe = getString(R.string.rechner_statistik_ausgabe_biggestNumber) + zahl;
        textViewStatistikErgebnis.setText(endAusgabe);
        return zahl;
    }

    private double minimumNummerSuchen() {
        Arrays.sort(numbers);
        double zahl = numbers[0];
        endAusgabe = getString(R.string.rechner_statistik_ausgabe_kleinsteZahl) + zahl;
        textViewStatistikErgebnis.setText(endAusgabe);
        return zahl;
    }

    private double spannweiteBerechnen() {
        double range;
        if (minimumNummerSuchen() < 0) {
            range = maximumNummerSuchen() + minimumNummerSuchen();
        } else {
            range = maximumNummerSuchen() - minimumNummerSuchen();
        }
        endAusgabe = getString(R.string.rechner_statistik_ausgabe_spannweite) + range;
        textViewStatistikErgebnis.setText(endAusgabe);
        return range;
    }

    private double arithmetischesMittelBerechnen() {
        double aMittel = 0;
        for (double number : numbers) {
            aMittel += number;
        }
        aMittel /= numbers.length;
        endAusgabe = getString(R.string.rechner_statistik_ausgabe_arithmetischesMittel) + aMittel;
        textViewStatistikErgebnis.setText(endAusgabe);
        return aMittel;
    }

    private String geometrischesMittelBerechnen() {
        double gMittel = 1;
        String solutionOrNot;

        for (double number : numbers) {
            gMittel *= number;
        }

        if (gMittel > 0) {
            gMittel = Math.pow(gMittel, (double) 1 / numbers.length);
            solutionOrNot = getString(R.string.rechner_statistik_ausgabe_geoMetrischesMittel) + gMittel;
            textViewStatistikErgebnis.setText(solutionOrNot);
        } else {
            solutionOrNot = getString(R.string.rechner_statistik_ausgabe_geoMittelNoSolutionTxt);
            textViewStatistikErgebnis.setText(solutionOrNot);
        }
        return solutionOrNot;
    }

    private double medianBerechnen() {
        Arrays.sort(numbers);
        double median;
        if (numbers.length % 2 == 0) {
            //ToDo: Median muss noch richtig berechnet werden bei gerade und ungerade zahlen.
            median = numbers[((numbers.length / 2) + ((numbers.length / 2) + 1)) / 2];
            endAusgabe = getString(R.string.rechner_statistik_ausgabe_median) + median;
            textViewStatistikErgebnis.setText(endAusgabe);
        } else {
            median = numbers[((numbers.length) / 2)];
            endAusgabe = getString(R.string.rechner_statistik_ausgabe_median) + median;
            textViewStatistikErgebnis.setText(endAusgabe);
        }
        return median;
    }

    private double modusBerechnen() {
        Arrays.sort(numbers);
        int count;
        double mod = 0;
        int modcount = 0;
        for (double number : numbers) {
            count = 0;
            for (double number1 : numbers) {
                if (number == number1) {
                    count++;
                }
            }
            if (modcount < count) {
                mod = number;
                modcount = count;
            }
        }
        endAusgabe = getString(R.string.rechner_statistik_ausgabe_modus) + mod;
        textViewStatistikErgebnis.setText(endAusgabe);
        return mod;
    }

    private double varianzBerechnen() {
        double varianz = 0;
        for (double number : numbers) {
            varianz += Math.pow(number - arithmetischesMittelBerechnen(), 2);
        }
        varianz /= numbers.length;
        endAusgabe = getString(R.string.rechner_statistik_ausgabe_varianz) + varianz;
        textViewStatistikErgebnis.setText(endAusgabe);
        return varianz;
    }

    private double standardabweichungBerechnen() {
        double standardabweichung;
        standardabweichung = Math.sqrt(varianzBerechnen());
        endAusgabe = getString(R.string.rechner_statistik_ausgabe_standardabweichung) + standardabweichung;
        textViewStatistikErgebnis.setText(endAusgabe);
        return standardabweichung;
    }

    private void alleszusammenBerechnen() {
        String ausgabe = "Der Maximum ist: " + maximumNummerSuchen() +
                "\nDer Minimum ist: " + minimumNummerSuchen() +
                "\nDie Spannweite ist: " + spannweiteBerechnen() +
                "\nDas Arithmetische Mittel ist: " + arithmetischesMittelBerechnen() +
                "\n" + geometrischesMittelBerechnen() +
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
            if (spinnerValues[0].equals(itemCase)) {
                maximumNummerSuchen();
            } else if (spinnerValues[1].equals(itemCase)) {
                minimumNummerSuchen();
            } else if (spinnerValues[2].equals(itemCase)) {
                spannweiteBerechnen();
            } else if (spinnerValues[3].equals(itemCase)) {
                arithmetischesMittelBerechnen();
            } else if (spinnerValues[4].equals(itemCase)) {
                geometrischesMittelBerechnen();
            } else if (spinnerValues[5].equals(itemCase)) {
                medianBerechnen();
            } else if (spinnerValues[6].equals(itemCase)) {
                modusBerechnen();
            } else if (spinnerValues[7].equals(itemCase)) {
                varianzBerechnen();
            } else if (spinnerValues[8].equals(itemCase)) {
                standardabweichungBerechnen();
            } else if (spinnerValues[9].equals(itemCase)) {
                alleszusammenBerechnen();
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