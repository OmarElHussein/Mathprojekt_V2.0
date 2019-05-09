package com.wipd.schulprojekt_mathe.quadratischeGleichungenClasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.wipd.schulprojekt_mathe.R;

public class QuadratischeGleichungenRechnerActivity extends AppCompatActivity {

    private EditText editText_a_quadrat, editText_b, editText_c;
    private TextView textViewErgebnis;
    private Spinner spinnerNachkomma;

    double p, q, a, d, x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratische_gleichungen_rechner);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText_a_quadrat = findViewById(R.id.editText_Xquadrat_eingabe);
        editText_b = findViewById(R.id.editText_x_eingabe);
        editText_c = findViewById(R.id.editText_c_eingabe);
        spinnerNachkomma = findViewById(R.id.spinnerNachkommastellen);

        textViewErgebnis = findViewById(R.id.textViewErgebnis);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void berechneQuadratischeFormel(View view) {

        d = 0;

        a = Double.parseDouble(editText_a_quadrat.getText().toString());
        p = Double.parseDouble(editText_b.getText().toString());
        q = Double.parseDouble(editText_c.getText().toString());


        p = a / p;
        q = a / q;

        d = Math.sqrt(Math.pow(p / 2D, 2D) - q);
        x1 = -p / 2 - d;
        x2 = -p / 2 + d;



        if (d == 0) {
            textViewErgebnis.setText("x1 = " + x1 + " + " +  d);
        } else if (d < 0) {
            textViewErgebnis.setText("ERROR");
        } else if(d > 0) {
            textViewErgebnis.setText("x1 = " + x1 + " + " + d + "\nx2 = " + x2);
        }

    }


}
