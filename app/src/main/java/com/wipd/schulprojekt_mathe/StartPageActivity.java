package com.wipd.schulprojekt_mathe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wipd.schulprojekt_mathe.matrixClasses.MatrixPageActivity;
import com.wipd.schulprojekt_mathe.prozesseClasses.ProzessePageActivity;
//import com.wipd.schulprojekt_mathe.settingsManager.SettingsActivity;
import com.wipd.schulprojekt_mathe.quadratischeGleichungenClasses.QuadratischeGleichungenPageActivity;
import com.wipd.schulprojekt_mathe.statistikClasses.StatistikPageActivity;
//import com.wipd.schulprojekt_mathe.vektorenClasses.VektorenPageActivty;


public class StartPageActivity extends AppCompatActivity {

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_start_page, menu);
        return true;
    }

    //public void openSettingsActivity(MenuItem item) {
    //   Intent intent = new Intent(this, SettingsActivity.class);
    //   startActivity(intent);
    //}

    public void openAboutActivity(MenuItem item) {
        Toast.makeText(this, "https://github.com/OmarElHussein/MathProjektV_2.0.git", Toast.LENGTH_LONG).show();
    }

    public void openStatistikPageActivity(View view) {
        Intent intent = new Intent(this, StatistikPageActivity.class);
        startActivity(intent);
    }

    public void openProzessePageActivity(View view) {
        Intent intent = new Intent(this, ProzessePageActivity.class);
        startActivity(intent);
    }

    public void openQuadratischeGleichungenPageActivity(View view) {
        Intent intent = new Intent(this, QuadratischeGleichungenPageActivity.class);
        startActivity(intent);
    }


    // public void openVektorenPageActivity(View view){
    //     Intent intent = new Intent(this, VektorenPageActivty.class);
    //    startActivity(intent);
    //}

    public void openMatirxPageActivty(View view) {
        Intent intent = new Intent(this, MatrixPageActivity.class);
        startActivity(intent);
    }


}
