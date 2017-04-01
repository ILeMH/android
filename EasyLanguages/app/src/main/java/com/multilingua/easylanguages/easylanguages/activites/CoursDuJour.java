package com.multilingua.easylanguages.easylanguages.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.RealmController;
import com.multilingua.easylanguages.easylanguages.realms.Users;

import io.realm.Realm;

/**
 * Created by Alexandre on 25/03/2017.
 */

public class CoursDuJour extends GlobalForMenu {

    public void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);

        setContentView(R.layout.cours);

        final RealmController rc = new RealmController(getApplication());
        Realm realm = Realm.getDefaultInstance();
        Users client = rc.getUserByName(GlobalForMenu.utilisateur);

        Intent i = getIntent();
        int cours = i.getIntExtra("numero",1);
        String[] listeDeCours = i.getStringArrayExtra("Liste");
        String titre = listeDeCours[cours];

        TextView titreDuCours = (TextView) findViewById(R.id.titreCours);
        titreDuCours.setText(titre);
        String url = "file:///android_asset/cours/"+titre+".html";
        Log.d("URL DU COURS", url);

    }
}
