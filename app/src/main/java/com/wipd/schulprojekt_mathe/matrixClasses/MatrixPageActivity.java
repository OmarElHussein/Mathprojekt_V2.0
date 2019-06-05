package com.wipd.schulprojekt_mathe.matrixClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.wipd.schulprojekt_mathe.R;

import java.util.Objects;

public class MatrixPageActivity extends AppCompatActivity {

    public static String matrixTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.strpage_matrix));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void oeffneMultiplikationMitEinerZahl(View view) {
        Intent intent = new Intent(this, MatrixRechnerActivity.class);
        matrixTitle = "Zahl mit Matrix";
        startActivity(intent);
    }
    public void oeffneAddidtionZweierMatrizen (View view) {
        Intent intent = new Intent(this, MatrixRechnerActivity.class);
        matrixTitle = "Addition Matrizen";
        startActivity(intent);
    }
    public void oeffeneMultiplikationZweierMatrizen (View view) {
        Intent intent = new Intent(this, MatrixRechnerActivity.class);
        matrixTitle = "Multiplikation Matrizen";
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
