package com.wipd.schulprojekt_mathe.quadratischeGleichungenClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wipd.schulprojekt_mathe.R;

@SuppressLint("ALL")
public class QuadratischeGleichungenRechnerActivity extends AppCompatActivity {

    private EditText editText_a_quadrat, editText_b, editText_c;
    private TextView textViewErgebnis;
    private Spinner spinnerNachkomma;

    private String[] spinnerValues = {"0", "1", "2", "3", "4", "5", "6"};
    private final String wurzel = " \u221a ";
    private final String plusMinus = " \u00b1 ";
    private final String slash = " \u2044 ";
    private boolean btnAktiv;

    private Toolbar toolbar;

    double p, q, a, d, x1, x2;

    /**
     * Erstellt den Layout und verbindet dies mit der Klasse
     *
     * @param savedInstanceState um speicher zu können
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratische_gleichungen_rechner);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(QuadratischeGleichungenPageActivity.extra_page_dateien);

        komponente_Initzialisieren();

        setSpinnerNachkomma();

        spinnerNachkomma.setSelection(2);

    }

    /**
     * Die Deklaration von alle Ein-und Ausgabe Felder + Spinner (JComboBox)
     */
    private void komponente_Initzialisieren() {
        editText_a_quadrat = findViewById(R.id.editText_Xquadrat_eingabe);
        editText_b = findViewById(R.id.editText_x_eingabe);
        editText_c = findViewById(R.id.editText_c_eingabe);
        spinnerNachkomma = findViewById(R.id.spinnerNachkommastellen);

        textViewErgebnis = findViewById(R.id.textViewErgebnis);
    }

    /**
     * erstellt Eigenschaften von das Back button auf
     * der obere Leite (Eigenschafter nur und nicht den Button selbst)
     *
     * @param item ist das back button
     * @return gibt true zurück um was zu ändern
     * Note: hier wird die Methode nur aufgerufen um Animationen anzupassen und auch in andere stellen
     *         wo nur onBackPressed in die Methode aufgerufen wird
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    /**
     * Hier werden die Fehler überprüft
     *
     * @param view ist nötig um das zeigen zu ermöglichen
     */
    public void berechneQuadratischeFormel(View view) {

        if (editText_a_quadrat.length() > 0 && editText_b.length() > 0 && editText_c.length() > 0) {

            try {

                a = Double.parseDouble(editText_a_quadrat.getText().toString());
                p = Double.parseDouble(editText_b.getText().toString());
                q = Double.parseDouble(editText_c.getText().toString());

                tastaturSchliessen(view);

                pqFormelBerechnung();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Bitte Felder ausfüllen", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Bitte Felder ausfüllen", Toast.LENGTH_SHORT).show();
        }
        clearFocusFromFields();
        btnAktiv = true;
    }

    /**
     * Diese Methode schließt die Tastatur, wird meistens benutzt nachdem man auf einem
     * Button clickt und die Tastatur danach schließen sollte
     */
    private void tastaturSchliessen(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Hier werden alle Ein-und Ausgaben gelöscht
     *
     * @param view ist nötig um das zeigen zu ermöglichen
     */
    public void dateienLoeschen(View view) {
        editText_a_quadrat.setText("");
        editText_b.setText("");
        editText_c.setText("");
        textViewErgebnis.setText("");
        clearFocusFromFields();
    }

    /**
     * Hier wird nur die PQ-Formel gezeigt
     *
     * @param view ist nötig um das zeigen zu ermöglichen
     */
    public void formelZeigen(View view) {
        if (editText_a_quadrat.length() > 0 && editText_b.length() > 0 && editText_c.length() > 0) {
            if (btnAktiv) {
                StringBuilder sb = new StringBuilder();
                sb.append("- (" + p + slash + 2 + ") " + plusMinus + wurzel + "(" + p + slash + 2 + ")²" + " - " + q);
                textViewErgebnis.setText(sb);
            } else {
                Toast.makeText(this, "Bitte erstmal ausrechnen", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Bitte Felder ausfüllen", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hier wird die PQ-Formel berechnet und die
     * Ergebnisse ausgegeben
     */
    private void pqFormelBerechnung() {
        p /= a; // p = p / a
        q /= a; // q = q / a

        double t = p / 2;

        d = Math.pow(t, 2);
        d = d - q;


        if (d >= 0) {
            x1 = -(p / 2) - Math.sqrt(d);
            x2 = (-p / 2) + Math.sqrt(d);

            x1 = berechneNachkommaX(x1);
            x2 = berechneNachkommaX(x2);

            if (d == 0) {
                textViewErgebnis.setText("x = " + x1);
            } else if (d > 0) {
                textViewErgebnis.setText("x1 = " + x1 + "\nx2 = " + x2);
            }
        } else {
            textViewErgebnis.setText("Keine Lösung");
        }
    }

    /**
     * Hier wird überprüft welche Auswahl der benutzer getroffen hat
     * um das Ergebnis mit beliebig viele nachkommastellen aufgerundet wird
     *
     * @param x ist der x wert der aufgerundet werden sollte
     * @return gibt die gerundete Zahl zurück
     */
    private double berechneNachkommaX(double x) {
        int i = Integer.parseInt(spinnerNachkomma.getSelectedItem().toString());
        double pow = Math.pow(10.0, i);

        x = Math.round(x * pow) / pow;
        return x;

    }

    /**
     * Das erstellen von den Adapter und
     * den zu unser Spinner hinzufügen
     */
    private void setSpinnerNachkomma() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spinnerValues);
        spinnerNachkomma.setAdapter(adapter);
    }

    /**
     * Nimmt den Focus von eingabe Feldern weg
     */
    private void clearFocusFromFields() {
        editText_a_quadrat.clearFocus();
        editText_b.clearFocus();
        editText_c.clearFocus();
    }
}
