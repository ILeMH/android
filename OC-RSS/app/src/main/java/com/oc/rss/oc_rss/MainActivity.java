package com.oc.rss.oc_rss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Test des lists");

        final RecyclerView rv = (RecyclerView) findViewById(R.id.list);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter());

        /*setContentView(R.layout.bouton);
        Button myButton =  (Button) findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("oc_rss", "OK");

                Intent myIntent = new Intent(MainActivity.this, NextActivity.class);
                startActivity(myIntent);
            }
        });*/
        setTitle("Abonnement RSS");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionItemSelected(MenuItem item)
    {
        switch(item.getItemId()){
            case R.id.action_edit:
                return true;
            case R.id.action_add:
                return true;
            case R.id.action_delete:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
