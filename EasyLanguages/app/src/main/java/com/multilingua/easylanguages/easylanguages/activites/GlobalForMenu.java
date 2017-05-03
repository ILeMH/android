package com.multilingua.easylanguages.easylanguages.activites;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.RealmController;
import com.multilingua.easylanguages.easylanguages.realms.Users;

import java.io.IOException;
import java.util.Calendar;

import io.realm.Realm;

import static com.multilingua.easylanguages.easylanguages.activites.MainActivity.settings;

/**
 * Created by Alexandre on 03/03/2017.
 */

public class GlobalForMenu extends AppCompatActivity{

    public static String utilisateur;
    public static Users client;
    public static RealmController rc;
    static SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.rc = new RealmController(this.getApplication());
        Realm.init(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public String[] tableauSpinner(int cours, String[] liste){
        String[] coursDispoFinal = new String[cours];
        for(int j = 0; j < cours; j++)
        {
            String[] separated = liste[j].split("\\.");
            coursDispoFinal[j] = separated[0];
        }
        return coursDispoFinal;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Contact:
                Intent goContact = ShareCompat.IntentBuilder.from(this)
                        .setType("text/html")
                        .addEmailTo("schanne.alexandre@gmail.com")
                        .setSubject("Contact")
                        .getIntent();
                if(goContact.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(goContact);
                }
                return true;
            case R.id.home:
                ConformationDeconnexion deco = new ConformationDeconnexion();
                deco.show(getFragmentManager(), getString(R.string.deconnection));
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("client", null);
                editor.commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String[] getCours(String path){
        String[] listeFinale;
        Resources res = getResources();
        AssetManager asset = res.getAssets();
        try {
            String[] list = asset.list(path);
            for ( int i = 0; i < list.length ; i++)
            {

            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void onStop(){
        settings = getPreferences(0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("client", utilisateur);
        editor.commit();
        super.onStop();
    }
}
