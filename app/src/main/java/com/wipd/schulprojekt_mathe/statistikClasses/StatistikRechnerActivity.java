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
import android.widget.TableLayout;
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

    private TableLayout tableStatistik;
    private TableLayout tableStatistikAusgaben;
    private TextView textViewMaxErgebnis, textViewMinErgebnis, textViewArMittelErgebnis, textViewGeoMittelErgebnis,
                    textViewSpannweiteErgebnis, textViewMedianErgebnis, textViewModalErgebnis, textViewVarianzErgebnis,
                    textViewStandAbweichungErgebnis, textViewZaehler;


    private boolean clearBtnActive = false;
    private boolean isFieldFilled = false;
    private boolean spinnerIsOn = true;

    private ImageButton btnCheckInputs, btnCheckInputSize;
    private TextView textViewInfoCounter;
    private Spinner spinnerStatistik;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik_rechner);

        toolbarEigenschaften();

        komponente_Initzialisieren();
        table_komponente_Initialisieren();

        setSpinner_layout_Values();

    }

    private void table_komponente_Initialisieren() {
        tableStatistik = findViewById(R.id.tableStatistik);
        tableStatistikAusgaben = findViewById(R.id.tableStatistikAusgaben);

        textViewMaxErgebnis = findViewById(R.id.textViewErgebnisMax);
        textViewMinErgebnis = findViewById(R.id.textViewErgebnisMin);
        textViewArMittelErgebnis = findViewById(R.id.textViewErgebnisArMittel);
        textViewGeoMittelErgebnis = findViewById(R.id.textViewErgebnisGeoMittel);
        textViewSpannweiteErgebnis= findViewById(R.id.textViewErgebnisSpannweite);
        textViewMedianErgebnis = findViewById(R.id.textViewErgebnisMedian);
        textViewModalErgebnis = findViewById(R.id.textViewErgebnisModus);
        textViewStandAbweichungErgebnis = findViewById(R.id.textViewErgebnisStandAbweichung);
        textViewVarianzErgebnis = findViewById(R.id.textViewErgebnisVarianz);
        textViewZaehler = findViewById(R.id.textViewErgebnisZaehler);
    }

    private void komponente_Initzialisieren() {
        editTextInputSize = findViewById(R.id.editTextInputSize);
        editTextInputs = findViewById(R.id.editTextInputs);

        btnCheckInputs = findViewById(R.id.imageButtonCheckInputs);
        btnCheckInputSize = findViewById(R.id.imageButtonCheckInputSize);

        textViewInfoCounter = findViewById(R.id.textViewInfoCounter);

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
            isFieldFilled = true;
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
            isFieldFilled = false;
            Toast.makeText(this, getString(R.string.message_groesse_eingabe), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hier werden die inputs von dem Benutzer in eine Array gespeichert
     * und eine Ausgabe (Zähler) erstellt
     */
    @SuppressLint("SetTextI18n")
    public void checkInputs(View view) {

        if ((editTextInputs.getText().length() > 0) && !(i > arraySize - 1)) {
            try {
                numbers[i] = Double.parseDouble(editTextInputs.getText().toString());
                textViewZaehler.setText(textViewZaehler.getText() + "" + numbers[i] + "\n");
                i++;
                if (i < arraySize) {
                    ausgabe = getString(R.string.rechner_statistik_text_zahlGeben) + (i + 1) + getString(R.string.rechner_statistik_textZahlNachCounter);
                }
                //TODO: Wenn man eine 1 als größe angibt geht der zähler im TextView auf "null"
                String infoCounter = ausgabe + "(" + i + "/" + arraySize + ")";
                textViewInfoCounter.setText(infoCounter);


                editTextInputs.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, getString(R.string.fill_fields_message), Toast.LENGTH_SHORT).show();
            }

        }

        if (i == arraySize) {
            buttonSuchen_methodeAusfuehren(spinnerStatistik.getSelectedItem().toString());
            tableStatistik.setVisibility(View.VISIBLE);
            tableStatistikAusgaben.setVisibility(View.VISIBLE);


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

            tableStatistik.setVisibility(View.INVISIBLE);
            tableStatistikAusgaben.setVisibility(View.INVISIBLE);
            btnCheckInputSize.setEnabled(true);
            editTextInputs.setEnabled(true);
        }
    }

    private double maximumNummerSuchen() {
        Arrays.sort(numbers);
        double zahl = numbers[arraySize - 1];
        endAusgabe = Double.toString(zahl);
        textViewMaxErgebnis.setText(endAusgabe);
        return zahl;
    }

    private double minimumNummerSuchen() {
        Arrays.sort(numbers);
        double zahl = numbers[0];
        endAusgabe = Double.toString(zahl);
        textViewMinErgebnis.setText(endAusgabe);
        return zahl;
    }

    private double spannweiteBerechnen() {
        double range;
        if (minimumNummerSuchen() < 0) {
            range = maximumNummerSuchen() + minimumNummerSuchen();
        } else {
            range = maximumNummerSuchen() - minimumNummerSuchen();
        }
        endAusgabe = Double.toString(range);
        textViewSpannweiteErgebnis.setText(endAusgabe);
        return range;
    }

    private double arithmetischesMittelBerechnen() {
        double aMittel = 0;
        for (double number : numbers) {
            aMittel += number;
        }
        aMittel /= numbers.length;
        endAusgabe = Double.toString(aMittel);
        textViewArMittelErgebnis.setText(endAusgabe);
        return aMittel;
    }

    private String geometrischesMittelBerechnen() {
        double gMittel = 1;
        String output;

        for (double number : numbers) {
            gMittel *= number;
        }

        if (gMittel > 0) {
            gMittel = Math.pow(gMittel, (double) 1 / numbers.length);
            output = Double.toString(gMittel);
            textViewGeoMittelErgebnis.setText(output);
        } else {
            output = getString(R.string.rechner_qg_text_keineLoesung);
            textViewGeoMittelErgebnis.setText(output);
        }
        return output;
    }

    private double medianBerechnen() {
        Arrays.sort(numbers);
        double median;
        double median2;
        if (numbers.length % 2 == 0) {
            median = numbers[(numbers.length / 2) - 1];
            median2 = numbers[numbers.length / 2];
            median += median2;
            median /= 2;
            endAusgabe = Double.toString(median);
            textViewMedianErgebnis.setText(endAusgabe);
        } else {
            median = numbers[numbers.length / 2];
            endAusgabe = Double.toString(median);
            textViewMedianErgebnis.setText(endAusgabe);
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
        endAusgabe = Double.toString(mod);
        textViewModalErgebnis.setText(endAusgabe);
        return mod;
    }

    private double varianzBerechnen() {
        double varianz = 0;
        for (double number : numbers) {
            varianz += Math.pow(number - arithmetischesMittelBerechnen(), 2);
        }
        varianz /= numbers.length;
        endAusgabe = Double.toString(varianz);
        textViewVarianzErgebnis.setText(endAusgabe);
        return varianz;
    }

    private double standardabweichungBerechnen() {
        double standardabweichung;
        standardabweichung = Math.sqrt(varianzBerechnen());
        endAusgabe = Double.toString(standardabweichung);
        textViewStandAbweichungErgebnis.setText(endAusgabe);
        return standardabweichung;
    }

    private void alleszusammenBerechnen() {
        String max = Double.toString(maximumNummerSuchen());
        textViewMaxErgebnis.setText(max);

        String min = Double.toString(minimumNummerSuchen());
        textViewMinErgebnis.setText(min);

        String arMittel = Double.toString(arithmetischesMittelBerechnen());
        textViewArMittelErgebnis.setText(arMittel);

        String geoMittel = geometrischesMittelBerechnen();
        textViewGeoMittelErgebnis.setText(geoMittel);

        String varianz = Double.toString(varianzBerechnen());
        textViewVarianzErgebnis.setText(varianz);

        String spannweite = Double.toString(spannweiteBerechnen());
        textViewSpannweiteErgebnis.setText(spannweite);

        String standAbweichung = Double.toString(standardabweichungBerechnen());
        textViewStandAbweichungErgebnis.setText(standAbweichung);

        String modus = Double.toString(modusBerechnen());
        textViewModalErgebnis.setText(modus);

        String median = Double.toString(medianBerechnen());
        textViewMedianErgebnis.setText(median);
    }

    /**
     * Diese methode überprüft welcher Button gecklickt wurde um
     * die rechnungen anzupassen
     */
    private void buttonSuchen_methodeAusfuehren(String itemCase) {

        setSpinnerItem();
        setTitle();
        spinnerIsOn = false;

        if (isFieldFilled) {
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