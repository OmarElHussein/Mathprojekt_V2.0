package com.wipd.schulprojekt_mathe.statistikClasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wipd.schulprojekt_mathe.R;

import java.util.Arrays;

public class StatistikRechnerActivity extends AppCompatActivity {

    private EditText editTextInputSize, editTextInputs;

    private double[] numbers;
    private int arraySize, i;

    private ImageButton btnCheckInputs, btnCheckInputSize;
    private TextView textViewInfoCounter, textViewGroessteZahl;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik_rechner);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextInputSize = findViewById(R.id.editTextInputSize);
        editTextInputs = findViewById(R.id.editTextInputs);

        btnCheckInputs = findViewById(R.id.imageButtonCheckInputs);
        btnCheckInputSize = findViewById(R.id.imageButtonCheckInputSize);

        textViewInfoCounter = findViewById(R.id.textViewInfoCounter);
        textViewGroessteZahl = findViewById(R.id.textViewGroessteZahl);


        i = 0;
    }

    public void checkInputSize(View view) {
        if (editTextInputSize.getText().length() > 0 && Integer.parseInt(editTextInputSize.getText().toString()) != 0) {

            textViewGroessteZahl.setVisibility(View.VISIBLE);
            textViewInfoCounter.setVisibility(View.VISIBLE);
            editTextInputs.setVisibility(View.VISIBLE);
            btnCheckInputSize.setVisibility(View.VISIBLE);

            arraySize = Integer.parseInt(editTextInputSize.getText().toString());
            numbers = new double[arraySize];
            editTextInputSize.setEnabled(false);
            btnCheckInputs.setEnabled(false);

            textViewInfoCounter.setText("Geben Sie die 1. Zahl ein: (1/" + arraySize + ")");

        } else {
            Toast.makeText(this, "Geben Sie die Größe ein!", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkInputs(View view) {

        if ((editTextInputs.getText().length() > 0) && !(i > arraySize - 1)) {
            try {
                numbers[i] = Integer.parseInt(editTextInputs.getText().toString());
                i++;
                textViewInfoCounter.setText("Geben Sie die " + i + ". Zahl ein : (" + i + "/" + arraySize + ")");
                editTextInputs.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Bitte Felder auswählen", Toast.LENGTH_SHORT).show();
            }

        }

        if (i == arraySize) {
            maximumNummerSuchen();

            editTextInputs.setEnabled(false);
            btnCheckInputSize.setEnabled(false);
        }
    }

    private void maximumNummerSuchen() {
        Arrays.sort(numbers);
        textViewGroessteZahl.setText("Die größte Zahl ist: " + numbers[arraySize - 1]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}

