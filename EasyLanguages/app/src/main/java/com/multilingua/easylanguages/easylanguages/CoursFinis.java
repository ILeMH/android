package com.multilingua.easylanguages.easylanguages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        final RecyclerView rv = (RecyclerView) findViewById(R.id.listeDeCours);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(new CoursAdapter(liste));
        setTitle("Liste de cours");

    }
}
