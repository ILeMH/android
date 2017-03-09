package com.multilingua.easylanguages.easylanguages;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Alexandre on 11/02/2017.
 */

public class Connecte extends GlobalForMenu {

    private String[] listeDeCours;

    public void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.connecte);

        listeDeCours = getCours("cours");

        Button coursValides = (Button) findViewById(R.id.coursValides);
        Button contacterMentor = (Button) findViewById(R.id.contacterMentor);
        coursValides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCoursFinis = new Intent(getApplicationContext(), CoursFinis.class);
                goCoursFinis.putExtra("Cours", listeDeCours);
                startActivity(goCoursFinis);
            }
        });
        final Activity act = this;
        contacterMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goContact = ShareCompat.IntentBuilder.from(act)
                        .setType("text/html")
                        .setSubject("Rendez-vous")
                        .getIntent();
                if(goContact.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(goContact);
                }
            }
        });
        Realm realm = Realm.getDefaultInstance();

    }

    public String[] getCours(String path){
        Resources res = getResources();
        AssetManager asset = res.getAssets();
        try {
            String[] list = asset.list(path);
            for ( int i = 0; i < list.length ; i++)
            {
                Log.d("",list[i]);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
