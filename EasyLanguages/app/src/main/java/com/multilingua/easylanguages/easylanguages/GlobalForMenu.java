package com.multilingua.easylanguages.easylanguages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;

/**
 * Created by Alexandre on 03/03/2017.
 */

public class GlobalForMenu extends AppCompatActivity{

    public static boolean isConnected;
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
                        .getIntent();
                if(goContact.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(goContact);
                }
                return true;
            case R.id.home:
                Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHome);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isConnected(){
        return this.isConnected;
    }
}
