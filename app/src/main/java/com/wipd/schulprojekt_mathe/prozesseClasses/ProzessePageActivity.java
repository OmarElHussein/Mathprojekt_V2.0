package com.wipd.schulprojekt_mathe.prozesseClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.wipd.schulprojekt_mathe.R;


public class ProzessePageActivity extends AppCompatActivity {

    public static String buttonInhalt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prozesse_page);
    }

    public void openProzesseRechnerPage_Sparvertrag(View view) {
        Intent intent = new Intent(this, ProzesseRechnerActivity.class);
        buttonInhalt = "Sparvertrag";
        intent.putExtra(buttonInhalt, buttonInhalt);
        startActivity(intent);
    }

    public void openProzessRechnerPage_Zellwachstum(View view) {
        Intent intent = new Intent(this, ProzesseRechnerActivity.class);
        buttonInhalt = "Zellwachstum";
        intent.putExtra(buttonInhalt, buttonInhalt);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
