package com.wipd.schulprojekt_mathe.matrixClasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.wipd.schulprojekt_mathe.R;

public class MatrixRechnerActivity extends AppCompatActivity {

    private EditText array1;
    private EditText array2;
    private int zeile;
    private int spalte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_rechner);

        array1 = findViewById(R.id.editText_Erste_Array);
        array2 = findViewById(R.id.editText_Zweiter_Array);
        zeile = Integer.parseInt(array1.getText().toString());
        spalte = Integer.parseInt(array2.getText().toString());
    }

    double [][] matrix1 = new double[zeile][spalte];



}
