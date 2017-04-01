package com.multilingua.easylanguages.easylanguages.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;

import com.multilingua.easylanguages.easylanguages.adapters.AlerteAdapter;
import com.multilingua.easylanguages.easylanguages.element.Alerte;
import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.RealmController;
import com.multilingua.easylanguages.easylanguages.realms.Users;

import io.realm.RealmList;

/**
 * Created by Alexandre on 15/03/2017.
 */

public class Alertes extends GlobalForMenu {

    RealmList<Alerte> listeDesAlertes;
    RecyclerView rv;
    AlerteAdapter adapter;

    public void onCreate(Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);

        final RealmController rc = new RealmController(getApplication());
        Users client = rc.getUserByName(GlobalForMenu.utilisateur);

        this.listeDesAlertes = client.getAlertes();
        adapter = new AlerteAdapter(listeDesAlertes);
        Intent i = getIntent();
        String[] listeDeCours = i.getStringArrayExtra("liste");
        setContentView(R.layout.alertes);

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DateFragment.class);
                startActivityForResult(i,1);
            }
        });

        this.rv = (RecyclerView) findViewById(R.id.alerteListe);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(adapter);
        //rv.setAdapter(new AlerteAdapter(listeDesAlertes));
        setTitle("Mes alertes");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                reloadData();
            }
            if(resultCode == Activity.RESULT_CANCELED)
            {
                super.onRestart();
            }
        }
    }

    private void reloadData() {

        adapter.notifyDataSetChanged();
        this.rv = (RecyclerView) findViewById(R.id.alerteListe);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(adapter);
        //rv.setAdapter(new AlerteAdapter(listeDesAlertes));
    }
}
