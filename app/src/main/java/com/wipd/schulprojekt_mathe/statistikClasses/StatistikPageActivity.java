package com.wipd.schulprojekt_mathe.statistikClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.wipd.schulprojekt_mathe.R;

import java.util.Objects;

public class StatistikPageActivity extends AppCompatActivity {

    public static String extra_statistik_dateien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.titleStatistik);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void openStatistikRechner_btnMinimum(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = getString(R.string.btnMinimum);
        startActivity(intent);
    }

    public void openStatistikRechner_btnMaximum(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = getString(R.string.btnMax);
        startActivity(intent);
    }

    public void openStatistikRechner_btnSpannweite(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = getString(R.string.btnSpannweite);
        startActivity(intent);
    }

    public void openStatistikRechner_btnArithmetischesMittel(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = getString(R.string.btnArithmeticMean);
        startActivity(intent);
    }

    public void openStatistikRechner_btnGeometrischesMittel(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = getString(R.string.btnGeometricMedium);
        startActivity(intent);
    }

    public void openStatistikRechner_btnMedian(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = getString(R.string.btnMedian);
        startActivity(intent);
    }

    public void openStatistikRechner_btnVarianz(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = getString(R.string.btnVariance);
        startActivity(intent);
    }

    public void openStatistikRechner_btnStandardabweichung(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = getString(R.string.btnStandardabweichung);
        startActivity(intent);
    }

    public void openStatistikRechner_btnModalwert(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = getString(R.string.btnModal);
        startActivity(intent);
    }

    public void openStatistikRechner_btnAllesZsm(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        extra_statistik_dateien = getString(R.string.btnAlles);
        startActivity(intent);
    }

}
