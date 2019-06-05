package com.wipd.schulprojekt_mathe;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VektorrechnungClasses extends AppCompatActivity {

    /**
     * deklarieren und Importieren
     */

    private EditText editTextArray2;
    private EditText editTextZahl;
    private EditText editTextVektor;
    private EditText editTextVektor2;
    private EditText editTextErgebnisZahl;
    private EditText editTextErgebnis2Vektor;
    private EditText editTextErgebnisVektor;
    private int groesse;
    private int i = 0, j = 0;
    private double[] vektorA;
    private double[] vektorB;


    /**
     * Methode um Design und Programm zu verbinden
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vektorrechnung_classes);

        editTextVektor = findViewById(R.id.editTextVektor);
        editTextErgebnis2Vektor = findViewById(R.id.editTextErgebnis2Vektor);
        editTextErgebnisZahl = findViewById(R.id.editTextErgebnisZahl);
        editTextErgebnisVektor = findViewById(R.id.editTextErgebnisVektor);
        editTextArray2 = findViewById(R.id.editTextArray2);
        editTextZahl = findViewById(R.id.editTextZahl);
        editTextVektor2 = findViewById(R.id.editTextVektor2);

    }

    public void buttonNeu(View view) {
        editTextZahl.setText("");

    }

    public void setButtonBestaetigen(View view) {
        /* Eingabe Vektor in Array
         */
        groesse = Integer.parseInt(editTextArray2.getText().toString());
        System.out.println("Die Array Größe ist: " + groesse);
        vektorA = new double[groesse];
        vektorB = new double[groesse];
    }

    public void setButtonBestaetigen2(View view) {
        /* Eingabe Vektor A und Vektor B Speichern
         * */
        try {
            if (editTextVektor.getText().length() > 0 && i < groesse) {
                vektorA[i] = Double.parseDouble(editTextVektor.getText().toString());
                System.out.println("Wert " + i + " von vektorA: " + vektorA[i]);
                i++;
                editTextVektor.setText("");
            }

            if (editTextVektor2.getText().length() > 0 && j < groesse) {
                vektorB[j] = Double.parseDouble(editTextVektor2.getText().toString());
                System.out.println("Wert " + j + " von vektorB: " + vektorB[j]);
                j++;
                editTextVektor2.setText("");
            }

        } catch (NumberFormatException e1) {
            Toast.makeText(this, "sicher?", Toast.LENGTH_SHORT).show();
        }
    }

    public void setButtonNewEingabe(View view) {
        /* Löscht die Eingabe */

        editTextVektor.setText("");
        editTextVektor2.setText("");
        editTextArray2.setText("");
    }

    public void setButtonLoeschen(View view) {
        /* Löscht alle Felder + Ergebnis */

        editTextArray2.setText("");
        editTextZahl.setText("");
        editTextVektor.setText("");
        editTextVektor2.setText("");
        editTextErgebnisZahl.setText("");
        editTextErgebnis2Vektor.setText("");
        editTextErgebnisVektor.setText("");
    }

    public void setButtonErgebnis(View view) {
        /* Multiplikation mit einer Zahl
         * Zbs. VektorA * Zahl = ErgebnisZahl
         */

        double[] summe = new double[groesse];
        double multiplikation = 1;
        try {
            /* Rechnung Vektor mit/* einer Zahl */

            for (int i = 0; i < groesse; i++) {
                summe[i] = (vektorA[i] * multiplikation);
                editTextErgebnisZahl.setText(editTextErgebnisZahl.getText() + "\n" + "SummeVektorA: " + summe[i] + "\n");

                summe[i] = (vektorB[i] * multiplikation);
                editTextErgebnisZahl.setText(editTextErgebnisZahl.getText() + "\n" + "SummeVektorB: " + summe[i] + "\n");
                multiplikation = Double.parseDouble(editTextErgebnisZahl.getText().toString());
            }

        }catch (NumberFormatException e1) {
            Toast.makeText(this, "Sicher?", Toast.LENGTH_SHORT).show();
        }
        /* Multiplikation mit zweier Vektoren
         * ((VektorA) + (VektorB) * Zahl = ErgebnisZahl
         */

        double[] summe1 = new double[groesse];
        try{
            /* Rechnung mit zweier Vektoren * Zahl */

            for(int i = 0; i < groesse; i ++){
                summe1[i] = vektorA[i] * vektorB[i];
                editTextErgebnis2Vektor.setText("Summe: " + summe1[i] + "\n");
            }

        } catch (NumberFormatException e1) {
            Toast.makeText(this, "Sicher?", Toast.LENGTH_SHORT).show();
        }

        /* Addition zweier Vektoren
         * (VektorA) + (VektorB) = ErgebnisZahl
         */

        double[] summe3 = new double[groesse];
        try{
            /* Rechnung mit Addition zweier Vektoren
             *
             */

            for (int i = 0; i < groesse; i ++){
                summe3[i] = vektorA[i] + vektorB[i];
                editTextErgebnisVektor.setText("Summe: " + summe3[i] + "\n");
            }
        } catch (NumberFormatException e1) {
            Toast.makeText(this, "Sicher?", Toast.LENGTH_SHORT).show();
            }
        }

    }
