package com.wipd.schulprojekt_mathe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CloseKeyboardClass extends AppCompatActivity {

    /**
     * Diese Methode schließt die Tastatur, wird meistens benutzt nachdem man auf einem
     * Button clickt und die Tastatur danach schließen sollte
     */
    public void tastaturSchliessen(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
