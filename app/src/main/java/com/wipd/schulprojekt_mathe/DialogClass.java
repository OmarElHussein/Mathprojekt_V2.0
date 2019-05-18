package com.wipd.schulprojekt_mathe;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

public class DialogClass extends AppCompatDialogFragment {

    /**
     * Erstellt ein Custom Dialog mit eigenschaften die wir brauchen
     * Custom Dialog ist ein kleines Fenster mit Informationen
     * @param title     erstellt ein Title für das kleine Fenster
     * @param message   erstellt das Inhalt von dem Fenster
     * @param btnTitle  erstellt den Title von dem Button der angezeigt wird
     * @param context   is "this" und das ist nötig um zu spezialisieren wozu es gehört
     */
    public void customDialog(String title, String message, String btnTitle, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(btnTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
