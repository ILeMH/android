package com.multilingua.easylanguages.easylanguages.activites;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;

import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.RealmController;
import com.multilingua.easylanguages.easylanguages.realms.Users;

import io.realm.Realm;

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
                                GlobalForMenu.utilisateur=identifiantEcrit;
                                getActivity().finish();
                            }
                            else
                            {
                                /*Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                                        R.string.email_archived, Snackbar.LENGTH_SHORT);
                                mySnackbar.setAction(R.string.undo_string, new MyUndoListener());
                                mySnackbar.show();*/

                            }
                        }catch(Exception e)
                        {
                            Log.e("erreur", e.toString());
                            Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.activity_main), "Erreur de connexion", Snackbar.LENGTH_LONG);
                            /*snack.setAction("Réessayer", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.i("Erreur", "Mauvais identifiants");
                                    Button connexion = (Button) findViewById(R.id.connexion);
                                }
                            });*/
                            snack.show();
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