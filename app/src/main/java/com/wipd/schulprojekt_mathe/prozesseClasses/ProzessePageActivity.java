package com.wipd.schulprojekt_mathe.prozesseClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.wipd.schulprojekt_mathe.R;


public class ProzessePageActivity extends AppCompatActivity {

    public static String buttonInhalt;

    /**
     * Diese methode ist verantwortlich um das Programm und Design zu verbinden und erstellen
     * @param savedInstanceState um speicher zu können wie änderungen zwischen Design und Class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prozesse_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Öffnet eine neue Page durch ein ButtonClick
     * @param view ermöglicht das Zeigen zu lassen
     */
    public void openProzesseRechnerPage_Sparvertrag(View view) {
        Intent intent = new Intent(this, ProzesseRechnerActivity.class);
        buttonInhalt = "Sparvertrag";
        intent.putExtra(buttonInhalt, buttonInhalt);
        startActivity(intent);
    }

    /**
     * Öffnet eine neue Page durch ein ButtonClick
     * @param view ermöglicht das Zeigen zu lassen
     */
    public void openProzessRechnerPage_Zellwachstum(View view) {
        Intent intent = new Intent(this, ProzesseRechnerActivity.class);
        buttonInhalt = "Zellwachstum";
        intent.putExtra(buttonInhalt, buttonInhalt);
        startActivity(intent);
    }

    /**
     * Die eigenschaften von das zurück Pfeil Top von den Toolbar
     * @param item benötigt den item
     * @return true um zu bestätigen
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
