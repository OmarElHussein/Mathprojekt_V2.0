package com.wipd.schulprojekt_mathe.quadratischeGleichungenClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.wipd.schulprojekt_mathe.R;

public class QuadratischeGleichungenPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public static String extra_page_dateien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratische_gleichungen_page);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quadratische Gleichungen");

    }

    public void openQuadratischeGleichungenRechner(View view){
        Intent intent = new Intent(this, QuadratischeGleichungenRechnerActivity.class);
        extra_page_dateien = "Quadratische Gleichungen";
        intent.putExtra(extra_page_dateien, extra_page_dateien);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
