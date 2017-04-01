package com.multilingua.easylanguages.easylanguages.activites;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.RealmController;

import io.realm.Realm;

public class MainActivity extends GlobalForMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Connection con = new Connection();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button connexion = (Button) findViewById(R.id.connexion);
        Button inscription = (Button) findViewById(R.id.inscription);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();

        final RealmController rc = new RealmController(this.getApplication());

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
    protected void onPause(){
        super.onPause();
        if(isConnected){
            //
        }
    }

    /*protected void onStart(){
        super.onStart();
        if(isConnected){
            Intent goConnect = new Intent(this, Connecte.class);
            startActivity(goConnect);
        }
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
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
    }
}
