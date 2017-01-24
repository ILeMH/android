package fr.ealen.legorafi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oc.rss.fake.FakeNewsList;

public class MainActivity extends AppCompatActivity implements RSSAdapter.URLLoader{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.listFragment, new MainFragment())
                .commit();
    }

    @Override
    public void load(String title, String link) {
        if(findViewById(R.id.articleFragment) != null) {
            ArticleFragment fragment = ArticleFragment.create(link);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.articleFragment, fragment)
                    .addToBackStack(null)
                    .commit();
        }else {
            Intent intent = new Intent(this, ArticleActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("link", link);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}