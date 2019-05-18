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

    /**
     * erstellt die Verbindung ziwschen layout und code, und die Seite selbst
     * ist auch verantwortlich was passiert wird beim Starten der App oder der Seite
     * passiert einmalig
     *
     * @param savedInstanceState um änderungen speichern zu können
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /**
     * Erstellt ein Menu im Toolbar (by Default in ActionBar)
     *
     * @param menu ruft menu um was ändern zu können
     * @return true wenn gecklickt wird
     */
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

    /**
     * Ein action wenn auf About Item gecklickt wird
     *
     * @param item diesesmal wird kein View aufgerufen weil das ein MenuItem ist
     *             und das automatisch erstellt wird mit der eingabe von item
     *             das item wird in der XML Datei schon übergeben (Automatisch)
     */
    public void openAboutActivity(MenuItem item) {
        openDialog();
    }

    /**
     * Erstellt ein Dialog von der Klasse DialogClass
     */
    private void openDialog() {
        DialogClass dialogClass = new DialogClass();
        dialogClass.customDialog("About", "https://github.com/OmarElHussein/MathProjektV_2.0.git\n\nProgrammed by:\nOmar\nMohamed\nVanessa", "OK", this);
    }

    /**
     * Öffnet eine Page auf ButtonClick
     * @param view nötig um die Methode zu Zeigen und funkionieren zulassen
     */
    public void openStatistikPageActivity(View view) {
        Intent intent = new Intent(this, StatistikPageActivity.class);
        startActivity(intent);
    }

    /**
     * Öffnet eine Page auf ButtonClick
     * @param view nötig um die Methode zu Zeigen und funkionieren zulassen
     */
    public void openProzessePageActivity(View view) {
        Intent intent = new Intent(this, ProzessePageActivity.class);
        startActivity(intent);
    }

    /**
     * Öffnet eine Page auf ButtonClick
     * @param view nötig um die Methode zu Zeigen und funkionieren zulassen
     */
    public void openQuadratischeGleichungenPageActivity(View view) {
        Intent intent = new Intent(this, QuadratischeGleichungenPageActivity.class);
        startActivity(intent);
    }

    /**
     * Öffnet eine Page auf ButtonClick
     * @param view nötig um die Methode zu Zeigen und funkionieren zulassen
     */
    // public void openVektorenPageActivity(View view){
    //     Intent intent = new Intent(this, VektorenPageActivty.class);
    //    startActivity(intent);
    //}

    /**
     * Öffnet eine Page auf ButtonClick
     * @param view nötig um die Methode zu Zeigen und funkionieren zulassen
     */
    public void openMatirxPageActivty(View view) {
        Intent intent = new Intent(this, MatrixPageActivity.class);
        startActivity(intent);
    }


}
