package com.wipd.schulprojekt_mathe.prozesseClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wipd.schulprojekt_mathe.R;

public class ProzesseRechnerActivity extends AppCompatActivity {

    private EditText editTextKapital, editTextZinssatz, editTextLaufzeit;
    private TextView textViewJahr, textViewKapital, textViewZinsbetrag, textViewJahresendbetrag;
    private double kapital, zinssatz;
    private int laufzeit;
    private TableLayout tableLayoutProzesse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prozesse_rechner);

        viewsDeclaration();

    }

    private void viewsDeclaration() {
        editTextKapital = findViewById(R.id.editTextKapital);
        editTextZinssatz = findViewById(R.id.editTextZinssatz);
        editTextLaufzeit = findViewById(R.id.editTextLaufzeit);

        textViewJahr = findViewById(R.id.textViewJahr);
        textViewKapital = findViewById(R.id.textViewKapital);
        textViewZinsbetrag = findViewById(R.id.textViewZinsbetrag);
        textViewJahresendbetrag = findViewById(R.id.textViewJahresendbetrag);
        tableLayoutProzesse = findViewById(R.id.tableProzesse);
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

        clearFocus();
        resetTextViews();
        tableLayoutProzesse.setVisibility(View.VISIBLE);

        for (int i = 1; i <= laufzeit; i++) {
            double zb = (zinssatz * kapital) / 100;
            zb = Math.round(zb * 100.) / 100.;
            double jb = zb + kapital;
            jb = Math.round(jb * 100.) / 100.;

            kapital = jb;
            kapital = Math.round(kapital * 100.) / 100.;

            textViewJahr.setText(textViewJahr.getText().toString() + i + "\n");
            textViewKapital.setText(textViewKapital.getText().toString() + kapital + "\n");
            textViewZinsbetrag.setText(textViewZinsbetrag.getText().toString() + zb + "\n");
            textViewJahresendbetrag.setText(textViewJahresendbetrag.getText().toString() + jb + "\n");
        }
    }

    private void resetTextViews() {
        textViewJahr.setText("");
        textViewKapital.setText("");
        textViewZinsbetrag.setText("");
        textViewJahresendbetrag.setText("");
    }

    private void clearFocus(){
        editTextKapital.clearFocus();
        editTextLaufzeit.clearFocus();
        editTextZinssatz.clearFocus();
    }

    private void berechneZellwachstum() {

    }

}
