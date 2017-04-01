package com.multilingua.easylanguages.easylanguages.activites;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.multilingua.easylanguages.easylanguages.R;

import java.io.IOException;

/**
 * Created by Alexandre on 03/03/2017.
 */

public class GlobalForMenu extends AppCompatActivity{

    public static boolean isConnected;
    public static String utilisateur;
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public String[] tableauSpinner(int cours, String[] liste){
        String[] coursDispoFinal = new String[cours];
        for(int j = 0; j < cours; j++)
        {
            coursDispoFinal[j] = liste[j];
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
                deco.show(getFragmentManager(), "Confirmation");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isConnected(){
        return this.isConnected;
    }

    public void setIsConnected(boolean bool){
        this.isConnected = bool;
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
}
