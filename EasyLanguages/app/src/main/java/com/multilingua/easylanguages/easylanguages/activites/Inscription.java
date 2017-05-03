package com.multilingua.easylanguages.easylanguages.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.multilingua.easylanguages.easylanguages.element.Alerte;
import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.RealmController;
import com.multilingua.easylanguages.easylanguages.realms.Users;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Alexandre on 10/02/2017.
 */

public class Inscription extends GlobalForMenu {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        Button connexion = (Button) findViewById(R.id.buttonInscription);
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.design.widget.TextInputEditText mdp = (android.support.design.widget.TextInputEditText) findViewById(R.id.mdpInscription);
                android.support.design.widget.TextInputEditText mdpVerification = (android.support.design.widget.TextInputEditText) findViewById(R.id.verificationMdpInscription);
                android.support.design.widget.TextInputEditText prenom = (android.support.design.widget.TextInputEditText) findViewById(R.id.prenomInscription);
                android.support.design.widget.TextInputEditText nom = (android.support.design.widget.TextInputEditText) findViewById(R.id.nomInscription);
                android.support.design.widget.TextInputEditText pseudo = (android.support.design.widget.TextInputEditText) findViewById(R.id.pseudoInscription);
                RadioGroup sexe = (RadioGroup) findViewById(R.id.sexeGroupInscription);

                if(prenom.getText().toString().length() <2)
                {

                    prenom.setError("Votre prénom et votre nom doivent posséder au moins 2 caractères");
                }
                else
                {
                    if(nom.getText().toString().length() < 2)
                    {
                        nom.setError("Votre nom doit posséder au moins 2 caractères");
                    }
                    else
                    {
                        if(pseudo.getText().length()<4)
                        {
                            pseudo.setError("Votre pseudo doit posséder au moins 4 caractères");
                        }
                        else
                        {
                            if(mdp.getText().length() < 4)
                            {
                                mdp.setError("Votre mot de passe doit posséder au moins 4 caractères");
                            }
                            else if(!mdpVerification.getText().toString().equals(mdp.getText().toString()))
                            {
                                mdpVerification.setError("Vous avez entré deux mots de passes différents");
                            }
                            else {
                                RealmController controler = new RealmController(getApplication());
                                Realm realm = Realm.getDefaultInstance();

                                Calendar calendar = Calendar.getInstance();
                                Date dateInscription = calendar.getTime();

                                Users client = new Users();
                                client.setUtilisateur(pseudo.getText().toString());
                                client.setMdp(mdp.getText().toString());
                                client.setId(controler.getUsersNumber()+1);
                                client.setCours(0);
                                client.setDate(dateInscription);
                                RealmList<Alerte> rlList = new RealmList<>();

                                client.setAlertes(rlList);
                                realm.beginTransaction();
                                realm.copyToRealm(client);
                                realm.commitTransaction();

                                Intent goHome = new Intent(Inscription.this, MainActivity.class);
                                startActivity(goHome);
                                finish();
                            }
                        }
                    }
                }
            }
        });
    }
}
