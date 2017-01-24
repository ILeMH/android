package fr.ealen.legorafi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Alexandre on 23/01/2017.
 */

public class ArticleActivity extends AppCompatActivity {
    @SupressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setTitle(getIntent().getStringExtra("title"));

        ArticleFragment fragment = ArticleFragment.create(getIntent().getStringExtra("link"));

        getFragmentManager().beginTransaction()
                .add(android.R.id.content, fragment)
                .commit();
    }

    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.article_menu);
        return true;
    }

    public boolean onOptionSelectedItem(MenuItem item){

    }
}
