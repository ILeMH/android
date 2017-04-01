package com.multilingua.easylanguages.easylanguages.activites;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.multilingua.easylanguages.easylanguages.R;

/**
 * Created by Alexandre on 12/03/2017.
 */

public class ConformationDeconnexion extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.connection)
                .setPositiveButton(R.string.confirmation_connection, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent deconnexion = new Intent(getActivity(), MainActivity.class);
                        startActivity(deconnexion);
                        getActivity().finish();
                    }
                })
                .setNegativeButton(R.string.annuler_connection, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Annulé", "la connexion failed");
                    }
                });

        Dialog dial = builder.create();


        return dial;
    }
}
