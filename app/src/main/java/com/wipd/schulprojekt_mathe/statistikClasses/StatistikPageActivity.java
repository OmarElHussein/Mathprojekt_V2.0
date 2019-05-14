package com.wipd.schulprojekt_mathe.statistikClasses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.wipd.schulprojekt_mathe.R;

@SuppressLint("ALL")
public class StatistikPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public static String extra_statistik_dateien = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik_page);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Statistik");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void openStatistikRechner_btnMinimum(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = "Minimum";
        startActivity(intent);
    }

    public void openStatistikRechner_btnMaximum(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = "Maximum";
        startActivity(intent);
    }

    public void openStatistikRechner_btnSpannweite(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = "Spannweite";
        startActivity(intent);
    }

    public void openStatistikRechner_btnArithmetischesMittel(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = "Arithmetisches Mittel";
        startActivity(intent);
    }

    public void openStatistikRechner_btnGeometrischesMittel(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = "Geometrisches Mittel";
        startActivity(intent);
    }

    public void openStatistikRechner_btnMedian(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = "Median";
        startActivity(intent);
    }

    public void openStatistikRechner_btnVarianz(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = "Varianz";
        startActivity(intent);
    }

    public void openStatistikRechner_btnStandardabweichung(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = "Standardabweichung";
        startActivity(intent);
    }

    public void openStatistikRechner_btnModalwert(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = "Modalwert";
        startActivity(intent);
    }

    public void openStatistikRechner_btnAllesZsm(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = "Alles zusammen";
        startActivity(intent);
    }

}
