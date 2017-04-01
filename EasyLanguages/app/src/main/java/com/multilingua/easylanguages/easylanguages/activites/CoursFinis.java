package com.multilingua.easylanguages.easylanguages.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.multilingua.easylanguages.easylanguages.adapters.CoursAdapter;
import com.multilingua.easylanguages.easylanguages.R;

/**
 * Created by Alexandre on 01/03/2017.
 */

public class CoursFinis extends GlobalForMenu {

    public void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.liste_cours);

        Intent i = getIntent();
        String[] liste = i.getStringArrayExtra("Cours");

        for(int j = 0; j < liste.length ; j++)
        {
            String[] separated = liste[j].split("\\.");
            liste[j] = separated[0];
        }

        final RecyclerView rv = (RecyclerView) findViewById(R.id.listeDeCours);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(new CoursAdapter(liste));
        setTitle("Liste de cours");
    }
}
