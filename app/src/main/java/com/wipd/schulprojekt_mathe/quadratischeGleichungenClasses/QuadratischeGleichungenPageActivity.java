package com.wipd.schulprojekt_mathe.quadratischeGleichungenClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.wipd.schulprojekt_mathe.R;

public class QuadratischeGleichungenPageActivity extends AppCompatActivity {

    public static String extra_page_dateien;

    /**
     * verantwortlich für das erstellen der Klasse und Design und die Verbindung
     * @param savedInstanceState von superClass um Speicher zu können
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratische_gleichungen_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quadratische Gleichungen");

    }

    /**
     * öffnet eine Page durch Button Click
     * @param view ist wichtig um das Zeigen zu ermöglichen
     */
    public void openQuadratischeGleichungenRechner(View view){
        Intent intent = new Intent(this, QuadratischeGleichungenRechnerActivity.class);
        extra_page_dateien = "Quadratische Gleichungen";
        intent.putExtra(extra_page_dateien, extra_page_dateien);
        startActivity(intent);
    }

    /**
     * Eigenschafter von Toolbar (zurück Pfeil)
     * @param item der Pfeil ist ein Item
     * @return true wenn gecklickt wird
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
