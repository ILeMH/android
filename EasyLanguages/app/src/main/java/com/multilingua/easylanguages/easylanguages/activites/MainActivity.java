package com.multilingua.easylanguages.easylanguages.activites;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.app.ShareCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.RealmController;
import com.multilingua.easylanguages.easylanguages.realms.Users;

import java.net.URISyntaxException;
import java.util.Calendar;

import io.realm.Realm;

public class MainActivity extends GlobalForMenu {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        settings = getPreferences(0);
        utilisateur = settings.getString("client", " ");

        try {
            if(utilisateur != " "){
                client = rc.getUserByName(utilisateur);
                Intent reconnect = new Intent(this, Connecte.class);
                Log.d("MESSAGE", utilisateur);
                startActivity(reconnect);
                finish();
            }
        }catch (Exception e) {
            Log.d("Erreur", e.toString());
        }

            final Connection con = new Connection();
            View v = findViewById(R.id.activity_main);

            setContentView(R.layout.activity_main);
            super.onCreate(savedInstanceState);
            Button connexion = (Button) findViewById(R.id.connexion);
            Button inscription = (Button) findViewById(R.id.inscription);


            // ******************************************************************//
            connexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    con.show(getFragmentManager(), "Connexion");
                }
            });
            // ****************************************************************** //
            inscription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInscription = new Intent(MainActivity.this, Inscription.class);
                    //goInscription.putExtra("rc", rc);
                    startActivity(goInscription);
                }
            });
            // ********************************************************************** //
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Contact:
                Intent goContact = ShareCompat.IntentBuilder.from(this)
                        .setType("text/html")
                        .setSubject("Rendez-vous")
                        .getIntent();
                if(goContact.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(goContact);
                }
                return true;
            case R.id.home:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
