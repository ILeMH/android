package com.multilingua.easylanguages.easylanguages.activites;

import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.RealmController;
import com.multilingua.easylanguages.easylanguages.realms.Users;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

import static com.multilingua.easylanguages.easylanguages.activites.MainActivity.settings;

/**
 * Created by Alexandre on 11/02/2017.
 */

public class Connecte extends GlobalForMenu {

    private String[] listeDeCours;
    Users client;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final RealmController rc = new RealmController(getApplication());

        setContentView(R.layout.connecte);

        client = rc.getUserByName(GlobalForMenu.utilisateur);

        listeDeCours = getCours("cours");
        differenceDate(client, rc, listeDeCours);

        final String [] listeDeCoursDisponible = tableauSpinner(rc.getUserByName(GlobalForMenu.utilisateur).getCours(), listeDeCours);
        final Activity act = this;
        Button coursValides = (Button) findViewById(R.id.coursValides);
        Button contacterMentor = (Button) findViewById(R.id.contacterMentor);
        Button alertes = (Button) findViewById(R.id.mesAlertes);
        final Button coursDuJours = (Button) findViewById(R.id.cours);
        coursValides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCoursFinis = new Intent(getApplicationContext(), CoursFinis.class);
                goCoursFinis.putExtra("Cours", listeDeCoursDisponible);
                startActivity(goCoursFinis);
            }
        });

        contacterMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goContact = ShareCompat.IntentBuilder.from(act)
                        .setType("text/html")
                        .addEmailTo("schanne.alexandre@gmail.com")
                        .setSubject("Rendez-vous")
                        .getIntent();
                if(goContact.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(goContact);
                }
            }
        });

        alertes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAlertes = new Intent(getApplicationContext(), Alertes.class);
                goAlertes.putExtra("Liste", listeDeCours);
                startActivity(goAlertes);
            }
        });
        coursDuJours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cours = client.getCours();
                final RealmController rc = new RealmController(getApplication());
                Realm realm = Realm.getDefaultInstance();
                Users client = rc.getUserByName(GlobalForMenu.utilisateur);
                String URL = listeDeCours[cours];

                String url = "file:///android_asset/cours/"+URL;
                Intent goCours = new Intent(getApplicationContext(), CoursDetail.class);
                goCours.putExtra("vue", url);
                startActivity(goCours);
            }
        });
    }

    private void differenceDate(Users client, RealmController rc, String[] listeDeCours) {

        Date dateClient = null;
        try{
            dateClient = client.getDate();
        }catch(Exception e){
            Long d = System.currentTimeMillis() -500;
            dateClient = new Date(d);
        }
        Calendar calendarDernierCours = Calendar.getInstance();
        calendarDernierCours.setTime(dateClient);
        Date ajd = new Date();


        long systemDate = SystemClock.currentThreadTimeMillis();
        String dateSystem = String.valueOf(systemDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ajd);
        Date dateDuCours = calendar.getTime();

        int jour = calendar.get(Calendar.DAY_OF_MONTH);
        int annee = calendar.get(Calendar.YEAR);
        int mois = calendar.get(Calendar.MONTH);

        int jourClient = calendarDernierCours.get(Calendar.DAY_OF_MONTH);
        int moisClient = calendarDernierCours.get(Calendar.MONTH);
        int anneeClient = calendarDernierCours.get(Calendar.YEAR);

        Log.d("current_MONTH", String.valueOf(mois));
        Log.d("current_DAY", String.valueOf(jour));
        Log.d("current_YEAR", String.valueOf(annee));

        Log.d("jour_cours", String.valueOf(jourClient));
        Log.d("month_cours", String.valueOf(moisClient));
        Log.d("year_cours", String.valueOf(anneeClient));

            if(mois == moisClient && jour > jourClient
                    ||
                    mois > moisClient
                    ||
                    annee > anneeClient)
            {
                int num = client.getCours();
                if(!(num == listeDeCours.length-1))
                {
                    num++;
                    Realm realm = rc.getRealm();
                    realm.beginTransaction();
                    client.setCours(num);
                    client.setDate(ajd);
                    realm.copyToRealmOrUpdate(client);
                    realm.commitTransaction();
                }
            }
    }
}
