package com.wipd.schulprojekt_mathe.prozesseClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wipd.schulprojekt_mathe.R;

public class ProzesseRechnerActivity extends AppCompatActivity {

    private EditText editTextKapital, editTextZinssatz, editTextLaufzeit;
    private TextView textViewErgebnis;

    private double kapital, zinssatz;
    private int laufzeit;
    private StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prozesse_rechner);

        editTextKapital = findViewById(R.id.editTextKapital);
        editTextZinssatz = findViewById(R.id.editTextZinssatz);
        editTextLaufzeit = findViewById(R.id.editTextLaufzeit);
        textViewErgebnis = findViewById(R.id.textViewProzessErgebnis);

        sb = new StringBuilder();
    }

    public void prozessBerechnung(View view) {

        if (editTextKapital.length() > 0 && editTextZinssatz.length() > 0 && editTextLaufzeit.length() > 0) {

            kapital = Double.parseDouble(editTextKapital.getText().toString());
            zinssatz = Double.parseDouble(editTextZinssatz.getText().toString());
            laufzeit = Integer.parseInt(editTextLaufzeit.getText().toString());

            Intent intent = getIntent();
            String methodeAuswahl = intent.getStringExtra(ProzessePageActivity.buttonInhalt);

            if (methodeAuswahl.equalsIgnoreCase("Sparvertrag")) {
                berechneSparvertrag();
                Toast.makeText(this, "Sparvetrag ist der Inhalt", Toast.LENGTH_SHORT).show();
            } else if (methodeAuswahl.equalsIgnoreCase("Zellwachstum")) {
                Toast.makeText(this, "Zellwachstum ist der Inhalt", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "Bitte Felder ausfüllen", Toast.LENGTH_SHORT).show();
        }
    }

    private void berechneSparvertrag() {

        textViewErgebnis.setText("\nJahr\tKapital\t\tZinsbetrag\t\tJahresendbetrag");

        for (int i = 1; i <= laufzeit; i++) {
            double zb = (zinssatz * kapital) / 100;
            zb = Math.round(zb * 100.) / 100.;
            double jb = zb + kapital;
            jb = Math.round(jb * 100.) / 100.;
            textViewErgebnis.setText(textViewErgebnis.getText() + "\n" + i + "\u0009" + kapital + "\u0009" + zb + "\u0009" + jb);
            kapital = jb;
            kapital = Math.round(kapital * 100.) / 100.;
        }
    }

    private void berechneZellwachstum() {

    }

}
