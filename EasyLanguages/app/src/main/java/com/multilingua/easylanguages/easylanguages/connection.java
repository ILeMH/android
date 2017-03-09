package com.multilingua.easylanguages.easylanguages;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;

import static com.multilingua.easylanguages.easylanguages.R.id.connexion;

/**
 * Created by Alexandre on 26/01/2017.
 */

public class Connection extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final RealmController rc = new RealmController(getActivity().getApplication());
        Realm realm = Realm.getDefaultInstance();
        //Realm realm = rc.getRealm();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.connection)
                .setPositiveButton(R.string.confirmation_connection, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog f = (Dialog) dialog;
                        android.support.design.widget.TextInputEditText u = (android.support.design.widget.TextInputEditText) f.findViewById(R.id.username);
                        android.support.design.widget.TextInputEditText p = (android.support.design.widget.TextInputEditText) f.findViewById(R.id.password);
                        try
                        {
                            String identifiantEcrit = u.getText().toString();
                            Users client = rc.getUserByName(identifiantEcrit);
                            Log.i("IDEcrit", identifiantEcrit);
                            Log.i("MDP", client.getMdp());
                            if(client.getMdp().equals(p.getText().toString()))
                            {
                                Intent goConnecte = new Intent(getContext(), Connecte.class);
                                startActivity(goConnecte);
                                GlobalForMenu.isConnected = true;
                                getActivity().finish();
                            }
                            else
                            {
                                Log.i("FAIL", "ECHEC DE CONNECTION");
                            }
                        }catch(Exception e)
                        {
                            Log.e("erreur", e.toString());
                        }
                    }
                })
                .setNegativeButton(R.string.annuler_connection, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Annulé", "la connexion failed");
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_signin, null));
        Dialog dial = builder.create();


        return dial;
    }

}