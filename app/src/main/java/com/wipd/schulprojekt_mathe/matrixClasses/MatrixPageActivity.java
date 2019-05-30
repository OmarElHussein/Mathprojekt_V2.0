package com.wipd.schulprojekt_mathe.matrixClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wipd.schulprojekt_mathe.R;

public class MatrixPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_page);
    }

    public void oeffneMultiplikationMitEinerZahl(View view) {
        Intent intent = new Intent(this, MatrixRechnerActivity.class);
        startActivity(intent);
    }
    public void oeffneAddidtionZweierMatrizen (View view) {
        Intent intent = new Intent(this, MatrixRechnerActivity.class);
        startActivity(intent);
    }
    public void oeffeneMultiplikationZweierMatrizen (View view) {
        Intent intent = new Intent(this, MatrixRechnerActivity.class);
        startActivity(intent);
    }
}
