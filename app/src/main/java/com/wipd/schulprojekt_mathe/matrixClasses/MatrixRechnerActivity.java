package com.wipd.schulprojekt_mathe.matrixClasses;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wipd.schulprojekt_mathe.R;

import java.util.Objects;

public class MatrixRechnerActivity extends AppCompatActivity {

    private String title;

    private EditText editText_matrix_spalte, editText_matrix_zeile;
    private EditText editText_matrix_werte;
    private EditText editText_eineZahlMatrix;

    private ConstraintLayout matrixSecondConstraint;

    private int spalte_groesse, zeile_groesse;
    private int spalte_groesse2, zeile_groesse2;
    private int i = 0, j = 0, countMatrix = 0;
    private boolean entrance;
    private double eineZahlMatrix = 0;

    private double[][] matrixEins;
    private double[][] matrixZwei;
    private double[][] matrixErgebnis;
    private double[] matrixLager;
    private double[] matrixLager2;

    private TextView textViewMatrixEins, textViewMatrixZwei, textViewMatrixErgebnis,
            textViewHilfe, textViewMatrixEingabeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_rechner);

        {//Title
            title = MatrixPageActivity.matrixTitle;

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        }

        editText_matrix_spalte = findViewById(R.id.editTextEingabeSpalte);
        editText_matrix_zeile = findViewById(R.id.editTextEingabeZeile);
        editText_matrix_werte = findViewById(R.id.editTextEingabeWerte);
        editText_eineZahlMatrix = findViewById(R.id.editTextEineZahlMatrix);

        matrixSecondConstraint = findViewById(R.id.matrixRechnerSecondConstraint);

        textViewMatrixEins = findViewById(R.id.matrixEinsAusgabe);
        textViewMatrixZwei = findViewById(R.id.matrixZweiAusgabe);
        textViewMatrixErgebnis = findViewById(R.id.matrixErgebnisAusgabe);
        textViewMatrixEingabeTitle = findViewById(R.id.textViewMatrixEingabeTitle);
        textViewHilfe = findViewById(R.id.textViewHilfeMatrix);
    }

    public void matrixGroesseSpeicher(View view) {

        if (editText_matrix_spalte.length() > 0 && editText_matrix_zeile.length() > 0) {

            if (title.equals("Addition Matrizen")) {
                spalte_groesse = Integer.parseInt(editText_matrix_spalte.getText().toString());
                zeile_groesse = Integer.parseInt(editText_matrix_zeile.getText().toString());

                matrixEins = new double[zeile_groesse][spalte_groesse];
                matrixZwei = new double[zeile_groesse][spalte_groesse];
                matrixErgebnis = new double[zeile_groesse][spalte_groesse];

                int groesse = spalte_groesse * zeile_groesse;
                matrixLager = new double[groesse];
                matrixLager2 = new double[groesse];

                editText_matrix_zeile.clearFocus();
                editText_matrix_spalte.clearFocus();

                editText_matrix_werte.requestFocus();
                hilfeZeigen(0, matrixEins);

            } else if (title.equals("Multiplikation Matrizen")) {

                if (countMatrix == 0) {
                    spalte_groesse = Integer.parseInt(editText_matrix_spalte.getText().toString());
                    zeile_groesse = Integer.parseInt(editText_matrix_zeile.getText().toString());

                    matrixEins = new double[zeile_groesse][spalte_groesse];
                    editText_matrix_spalte.setText("");
                    editText_matrix_zeile.setText("");

                    int groesse = spalte_groesse * zeile_groesse;
                    matrixLager = new double[groesse];

                    editText_matrix_zeile.requestFocus();
                    countMatrix = 1;

                } else if (editText_matrix_spalte.length() > 0 && editText_matrix_zeile.length() > 0 && countMatrix == 1) {
                    spalte_groesse2 = Integer.parseInt(editText_matrix_spalte.getText().toString());
                    zeile_groesse2 = Integer.parseInt(editText_matrix_zeile.getText().toString());
                    if (spalte_groesse == zeile_groesse2) {
                        matrixZwei = new double[zeile_groesse2][spalte_groesse2];

                        editText_matrix_zeile.clearFocus();
                        editText_matrix_spalte.clearFocus();
                        editText_matrix_werte.requestFocus();

                        int groesse2 = spalte_groesse2 * zeile_groesse2;
                        matrixLager2 = new double[groesse2];

                        matrixErgebnis = new double[zeile_groesse][spalte_groesse2];

                        countMatrix = 0;
                    } else {
                        Toast.makeText(this, "versuchen Sie es erneut", Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (title.equals("Zahl mit Matrix")) {
                spalte_groesse = Integer.parseInt(editText_matrix_spalte.getText().toString());
                zeile_groesse = Integer.parseInt(editText_matrix_zeile.getText().toString());

                matrixEins = new double[spalte_groesse][zeile_groesse];
                int groesse = spalte_groesse * zeile_groesse;
                matrixLager = new double[groesse];

                matrixErgebnis = new double[zeile_groesse][spalte_groesse];

                editText_matrix_zeile.clearFocus();
                editText_matrix_spalte.clearFocus();

                editText_matrix_werte.requestFocus();
            }

        } else {
            Toast.makeText(this, getString(R.string.fill_fields_message), Toast.LENGTH_SHORT).show();
        }
        matrixSecondConstraint.setVisibility(View.VISIBLE);
    }

    public void matrixWerteSpeichern(View view) {
        if (editText_matrix_werte.length() > 0) {
            try {
                if (title.equals("Addition Matrizen")) {
                    try {
                        zweiMatrizenWerteSpeichern(matrixLager, matrixLager2);
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, getString(R.string.fill_fields_message), Toast.LENGTH_SHORT).show();
                    }
                } else if (title.equals("Multiplikation Matrizen")) {
                    try {
                        zweiMatrizenWerteSpeichern(matrixLager, matrixLager2);
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, getString(R.string.fill_fields_message), Toast.LENGTH_SHORT).show();
                    }
                } else if (title.equals("Zahl mit Matrix")) {
                    try {
                        multiMitEinerZahlWerteSpeichern();
                    } catch (NullPointerException e) {
                        Toast.makeText(this, getString(R.string.fill_fields_message), Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (NumberFormatException err) {
                Toast.makeText(this, getString(R.string.fill_fields_message), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getString(R.string.fill_fields_message), Toast.LENGTH_SHORT).show();
        }
    }

    public void ergebnisseMatrizen(View view) {
        try {
            if (title.equals("Addition Matrizen")) {
                berechneAdditionZweierMatrizen();
                zeigeSummeMatrizen();
            } else if (title.equals("Multiplikation Matrizen")) {
                berechneMultiplikationZweierMatrizen();
                zeigeMultiMatrizen();
            } else if (title.equals("Zahl mit Matrix")) {
                berechneMultiplikationMitEinerZahl();
                zeigeEineMatrixMitZahl();
            }
        } catch (NullPointerException err) {
            Toast.makeText(this, getString(R.string.fill_fields_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void zeigeMultiMatrizen() {
        if (matrixEins != null && matrixZwei != null && matrixErgebnis != null) {
            StringBuilder stringErgebnis = new StringBuilder();
            StringBuilder stringMatrixEins = new StringBuilder();
            StringBuilder stringMatrixZwei = new StringBuilder();
            for (int k = 0; k < matrixEins.length; k++) {
                for (int l = 0; l < matrixEins[k].length; l++) {
                    stringMatrixEins.append(matrixEins[k][l]).append(" |");
                }
                stringMatrixEins.append("\n");
            }
            for (int k = 0; k < matrixZwei.length; k++) {
                for (int l = 0; l < matrixZwei[k].length; l++) {
                    stringMatrixZwei.append(matrixZwei[k][l]).append(" |");
                }
                stringMatrixZwei.append("\n");
            }
            for (int k = 0; k < matrixErgebnis.length; k++) {
                for (int l = 0; l < matrixErgebnis[k].length; l++) {
                    stringErgebnis.append(matrixErgebnis[k][l]).append(" |");
                }
                stringErgebnis.append("\n");
            }
            textViewMatrixEins.setText(stringMatrixEins);
            textViewMatrixZwei.setText(stringMatrixZwei);
            textViewMatrixErgebnis.setText(stringErgebnis);
        } else {
            Toast.makeText(this, getString(R.string.message_erstmal_ausrechnen), Toast.LENGTH_SHORT).show();
        }
    }

    private void zeigeSummeMatrizen() {
        if (matrixEins != null && matrixZwei != null && matrixErgebnis != null) {
            StringBuilder stringErgebnis = new StringBuilder();
            StringBuilder stringMatrixEins = new StringBuilder();
            StringBuilder stringMatrixZwei = new StringBuilder();
            for (int k = 0; k < matrixEins.length; k++) {
                for (int l = 0; l < matrixEins[k].length; l++) {
                    stringErgebnis.append(matrixErgebnis[k][l]).append(" |");
                    stringMatrixEins.append(matrixEins[k][l]).append(" |");
                    stringMatrixZwei.append(matrixZwei[k][l]).append(" |");
                }
                stringErgebnis.append("\n");
                stringMatrixEins.append("\n");
                stringMatrixZwei.append("\n");
            }
            textViewMatrixErgebnis.setText(stringErgebnis);
            textViewMatrixEins.setText(stringMatrixEins);
            textViewMatrixZwei.setText(stringMatrixZwei);
        } else {
            Toast.makeText(this, getString(R.string.message_erstmal_ausrechnen), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void zeigeEineMatrixMitZahl() {
        if (matrixEins != null) {
            StringBuilder stringMatrixEins = new StringBuilder();
            StringBuilder stringMatrixErgebnis = new StringBuilder();
            for (int k = 0; k < matrixEins.length; k++) {
                for (int l = 0; l < matrixEins[k].length; l++) {
                    stringMatrixEins.append(matrixEins[k][l]).append(" |");
                    stringMatrixErgebnis.append(matrixErgebnis[k][l]).append(" |");
                }
                stringMatrixEins.append("\n");
                stringMatrixErgebnis.append("\n");
            }
            textViewMatrixEins.setText("" + eineZahlMatrix);
            textViewMatrixZwei.setText(stringMatrixEins);
            textViewMatrixErgebnis.setText(stringMatrixErgebnis);
        } else {
            Toast.makeText(this, getString(R.string.message_erstmal_ausrechnen), Toast.LENGTH_SHORT).show();
        }
    }

    private void multiMitEinerZahlWerteSpeichern() {
        if (i < matrixLager.length) {
            entrance = false;
            matrixEinsSpeichern(matrixLager, "Geben Sie die Werte ein: (Matrix)");
            if (i == matrixLager.length) {
                if (!entrance) {
                    editText_matrix_werte.clearFocus();
                    editText_eineZahlMatrix.requestFocus();

                    int c = 0;
                    for (int k = 0; k < matrixEins.length; k++) {
                        for (int l = 0; l < matrixEins[k].length; l++) {
                            matrixEins[k][l] = matrixLager[c];
                            c++;
                        }
                    }
                    i = 0;
                    entrance = true;

                } else if (editText_eineZahlMatrix.length() > 0 && entrance) {
                    eineZahlMatrix = Double.parseDouble(editText_eineZahlMatrix.getText().toString());
                } else {
                    Toast.makeText(this, getString(R.string.fill_fields_message), Toast.LENGTH_SHORT).show();
                }
                entrance = false;
            }

        }
    }

    private void berechneMultiplikationMitEinerZahl() {
        for (int k = 0; k < matrixEins.length; k++) {
            for (int l = 0; l < matrixEins[k].length; l++) {
                matrixErgebnis[k][l] = matrixEins[k][l] * eineZahlMatrix;
            }
        }
    }

    private void berechneMultiplikationZweierMatrizen() {
        for (int i = 0; i < zeile_groesse; i++) {
            for (int j = 0; j < spalte_groesse2; j++) {
                for (int k = 0; k < spalte_groesse; k++) {
                    matrixErgebnis[i][j] += matrixEins[i][k] * matrixZwei[k][j];
                }
            }
        }
    }

    private void berechneAdditionZweierMatrizen() {
        for (int k = 0; k < matrixEins.length; k++) {
            for (int l = 0; l < matrixEins[k].length; l++) {
                matrixErgebnis[k][l] = matrixEins[k][l] + matrixZwei[k][l];
            }
        }
    }

    private void zweiMatrizenWerteSpeichern(double[] matrixLager, double[] matrixLager2) {

        if (i < matrixLager.length) {
            matrixEinsSpeichern(matrixLager, "Geben Sie die Werte ein: (Matrix 1)");
        } else {
            if (j < matrixLager2.length) {
                matrixZweiSpeichern(matrixLager2);
            }
        }

        int f = 0;
        if (i == matrixLager.length && j == matrixLager2.length) {
            for (int k = 0; k < matrixEins.length; k++) {
                for (int l = 0; l < matrixEins[k].length; l++) {
                    matrixEins[k][l] = matrixLager[f];
                    f++;
                }
            }
            f = 0;

            for (int k = 0; k < matrixZwei.length; k++) {
                for (int l = 0; l < matrixZwei[k].length; l++) {
                    matrixZwei[k][l] = matrixLager2[f];
                    f++;
                }
            }
            i = 0;
            j = 0;
            editText_matrix_werte.clearFocus();
        }
    }

    private void matrixZweiSpeichern(double[] matrixLager2) {
        matrixLager2[j] = Double.parseDouble(editText_matrix_werte.getText().toString());
        hilfeZeigen(j, matrixZwei);
        j++;
        String counter = " (" + j + "/" + matrixLager2.length + ")";
        textViewMatrixEingabeTitle.setText("Geben Sie die Werte ein: (Matrix 2)" + counter);
        editText_matrix_werte.setText("");
    }

    private void matrixEinsSpeichern(double[] matrixLager, String s) {
        matrixLager[i] = Double.parseDouble(editText_matrix_werte.getText().toString());
        hilfeZeigen(i, matrixEins);
        i++;
        String counter = " (" + i + "/" + matrixLager.length + ")";
        textViewMatrixEingabeTitle.setText(s + counter);
        editText_matrix_werte.setText("");
    }

    private void hilfeZeigen(int index, double[][] matrixTransferable) {
        SpannableString spannableString;
        StringBuilder matrix = new StringBuilder();
        ForegroundColorSpan green = new ForegroundColorSpan(Color.GREEN);
        for (int k = 0; k < matrixTransferable.length; k++) {
            for (int l = 0; l < matrixTransferable[k].length; l++) {
                matrix.append("o");
            }
            matrix.append("\n");
        }
        //TODO
        spannableString = new SpannableString(matrix.toString());
        spannableString.setSpan(green, index, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewHilfe.setText(spannableString);
    }
}