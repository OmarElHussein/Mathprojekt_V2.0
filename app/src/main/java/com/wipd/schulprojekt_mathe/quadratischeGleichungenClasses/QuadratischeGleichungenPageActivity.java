package com.wipd.schulprojekt_mathe.quadratischeGleichungenClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.wipd.schulprojekt_mathe.R;

public class QuadratischeGleichungenPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratische_gleichungen_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openQuadratischeGleichungenRechner(View view){
        Intent intent = new Intent(this, QuadratischeGleichungenRechnerActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
